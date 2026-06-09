
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONReader {

    public static ArrayList<StopOver> load(String path) {
        ArrayList<StopOver> stops = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject root = new JSONObject(content);
            JSONArray tickets = root.getJSONArray("tickets");

            for (int i = 0; i < tickets.length(); i++) {
                JSONObject ticket = tickets.getJSONObject(i);

                String status = ticket.getString("status");
                String type = ticket.getString("type");
                String plate = ticket.getString("plate");
                int position = ticket.getInt("position");
                double price = ticket.getDouble("price");
                long start = ticket.getLong("start");
                long end = status.equals("CLOSED") ? ticket.getLong("end") : 0;

                Vehicle vehicle;
                switch (type) {
                    case "CAR":
                        vehicle = new Car(plate);
                        break;
                    case "TRUCK":
                        int length = ticket.optInt("length", 4);
                        vehicle = new Truck(plate, length);
                        break;
                    default:
                        vehicle = new Vehicle(plate);
                        break;
                }

                stops.add(new StopOver(vehicle, position, price, start, end));
            }

        } catch (IOException e) {
            System.out.println("File not found or unreadable: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing DB: " + e.getMessage());
        }

        return stops;
    }
}
