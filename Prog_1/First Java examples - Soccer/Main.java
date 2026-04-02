public class Main {
    public static void main(String[] args) throws Exception {
        Player p1 = new Player("Roberto", "Baggio", 10, Roles.ATT, true, "Italy");
        Player p2 = new Player("Alessandro", "Nesta", 2, Roles.DEF, false, "Italy");
        Player p3 = new Player("Niccolò", "Barella", 15, Roles.CEN, false, "Italy");
        Player[] t1Players = new Player[3];
        t1Players[0] = p1;
        t1Players[1] = p2;
        t1Players[2] = p3;
        Team t1 = new Team("\n\nItaly AllTimeLegends", t1Players);

        new ProcessBuilder("clear").inheritIO().start().waitFor();
        System.out.println(t1.name + "\n\n TEAM COMP:");

        for (Player player : t1.players) {
            System.out.println(player.toString() + "\nAbbreviation: " + player.getAbbreviation() + "\n\nFull Info:\n" + player.getFullInfo());
        }

        // creare un metodo che stampi l'abbreviazione di ogni giocatore. Es: Cristiano Ronaldo 7 => CR7
    }
}