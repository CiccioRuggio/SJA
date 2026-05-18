import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        NationalFlightRegistry registry = DBManager.load("DB.txt");

        System.out.println("--- Filter by departure: CTA ---");
        System.out.println(registry.printFlights(registry.filterByDeparture("CTA")));

        System.out.println("--- Filter by departure + arrival: CTA -> FCO ---");
        System.out.println(registry.printFlights(registry.filterByDepartureAndArrival("CTA", "FCO")));

        System.out.println("--- Filter by departure + arrival + departure date: CTA -> NAP on 2026-07-20 ---");
        System.out.println(registry.printFlights(registry.filterByDepartureArrivalAndDepartureDate("CTA", "NAP", LocalDate.of(2026, 7, 20))));

        System.out.println("--- Filter by all fields: CTA -> MPX, dep 2026-05-20, arr 2026-05-20 ---");
        System.out.println(registry.printFlights(registry.filterByAllFields("CTA", "MPX", LocalDate.of(2026, 5, 20), LocalDate.of(2026, 5, 20))));
    }
}
