import java.io.*;
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

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> data) {
        inventory = data;
    }
}


/**
 * ==========================================================
 * CLASS - FilePersistenceService
 * ==========================================================
 */
class FilePersistenceService {

    // Save inventory to file
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    // Load inventory from file
    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        Map<String, Integer> data = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");
                if (parts.length == 2) {
                    data.put(parts[0], Integer.parseInt(parts[1]));
                }
            }

            inventory.setInventory(data);

        } catch (Exception e) {
            System.out.println("Error loading inventory. Starting fresh.");
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

        System.out.println("System Recovery");

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistence = new FilePersistenceService();

        // Load existing data
        persistence.loadInventory(inventory, filePath);

        // Display inventory
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Save data
        persistence.saveInventory(inventory, filePath);
    }
}