public class LinePlane extends Plane {

    private int passengers;
    private int cabinCrew;

    public LinePlane(String name, String pilot, String coPilot, int passengers, int cabinCrew) {
        super(name, pilot, coPilot);
        setPassengers(passengers);
        setCabinCrew(cabinCrew);
    }

    public int getPassengers()            { return passengers; }
    public void setPassengers(int p)      { this.passengers = p; }

    public int getCabinCrew()             { return cabinCrew; }
    public void setCabinCrew(int c)       { this.cabinCrew = c; }

    @Override
    public double getTotWeight() { return passengers * 80.0; }
}
