public class CargoPlane extends Plane {

    private float cargoWeight;

    public CargoPlane(String name, String pilot, String coPilot, float cargoWeight) {
        super(name, pilot, coPilot);
        setCargoWeight(cargoWeight);
    }

    public float getCargoWeight()        { return cargoWeight; }
    public void setCargoWeight(float cw) { this.cargoWeight = cw; }

    @Override
    public double getTotWeight() { return cargoWeight; }
}
