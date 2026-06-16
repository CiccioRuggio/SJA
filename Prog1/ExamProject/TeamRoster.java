import java.util.ArrayList;

// ============================================================================
//  HARDCODED TEAMS, PLAYERS AND REFEREE
//  ----------------------------------------------------------------------
//  This is the ONLY place you need to edit to change the default squads.
//  These teams are used to create data/teams.json the first time the
//  program runs. Once that file exists, it becomes the source of truth:
//  any team/player/formation changes made from the UI are saved there,
//  and this class is no longer read again (delete data/teams.json to
//  reset back to these defaults).
//
//  How to add a player:
//      new Player(name, surname, shirtNumber, role, captain, nationality)
//      roles: Roles.POR (goalkeeper), Roles.DEF, Roles.CEN, Roles.ATT
//
//  Use start(player) to put a player in the starting XI. Each team needs
//  EXACTLY 11 starters matching its Formation: 1 POR + def + mid + att
//  (see Formation enum in Team.java). Players not wrapped in start(...)
//  begin on the bench and can be substituted in from the UI.
// ============================================================================
public class TeamRoster {

    public static ArrayList<Team> getInitialTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(buildItalyLegends());
        teams.add(buildFranceLegends());
        return teams;
    }

    public static Referee getDefaultReferee() {
        return new Referee("Pierluigi", "Collina", "Italy", License.EURO1);
    }

    // ---- Team 1: edit/replace freely ----
    private static Team buildItalyLegends() {
        Player[] players = {
            start(new Player("Gianluigi", "Buffon", 1, Roles.POR, true, "Italy")),
            new Player("Gianluigi", "Donnarumma", 99, Roles.POR, true, "Italy"),

            start(new Player("Paolo", "Maldini", 3, Roles.DEF, false, "Italy")),
            start(new Player("Alessandro", "Nesta", 4, Roles.DEF, false, "Italy")),
            start(new Player("Fabio", "Cannavaro", 5, Roles.DEF, false, "Italy")),
            start(new Player("Giorgio", "Chiellini", 15, Roles.DEF, false, "Italy")),
            new Player("Giacinto", "Facchetti", 2, Roles.DEF, false, "Italy"),

            start(new Player("Andrea", "Pirlo", 21, Roles.CEN, false, "Italy")),
            start(new Player("Gennaro", "Gattuso", 8, Roles.CEN, false, "Italy")),
            start(new Player("Marco", "Verratti", 6, Roles.CEN, false, "Italy")),
            start(new Player("Daniele", "De Rossi", 16, Roles.CEN, false, "Italy")),
            new Player("Niccolò", "Barella", 18, Roles.CEN, false, "Italy"),

            start(new Player("Roberto", "Baggio", 10, Roles.ATT, false, "Italy")),
            start(new Player("Alessandro", "Del Piero", 7, Roles.ATT, false, "Italy")),
            new Player("Francesco", "Totti", 9, Roles.ATT, false, "Italy"),
            new Player("Christian", "Vieri", 14, Roles.ATT, false, "Italy"),
        };

        return new Team("Italy Legends", players, Formation.F_442);
    }

    // ---- Team 2: edit/replace freely ----
    private static Team buildFranceLegends() {
        Player[] players = {
            start(new Player("Hugo", "Lloris", 1, Roles.POR, true, "France")),
            new Player("Fabien", "Barthez", 16, Roles.POR, false, "France"),

            start(new Player("Lilian", "Thuram", 15, Roles.DEF, false, "France")),
            start(new Player("Marcel", "Desailly", 6, Roles.DEF, false, "France")),
            start(new Player("Laurent", "Blanc", 5, Roles.DEF, false, "France")),
            start(new Player("Bixente", "Lizarazu", 3, Roles.DEF, false, "France")),
            new Player("Patrice", "Evra", 21, Roles.DEF, false, "France"),

            start(new Player("Zinedine", "Zidane", 10, Roles.CEN, false, "France")),
            start(new Player("Didier", "Deschamps", 4, Roles.CEN, false, "France")),
            start(new Player("Paul", "Pogba", 19, Roles.CEN, false, "France")),
            new Player("N'Golo", "Kanté", 13, Roles.CEN, false, "France"),

            start(new Player("Kylian", "Mbappé", 7, Roles.ATT, false, "France")),
            start(new Player("Thierry", "Henry", 12, Roles.ATT, false, "France")),
            start(new Player("Olivier", "Giroud", 9, Roles.ATT, false, "France")),
            new Player("Antoine", "Griezmann", 20, Roles.ATT, false, "France"),
            new Player("Karim", "Benzema", 18, Roles.ATT, false, "France"),
        };

        return new Team("France Legends", players, Formation.F_433);
    }

    private static Player start(Player p) {
        p.setStarting(true);
        return p;
    }
}
