import java.util.*;

/**
 * AddOnService class
 */
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}


/**
 * AddOnServiceManager class
 */
class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) return 0.0;

        double total = 0;
        for (AddOnService s : services) {
            total += s.getCost();
        }

        return total;
    }
}


/**
 * MAIN CLASS
 */
class BookMyStayApp {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Spa", 700));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 300));

        double total = manager.calculateTotalServiceCost(reservationId);

        // EXACT REQUIRED OUTPUT
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + total);
    }
}