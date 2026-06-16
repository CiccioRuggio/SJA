
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONWriter {

    public static void saveTeams(String path, ArrayList<Team> teams) {
        try {
            JSONArray teamsArray = new JSONArray();

            for (Team team : teams) {
                JSONObject teamObj = new JSONObject();
                teamObj.put("name", team.getName());
                teamObj.put("formation", team.getFormation() != null ? team.getFormation().toString() : "");

                JSONArray playersArray = new JSONArray();
                for (Player player : team.getPlayers()) {
                    JSONObject p = new JSONObject();
                    p.put("name", player.getName());
                    p.put("surname", player.getSurname());
                    p.put("nationality", player.getNationality().name());
                    p.put("number", player.getNumber());
                    p.put("role", player.getRole().name());
                    p.put("captain", player.getCap());
                    p.put("starting", player.isStarting());
                    playersArray.put(p);
                }
                teamObj.put("players", playersArray);
                teamsArray.put(teamObj);
            }

            JSONObject root = new JSONObject();
            root.put("teams", teamsArray);

            FileWriter fw = new FileWriter(path);
            fw.write(root.toString(2));
            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving teams DB: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error serializing teams DB: " + e.getMessage());
        }
    }
}
