public class Flight {
    private Plane plane;
    private String departure;
    private String arrival;
    private long timeArrival;
    private long timeDeparture;

    public Flight(Plane plane, String departure, String arrival, long timeDeparture, long timeArrival) {
        setPlane(plane);
        setDeparture(departure);
        setArrival(arrival);
        setTimeDeparture(timeDeparture);
        setTimeArrival(timeArrival);
    }

    public Plane getPlane() {
        return plane;
    }
    
    public String getDeparture() {
        return departure;
    }
    
    public String getArrival() {
        return arrival;
    }
    
    public long getTimeDeparture() {
        return timeDeparture;
    }
    
    public long getTimeArrival() {
        return timeArrival;
    }

    private void setPlane(Plane plane) {
        this.plane = plane;
    }
    
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setTimeDeparture(long timeDeparture) {
        if (this.timeArrival > 0 && timeDeparture >= this.timeArrival)
            throw new IllegalArgumentException("Departure must be before arrival.");
        this.timeDeparture = timeDeparture;
    }

    public void setTimeArrival(long timeArrival) {
        if (this.timeDeparture > 0 && timeArrival <= this.timeDeparture)
            throw new IllegalArgumentException("Arrival must be after departure.");
        this.timeArrival = timeArrival;
    }
}
