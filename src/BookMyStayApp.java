import java.util.*;

/**
 * ==========================================================
 * CLASS - RoomInventory
 * ==========================================================
 */
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void increaseRoom(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}


/**
 * ==========================================================
 * CLASS - BookingCancellationService
 * ==========================================================
 */
class BookingCancellationService {

    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomMap;

    public BookingCancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomMap = new HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation request.");
            return;
        }

        String roomType = reservationRoomMap.get(reservationId);

        // Push to stack (rollback)
        releasedRoomIds.push(reservationId);

        // Restore inventory
        inventory.increaseRoom(roomType);

        // Remove booking
        reservationRoomMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");

        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
        }
    }
}


/**
 * ==========================================================
 * MAIN CLASS - BookMyStayApp
 * ==========================================================
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        BookingCancellationService service = new BookingCancellationService();

        // Simulate confirmed booking
        String reservationId = "Single-1";
        String roomType = "Single";

        service.registerBooking(reservationId, roomType);

        // Cancel booking
        service.cancelBooking(reservationId, inventory);

        // Show rollback
        service.showRollbackHistory();

        // Show updated inventory
        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getAvailability("Single"));
    }
}