import java.util.*;

public class Problem5 {
    private Map<String, Integer> pageViews = new HashMap<>();
    private Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    private Map<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {
        // 1. Total Page Views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // 2. Unique Visitors (Set handles duplicates)
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);

        // 3. Traffic Sources
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void getDashboard() {
        System.out.println("\n--- Real-Time Dashboard ---");
        System.out.println("Top Pages:");

        // Using a PriorityQueue to get Top N elements efficiently
        PriorityQueue<Map.Entry<String, Integer>> topPages = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        topPages.addAll(pageViews.entrySet());

        for (int i = 0; i < 10 && !topPages.isEmpty(); i++) {
            Map.Entry<String, Integer> entry = topPages.poll();
            String url = entry.getKey();
            int total = entry.getValue();
            int unique = uniqueVisitors.get(url).size();
            System.out.println((i + 1) + ". " + url + " - " + total + " views (" + unique + " unique)");
        }

        System.out.println("\nTraffic Sources:");
        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " visits");
        }
    }

    // Demo
    public static void main(String[] args) {
        Problem5 analytics = new Problem5();

        // Simulate events
        analytics.processEvent("/home", "User1", "Google");
        analytics.processEvent("/home", "User2", "Google");
        analytics.processEvent("/about", "User1", "Direct");
        analytics.processEvent("/home", "User3", "Facebook");
        analytics.processEvent("/contact", "User2", "Google");
        analytics.processEvent("/home", "User1", "Google"); // repeat visitor

        // Show dashboard
        analytics.getDashboard();
    }
}