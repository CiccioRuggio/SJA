
public class Parking {

    private StopOver stops[];

    private static int DEFAULT_SIZE = 10;

    public Parking() {
        this.stops = new StopOver[DEFAULT_SIZE];
    }

    public Parking(int size) {
        this.stops = new StopOver[size];
    }

    public boolean checkPlate(StopOver s) {
        for (StopOver stop : this.stops) {
            if (stop.getCar() != null && stop.getCar().getPlate() != s.getCar().getPlate()) {
                return true;
            }
        }
        return false;
    }

    public void addCar(StopOver stop, int place) {
        if (this.checkPlate(stop) || stop == null || place < 0 || place > this.stops.length || this.stops[place] != null) {
            return;
        } else {
            this.stops[place] = stop;
        }
    }

    public void removeCar(int place) {
        if (place < 0 || place > this.stops.length) {
            return;
        } else {
            this.stops[place] = null;
        }
    }

    public int freePlaces() {
        int count = 0;
        for (StopOver inside : this.stops) {
            if (inside == null) {
                count++;
            }
        }
        return count;
    }

    public int occupatedPlaces() {
        return this.stops.length - this.freePlaces();
    }

    public boolean isFull() {
        return this.freePlaces() == 0;
    }

    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = super.hashCode();
    //     result = prime * result + Arrays.hashCode(cars);
    //     return result;
    // }
    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj) {
    //         return true;
    //     }
    //     if (obj == null) {
    //         return false;
    //     }
    //     if (!super.equals(obj)) {
    //         return false;
    //     }
    //     if (getClass() != obj.getClass()) {
    //         return false;
    //     }
    //     Parking other = (Parking) obj;
    //     if (!Arrays.equals(cars, other.cars)) {
    //         return false;
    //     }
    //     return true;
    // }
    // public Parking(Car cars[]) {
    //     setCars(cars);
    // }
    // public Car[] getCars() {
    //     return this.cars;
    // }
    // public boolean checkPlate(Car c) {
    //     for (Car car : this.cars) {
    //         if (car != null && car.equals(c)) {
    //             return true;
    //         }
    //         return false;
    //     }
    //     return false;
    // }
    // public void setCars(Car[] cars) {
    //     if (cars.length == this.cars.length) {
    //         this.cars = cars;
    //         for (Car car : cars) {
    //             for (Car carInLot : this.cars) {
    //                 if (!carInLot.equals(car)) {
    //                 }
    //             }
    //         }
    //     }
    // }
}
