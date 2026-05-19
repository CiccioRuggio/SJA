import subprocess, requests, os, json
subprocess.run("clear", shell=True)

DATA_PATH = "data/"

class Creature:
    id = 0
    name = ""
    height = 0
    weight = 0
    types = []
    stats = {
        "hp": 0,
        "attack": 0,
        "defense": 0,
        "special-attack": 0,
        "special-defense": 0,
        "speed": 0
        }
    
    def __init__(self, pkmJson):
        SIMPLE_FIELDS = {'name', 'id', 'height', 'weight'}
        for k, v in pkmJson.items():
            if k in SIMPLE_FIELDS:
                setattr(self, k, v)
            elif k == 'types':
                self.types = [t['type']['name'] for t in v]
            elif k == 'stats':
                self.stats = {s['stat']['name']: s['base_stat'] for s in v}
        
class PokemonReader:
    POKEPATH = "https://pokeapi.co/api/v2/pokemon/"
    
    def __init__(self, pokeOrId):
        try:
            response = requests.get(self.POKEPATH+pokeOrId)
            if response.status_code == 404:
                raise requests.exceptions.ConnectionError()
            else:
                self.response = response.json()
                self.fixRaw()
        except Exception as e:
            print(f"ERROR! {e}\n{pokeOrId} NOT FOUND. Code: {response.status_code}")
            
    def fixRaw(self):
        keysToKeep = {'id', 'name', 'height', 'weight', 'types', 'stats', 'sprites'}
        self.fixedInfos = {k: self.response[k] for k in keysToKeep if k in self.response}
        if self.fixedInfos['sprites']['versions']['generation-v']['black-white']['animated']['front_default']:
            self.fixedInfos['sprites'] = self.fixedInfos['sprites']['versions']['generation-v']['black-white']['animated']['front_default']
        else:
            self.fixedInfos['sprites'] = self.fixedInfos['sprites']['front_default']
            
        
#         original_dict = {'a': 1, 'b': 2, 'c': 3, 'd': 4}
# keys_to_keep = {'a', 'c'}

# new_dict = {k: original_dict[k] for k in keys_to_keep if k in original_dict}
# # Output: {'a': 1, 'c': 3}

class JSONWriter:
    def writeJSONPkm(self,response):
        try:
            os.makedirs(os.path.dirname(f"{DATA_PATH}creatures/{response['name']}.json"), exist_ok=True)
            with open(f"{DATA_PATH}creatures/{response['name']}.json", "w") as f:
                json.dump(response, f)
            self.writeJSONPkdx()
        except Exception as e:
            print(f"ERROR! {e}")
    
    def writeJSONPkdx(self):
        pokeList = []
        try:
            for el in os.listdir(f"{DATA_PATH}creatures/"):
                with open(f'{DATA_PATH}creatures/{el}', 'r') as f:
                    dictio = {}
                    a=json.load(f)
                    
                keys=['id', 'name']
                for key in keys:
                    dictio[key] = a[key]
                    # print(dictio)
                    # print(json.load(f))
                    # print('========================')
                pokeList.append(dictio)
            with open(f'{DATA_PATH}pokedex.json', 'w') as f:
                json.dump(pokeList, f)
        except Exception as e:
            print(f"ERROR! {e}")


class JSONReader:
    def readJSONPkm(self,poke):
        try:
            with open(f"{DATA_PATH}creatures/{poke['name']}.json",'r')as f:
                return json.load(f)
        except:
            return f"ERROR! {poke} NOT found!"
        
    def readJSONPkdx(self):
        try:
            with open(f"{DATA_PATH}pokedex.json",'r')as f:
                return json.load(f)
        except Exception as e:
            return f"ERROR!  {e}"
    
    
    # def getJSON(self,pokeOrId):
    #     try:
    #         return requests.get(self.POKEPATH+pokeOrId)
    #     except:
    #         print(f"'{pokeOrId}' NOT FOUND")
        
    # response = requests.get("https://jsonplaceholder.typicode.com/todos/1")
    # print(response.json())

# print(response.text)

def mainRun():
    while True:
        match input('''
Select your action:
1) print Pokedex
2) add a new Pokemon
3) search a pokemon
0) Bye!
'''):
            case '1':
                JSONWriter().writeJSONPkdx()
                pkdxRead = JSONReader().readJSONPkdx()
                print(pkdxRead)
            case '2':
                try:
                    idOrName = input('Insert number of Pokedex or Name:\n').lower().strip()
                    readhttp = PokemonReader(idOrName) # WORKS
                    JSONWriter().writeJSONPkm(readhttp.fixedInfos) # WORKS
                    readJson = JSONReader().readJSONPkm(readhttp.fixedInfos) # WORKS
                    print(readJson)
                except Exception as e:
                    print(f"ERROR!  {e}")

                # poke1 = Creature(readJson)
                # print(poke1.stats)
            case '3':
                idOrName = input('Insert number of Pokedex or Name:\n').lower().strip()
                found = False
                for el in os.listdir(f"{DATA_PATH}creatures/"):
                    with open(f'{DATA_PATH}creatures/{el}', 'r') as f:
                        a = json.load(f)
                    if str(a['id']) == idOrName or a['name'] == idOrName:
                        found = True
                        keys = ['id', 'name']
                        dictio = {}
                        for key in keys:
                            dictio[key] = a[key]
                        print(dictio)
                        break
                if not found:
                    if input(f"Pokemon {idOrName} NOT found. You want to add it as new? (y/n)\n").lower().strip() == 'y':
                        try:
                            readhttp = PokemonReader(idOrName)
                            JSONWriter().writeJSONPkm(readhttp.fixedInfos)
                            readJson = JSONReader().readJSONPkm(readhttp.fixedInfos)
                            print(readJson)
                        except Exception as e:
                            print(f"ERROR!  {e}")
                
            case '0':
                
                break

mainRun()