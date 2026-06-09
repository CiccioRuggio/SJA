
public class Truck extends Vehicle {

    private int length = 4;

    public Truck(String plate) {
        super(plate);
    }
    public Truck(String plate, int length) {
        super(plate);
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public double getPriceMultiplier() {
        return super.getPriceMultiplier() * (this.length / 2);
    }
}
