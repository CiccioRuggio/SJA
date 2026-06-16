import java.util.ArrayList;

public class Team {
    private String name;
    private Player[] players;
    private Formation formation;

    // Empty constructor
    public Team() {
        this.name = "Unknown";
        this.players = new Player[0];
        this.formation = null;
    }

    // Parameterized constructor
    public Team(String name, Player[] players) {
        this.name = name;
        this.players = players;
        this.formation = null;
    }

    public Team(String name, Player[] players, Formation formation) {
        this.name = name;
        this.players = players;
        this.formation = formation;
    }

    public String getName() {
        return this.name;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Formation getFormation() {
        return this.formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    // Players currently selected in the starting XI
    public Player[] getStartingEleven() {
        ArrayList<Player> starters = new ArrayList<>();
        for (Player p : this.players) {
            if (p.isStarting()) {
                starters.add(p);
            }
        }
        return starters.toArray(new Player[0]);
    }

    // Players left out of the starting XI (the bench)
    public Player[] getSubstitutes() {
        ArrayList<Player> bench = new ArrayList<>();
        for (Player p : this.players) {
            if (!p.isStarting()) {
                bench.add(p);
            }
        }
        return bench.toArray(new Player[0]);
    }

    // The starting goalkeeper, or null if none is selected
    public Player getGoalkeeper() {
        for (Player p : this.players) {
            if (p.isStarting() && p.getRole() == Roles.POR) {
                return p;
            }
        }
        return null;
    }

    // A formation is valid only if the starting XI matches the role counts required by the chosen formation: exactly 1 POR plus the DEF/CEN/ATT split.
    public boolean hasValidFormation() {
        if (this.formation == null) {
            return false;
        }
        int por = 0, def = 0, cen = 0, att = 0;
        for (Player p : this.players) {
            if (!p.isStarting()) {
                continue;
            }
            switch (p.getRole()) {
                case POR: por++; break;
                case DEF: def++; break;
                case CEN: cen++; break;
                case ATT: att++; break;
                default: break;
            }
        }
        return por == 1 && def == this.formation.getDef()
                && cen == this.formation.getMid() && att == this.formation.getAtt();
    }

    @Override
    public String toString() {
        return this.name;
    }
}

// Classic formations: number of defenders, midfielders and attackers among the 10 outfield starters. The 11th starter is always the goalkeeper.
enum Formation {
    F_442("4-4-2", 4, 4, 2),
    F_433("4-3-3", 4, 3, 3),
    F_352("3-5-2", 3, 5, 2),
    F_343("3-4-3", 3, 4, 3),
    F_532("5-3-2", 5, 3, 2);

    private final String label;
    private final int def, mid, att;

    Formation(String label, int def, int mid, int att) {
        this.label = label;
        this.def = def;
        this.mid = mid;
        this.att = att;
    }

    public int getDef() {
        return this.def;
    }

    public int getMid() {
        return this.mid;
    }

    public int getAtt() {
        return this.att;
    }

    @Override
    public String toString() {
        return this.label;
    }

    // Looks up a formation by its "4-4-2" style label, returns null if unknown
    public static Formation fromLabel(String label) {
        for (Formation f : Formation.values()) {
            if (f.label.equals(label)) {
                return f;
            }
        }
        return null;
    }
}
