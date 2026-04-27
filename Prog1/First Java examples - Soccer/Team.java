public class Team {
    private String name;
    private Player[] players;

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
}
