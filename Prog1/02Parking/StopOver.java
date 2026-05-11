
import java.time.LocalDateTime;

public class StopOver {
    private Car car;
    private LocalDateTime in;
    private LocalDateTime out = null;
    private int place;
    
    public StopOver(Car car, LocalDateTime in, int place) {
        this.car = car;
        this.in = in;
        this.out = null;
        this.place = place;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getIn() {
        return in;
    }

    public void setIn(LocalDateTime in) {
        this.in = in;
    }

    public LocalDateTime getOut() {
        return out;
    }

    public void setOut(LocalDateTime out) {
        this.out = out;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
    
}
