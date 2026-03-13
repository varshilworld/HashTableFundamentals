import java.util.*;

public class Problem1 {

    // Stores: Username -> UserID (To check existence)
    private HashMap<String, Integer> takenUsernames = new HashMap<>();

    // Stores: Username -> Number of attempts (To track popularity)
    private HashMap<String, Integer> attemptFrequency = new HashMap<>();

    // O(1) Time Complexity
    public boolean checkAvailability(String username) {
        // Track the attempt regardless of availability
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !takenUsernames.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        int suffix = 1;
        while (suggestions.size() < 3) {
            String candidate = username + suffix;
            if (!takenUsernames.containsKey(candidate)) {
                suggestions.add(candidate);
            }
            suffix++;
        }
        return suggestions;
    }

    // Method to register a taken username
    public void registerUsername(String username, int userId) {
        takenUsernames.put(username, userId);
    }

    // Method to get attempt frequency
    public int getAttemptFrequency(String username) {
        return attemptFrequency.getOrDefault(username, 0);
    }

    // Main method to test functionality
    public static void main(String[] args) {
        Problem1 app = new Problem1();

        // Register some usernames
        app.registerUsername("Alice", 1);
        app.registerUsername("Bob", 2);

        // Check availability
        System.out.println("Is 'Alice' available? " + app.checkAvailability("Alice"));
        System.out.println("Is 'Charlie' available? " + app.checkAvailability("Charlie"));

        // Suggest alternatives
        System.out.println("Suggestions for 'Alice': " + app.suggestAlternatives("Alice"));
        System.out.println("Suggestions for 'Charlie': " + app.suggestAlternatives("Charlie"));

        // Check attempt frequency
        System.out.println("Attempts for 'Alice': " + app.getAttemptFrequency("Alice"));
        System.out.println("Attempts for 'Charlie': " + app.getAttemptFrequency("Charlie"));
    }
}