import java.util.concurrent.*;
import java.util.*;

public class Problem2 {

    // Thread-safe map for product stock levels
    private ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();

    // ProductID -> Queue of UserIDs (FIFO waiting list)
    private Map<String, Queue<String>> waitingLists = new HashMap<>();

    public String purchaseItem(String productId, String userId) {
        // compute() is atomic - prevents race conditions
        return inventory.compute(productId, (key, currentStock) -> {
            if (currentStock != null && currentStock > 0) {
                // Success path
                return currentStock - 1;
            } else {
                // Waiting list path
                addToWaitingList(productId, userId);
                return 0; // Stock stays at zero
            }
        }) > 0 ? "Success" : "Added to Waiting List";
    }

    private void addToWaitingList(String productId, String userId) {
        waitingLists.computeIfAbsent(productId, k -> new LinkedList<>()).add(userId);
    }

    // Method to add stock
    public void addStock(String productId, int quantity) {
        inventory.put(productId, inventory.getOrDefault(productId, 0) + quantity);
    }

    // Method to view waiting list
    public Queue<String> getWaitingList(String productId) {
        return waitingLists.getOrDefault(productId, new LinkedList<>());
    }

    // Main method to test functionality
    public static void main(String[] args) {
        Problem2 store = new Problem2();

        // Add stock
        store.addStock("Laptop", 2);

        // Users try to purchase
        System.out.println("User1 purchase: " + store.purchaseItem("Laptop", "User1"));
        System.out.println("User2 purchase: " + store.purchaseItem("Laptop", "User2"));
        System.out.println("User3 purchase: " + store.purchaseItem("Laptop", "User3")); // Should go to waiting list

        // Show waiting list
        System.out.println("Waiting list for Laptop: " + store.getWaitingList("Laptop"));
    }
}