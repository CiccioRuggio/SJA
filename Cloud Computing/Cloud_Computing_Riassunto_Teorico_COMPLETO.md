# Cloud Computing — Riassunto Teorico

---

## 1. Modelli di servizio: IaaS, PaaS, SaaS

Il cloud non è un servizio unico ma una scala di compromessi tra controllo e comodità. Lo stack di responsabilità completo è:

`Hardware → Virtualizzazione → OS → Middleware → Runtime → Dati → Applicazione → Utente`

Più sali verso il SaaS, più pezzi dello stack li gestisce il provider e meno ne gestisci tu. Meno lavoro, meno libertà.

**IaaS (Infrastructure as a Service)** — Il provider ti dà infrastruttura virtuale (server, storage, rete, firewall, load balancer) on-demand via API o console. È la forma più "tecnica": configuri l'ambiente come se avessi un data center, ma senza comprare hardware.
- Esempi: AWS EC2, EBS, VPC / Azure VM / GCP Compute Engine.
- Vantaggi: massimo controllo, scali in secondi, paghi solo ciò che usi, ideale per migrare sistemi legacy.
- Svantaggi: patching/sicurezza/scaling a carico tuo, rischio costi se lasci le VM accese, servono ancora i sysadmin.
- `[GANCIO P2]`: tutta la parte 2 vive qui dentro. **EC2 è IaaS.**

**PaaS (Platform as a Service)** — Il provider ti dà una piattaforma pronta: carichi il codice e lui pensa a compilazione, deploy, log, scalabilità. Non tocchi OS né server.
- Esempi: AWS Elastic Beanstalk, Google App Engine, Heroku, Firebase.
- Vantaggi: deploy rapido, scaling automatico, ottimo per DevOps/CI-CD.
- Svantaggi: poca libertà di configurazione, rischio di lock-in alto.

**SaaS (Software as a Service)** — Applicazione completa via Internet, da browser o API. Non gestisci nulla.
- Esempi: Google Workspace, Microsoft 365, Salesforce, Slack, Dropbox, Figma.
- Vantaggi: zero installazione/manutenzione, aggiornamenti automatici, accesso ovunque.
- Svantaggi: personalizzazione limitata, lock-in, il provider ha responsabilità diretta sui tuoi dati (privacy/GDPR in UE).

**Analogia pizza:** cucini tutto in casa = On-premises; ti portano gli ingredienti = IaaS; pizza da asporto = PaaS; vai al ristorante = SaaS.

### Il modello della responsabilità condivisa

Ogni modello sposta il confine tra ciò che gestisci tu e ciò che gestisce il provider. Il cloud non elimina la sicurezza, la ridistribuisce.

| Livello | On-premises | IaaS | PaaS | SaaS |
|---|---|---|---|---|
| Applicazione | Utente | Utente | Provider | Provider |
| Dati | Utente | Utente | Utente | Provider |
| Runtime | Utente | Utente | Provider | Provider |
| Middleware | Utente | Utente | Provider | Provider |
| Sistema Operativo | Utente | Utente | Provider | Provider |
| Virtualizzazione | Utente | Provider | Provider | Provider |
| Server/Storage/Rete | Utente | Provider | Provider | Provider |

Le due righe che contano di più: in **IaaS** aggiorni ancora tu l'OS e proteggi tu i dati. In **SaaS** non fai nulla ma i dati li gestisce il provider. Il confine "Dati" è l'ultimo a passare al provider (solo in SaaS) — è il punto più delicato.

---

## 2. Infrastruttura globale AWS

AWS è il più grande cloud provider pubblico, oltre 175 servizi, modello pay-per-use. Tre elementi fisici:

**Regioni Geografiche** — Posizione fisica dove AWS ospita un cluster di data center. Scegli la regione per: vicinanza agli utenti, requisiti legali (dati nel paese del cliente), sicurezza e disaster recovery. Esempi del lab: `eu-south-1` (Milano), `eu-west-1` (Irlanda).

**Availability Zones (AZ)** — Raggruppamento logico e fisico di data center dentro una regione. Separati tra loro ma entro ~100 km. Una regione ha minimo 2 AZ, spesso 3+. Servono a costruire soluzioni scalabili, ad alta disponibilità e fault-tolerant.
- `[GANCIO P2]`: l'Auto Scaling Group e il Load Balancer distribuiscono le istanze su più AZ proprio per questo.

**Edge Location** — Server per il caching di contenuti vicino agli utenti (CDN, es. CloudFront). Riducono la latenza di download dei file frequenti.

### Servizi Regionali vs Globali vs Locali
- **Regionali** (la maggioranza): scegli prima la regione, poi crei la risorsa. Es. RDS.
- **Globali**: accessibili ovunque senza scegliere regione. IAM, CloudFront, Route 53.
- **Locali** (on-premises): nei data center del cliente. Es. Snow Family, Storage Gateway.

**Caso ambiguo — S3:** presentato come servizio globale, MA i bucket sono creati in una regione specifica e sono region-specific. Il nome del bucket però è univoco a livello globale. Doppia natura = classica domanda-trabocchetto. [Likely]

---

## 3. IAM — Identity and Access Management

**Cos'è:** l'infrastruttura di controllo accessi di AWS. Servizio globale, serverless e gratuito.
- `[GANCIO P2]`: l'host vi crea utenti IAM e voi entrate nel suo account come utenti IAM, non come root. Tutta l'esercitazione gira dentro questa cornice.

### Le entità (gerarchia)
- **Utente Root** — Creato all'apertura dell'account. Privilegi completi e irrevocabili. Solo per il bootstrap iniziale, mai per il lavoro quotidiano.
- **Utenti IAM** — Identità per persone o applicazioni. Credenziali proprie: password (Console) e/o Access Key (CLI/SDK).
- **Gruppi IAM** — Contenitori logici di utenti. Regole ferree: un gruppo non ha credenziali; non si annidano; un utente può stare in più gruppi (cumula i permessi); nei gruppi si mettono solo utenti.
- **Policy** — Documenti JSON che definiscono chi può fare cosa su quali risorse.

### Least Privilege
Concedi solo i permessi strettamente necessari. Team di 10 → non condividi root, crei 10 utenti IAM in un gruppo con policy "sola lettura".

### Struttura Policy JSON
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "123Test",
      "Effect": "Allow",
      "Action": ["s3:GetObject", "s3:PutObject"],
      "Resource": "arn:aws:s3:::x-altri-x-venduti/*"
    }
  ]
}
```
- **Version**: release della sintassi (`2012-10-17`), non la data della tua policy.
- **Effect**: Allow o Deny.
- **Principal**: a chi si applica. Implicito nelle identity-based policy; serve nelle bucket policy.
- **Action**: convenzione `servizio:operazione`, es. `s3:GetObject` (lettura), `s3:PutObject` (scrittura).
- **Resource**: ARN del target, wildcard `*` ammesse.
- **Condition**: vincoli contestuali opzionali (orario, MFA, IP).

`AdministratorAccess` = `Action:"*"`, `Resource:"*"` → potere totale, come root ma senza usarne le credenziali.

### Regole di valutazione (domanda quasi certa) [Likely]
- **Implicit Deny**: tutto ciò che non è esplicitamente Allow è negato.
- **Deny esplicito vince sempre** su qualsiasi Allow, a qualunque livello.
- Senza Deny i permessi **si cumulano**: FullAccess + ReadOnlyAccess → vince il più ampio.

Scenario: utente con IAMFullAccess diretta + IAMReadOnlyAccess ereditata → può ancora creare/eliminare utenti. Ma su EC2 → **Access Denied** (nessun Allow esplicito = implicit deny). `[GANCIO P2]`: è il test IAM che probabilmente farete come gruppo.

**Limite di auto-modifica**: un utente non può togliersi una policy Full attaccata direttamente. Serve un'identità amministrativa terza.

### Policy vs Ruoli
- **Policy**: autorizzazioni granulari.
- **Ruolo**: set di autorizzazioni assumibile da un'identità/servizio. `[GANCIO P2]`: l'istanza EC2 assume un ruolo IAM per parlare con S3, invece di avere Access Key hardcoded.

### Sicurezza account: password policy + MFA
**MFA** — almeno 2 fattori distinti su 3 categorie: conoscenza (password), possesso (telefono/token), inerenza (biometria). Tipi: virtuali (Google Authenticator, TOTP RFC 6238, 6 cifre ogni 30s) e fisici (FIDO2/U2F). Priorità assoluta su root e admin.

### Le 3 vie di accesso ad AWS
- **Console** — GUI web, user+password (+MFA).
- **CLI** — riga di comando, via Access Key.
- **SDK** — librerie per linguaggi, machine-to-machine, via Access Key.

**Access Key** — due parti: **Access Key ID** (pubblico, inizia con `AKIA...`, dice chi sei) e **Secret Access Key** (privato, firma la richiesta con HMAC, dimostra che sei tu). Private, personali, mai nel codice sorgente.

### FinOps — perché IAM mal configurato costa
IAM è gratis, ma permessi eccessivi costano: provisioning incontrollato (EC2 sovradimensionate) → bolletta impazzita; Access Key esposte → cryptomining fraudolento. Mitigazione: Permissions Boundaries, limitare risorse a classi/regioni specifiche, monitorare permessi reali vs usati.

---

## 4. S3 — Simple Storage Service

**Cos'è:** object storage robusto, scalabile, durevole, economico.

### Concetti base
- Gli oggetti stanno in **Bucket**. Nome univoco globalmente, creato in una regione specifica.
- Ogni oggetto ha una **chiave** = path completo.
- **Non è un file system**: è object storage. Ogni file = oggetto con nome, chiave, metadati, permessi.
- Dimensione max oggetto: **5 TB**. Oltre 5 GB serve multipart upload.
- Durabilità **11 nove** (99,999999999%).

### Il concetto centrale
**Caricare un file su S3 ≠ renderlo pubblico.** S3 separa conservare un oggetto dal permettere l'accesso. In un bucket privato l'oggetto esiste e ha un Object URL, ma aprirlo → Access Denied. Comportamento corretto.

### Rendere pubblico — due passi separati
1. **Disattivare Block Public Access** (scheda Permissions). Da solo non rende pubblico nulla, toglie solo il blocco.
2. **Bucket Policy** che autorizza la lettura:
```json
{
  "Version": "2012-10-17",
  "Statement": [{
    "Sid": "<nome_sid>",
    "Effect": "Allow",
    "Principal": "*",
    "Action": "s3:GetObject",
    "Resource": "<arn_bucket>/foto-lab.jpg"
  }]
}
```
Differenza di sicurezza: `<arn>/foto-lab.jpg` = un solo oggetto pubblico; `<arn>/*` = tutti gli oggetti. Usa `/*` solo quando il bucket è davvero destinato a contenuto pubblico (es. sito statico).

### Static Website Hosting
S3 può ospitare siti statici (HTML/CSS/JS), **ma non esegue backend** (PHP, Java, Python, Node.js).
- `[GANCIO P2]`: questo limite è il motivo per cui esiste EC2. Serve backend dinamico → esci da S3 e vai su compute (EC2 + web server).

**Object URL vs Website endpoint** (domanda probabile): Object URL punta a un singolo oggetto; Website endpoint punta al bucket servito come sito, usando `index.html` come pagina iniziale. Per un sito, l'indirizzo giusto è il Website endpoint.

### Altre feature (teoria)
- **Encryption** server-side: SSE-S3 (chiavi gestite da S3, gratis, default sensato), SSE-KMS (controllo più fine, a pagamento), DSSE-KMS (doppio strato, il più costoso).
- **Versioning**: preserva versioni vecchie, protegge da cancellazioni accidentali. Richiesto per la Replication.
- **Replication**: CRR (cross-region), SRR (same-region). Origine e destinazione devono avere versioning attivo.
- **Storage Class**: durabilità sempre 11 nove, cambia la disponibilità/costo.

### Pulizia finale (domanda-billing) [Likely]
Ordine: svuota gli oggetti → rimuovi la bucket policy → disattiva static hosting → elimina il bucket. Ogni risorsa costa.

---


# Cloud Computing — Riassunto Teorico (Parte 2)

Aggancio dalla Parte 1: questa parte chiude tutti i `[GANCIO P2]` lasciati aperti. EC2 è l'incarnazione dello IaaS; le AZ multiple servono a LB e Auto Scaling; il ruolo IAM è come l'istanza parla con S3 senza chiavi hardcoded; il limite backend di S3 è il motivo per cui esiste EC2; l'Access Denied da implicit deny è il test IAM di gruppo; billing e pulizia valgono identici (anzi peggio) per un'istanza accesa.

**Nota critica sui web server (segnala questa incoerenza dei materiali):** i lab sul Load Balancer usano **Apache2**, il lab di hosting statico usa **Nginx**. Fanno la stessa cosa qui — ricevono richieste HTTP e servono file statici da `/var/www/html`. Sono alternative, non tecnologie diverse. Sai fare entrambi. Se ti chiedono "quale hai usato", la risposta onesta è "Nginx nell'esercitazione di hosting statico, Apache negli scenari con Load Balancer".

---

## 5. EC2 — Elastic Compute Cloud

**Cos'è:** il servizio di calcolo di AWS. Ti dà macchine virtuali (istanze) on-demand nel cloud. È l'incarnazione pura dello **IaaS**: gestisci OS, software, patch, sicurezza; AWS gestisce solo l'hypervisor e l'hardware fisico sotto. [Certain]

Un'istanza EC2 è a tutti gli effetti un server Linux (o Windows) remoto: ci installi software, apri porte, pubblichi contenuti. La differenza rispetto a un server tuo è che non l'hai comprato — lo accendi in secondi e lo paghi mentre è acceso.

### Anatomia di un'istanza (i pezzi da sapere)

- **AMI (Amazon Machine Image):** il template da cui nasce l'istanza. Contiene OS + software preinstallato. Es. "Ubuntu Server 24.04 LTS". Scegliere l'AMI = scegliere il punto di partenza. → sezione 11.
- **Instance type:** la taglia hardware (vCPU + RAM). Es. `t2.micro` / `t3.micro`, la classe economica del Free Tier. Le famiglie `t` sono **burstable**: accumulano crediti CPU quando sono ferme e li spendono nei picchi. [Certain]
- **Storage (EBS):** il disco dell'istanza è un volume **EBS (Elastic Block Store)** — storage a blocchi, non object storage come S3. EBS è IaaS, S3 è object storage: sono due cose diverse (domanda-trabocchetto probabile). [Certain]
- **Key pair (.pem):** la coppia di chiavi per entrare via SSH. La privata `.pem` la scarichi una volta sola e la custodisci. → sezione 7.
- **Security Group:** il firewall virtuale dell'istanza. → sezione 8.
- **User Data:** script eseguito **una sola volta al primo avvio** per automatizzare il provisioning (installare Apache, scrivere l'index, ecc.). → sezione 11.

### Modelli di prezzo (domanda probabile) [Likely]

- **On-Demand:** paghi al secondo/ora, zero impegno. Massima flessibilità, prezzo pieno.
- **Reserved / Savings Plans:** impegno 1-3 anni, sconto forte. Per carichi stabili e prevedibili.
- **Spot:** capacità inutilizzata a prezzo crollato (fino a -90%), ma AWS può spegnerti l'istanza con 2 minuti di preavviso. Per lavori interrompibili (batch, rendering).

Regola mentale: carico imprevedibile → On-Demand; carico costante → Reserved; carico interrompibile → Spot.

### Il collegamento con IAM (chiudo il gancio)

In produzione l'istanza EC2 **assume un ruolo IAM** per parlare con S3 (o altri servizi), invece di avere Access Key scritte nel codice. Il ruolo è un set di permessi temporanei che AWS inietta nell'istanza. È il modo pulito: se l'istanza viene compromessa, non c'è nessuna chiave permanente da rubare. **Access Key hardcoded nel codice = violazione grave.** [Certain]

---

## 6. Linux essenziale

Le tue istanze sono Ubuntu Server: senza terminale non fai niente. Questo è il minimo operativo.

### Il principio fondante: "tutto è un file"

In Linux non solo documenti e cartelle, ma anche **dispositivi hardware, processi e configurazioni** sono esposti come file. Interfaccia uniforme: stessi comandi (`cat`, `cp`, `dd`) su cose diverse. Questo dà flessibilità e rende tutto scriptabile. [Certain]

### Il filesystem è un albero, la radice è `/`

| Directory | Ruolo |
|---|---|
| `/` | radice, punto di partenza di tutto |
| `/home` | cartelle personali degli utenti |
| `/etc` | file di configurazione di sistema (rete, sicurezza, servizi) |
| `/bin`, `/usr/bin` | eseguibili di base (`ls`, `cat`, `bash`) |
| `/var` | file variabili: **log**, cache, spool. `/var/www/html` = document root web |
| `/dev` | hardware esposto come file speciali |

Il perché conta: separazione dati utente / sistema = più sicurezza, più facilità di backup e automazione.

### Navigazione e ispezione

```bash
pwd              # dove sono
ls -la /etc      # lista dettagliata + file nascosti
cd /home         # spostati
cd ..            # sali di un livello
cd ~             # torna alla home
head -n 3 file   # prime 3 righe
tail -n 5 file   # ultime 5 righe
less file        # scorri interattivamente (q per uscire)
cat file         # sputa tutto in una volta
```

### Creare e gestire

```bash
mkdir -p a/b/c              # crea annidato in un colpo (-p: crea intermedie, no errore se esiste)
touch file.txt             # crea file vuoto / aggiorna data
nano file.txt              # editor: Ctrl+O salva, Ctrl+X esce
cp -r dir1 dir2            # copia ricorsiva di cartelle
mv vecchio.txt nuovo.txt   # sposta E rinomina (stesso comando)
rmdir cartella             # rimuove SOLO se vuota
rm -r cartella             # rimuove ricorsivamente tutto
```

### Permessi — il blocco che devi padroneggiare

Ogni file ha **proprietario (user), gruppo (group), altri (others)** e per ciascuno tre permessi: **r**ead, **w**rite, e**x**ecute.

Lettura di `-rwxr-xr--`:
- `rwx` proprietario: legge, scrive, esegue
- `r-x` gruppo: legge, esegue
- `r--` altri: solo legge

**Notazione ottale** (r=4, w=2, x=1, si sommano per categoria):

| chmod | Proprietario | Gruppo | Altri | Uso tipico |
|---|---|---|---|---|
| `755` | rwx (7) | r-x (5) | r-x (5) | directory e script pubblici |
| `644` | rw- (6) | r-- (4) | r-- (4) | file statici (HTML/CSS) |
| `400` | r-- (4) | --- (0) | --- (0) | **la chiave .pem** |
| `600` | rw- (6) | --- (0) | --- (0) | file privati (solo tu) |

**Perché la .pem vuole `400`:** SSH **rifiuta** una chiave privata leggibile da altri utenti del sistema. Solo il proprietario può leggerla, nessun altro. È l'errore n.1 di chi non riesce a connettersi. [Certain]

**Perché le directory web vogliono `755` e non `644`:** su una directory il bit `x` significa "puoi attraversarla". Senza `x`, Nginx/Apache non riesce a entrare nella cartella per leggere l'index → errore 403 Forbidden. [Certain]

Notazione simbolica (alternativa):
```bash
chmod u+x script.sh     # aggiungi execute al proprietario (u)
chmod go-rw secret.txt  # togli read+write a gruppo (g) e altri (o)
```

Cambiare proprietà:
```bash
chown ubuntu:ubuntu file        # proprietario:gruppo
chown -R ubuntu:ubuntu /var/www/html/sito-scp   # -R ricorsivo
```
Questo è il motivo del `chown` nei lab: dai a `ubuntu` la proprietà delle cartelle dei siti così scrivi **senza `sudo` ogni volta**.

### Utenti e gruppi

```bash
sudo adduser mario              # crea utente interattivo (password + home)
sudo usermod -aG sudo mario     # aggiungi mario al gruppo sudo (-aG: aggiungi senza rimuovere)
sudo groupadd contabilita       # crea gruppo
groups mario                    # gruppi di mario
sudo deluser mario              # rimuovi utente
```

File coinvolti (domanda probabile): `/etc/passwd` (utenti + shell), `/etc/shadow` (password criptate), `/etc/group` (gruppi). [Certain]

---

## 7. SSH e SCP — accesso e trasferimento

**SSH (Secure Shell):** protocollo per accedere da terminale a un server remoto in modo cifrato. Su EC2 Linux l'accesso avviene con **coppia di chiavi**: pubblica sulla macchina, privata (`.pem`) sul tuo computer. [Certain]

Sequenza di connessione:
```bash
chmod 400 chiave.pem                          # prima cosa, sempre
ssh -i chiave.pem ubuntu@IP_PUBBLICO          # -i: identity file
```
- Utente di default: **`ubuntu`** su Ubuntu, **`ec2-user`** su Amazon Linux. [Certain]
- Alla prima connessione ti chiede di confermare il fingerprint → rispondi `yes`.

**SCP (Secure Copy):** copia file tra locale e remoto usando SSH come canale sicuro.
```bash
# locale → remoto
scp -i chiave.pem index.html ubuntu@IP:/tmp/index.html
# intera cartella (ricorsivo)
scp -i chiave.pem -r sito-scp/* ubuntu@IP:/var/www/html/sito-scp/
```
Nota pratica dai lab: spesso carichi in `/tmp` (dove `ubuntu` può scrivere) e poi con SSH fai `sudo mv /tmp/index.html /var/www/html/` (dove serve root). È un pattern per aggirare i permessi della document root. [Certain]

---

## 8. Security Groups — il firewall virtuale (sezione ad alta priorità)

**Cos'è:** firewall a livello di istanza. Definisce **quali connessioni in ingresso** sono permesse. È il concetto pratico più interrogato di tutta la Parte 2.

Proprietà da sapere a memoria [Certain]:
- **Solo regole di Allow.** Non esistono regole di Deny in un Security Group. Ciò che non è esplicitamente permesso è negato (implicit deny — stesso principio di IAM).
- **Stateful:** se permetti il traffico in ingresso, la risposta in uscita è automaticamente consentita. Non devi aprire la porta di ritorno.
- La **Source** di una regola può essere: un IP/CIDR (es. `0.0.0.0/0` = chiunque) **oppure l'ID di un altro Security Group.**

### Il pattern architetturale chiave (impara questo sopra tutto)

Negli scenari con Load Balancer configuri **due** Security Group con una relazione precisa:

- **ALB-SG** (firewall del Load Balancer): Inbound HTTP porta 80 da `0.0.0.0/0` → chiunque da Internet può raggiungere l'ALB.
- **WebServers-SG** (firewall delle istanze): Inbound HTTP porta 80 con **Source = ALB-SG** (non un IP, l'ID del gruppo).

**Cosa ottieni:** le istanze accettano traffico **solo** dal Load Balancer, mai direttamente da Internet. Se qualcuno prova a colpire l'IP pubblico di un'istanza, viene bloccato. Il Load Balancer diventa l'**unico punto d'ingresso**. Questa è segmentazione di rete, ed è esattamente la "sicurezza nel cloud" che ricade su di te (non su AWS). [Certain]

Nel lab manuale aggiungi anche una regola **SSH porta 22 con Source = My IP** sulle istanze, per poterci entrare a installare Apache. In produzione la 22 si apre **solo verso il tuo IP**, mai verso `0.0.0.0/0`. [Certain]

Riepilogo porte: **22 = SSH/SCP**, **80 = HTTP**, **443 = HTTPS**.

---

## 9. Git e GitHub

**Git ≠ GitHub.** Git è il sistema di versionamento locale (gira sulla tua macchina). GitHub è il servizio remoto che ospita repository Git. Confonderli è un errore da matita rossa. [Certain]

### Setup una tantum

```bash
git --version                                    # verifica installazione
git config --global user.name "Francesco Ruggeri"
git config --global user.email "tua@email.com"
git config --global core.editor "code --wait"
git config --list                                # verifica profilo
```
Nome + email finiscono in **ogni commit** come autore. Si impostano una volta e valgono per tutte le repo.

### Autenticazione con chiave SSH (raccomandato su password)

```bash
ssh-keygen -t ed25519 -C "tua@email.com"   # crea id_ed25519 (privata) e id_ed25519.pub (pubblica)
eval "$(ssh-agent -s)"                      # avvia l'agent
ssh-add ~/.ssh/id_ed25519                   # carica la chiave
cat ~/.ssh/id_ed25519.pub                   # copia questo su GitHub → Settings → SSH keys
ssh -T git@github.com                       # verifica: "Hi username! You've successfully authenticated."
```
La privata (`id_ed25519`) **non si condivide mai**. Solo la `.pub` va su GitHub. [Certain]

### Ciclo di lavoro quotidiano

```bash
git clone git@github.com:USER/REPO.git   # scarica repo esistente + configura il remote
git status                                # cosa è cambiato
git add .                                 # prepara i file (staging)
git commit -m "Descrizione"               # crea uno snapshot locale
git push                                  # invia a GitHub
git pull                                  # scarica le modifiche remote
```
Nuovo progetto da zero: `git init` → `git add .` → `git commit -m "Initial commit"` → `git remote add origin git@github.com:USER/REPO.git` → `git push -u origin main`.

**Collegamento diretto ai tuoi lab:** nel lab di hosting statico, il secondo sito arriva sull'istanza con `git clone` eseguito **direttamente sull'EC2** (non SCP dal locale). È la differenza tra i due metodi di deploy: SCP = "spingo dal mio computer", git clone = "l'istanza tira dal repository". Il secondo è il modello DevOps. [Certain]

---

## 10. Web server e hosting statico su EC2

Chiude il gancio "limite backend di S3". S3 serve solo file statici; quando serve **compute** o backend dinamico esci da S3 e vai su EC2. Ma attenzione: negli esercizi qui servi comunque **contenuto statico** — la differenza è che ora hai una macchina intera sotto, non un servizio gestito.

### Nginx (lab hosting statico) e Apache2 (lab Load Balancer)

Entrambi: ricevono richieste HTTP sulla porta 80 e servono file dalla **document root `/var/www/html`**. Installazione identica come logica:
```bash
sudo apt update
sudo apt install nginx -y     # oppure: sudo apt install apache2 -y
sudo systemctl status nginx   # verifica "active (running)"
curl http://localhost         # test locale
```

### Il principio del path = cartella

Con la config di default (senza toccare virtual host / server block), l'URL rispecchia la struttura delle cartelle:

```
/var/www/html/sito-scp/index.html  →  http://IP/sito-scp/
/var/www/html/sito-git/index.html  →  http://IP/sito-git/
```

Due siti sulla stessa macchina, stessa porta 80, distinti dal path. Semplice e didattico, ma con **un limite reale** (domanda probabile): tutti i siti condividono la stessa configurazione e lo stesso IP/dominio. In produzione, per siti separati veri, useresti **server block / virtual host** con domini o sottodomini dedicati. [Certain]

**File che NON tocchi** in questo approccio: `/etc/nginx/nginx.conf`, `/etc/nginx/sites-available/default`, `/etc/nginx/sites-enabled/default`. Usi solo la document root.

Errori tipici: **404** = cartella o `index.html` mancante; **403** = permessi sbagliati (torna alla sezione 6, serve `755` sulle directory). [Certain]

---

## 11. AMI, Launch Template, User Data — automatizzare l'istanza

Questi tre concetti trasformano il provisioning manuale in provisioning automatico. Sono il ponte verso l'Auto Scaling.

- **AMI (Amazon Machine Image):** l'immagine-template (OS + software) da cui nasce ogni istanza. Puoi usare quelle Quick Start (Ubuntu) o crearne di custom con software già dentro.
- **Launch Template:** la "ricetta" di lancio. Definisce AMI + instance type + Security Group + key pair + User Data. Serve all'Auto Scaling Group per sapere **come** creare ogni nuova istanza. [Certain]
- **User Data:** script Bash eseguito **una volta al primo boot**. È il modo di automatizzare l'installazione senza entrare a mano via SSH.

### Lo script User Data dei lab (capirlo, non memorizzarlo)

```bash
#!/bin/bash
apt-get update -y
apt-get install apache2 -y
systemctl start apache2
systemctl enable apache2
# recupera l'ID dell'istanza dai metadati (IMDSv2)
TOKEN=$(curl -s -X PUT "http://169.254.169.254/latest/api/token" \
  -H "X-aws-ec2-metadata-token-ttl-seconds: 21600")
INSTANCE_ID=$(curl -s -H "X-aws-ec2-metadata-token: $TOKEN" \
  http://169.254.169.254/latest/meta-data/instance-id)
echo "<h1>Risposta dal Server EC2: $INSTANCE_ID</h1>" > /var/www/html/index.html
```

**Cosa devi saper spiegare:**
- `169.254.169.254` è l'**endpoint dei metadati dell'istanza** (link-local, raggiungibile solo dall'interno dell'istanza stessa). [Certain]
- **IMDSv2** richiede prima un **token** (`PUT`) e poi lo usa in header per leggere i metadati (`GET`). È più sicuro di IMDSv1 (che leggeva i metadati senza token, esponendo a certi attacchi SSRF). [Certain]
- Lo scopo qui è didattico: ogni istanza scrive **il proprio ID** nella pagina, così quando aggiorni il browser e vedi l'ID cambiare, **stai vedendo il Load Balancer che ti manda su istanze diverse.**

---

## 12. Application Load Balancer (ALB)

**Cos'è:** il punto d'ingresso unico che distribuisce il traffico HTTP/HTTPS su più istanze. Opera al **livello 7 (Application Layer) del modello OSI** — cioè "capisce" HTTP, può instradare in base a URL e header. [Certain]

Contrasto da sapere (domanda quasi certa) [Likely]:

| | ALB | NLB (Network) |
|---|---|---|
| Livello OSI | 7 (Application) | 4 (Transport) |
| Protocolli | HTTP/HTTPS | TCP/UDP |
| Routing | per path, per host header | per porta, ultra-basso latency |
| Quando | app web | throughput estremo, protocolli non-HTTP |

### I tre componenti logici dell'ALB

1. **Listener:** processo che ascolta su protocollo+porta configurati (es. HTTP:80) e verifica le richieste in arrivo.
2. **Regole di Routing:** associate al listener, decidono dove mandare il traffico — **path-based** (in base al percorso URL) o **host-based** (in base all'host header).
3. **Target Group:** raggruppamento logico delle istanze di destinazione. Responsabile degli **Health Check**.

### Health Check (meccanismo centrale)

L'ALB invia richieste periodiche a un percorso (es. `/` o `/index.html`) di ogni istanza. Se un'istanza **fallisce N controlli consecutivi**, viene marcata **Unhealthy** e l'ALB **smette di mandarle traffico** — ma le altre continuano a servire. È così che si garantisce continuità del servizio senthza intervento umano. [Certain]

Funzionamento a livello di rete: l'ALB fa **terminazione della connessione** del client e ne apre una **nuova** verso il target scelto. Il client non parla mai direttamente con l'istanza. Algoritmo di default: **Round Robin** (turnazione ciclica). [Certain]

Metriche dai tuoi materiali: disponibilità nativa **99,99%**, scala automaticamente fino a milioni di richieste/secondo.

---

## 13. Auto Scaling Group (ASG)

**Cos'è:** gestisce automaticamente il **ciclo di vita e il numero** delle istanze EC2. Usa il **Launch Template** per sapere come crearle e mantiene il parco istanze allineato a tre parametri:

- **Minimum capacity:** il pavimento. L'ASG non scende mai sotto (nei lab: 2).
- **Desired capacity:** quante ne vuoi normalmente (nei lab: 2).
- **Maximum capacity:** il tetto per lo scaling-out (nei lab: 4).

[Certain]

### I due movimenti

- **Scaling-out** (carico su): l'ASG avvia nuove istanze e le **registra automaticamente nel Target Group** dell'ALB. Nuove istanze → subito nel pool bilanciato.
- **Scaling-in** (carico giù): l'ASG rimuove istanze. Ma prima applica il **Deregistration Delay** (o **Connection Draining**): mantiene attive le connessioni in corso il tempo di completare le richieste **in volo**, poi spegne. Nessun utente vede la connessione tagliata a metà. [Certain]

### Auto-riparazione

Se l'ALB marca un'istanza Unhealthy, l'ASG (con gli health check ELB attivi) **la sostituisce**: ne spegne una malata, ne avvia una sana dal Launch Template. Questa è la differenza vera tra il lab manuale e quello automatico: nel manuale un'istanza morta resta morta finché non intervieni tu. [Certain]

---

## 14. Architettura combinata + Multi-AZ (chiude il gancio AZ)

L'integrazione si concretizza quando **l'ASG viene associato al Target Group dell'ALB**. Da lì il flusso è automatico: ASG crea/distrugge istanze, ALB le scopre e ci bilancia sopra.

**Multi-AZ = eliminare il Single Point of Failure.** Distribuisci ALB e istanze su **due o più Availability Zone** geograficamente isolate. Se un'intera AZ va giù (blackout, incendio del datacenter), l'altra AZ continua a servire. Questo chiude il `[GANCIO P2]` della Parte 1: le AZ multiple non sono un dettaglio, sono **il motivo per cui esistono LB e ASG**. [Certain]

Nei lab: due subnet pubbliche in due AZ diverse (es. `eu-west-1a` e `eu-west-1b`), sia per l'ALB sia per l'ASG. **Devono essere le stesse subnet** per entrambi, altrimenti l'ALB non raggiunge le istanze.

---

## 15. Modello di Responsabilità Condivisa — configurazione ibrida

Questo scenario è **misto** e all'orale è oro, perché ti costringe a ragionare invece di ripetere la tabella.

**L'ALB è un servizio gestito (PaaS / Managed).** AWS ha la responsabilità "del" cloud: hardware dei nodi di bilanciamento, patch dell'OS sottostante, tolleranza ai guasti del routing, scaling interno dell'ALB, protezione DDoS a livello di rete. Tu non tocchi niente di tutto ciò. [Certain]

**Le istanze EC2 dell'ASG sono IaaS.** AWS gestisce solo hypervisor e virtualizzazione. Tutto il resto è tuo — sicurezza "nel" cloud:
- OS guest (Ubuntu): aggiornamenti `apt`, patch di sicurezza.
- Software applicativo (Apache/Nginx) e suo ciclo di vita.
- Custodia delle chiavi private `.pem`.
- **Security Group:** la segmentazione rigida (istanze accettano solo da ALB-SG, SSH solo dal tuo IP).
- Config del Launch Template, User Data, politiche di scaling, parametri di Health Check.

La frase da avere pronta: *"AWS è responsabile della sicurezza **del** cloud, io della sicurezza **nel** cloud. In questo scenario ibrido il confine cade dentro l'architettura: l'ALB è gestito, le istanze sotto sono mie."* [Certain]

---

## 16. I due lab a confronto (probabile domanda "che differenza c'è")

| | Lab EC2 statiche + ALB (manuale) | Lab ALB + ASG (automatico) |
|---|---|---|
| Creazione istanze | manuale, una per una | automatica dal Launch Template |
| Web server | Apache2 | Apache2 |
| Deploy contenuti | SSH + SCP dal locale | User Data (script al boot) |
| Registrazione nel Target Group | manuale dall'admin | automatica dall'ASG |
| Se un'istanza muore | resta morta | l'ASG la sostituisce |
| Numero istanze | fisso | elastico (min/desired/max) |
| Come verifichi il bilanciamento | due pagine HTML diverse ("SERVER UNO/DUE") | l'ID istanza cambia nella pagina |

Il lab manuale ti fa **vedere** il bilanciamento senza automatismi che confondono. Il lab con ASG ti mostra il sistema **reale, resiliente e auto-riparante**. Il primo è didattico, il secondo è produzione.

**Come si verifica il test in entrambi:** copi il **DNS name** dell'ALB (non l'IP di un'istanza), lo apri nel browser, premi **Ctrl+F5** ripetutamente. Se il contenuto **alterna** (pagina UNO/DUE, oppure ID istanza che cambia), l'ALB sta distribuendo il carico. Test superato. [Certain]

---

## 17. Ganci chiusi e pulizia risorse

**Tutti i `[GANCIO P2]` della Parte 1, chiusi:**
- IaaS → EC2 è la sua incarnazione (sez. 5).
- AZ multiple → servono a LB+ASG per eliminare il SPOF (sez. 14).
- Ruolo IAM → l'istanza lo assume per S3, niente chiavi hardcoded (sez. 5).
- Limite backend S3 → EC2 + web server è la risposta (sez. 10).
- Access Denied da implicit deny → stesso principio nei Security Group (sez. 8).
- Billing/pulizia → sotto.

**Pulizia (domanda-billing quasi garantita)** [Likely]: un'istanza accesa costa **più** di un bucket, e un ALB si paga a ora + a traffico. Ordine di smantellamento: elimina l'**Auto Scaling Group** (così smette di ricreare istanze) → elimina il **Load Balancer** → elimina il **Target Group** → termina le **istanze** rimaste → elimina **Launch Template** e **Security Group**. Se termini le istanze prima dell'ASG, lui te ne ricrea di nuove: è il suo lavoro. Controlla sempre **Billing & Cost**. [Certain]

---

### Le 5 domande orali più probabili (preparale a memoria)

1. **Perché le istanze non sono raggiungibili direttamente da Internet?** → Security Group delle istanze accetta la 80 solo da ALB-SG, non da `0.0.0.0/0`.
2. **A che livello OSI opera l'ALB e perché conta?** → Livello 7, capisce HTTP, può fare routing per path/host.
3. **Cosa succede se un'istanza si guasta?** → Health Check la marca Unhealthy, l'ALB smette di instradarci, l'ASG la sostituisce.
4. **Differenza tra Desired, Min e Max capacity?** → Desired = normale, Min = pavimento garantito, Max = tetto per lo scaling-out.
5. **Perché Multi-AZ?** → Elimina il Single Point of Failure: se cade un'intera AZ, l'altra continua.
