public class Team {
    public String name;
    public Player[] players;

    // Empty constructor
    public Team() {
        this.name = "Unknown";
        this.players = new Player[0];
    }

    // Parameterized constructor
    public Team(String name, Player[] players) {
        this.name = name;
        this.players = players;
    }
}
