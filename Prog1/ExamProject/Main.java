
import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class Main {

    private static final String DB_PATH = "data/teams.json";

    public static void main(String[] args) {
        ArrayList<Team> teams = JSONReader.loadTeams(DB_PATH);

        if (teams.isEmpty()) {
            teams = TeamRoster.getInitialTeams();
            JSONWriter.saveTeams(DB_PATH, teams);
        }

        final ArrayList<Team> initialTeams = teams;
        SwingUtilities.invokeLater(() -> new MatchGUI(initialTeams));
    }
}
