import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 5: Booking Request (FIFO)
 *
 * Demonstrates how booking requests are accepted
 * and processed in First-Come-First-Served order.
 *
 * @version 5.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        // Display application header
        System.out.println("Booking Request Queue");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {

            Reservation nextRequest = bookingQueue.getNextRequest();

            System.out.println(
                    "Processing booking for Guest: "
                            + nextRequest.getGuestName()
                            + ", Room Type: "
                            + nextRequest.getRoomType()
            );
        }
    }
}


/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 *
 * Represents a booking request made by a guest.
 *
 * @version 5.0
 */

class Reservation {

    /** Name of the guest making the booking. */
    private String guestName;

    /** Requested room type. */
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * ============================================================
 * CLASS - BookingRequestQueue
 * ============================================================
 *
 * Manages booking requests using FIFO queue.
 *
 * @version 5.0
 */

class BookingRequestQueue {

    /** Queue storing booking requests */
    private Queue<Reservation> requestQueue;

    /** Initializes empty queue */
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /** Adds booking request to queue */
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    /** Retrieves next request */
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    /** Checks if requests are pending */
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}