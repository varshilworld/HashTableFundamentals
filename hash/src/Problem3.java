import java.util.*;

public class Problem3 {

    // Inner class to represent a cache entry
    static class CacheEntry {
        String ipAddress;
        long expiryTime; // in milliseconds

        CacheEntry(String ipAddress, long ttlSeconds) {
            this.ipAddress = ipAddress;
            this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    private final int maxCapacity;
    private final long defaultTTL; // seconds
    private int hits = 0;
    private int misses = 0;
    private long totalLookupTime = 0;

    // LinkedHashMap with accessOrder=true for LRU eviction
    private final Map<String, CacheEntry> cache;

    public Problem3(int maxCapacity, long defaultTTL) {
        this.maxCapacity = maxCapacity;
        this.defaultTTL = defaultTTL;
        this.cache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
                return size() > Problem3.this.maxCapacity;
            }
        };
    }

    // Simulated upstream DNS query (returns dummy IPs)
    private String queryUpstream(String domain) {
        try {
            Thread.sleep(100); // simulate 100ms upstream delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Generate a pseudo IP based on domain hash
        int hash = Math.abs(domain.hashCode() % 255);
        return "172.217.14." + hash;
    }

    public String resolve(String domain) {
        long start = System.nanoTime();
        CacheEntry entry = cache.get(domain);

        if (entry != null && !entry.isExpired()) {
            hits++;
            long duration = (System.nanoTime() - start) / 1_000_000; // ms
            totalLookupTime += duration;
            return "Cache HIT: " + entry.ipAddress + " (retrieved in " + duration + "ms)";
        } else {
            misses++;
            String ip = queryUpstream(domain);
            cache.put(domain, new CacheEntry(ip, defaultTTL));
            long duration = (System.nanoTime() - start) / 1_000_000; // ms
            totalLookupTime += duration;
            if (entry != null && entry.isExpired()) {
                return "Cache EXPIRED -> Upstream: " + ip + " (TTL reset)";
            }
            return "Cache MISS -> Upstream: " + ip;
        }
    }

    public String getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        double avgLookup = total == 0 ? 0 : (totalLookupTime * 1.0 / total);
        return String.format("Hit Rate: %.2f%%, Avg Lookup Time: %.2fms", hitRate, avgLookup);
    }

    // Demo
    public static void main(String[] args) throws InterruptedException {
        Problem3 dns = new Problem3(5, 3); // capacity=5, TTL=3s

        System.out.println(dns.resolve("google.com"));   // MISS
        System.out.println(dns.resolve("google.com"));   // HIT
        Thread.sleep(3100); // wait > TTL
        System.out.println(dns.resolve("google.com"));   // EXPIRED -> MISS
        System.out.println(dns.resolve("yahoo.com"));    // MISS
        System.out.println(dns.resolve("bing.com"));     // MISS
        System.out.println(dns.resolve("duckduckgo.com"));// MISS
        System.out.println(dns.resolve("ask.com"));      // MISS (LRU eviction may occur)

        System.out.println("\n" + dns.getCacheStats());
    }
}