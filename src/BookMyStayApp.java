import java.util.*;

/**
 * ==========================================================
 * CLASS - InvalidBookingException
 * ==========================================================
 */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}


/**
 * ==========================================================
 * CLASS - RoomInventory (simple version for validation)
 * ==========================================================
 */
class RoomInventory {

    private Set<String> validRoomTypes;

    public RoomInventory() {
        validRoomTypes = new HashSet<>();
        validRoomTypes.add("Single");
        validRoomTypes.add("Double");
        validRoomTypes.add("Suite");
    }

    public boolean isValidRoomType(String roomType) {
        return validRoomTypes.contains(roomType);
    }
}


/**
 * ==========================================================
 * CLASS - ReservationValidator
 * ==========================================================
 */
class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}


/**
 * ==========================================================
 * CLASS - BookingRequestQueue (dummy for structure)
 * ==========================================================
 */
class BookingRequestQueue {
    // Not needed for this use case (placeholder)
}


/**
 * ==========================================================
 * MAIN CLASS - UseCase9ErrorHandlingValidation
 * ==========================================================
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        // Display header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            // Input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(guestName, roomType, inventory);

            // If valid
            System.out.println("Booking successful!");

        } catch (InvalidBookingException e) {
            // Handle validation error
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}