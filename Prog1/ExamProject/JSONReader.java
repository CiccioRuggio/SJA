
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONReader {

    // Loads the team database. Returns an empty list if the file is
    // missing or invalid, so the caller can fall back to TeamRoster.
    public static ArrayList<Team> loadTeams(String path) {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject root = new JSONObject(content);
            JSONArray teamsArray = root.getJSONArray("teams");

            for (int i = 0; i < teamsArray.length(); i++) {
                JSONObject teamObj = teamsArray.getJSONObject(i);

                String name = teamObj.getString("name");
                JSONArray playersArray = teamObj.getJSONArray("players");

                Player[] players = new Player[playersArray.length()];
                for (int j = 0; j < playersArray.length(); j++) {
                    JSONObject p = playersArray.getJSONObject(j);

                    Player player = new Player(
                            p.getString("name"),
                            p.getString("surname"),
                            p.getInt("number"),
                            Roles.valueOf(p.getString("role")),
                            p.getBoolean("captain"),
                            p.getString("nationality"));
                    player.setStarting(p.optBoolean("starting", false));
                    players[j] = player;
                }

                Team team = new Team(name, players);
                team.setFormation(Formation.fromLabel(teamObj.optString("formation", "")));
                teams.add(team);
            }

        } catch (IOException e) {
            System.out.println("Teams DB not found, using defaults: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing teams DB: " + e.getMessage());
        }

        return teams;
    }
}
