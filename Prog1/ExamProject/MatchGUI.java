
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchGUI extends JFrame {

    private static final String DB_PATH = "data/teams.json";

    private static final Color FIELD_GREEN  = new Color(26, 92, 42);
    private static final Color HEADER_BG    = new Color(10, 50, 20);
    private static final Color CAPTAIN_GOLD = new Color(255, 215, 0);
    private static final Color LOG_BG       = new Color(15, 40, 20);
    private static final Color SUB_OUT_COLOR = new Color(220, 60, 60);
    private static final Color SUB_IN_COLOR  = new Color(60, 200, 100);

    // Typical national-team jersey colors, used to color the player chips
    // shown on the field. Teams without a mapped majority nationality fall
    // back to a color derived from the team name (see jerseyColor()).
    private static final Map<Nationalities, Color> JERSEY_COLORS = new HashMap<>();
    static {
        JERSEY_COLORS.put(Nationalities.ITALY, new Color(0, 102, 204));
        JERSEY_COLORS.put(Nationalities.FRANCE, new Color(0, 56, 147));
        JERSEY_COLORS.put(Nationalities.BRAZIL, new Color(255, 223, 0));
        JERSEY_COLORS.put(Nationalities.GERMANY, new Color(245, 245, 245));
        JERSEY_COLORS.put(Nationalities.SPAIN, new Color(196, 30, 58));
        JERSEY_COLORS.put(Nationalities.ARGENTINA, new Color(115, 195, 240));
        JERSEY_COLORS.put(Nationalities.UNITED_KINGDOM, new Color(200, 16, 46));
        JERSEY_COLORS.put(Nationalities.PORTUGAL, new Color(180, 28, 41));
        JERSEY_COLORS.put(Nationalities.NETHERLANDS, new Color(255, 102, 0));
        JERSEY_COLORS.put(Nationalities.BELGIUM, new Color(200, 16, 46));
        JERSEY_COLORS.put(Nationalities.CROATIA, new Color(220, 20, 60));
        JERSEY_COLORS.put(Nationalities.URUGUAY, new Color(95, 180, 230));
        JERSEY_COLORS.put(Nationalities.JAPAN, new Color(0, 60, 145));
        JERSEY_COLORS.put(Nationalities.USA, new Color(0, 40, 104));
        JERSEY_COLORS.put(Nationalities.MEXICO, new Color(0, 104, 71));
        JERSEY_COLORS.put(Nationalities.COLOMBIA, new Color(255, 205, 0));
        JERSEY_COLORS.put(Nationalities.SWEDEN, new Color(254, 204, 2));
        JERSEY_COLORS.put(Nationalities.DENMARK, new Color(200, 16, 46));
        JERSEY_COLORS.put(Nationalities.POLAND, new Color(220, 20, 60));
        JERSEY_COLORS.put(Nationalities.SWITZERLAND, new Color(220, 20, 60));
        JERSEY_COLORS.put(Nationalities.AUSTRIA, new Color(237, 41, 57));
        JERSEY_COLORS.put(Nationalities.GREECE, new Color(13, 94, 175));
        JERSEY_COLORS.put(Nationalities.TURKEY, new Color(227, 10, 23));
        JERSEY_COLORS.put(Nationalities.RUSSIA, new Color(0, 57, 166));
        JERSEY_COLORS.put(Nationalities.SERBIA, new Color(196, 30, 58));
        JERSEY_COLORS.put(Nationalities.MOROCCO, new Color(196, 30, 58));
        JERSEY_COLORS.put(Nationalities.SENEGAL, new Color(0, 158, 73));
        JERSEY_COLORS.put(Nationalities.NIGERIA, new Color(0, 135, 81));
        JERSEY_COLORS.put(Nationalities.GHANA, new Color(40, 40, 40));
        JERSEY_COLORS.put(Nationalities.CAMEROON, new Color(0, 122, 61));
        JERSEY_COLORS.put(Nationalities.EGYPT, new Color(206, 17, 38));
        JERSEY_COLORS.put(Nationalities.TUNISIA, new Color(206, 17, 38));
        JERSEY_COLORS.put(Nationalities.ALGERIA, new Color(0, 99, 65));
        JERSEY_COLORS.put(Nationalities.SOUTH_KOREA, new Color(206, 17, 38));
        JERSEY_COLORS.put(Nationalities.AUSTRALIA, new Color(255, 205, 0));
        JERSEY_COLORS.put(Nationalities.CANADA, new Color(200, 16, 46));
        JERSEY_COLORS.put(Nationalities.CHILE, new Color(200, 16, 46));
        JERSEY_COLORS.put(Nationalities.PERU, new Color(200, 16, 46));
        JERSEY_COLORS.put(Nationalities.ECUADOR, new Color(255, 205, 0));
        JERSEY_COLORS.put(Nationalities.PARAGUAY, new Color(210, 16, 52));
        JERSEY_COLORS.put(Nationalities.IRELAND, new Color(0, 132, 80));
    }

    private final ArrayList<Team> teams;
    private final Referee referee;

    private JTabbedPane mainTabs;
    private JTabbedPane teamsTabs;

    private JComboBox<Team> homeBox;
    private JComboBox<Team> awayBox;
    private JButton startButton;

    private JLabel scoreLabel;
    private JLabel periodLabel;
    private JLabel possessionLabel;
    private JTextArea logArea;

    private JPanel fieldPanel;
    private Team fieldHome;
    private Team fieldAway;
    private Player fieldBallCarrier;
    private final Map<Player, Color> fieldHighlights = new HashMap<>();

    public MatchGUI(ArrayList<Team> teams) {
        this.teams = teams;
        this.referee = TeamRoster.getDefaultReferee();

        setTitle("Soccer Match Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainTabs = new JTabbedPane();
        mainTabs.addTab("Squadre", buildTeamsTab());
        mainTabs.addTab("Partita", buildMatchTab());
        add(mainTabs, BorderLayout.CENTER);

        pack();
        setMinimumSize(new Dimension(950, 650));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ==================================================================
    //  TEAMS TAB — squad management
    // ==================================================================

    private JPanel buildTeamsTab() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FIELD_GREEN);

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        toolbar.setBackground(HEADER_BG);
        JButton addTeamBtn = new JButton("+ Nuova Squadra");
        addTeamBtn.addActionListener(e -> showAddTeamDialog());
        toolbar.add(addTeamBtn);
        panel.add(toolbar, BorderLayout.NORTH);

        teamsTabs = new JTabbedPane();
        teamsTabs.setBackground(FIELD_GREEN);
        for (Team t : teams) {
            teamsTabs.addTab(t.getName(), buildTeamPanel(t));
        }
        panel.add(teamsTabs, BorderLayout.CENTER);

        return panel;
    }

    private JPanel buildTeamPanel(Team team) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FIELD_GREEN);
        panel.add(buildTeamHeader(team), BorderLayout.NORTH);
        panel.add(buildPlayersArea(team), BorderLayout.CENTER);
        panel.add(buildTeamFooter(team), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buildTeamHeader(Team team) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_BG);
        header.setBorder(BorderFactory.createEmptyBorder(12, 30, 10, 30));

        JLabel teamLabel = new JLabel("⚽ " + team.getName(), SwingConstants.CENTER);
        teamLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        teamLabel.setForeground(Color.WHITE);

        String formationText = team.getFormation() != null ? team.getFormation().toString() : "nessuna";
        String validText = team.hasValidFormation() ? "pronta ✅" : "da completare ⚠";
        JLabel infoLabel = new JLabel(team.getPlayers().length + " giocatori — Modulo: "
                + formationText + " (" + validText + ")", SwingConstants.CENTER);
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        infoLabel.setForeground(new Color(160, 210, 160));

        header.add(teamLabel, BorderLayout.CENTER);
        header.add(infoLabel, BorderLayout.SOUTH);
        return header;
    }

    private JPanel buildTeamFooter(Team team) {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        footer.setBackground(HEADER_BG);

        JButton addPlayerBtn = new JButton("+ Giocatore");
        addPlayerBtn.addActionListener(e -> showAddPlayerDialog(team));

        JButton formationBtn = new JButton("Formazione");
        formationBtn.addActionListener(e -> showFormationDialog(team));

        footer.add(addPlayerBtn);
        footer.add(formationBtn);
        return footer;
    }

    private JScrollPane buildPlayersArea(Team team) {
        int cols = 4;
        int rows = Math.max(1, (int) Math.ceil((double) team.getPlayers().length / cols));

        JPanel grid = new JPanel(new GridLayout(rows, cols, 12, 12));
        grid.setBackground(FIELD_GREEN);
        grid.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        for (Player p : team.getPlayers()) {
            grid.add(buildPlayerCard(p, team));
        }

        JScrollPane scroll = new JScrollPane(grid);
        scroll.getViewport().setBackground(FIELD_GREEN);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JPanel buildPlayerCard(Player player, Team team) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(roleColor(player.getRole()));

        var outerBorder = player.isStarting()
                ? BorderFactory.createLineBorder(CAPTAIN_GOLD, 3)
                : BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 2);
        card.setBorder(BorderFactory.createCompoundBorder(outerBorder, BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        card.setPreferredSize(new Dimension(170, 200));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        topRow.setOpaque(false);
        String flag = (player.getNationality() != null) ? player.getNationality().getFlag() : "❓";
        topRow.add(new JLabel(emojiIcon(flag, 22)));
        JLabel numLabel = new JLabel("#" + player.getNumber());
        numLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        numLabel.setForeground(Color.WHITE);
        topRow.add(numLabel);
        card.add(topRow);
        card.add(Box.createVerticalStrut(6));

        card.add(centeredLabel(player.getName() + " " + player.getSurname(), Font.BOLD, 13, Color.WHITE));
        card.add(Box.createVerticalStrut(4));

        JLabel roleLabel = centeredLabel(player.getRole().toString(), Font.BOLD, 11, Color.WHITE);
        roleLabel.setOpaque(true);
        roleLabel.setBackground(new Color(0, 0, 0, 70));
        roleLabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        card.add(roleLabel);

        card.add(Box.createVerticalStrut(4));
        card.add(centeredLabel(player.isStarting() ? "TITOLARE" : "PANCHINA", Font.BOLD, 10,
                player.isStarting() ? CAPTAIN_GOLD : new Color(220, 220, 220)));

        if (player.getCap()) {
            card.add(Box.createVerticalStrut(4));
            card.add(centeredLabel("© Capitano", Font.ITALIC, 10, CAPTAIN_GOLD));
        }

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showEditPlayerDialog(player, team);
            }
        });

        return card;
    }

    // ---- Add team / player dialogs ----

    private void showAddTeamDialog() {
        JTextField nameField = new JTextField(20);
        JPanel form = new JPanel(new GridLayout(1, 2, 10, 0));
        form.add(new JLabel("Nome squadra:"));
        form.add(nameField);

        int result = JOptionPane.showConfirmDialog(this, form, "Nuova Squadra", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Il nome della squadra non può essere vuoto.");
            return;
        }

        Team newTeam = new Team(name, new Player[0]);
        teams.add(newTeam);
        teamsTabs.addTab(newTeam.getName(), buildTeamPanel(newTeam));
        teamsTabs.setSelectedIndex(teamsTabs.getTabCount() - 1);
        refreshMatchTeamCombos();
        saveDB();
    }

    private void showAddPlayerDialog(Team team) {
        PlayerFormResult result = showPlayerForm(null);
        if (result == null) {
            return;
        }

        Player newPlayer = new Player(result.name, result.surname, result.number,
                result.role, result.captain, result.nationality.name());

        Player[] old = team.getPlayers();
        Player[] updated = new Player[old.length + 1];
        System.arraycopy(old, 0, updated, 0, old.length);
        updated[old.length] = newPlayer;
        team.setPlayers(updated);

        refreshTeamTab(teams.indexOf(team), team);
        saveDB();
    }

    private void showEditPlayerDialog(Player player, Team team) {
        PlayerFormResult result = showPlayerForm(player);
        if (result == null) {
            return;
        }

        player.setName(result.name);
        player.setSurname(result.surname);
        player.setNumber(result.number);
        player.setRole(result.role);
        player.setCap(result.captain);
        player.setNationality(result.nationality.name());

        refreshTeamTab(teams.indexOf(team), team);
        saveDB();
    }

    private PlayerFormResult showPlayerForm(Player existing) {
        JTextField nameField = new JTextField(existing != null ? existing.getName() : "", 15);
        JTextField surnameField = new JTextField(existing != null ? existing.getSurname() : "", 15);
        JSpinner numSpinner = new JSpinner(new SpinnerNumberModel(
                existing != null ? existing.getNumber() : 1, 1, 99, 1));

        Roles[] assignableRoles = {Roles.POR, Roles.DEF, Roles.CEN, Roles.ATT};
        JComboBox<Roles> roleBox = new JComboBox<>(assignableRoles);

        JComboBox<Nationalities> natBox = new JComboBox<>(Nationalities.values());
        natBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Nationalities n) {
                    setIcon(emojiIcon(n.getFlag(), 16));
                    setText(n.name().replace("_", " "));
                }
                return this;
            }
        });
        JCheckBox captainBox = new JCheckBox("Capitano", existing != null && existing.getCap());

        if (existing != null) {
            roleBox.setSelectedItem(existing.getRole());
            natBox.setSelectedItem(existing.getNationality());
        }

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.add(new JLabel("Nome:"));
        form.add(nameField);
        form.add(new JLabel("Cognome:"));
        form.add(surnameField);
        form.add(new JLabel("Numero maglia:"));
        form.add(numSpinner);
        form.add(new JLabel("Ruolo:"));
        form.add(roleBox);
        form.add(new JLabel("Nazionalità:"));
        form.add(natBox);
        form.add(new JLabel(""));
        form.add(captainBox);

        String title = existing != null ? "Modifica Giocatore" : "Nuovo Giocatore";
        int res = JOptionPane.showConfirmDialog(this, form, title, JOptionPane.OK_CANCEL_OPTION);
        if (res != JOptionPane.OK_OPTION) {
            return null;
        }

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        if (name.isEmpty() || surname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e cognome non possono essere vuoti.");
            return null;
        }

        PlayerFormResult out = new PlayerFormResult();
        out.name = name;
        out.surname = surname;
        out.number = (int) numSpinner.getValue();
        out.role = (Roles) roleBox.getSelectedItem();
        out.nationality = (Nationalities) natBox.getSelectedItem();
        out.captain = captainBox.isSelected();
        return out;
    }

    private static class PlayerFormResult {
        String name, surname;
        int number;
        Roles role;
        boolean captain;
        Nationalities nationality;
    }

    // ---- Formation editor ----

    private void showFormationDialog(Team team) {
        JComboBox<Formation> formationBox = new JComboBox<>(Formation.values());
        if (team.getFormation() != null) {
            formationBox.setSelectedItem(team.getFormation());
        }

        LinkedHashMap<Player, JCheckBox> checks = new LinkedHashMap<>();
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        for (Roles role : new Roles[]{Roles.POR, Roles.DEF, Roles.CEN, Roles.ATT}) {
            ArrayList<Player> ofRole = new ArrayList<>();
            for (Player p : team.getPlayers()) {
                if (p.getRole() == role) {
                    ofRole.add(p);
                }
            }
            if (ofRole.isEmpty()) {
                continue;
            }
            listPanel.add(boldLabel(roleGroupName(role)));
            for (Player p : ofRole) {
                JCheckBox cb = new JCheckBox(p.getMatchLabel() + " " + p.getName(), p.isStarting());
                checks.put(p, cb);
                listPanel.add(cb);
            }
            listPanel.add(Box.createVerticalStrut(8));
        }

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setPreferredSize(new Dimension(340, 320));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Modulo:"));
        top.add(formationBox);

        JPanel form = new JPanel(new BorderLayout(10, 10));
        form.add(top, BorderLayout.NORTH);
        form.add(scroll, BorderLayout.CENTER);

        boolean saved = false;
        while (!saved) {
            int res = JOptionPane.showConfirmDialog(this, form, "Formazione - " + team.getName(), JOptionPane.OK_CANCEL_OPTION);
            if (res != JOptionPane.OK_OPTION) {
                return;
            }

            Formation chosen = (Formation) formationBox.getSelectedItem();
            int por = 0, def = 0, cen = 0, att = 0;
            for (Map.Entry<Player, JCheckBox> e : checks.entrySet()) {
                if (e.getValue().isSelected()) {
                    switch (e.getKey().getRole()) {
                        case POR: por++; break;
                        case DEF: def++; break;
                        case CEN: cen++; break;
                        case ATT: att++; break;
                        default: break;
                    }
                }
            }

            if (por != 1 || def != chosen.getDef() || cen != chosen.getMid() || att != chosen.getAtt()) {
                JOptionPane.showMessageDialog(this,
                        "Il modulo " + chosen + " richiede:\n"
                                + "1 portiere (selezionati: " + por + ")\n"
                                + chosen.getDef() + " difensori (selezionati: " + def + ")\n"
                                + chosen.getMid() + " centrocampisti (selezionati: " + cen + ")\n"
                                + chosen.getAtt() + " attaccanti (selezionati: " + att + ")",
                        "Formazione non valida", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            for (Map.Entry<Player, JCheckBox> e : checks.entrySet()) {
                e.getKey().setStarting(e.getValue().isSelected());
            }
            team.setFormation(chosen);
            saved = true;
        }

        refreshTeamTab(teams.indexOf(team), team);
        saveDB();
    }

    private String roleGroupName(Roles role) {
        switch (role) {
            case POR: return "Portieri";
            case DEF: return "Difensori";
            case CEN: return "Centrocampisti";
            case ATT: return "Attaccanti";
            default: return role.toString();
        }
    }

    private void refreshTeamTab(int tabIndex, Team team) {
        teamsTabs.setComponentAt(tabIndex, buildTeamPanel(team));
        teamsTabs.setTitleAt(tabIndex, team.getName());
        teamsTabs.revalidate();
        teamsTabs.repaint();
    }

    // ==================================================================
    //  MATCH TAB — setup + live simulation
    // ==================================================================

    private JPanel buildMatchTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(FIELD_GREEN);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setOpaque(false);
        top.add(buildSetupPanel(), BorderLayout.NORTH);

        JPanel middle = new JPanel(new BorderLayout(0, 10));
        middle.setOpaque(false);
        middle.add(buildScoreboardPanel(), BorderLayout.NORTH);
        middle.add(buildFieldPanel(), BorderLayout.CENTER);
        top.add(middle, BorderLayout.CENTER);

        panel.add(top, BorderLayout.NORTH);

        panel.add(buildLogPanel(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel buildSetupPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(HEADER_BG);
        TitledBorder border = BorderFactory.createTitledBorder("Imposta Partita");
        border.setTitleColor(Color.WHITE);
        panel.setBorder(border);

        homeBox = new JComboBox<>();
        awayBox = new JComboBox<>();
        refreshMatchTeamCombos();

        startButton = new JButton("Inizia Partita");
        startButton.addActionListener(e -> startMatch());

        panel.add(whiteLabel("Casa:"));
        panel.add(homeBox);
        panel.add(whiteLabel("Ospiti:"));
        panel.add(awayBox);
        panel.add(startButton);
        return panel;
    }

    private JPanel buildScoreboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(HEADER_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scoreLabel = centeredLabel("- vs -", Font.BOLD, 30, Color.WHITE);
        periodLabel = centeredLabel("Pronti al calcio d'inizio", Font.PLAIN, 14, new Color(160, 210, 160));
        possessionLabel = centeredLabel(" ", Font.PLAIN, 14, new Color(160, 210, 160));

        panel.add(scoreLabel);
        panel.add(periodLabel);
        panel.add(possessionLabel);
        return panel;
    }

    // Visual lineup of both teams, arranged by formation, with a ball icon
    // next to the current ball carrier.
    private JPanel buildFieldPanel() {
        fieldPanel = new JPanel();
        fieldPanel.setBackground(new Color(34, 110, 50));
        fieldPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        fieldPanel.setPreferredSize(new Dimension(360, 480));
        refreshField();
        return fieldPanel;
    }

    // Rebuilds the field panel from the current fieldHome/fieldAway lineups,
    // fieldBallCarrier and fieldHighlights.
    private void refreshField() {
        fieldPanel.removeAll();

        if (fieldHome == null || fieldAway == null) {
            fieldPanel.setLayout(new BorderLayout());
            fieldPanel.add(centeredLabel("Seleziona le squadre e premi \"Inizia Partita\"",
                    Font.ITALIC, 14, new Color(220, 240, 220)), BorderLayout.CENTER);
        } else {
            // Stack the two teams' lineups vertically, one pitch from top to
            // bottom: home POR/DEF/CEN/ATT first, then away ATT/CEN/DEF/POR,
            // so the home goalkeeper is the very first row and the away
            // goalkeeper is the very last.
            fieldPanel.setLayout(new GridLayout(2, 1, 0, 4));
            fieldPanel.add(buildFormationColumn(fieldHome, false));
            fieldPanel.add(buildFormationColumn(fieldAway, true));
        }

        fieldPanel.revalidate();
        fieldPanel.repaint();
    }

    // One team's lineup, stacked POR/DEF/CEN/ATT (or reversed for the away
    // team), forming one half of the vertical pitch built in refreshField().
    private JPanel buildFormationColumn(Team team, boolean reversed) {
        JPanel column = new JPanel(new GridLayout(4, 1, 2, 4));
        column.setOpaque(false);
        column.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Roles[] order = reversed
                ? new Roles[]{Roles.ATT, Roles.CEN, Roles.DEF, Roles.POR}
                : new Roles[]{Roles.POR, Roles.DEF, Roles.CEN, Roles.ATT};

        Color jersey = jerseyColor(team);
        for (Roles role : order) {
            column.add(buildFormationRow(team, role, jersey));
        }
        return column;
    }

    private JPanel buildFormationRow(Team team, Roles role, Color jersey) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 2));
        row.setOpaque(false);
        for (Player p : team.getStartingEleven()) {
            if (p.getRole() == role) {
                row.add(buildFieldChip(p, jersey));
            }
        }
        return row;
    }

    // A single player chip: shirt number on a jersey-shaped icon in the
    // team's jersey color, with a ball icon next to it if this player
    // currently has the ball, and a colored outline if involved in a
    // substitution highlight.
    private JPanel buildFieldChip(Player player, Color jersey) {
        JPanel chip = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        chip.setOpaque(false);

        Color highlight = fieldHighlights.get(player);
        Color outline = (highlight != null) ? highlight : Color.WHITE;
        float outlineWidth = (highlight != null) ? 3f : 1f;

        JerseyIcon shirt = new JerseyIcon(player.getNumber(), jersey, outline, outlineWidth);
        shirt.setToolTipText(player.getMatchLabel());

        chip.add(shirt);
        if (player == fieldBallCarrier) {
            chip.add(new JLabel(emojiIcon("⚽", 16)));
        }
        return chip;
    }

    // Picks a jersey color based on the team's most common nationality,
    // falling back to a color derived from the team name.
    private Color jerseyColor(Team team) {
        Map<Nationalities, Integer> counts = new HashMap<>();
        for (Player p : team.getPlayers()) {
            counts.merge(p.getNationality(), 1, Integer::sum);
        }

        Nationalities top = null;
        int best = -1;
        for (Map.Entry<Nationalities, Integer> e : counts.entrySet()) {
            if (e.getValue() > best) {
                best = e.getValue();
                top = e.getKey();
            }
        }

        if (top != null && JERSEY_COLORS.containsKey(top)) {
            return JERSEY_COLORS.get(top);
        }

        float hue = (Math.abs(team.getName().hashCode()) % 360) / 360f;
        return Color.getHSBColor(hue, 0.55f, 0.85f);
    }

    private static Color contrastingTextColor(Color background) {
        double luminance = (0.299 * background.getRed() + 0.587 * background.getGreen() + 0.114 * background.getBlue()) / 255;
        return luminance > 0.6 ? Color.BLACK : Color.WHITE;
    }

    private JScrollPane buildLogPanel() {
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setBackground(LOG_BG);
        logArea.setForeground(Color.WHITE);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(logArea);
        TitledBorder border = BorderFactory.createTitledBorder("Cronaca");
        border.setTitleColor(Color.WHITE);
        scroll.setBorder(border);
        return scroll;
    }

    private void refreshMatchTeamCombos() {
        Team selectedHome = (Team) homeBox.getSelectedItem();
        Team selectedAway = (Team) awayBox.getSelectedItem();

        homeBox.removeAllItems();
        awayBox.removeAllItems();
        for (Team t : teams) {
            homeBox.addItem(t);
            awayBox.addItem(t);
        }

        if (selectedHome != null) {
            homeBox.setSelectedItem(selectedHome);
        }
        if (selectedAway != null) {
            awayBox.setSelectedItem(selectedAway);
        } else if (teams.size() > 1) {
            awayBox.setSelectedIndex(1);
        }
    }

    private void startMatch() {
        Team home = (Team) homeBox.getSelectedItem();
        Team away = (Team) awayBox.getSelectedItem();

        if (home == null || away == null) {
            JOptionPane.showMessageDialog(this, "Seleziona entrambe le squadre.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (home == away) {
            JOptionPane.showMessageDialog(this, "Le due squadre devono essere diverse.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!home.hasValidFormation()) {
            JOptionPane.showMessageDialog(this, formationErrorMessage(home), "Formazione non valida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!away.hasValidFormation()) {
            JOptionPane.showMessageDialog(this, formationErrorMessage(away), "Formazione non valida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        logArea.setText("");
        logEvent("Italia... ehm, " + home.getName() + " vs " + away.getName() + "! Arbitro: "
                + referee.getName() + " " + referee.getSurname() + ".");
        setMatchControlsEnabled(false);

        fieldHome = home;
        fieldAway = away;
        fieldBallCarrier = null;
        fieldHighlights.clear();
        refreshField();

        Match match = new Match(home, away, referee, this);
        new Thread(match).start();
    }

    private String formationErrorMessage(Team team) {
        String formationStr = team.getFormation() != null ? team.getFormation().toString() : "nessuna";
        return "La squadra \"" + team.getName() + "\" non ha una formazione valida (modulo: " + formationStr + ").\n"
                + "Vai nella scheda Squadre e usa \"Formazione\" per selezionare 11 titolari corretti.";
    }

    private void setMatchControlsEnabled(boolean enabled) {
        homeBox.setEnabled(enabled);
        awayBox.setEnabled(enabled);
        startButton.setEnabled(enabled);
        mainTabs.setEnabledAt(0, enabled);
        if (!enabled) {
            mainTabs.setSelectedIndex(1);
        }
    }

    // ==================================================================
    //  CALLBACKS — invoked from the Match thread (see Match.java)
    // ==================================================================

    // Coin toss: the home team calls Heads/Tails, then the referee flips.
    public Team runCoinToss(Referee referee, Team home, Team away) {
        final Team[] result = new Team[1];
        runOnEdt(() -> {
            Object[] options = {"Testa", "Croce"};
            int choice = JOptionPane.showOptionDialog(this,
                    "L'arbitro " + referee.getName() + " " + referee.getSurname() + " lancia la moneta.\n"
                            + home.getName() + " sceglie:",
                    "Testa o Croce",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            boolean calledHeads = (choice != 1);
            boolean isHeads = Math.random() < 0.5;
            Team winner = (calledHeads == isHeads) ? home : away;
            result[0] = winner;

            JOptionPane.showMessageDialog(this,
                    "Esce " + (isHeads ? "Testa" : "Croce") + "!\n"
                            + winner.getName() + " vince il sorteggio e inizia con il pallone.",
                    "Risultato sorteggio", JOptionPane.INFORMATION_MESSAGE);
        });
        logEvent("--- Sorteggio: inizia " + result[0].getName() + " ---");
        return result[0];
    }

    // The ball carrier (an outfield player) chooses Pass or Shot.
    public String askAttackingMove(Team team, Player carrier) {
        final String[] result = {"PASS"};
        runOnEdt(() -> {
            Object[] options = {"Passaggio", "Tiro"};
            int choice = JOptionPane.showOptionDialog(this,
                    team.getName() + " ha il pallone.\n" + carrier.getMatchLabel() + ": cosa fai?",
                    "Turno di " + team.getName(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            result[0] = (choice == 1) ? "SHOT" : "PASS";
        });
        return result[0];
    }

    // Choose a teammate (outfield, excluding the carrier) to pass to.
    public Player askPassTarget(Team team, Player carrier) {
        ArrayList<Player> options = new ArrayList<>();
        for (Player p : team.getStartingEleven()) {
            if (p != carrier && p.getRole() != Roles.POR) {
                options.add(p);
            }
        }

        final Player[] result = new Player[1];
        runOnEdt(() -> {
            JComboBox<Player> box = new JComboBox<>(options.toArray(new Player[0]));
            box.setRenderer(playerRenderer());
            String message = (carrier.getRole() == Roles.POR)
                    ? "Il portiere " + carrier.getMatchLabel() + " ha la palla.\nA chi rilancia?"
                    : carrier.getMatchLabel() + " ha la palla.\nA chi passa?";
            JOptionPane.showMessageDialog(this, wrapWithLabel(message, box), "Passaggio - " + team.getName(), JOptionPane.PLAIN_MESSAGE);
            result[0] = (Player) box.getSelectedItem();
        });
        return result[0];
    }

    // The defending team chooses which outfield player attempts the tackle.
    public Player askTackler(Team team, Player opponentCarrier) {
        ArrayList<Player> options = new ArrayList<>();
        for (Player p : team.getStartingEleven()) {
            if (p.getRole() != Roles.POR) {
                options.add(p);
            }
        }

        final Player[] result = new Player[1];
        runOnEdt(() -> {
            JComboBox<Player> box = new JComboBox<>(options.toArray(new Player[0]));
            box.setRenderer(playerRenderer());
            String message = "Tocca a " + team.getName() + "!\n"
                    + opponentCarrier.getMatchLabel() + " ha il pallone.\n"
                    + "Scegli chi tenta il contrasto:";
            JOptionPane.showMessageDialog(this, wrapWithLabel(message, box), "Contrasto - " + team.getName(), JOptionPane.PLAIN_MESSAGE);
            result[0] = (Player) box.getSelectedItem();
        });
        return result[0];
    }

    // Result popup for shots and tackles (outcome is rolled by Match, not chosen by the user).
    public void showResult(String title, String message) {
        runOnEdt(() -> JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE));
        logEvent("[" + title + "] " + message);
    }

    // Blocking announcement, e.g. before extra time starts.
    public void announce(String message) {
        runOnEdt(() -> JOptionPane.showMessageDialog(this, message, "Arbitro", JOptionPane.INFORMATION_MESSAGE));
        logEvent("--- " + message + " ---");
    }

    // End-of-first-half substitutions: the user decides if/which players to swap.
    public void runHalftimeSubstitutions(Team team) {
        runOnEdt(() -> {
            while (true) {
                Player[] subs = team.getSubstitutes();
                if (subs.length == 0) {
                    JOptionPane.showMessageDialog(this,
                            team.getName() + ": panchina vuota, nessuna sostituzione possibile.",
                            "Sostituzioni - " + team.getName(), JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(this,
                        team.getName() + ": vuoi effettuare una sostituzione?",
                        "Sostituzioni - " + team.getName(), JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                Player[] starters = team.getStartingEleven();
                JComboBox<Player> outBox = new JComboBox<>(starters);
                outBox.setRenderer(playerRenderer());
                JComboBox<Player> inBox = new JComboBox<>(subs);
                inBox.setRenderer(playerRenderer());

                JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
                form.add(new JLabel("Esce:"));
                form.add(outBox);
                form.add(new JLabel("Entra:"));
                form.add(inBox);

                int res = JOptionPane.showConfirmDialog(this, form, "Sostituzione - " + team.getName(), JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION) {
                    Player out = (Player) outBox.getSelectedItem();
                    Player in = (Player) inBox.getSelectedItem();
                    out.setStarting(false);
                    in.setStarting(true);

                    fieldHighlights.clear();
                    fieldHighlights.put(out, SUB_OUT_COLOR);
                    fieldHighlights.put(in, SUB_IN_COLOR);
                    refreshField();

                    JOptionPane.showMessageDialog(this,
                            "Cambio in " + team.getName() + ":\n"
                                    + "ESCE: " + out.getMatchLabel() + "\n"
                                    + "ENTRA: " + in.getMatchLabel(),
                            "Sostituzione", JOptionPane.INFORMATION_MESSAGE);

                    fieldHighlights.clear();
                    refreshField();

                    logEvent(team.getName() + ": esce " + out.getMatchLabel() + ", entra " + in.getMatchLabel() + ".");
                }
            }
        });
        saveDB();
    }

    // Final whistle.
    public void showFinalResult(Match match) {
        runOnEdt(() -> {
            String outcome;
            if (match.getScoreHome() > match.getScoreAway()) {
                outcome = "Vince " + match.getHome().getName() + "!";
            } else if (match.getScoreAway() > match.getScoreHome()) {
                outcome = "Vince " + match.getAway().getName() + "!";
            } else {
                outcome = "Pareggio!";
            }

            JOptionPane.showMessageDialog(this,
                    "Risultato finale:\n"
                            + match.getHome().getName() + " " + match.getScoreHome() + " - "
                            + match.getScoreAway() + " " + match.getAway().getName()
                            + "\n\n" + outcome,
                    "Fine Partita", JOptionPane.INFORMATION_MESSAGE);

            setMatchControlsEnabled(true);
            refreshTeamTab(teams.indexOf(match.getHome()), match.getHome());
            refreshTeamTab(teams.indexOf(match.getAway()), match.getAway());
        });
        logEvent("=== FINE PARTITA: " + match.getHome().getName() + " " + match.getScoreHome()
                + " - " + match.getScoreAway() + " " + match.getAway().getName() + " ===");
    }

    // Non-blocking: append a line to the match log.
    public void logEvent(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    // Non-blocking: refresh the scoreboard.
    public void refresh(Match match) {
        SwingUtilities.invokeLater(() -> {
            scoreLabel.setText(match.getHome().getName() + "  " + match.getScoreHome()
                    + " - " + match.getScoreAway() + "  " + match.getAway().getName());

            if (match.getPeriodName().isEmpty()) {
                periodLabel.setText("Pronti al calcio d'inizio");
            } else {
                int maxMoves = match.getPeriodName().contains("Supplementare") ? Match.MOVES_PER_EXTRA : Match.MOVES_PER_HALF;
                periodLabel.setText(match.getPeriodName() + " — Mossa " + match.getMove() + "/" + maxMoves);
            }

            possessionLabel.setText("⚽ Possesso: " + match.getPossession().getName()
                    + " — " + match.getBallCarrier().getMatchLabel());

            fieldBallCarrier = match.getBallCarrier();
            refreshField();
        });
    }

    // ==================================================================
    //  SHARED HELPERS
    // ==================================================================

    // Runs a Swing dialog on the EDT and blocks the calling (Match) thread
    // until it is dismissed — this is what makes the simulation turn based.
    private void runOnEdt(Runnable task) {
        try {
            SwingUtilities.invokeAndWait(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (InvocationTargetException e) {
            System.out.println("Errore nell'interfaccia grafica: " + e.getMessage());
        }
    }

    private void saveDB() {
        JSONWriter.saveTeams(DB_PATH, teams);
    }

    private DefaultListCellRenderer playerRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Player p) {
                    setText(p.getMatchLabel());
                }
                return this;
            }
        };
    }

    private JPanel wrapWithLabel(String message, JComponent comp) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(new JLabel("<html>" + message.replace("\n", "<br>") + "</html>"), BorderLayout.NORTH);
        panel.add(comp, BorderLayout.CENTER);
        return panel;
    }

    private JLabel whiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JLabel boldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 13f));
        return label;
    }

    private JLabel centeredLabel(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", style, size));
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    // Renders an emoji onto a BufferedImage via Graphics2D (CoreText on macOS),
    // which correctly composes Regional Indicator sequences into flag glyphs.
    private ImageIcon emojiIcon(String emoji, int size) {
        BufferedImage img = new BufferedImage(size * 2, size + 6, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Apple Color Emoji", Font.PLAIN, size));
        g.drawString(emoji, 0, size);
        g.dispose();
        return new ImageIcon(img);
    }

    private Color roleColor(Roles role) {
        switch (role) {
            case ATT: return new Color(200, 50, 50);
            case DEF: return new Color(50, 100, 200);
            case CEN: return new Color(160, 120, 20);
            case POR: return new Color(200, 120, 30);
            default:  return new Color(80, 80, 80);
        }
    }

    // A small jersey-shaped icon for player chips on the field: a shirt
    // silhouette (collar + sleeves + body) filled with the team's jersey
    // color, with the player's shirt number drawn on top.
    private static class JerseyIcon extends JComponent {
        private final int number;
        private final Color jersey;
        private final Color outline;
        private final float outlineWidth;

        JerseyIcon(int number, Color jersey, Color outline, float outlineWidth) {
            this.number = number;
            this.jersey = jersey;
            this.outline = outline;
            this.outlineWidth = outlineWidth;
            setOpaque(false);
            setPreferredSize(new Dimension(38, 34));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Shape shape = jerseyShape(getWidth(), getHeight());

            g2.setColor(jersey);
            g2.fill(shape);

            Color textColor = contrastingTextColor(jersey);

            // Collar trim, in the contrasting color, for a bit of jersey detail.
            g2.setColor(textColor);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine((int) (getWidth() * 0.32), 0, (int) (getWidth() * 0.5), (int) (getHeight() * 0.18));
            g2.drawLine((int) (getWidth() * 0.5), (int) (getHeight() * 0.18), (int) (getWidth() * 0.68), 0);

            g2.setColor(outline);
            g2.setStroke(new BasicStroke(outlineWidth));
            g2.draw(shape);

            g2.setColor(textColor);
            g2.setFont(getFont().deriveFont(Font.BOLD, getHeight() * 0.4f));
            String text = String.valueOf(number);
            FontMetrics fm = g2.getFontMetrics();
            int tx = (getWidth() - fm.stringWidth(text)) / 2;
            int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2 + (getHeight() / 10);
            g2.drawString(text, tx, ty);

            g2.dispose();
        }

        // Shirt silhouette: V-neck collar at the top center, a sleeve
        // sticking out on each side, and a rectangular body below.
        private Shape jerseyShape(int w, int h) {
            Path2D path = new Path2D.Double();
            path.moveTo(w * 0.32, 0);
            path.lineTo(w * 0.5, h * 0.18);
            path.lineTo(w * 0.68, 0);
            path.lineTo(w, h * 0.22);
            path.lineTo(w * 0.84, h * 0.42);
            path.lineTo(w * 0.84, h);
            path.lineTo(w * 0.16, h);
            path.lineTo(w * 0.16, h * 0.42);
            path.lineTo(0, h * 0.22);
            path.closePath();
            return path;
        }
    }
}
