
public class Main {

    public static void main(String[] args) throws Exception {
        Player p1 = new Player("Roberto", "Baggio", 10, Roles.ATT, true, "Italy");
        Player p2 = new Player("Alessandro", "Nesta", 2, Roles.DEF, false, "Italy");
        Player p3 = new Player("Niccolò", "Barella", 15, Roles.CEN, false, "Italy");
        Player[] t1Players = new Player[3];
        t1Players[0] = p1;
        t1Players[1] = p2;
        t1Players[2] = p3;
        Team t1 = new Team("Italy AllTimeLegends", t1Players);

        Player p4 = new Player("Kylian", "Mbappè", 10, Roles.ATT, true, "France");
        Player p5 = new Player("Olivier", "Giroud", 9, Roles.ATT, false, "France");
        Player p6 = new Player("Zinedine", "Zidane", 10, Roles.CEN, false, "France");
        Player[] t2Players = new Player[3];
        t2Players[0] = p4;
        t2Players[1] = p5;
        t2Players[2] = p6;
        Team t2 = new Team("France AllTimeLegends", t2Players);

        new SoccerGUI(t1, t2);
    }
}
