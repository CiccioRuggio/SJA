public abstract class Plane {
    protected String name;
    protected String pilot;
    protected String coPilot;

    public Plane(String name, String pilot, String coPilot) {
        setName(name);
        setPilot(pilot);
        setCoPilot(coPilot);
    }

    public void setName(String name)       { this.name = name; }
    public void setPilot(String pilot)     { this.pilot = pilot; }
    public void setCoPilot(String coPilot) { this.coPilot = coPilot; }

    public String getName()    { return name; }
    public String getPilot()   { return pilot; }
    public String getCoPilot() { return coPilot; }

    public abstract double getTotWeight();
}
