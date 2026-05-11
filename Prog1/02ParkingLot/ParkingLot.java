
import java.util.ArrayList;

public class ParkingLot {
    private int DEFAULT_SIZE = 10;
    private double DEFAULT_PRICE = 1;
    private double pricePerMinute = DEFAULT_PRICE;
    private int places = DEFAULT_SIZE;
    private ArrayList<StopOver> Stops = new ArrayList();

    public ParkingLot(int places) {
        this.places = places;
    }

    public boolean addVehicle(Vehicle vehicle, int position) {
        for (StopOver s : Stops) {
            if (s.isOngoing() && s.getPosition() == position) {
                return false;
            }
        }
        this.Stops.add(new StopOver(vehicle, position, this.pricePerMinute));
        return true;
    }

    public boolean addCar(Car car, int position) {
        for (StopOver s : Stops) {
            if (s.isOngoing() && s.getPosition() == position) {
                return false;
            }
        }
        this.Stops.add(new StopOver(car, position, this.pricePerMinute));
        return true;
    }

    public void exitCar(String plate) {
        for (StopOver s : Stops) {
            if (s.getVehicle().getPlate() == plate) {
                s.exit();
            }
        }
    }

    public int getAvailableSlots() {
        int busy = 0;
        for (StopOver s : this.Stops) {
            if (s.isOngoing()) {
                busy++;
            }
        }
        return this.places - busy;
    }

    public boolean isFull() {
        return this.getAvailableSlots() == 0;
    }

    public double getAmount() {
        double amount = 0;
        for (StopOver s : Stops) {
            amount += s.getPrice();
        }
        return amount;
    }
}


// aggiungere modifica per furgoni, pagano il doppio, o in base alla lunghezza del mezzo
