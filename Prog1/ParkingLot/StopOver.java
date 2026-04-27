import java.util.Date;

public class StopOver {
    public StopOver(Vehicle vehicle, int position, double price) {
        this.start = new Date().getTime();
        this.vehicle = vehicle;
        this.position = position;
        this.price = price;
    }
    private long start;
    private long end;
    private Vehicle vehicle;
    private int position;
    private double price;

    public boolean isCar() {
        return this.vehicle instanceof Car;
    }

    public boolean isOver() {
        return this.end != 0;
    }

    public boolean isOngoing() {
        return !this.isOver();
    }

    public void exit() {
        this.end = new Date().getTime();
    }

    public double getPrice() {
        if (this.isOver()) {
            // return (this.end - this.start) * this.price * vehicle.getPriceMultiplier() / 3600000;
            return (this.end - this.start) * this.price * vehicle.getPriceMultiplier() / 3_600_000.0;
        }
        else {
            return 0;
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setCar(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
