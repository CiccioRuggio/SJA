
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONWriter {

    public static void save(String path, ArrayList<StopOver> stops) {
        try {
            JSONArray tickets = new JSONArray();

            for (StopOver stop : stops) {
                JSONObject ticket = new JSONObject();
                ticket.put("status", stop.isOngoing() ? "OPEN" : "CLOSED");
                ticket.put("plate", stop.getVehicle().getPlate());
                ticket.put("position", stop.getPosition());
                ticket.put("price", stop.getPriceRate());
                ticket.put("start", stop.getStart());

                Vehicle v = stop.getVehicle();
                if (v instanceof Truck) {
                    ticket.put("type", "TRUCK");
                    ticket.put("length", ((Truck) v).getLength());
                } else if (v instanceof Car) {
                    ticket.put("type", "CAR");
                } else {
                    ticket.put("type", "VEHICLE");
                }

                if (stop.isOver()) {
                    ticket.put("end", stop.getEnd());
                }

                tickets.put(ticket);
            }

            JSONObject root = new JSONObject();
            root.put("tickets", tickets);

            FileWriter fw = new FileWriter(path);
            fw.write(root.toString(2));
            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving DB: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error serializing DB: " + e.getMessage());
        }
    }
}
