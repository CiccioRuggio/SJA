import java.util.ArrayList;

public class Airport {
    private ArrayList<Flight> flights = new ArrayList<>();

    public Airport() {
        
    }

    public ArrayList<Flight> getFlights()     { return this.flights; }

    public void addFlight(Flight f)           { flights.add(f); }

    public void removeFlight(int index) {
        if (index >= 0 && index < flights.size())
            flights.remove(index);
    }
}
