import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    // ===================== PROBLEM 6: RATE LIMITER =====================
    static class TokenBucket {
        int tokens, maxTokens, refillRate;
        long lastRefill;
        // ===================== PROBLEM 1 =====================
        static class Transaction {
            int id;
            double fee;
            String time;

            TokenBucket(int maxTokens, int refillRate) {
                this.maxTokens = maxTokens;
                this.refillRate = refillRate;
                this.tokens = maxTokens;
                this.lastRefill = System.currentTimeMillis();
                Transaction(int id, double fee, String time) {
                    this.id = id;
                    this.fee = fee;
                    this.time = time;
                }
            }

            synchronized boolean allowRequest() {
                long now = System.currentTimeMillis();
                long seconds = (now - lastRefill) / 1000;
                if (seconds > 0) {
                    tokens = Math.min(maxTokens, tokens + (int)(seconds * refillRate));
                    lastRefill = now;
                }
                if (tokens > 0) {
                    tokens--;
                    return true;
                    static void bubbleSortFee(List<Transaction> list) {
                        int n = list.size();
                        for (int i = 0; i < n - 1; i++) {
                            boolean swapped = false;
                            for (int j = 0; j < n - i - 1; j++) {
                                if (list.get(j).fee > list.get(j + 1).fee) {
                                    Collections.swap(list, j, j + 1);
                                    swapped = true;
                                }
                            }
                            return false;
                            if (!swapped) break;
                        }
                    }

                    static class RateLimiter {
                        ConcurrentHashMap<String, TokenBucket> map = new ConcurrentHashMap<>();

                        boolean check(String clientId) {
                            map.putIfAbsent(clientId, new TokenBucket(1000, 1000 / 3600));
                            return map.get(clientId).allowRequest();
                            static void insertionSortTime(List<Transaction> list) {
                                for (int i = 1; i < list.size(); i++) {
                                    Transaction key = list.get(i);
                                    int j = i - 1;
                                    while (j >= 0 && list.get(j).time.compareTo(key.time) > 0) {
                                        list.set(j + 1, list.get(j));
                                        j--;
                                    }
                                    list.set(j + 1, key);
                                }
                            }

                            // ===================== PROBLEM 7: AUTOCOMPLETE =====================
                            static class TrieNode {
                                Map<Character, TrieNode> children = new HashMap<>();
                                Map<String, Integer> freqMap = new HashMap<>();
                                boolean isEnd;
                                static List<Transaction> highFee(List<Transaction> list) {
                                    List<Transaction> res = new ArrayList<>();
                                    for (Transaction t : list) if (t.fee > 50) res.add(t);
                                    return res;
                                }

                                static class Autocomplete {
                                    TrieNode root = new TrieNode();
                                    // ===================== PROBLEM 2 =====================
                                    static class Client {
                                        String name;
                                        int risk, balance;

                                        Client(String name, int risk, int balance) {
                                            this.name = name;
                                            this.risk = risk;
                                            this.balance = balance;
                                        }
                                    }

                                    void insert(String word) {
                                        TrieNode node = root;
                                        for (char c : word.toCharArray()) {
                                            node.children.putIfAbsent(c, new TrieNode());
                                            node = node.children.get(c);
                                            node.freqMap.put(word, node.freqMap.getOrDefault(word, 0) + 1);
                                            static void bubbleRiskAsc(List<Client> list) {
                                                for (int i = 0; i < list.size() - 1; i++) {
                                                    for (int j = 0; j < list.size() - i - 1; j++) {
                                                        if (list.get(j).risk > list.get(j + 1).risk) {
                                                            Collections.swap(list, j, j + 1);
                                                        }
                                                    }
                                                    node.isEnd = true;
                                                }
                                            }

                                            List<String> search(String prefix) {
                                                TrieNode node = root;
                                                for (char c : prefix.toCharArray()) {
                                                    if (!node.children.containsKey(c)) return new ArrayList<>();
                                                    node = node.children.get(c);
                                                    static void insertionRiskDesc(List<Client> list) {
                                                        for (int i = 1; i < list.size(); i++) {
                                                            Client key = list.get(i);
                                                            int j = i - 1;
                                                            while (j >= 0 &&
                                                                    (list.get(j).risk < key.risk ||
                                                                            (list.get(j).risk == key.risk && list.get(j).balance < key.balance))) {
                                                                list.set(j + 1, list.get(j));
                                                                j--;
                                                            }
                                                            PriorityQueue<Map.Entry<String, Integer>> pq =
                                                                    new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
                                                            pq.addAll(node.freqMap.entrySet());
                                                            List<String> res = new ArrayList<>();
                                                            int k = 10;
                                                            while (!pq.isEmpty() && k-- > 0) res.add(pq.poll().getKey());
                                                            return res;
                                                            list.set(j + 1, key);
                                                        }
                                                    }

                                                    // ===================== PROBLEM 8: PARKING LOT =====================
                                                    static class ParkingLot {
                                                        String[] table;
                                                        int size;
                                                        // ===================== PROBLEM 3 =====================
                                                        static class Trade {
                                                            int volume;

                                                            ParkingLot(int size) {
                                                                this.size = size;
                                                                table = new String[size];
                                                            }
                                                            Trade(int v) { volume = v; }
                                                        }

                                                        int hash(String plate) {
                                                            return Math.abs(plate.hashCode()) % size;
                                                            static void mergeSort(List<Trade> arr, int l, int r) {
                                                                if (l >= r) return;
                                                                int m = (l + r) / 2;
                                                                mergeSort(arr, l, m);
                                                                mergeSort(arr, m + 1, r);
                                                                merge(arr, l, m, r);
                                                            }

                                                            static void merge(List<Trade> arr, int l, int m, int r) {
                                                                List<Trade> temp = new ArrayList<>();
                                                                int i = l, j = m + 1;
                                                                while (i <= m && j <= r) {
                                                                    if (arr.get(i).volume <= arr.get(j).volume)
                                                                        temp.add(arr.get(i++));
                                                                    else temp.add(arr.get(j++));
                                                                }
                                                                while (i <= m) temp.add(arr.get(i++));
                                                                while (j <= r) temp.add(arr.get(j++));
                                                                for (int k = 0; k < temp.size(); k++) arr.set(l + k, temp.get(k));
                                                            }

                                                            int park(String plate) {
                                                                int idx = hash(plate);
                                                                int probes = 0;
                                                                while (table[idx] != null) {
                                                                    idx = (idx + 1) % size;
                                                                    probes++;
                                                                }
                                                                table[idx] = plate;
                                                                return idx;
                                                                static void quickSort(List<Trade> arr, int low, int high) {
                                                                    if (low < high) {
                                                                        int pi = partition(arr, low, high);
                                                                        quickSort(arr, low, pi - 1);
                                                                        quickSort(arr, pi + 1, high);
                                                                    }
                                                                }

                                                                void exit(String plate) {
                                                                    int idx = hash(plate);
                                                                    while (table[idx] != null) {
                                                                        if (table[idx].equals(plate)) {
                                                                            table[idx] = null;
                                                                            return;
                                                                        }
                                                                        idx = (idx + 1) % size;
                                                                        static int partition(List<Trade> arr, int low, int high) {
                                                                            int pivot = arr.get(high).volume;
                                                                            int i = low - 1;
                                                                            for (int j = low; j < high; j++) {
                                                                                if (arr.get(j).volume > pivot) {
                                                                                    i++;
                                                                                    Collections.swap(arr, i, j);
                                                                                }
                                                                            }
                                                                            Collections.swap(arr, i + 1, high);
                                                                            return i + 1;
                                                                        }

                                                                        // ===================== PROBLEM 9: TWO SUM VARIANTS =====================
                                                                        static class Transaction {
                                                                            int id, amount;
                                                                            String merchant, time;
                                                                            // ===================== PROBLEM 4 =====================
                                                                            static class Asset {
                                                                                String name;
                                                                                double ret;

                                                                                Transaction(int id, int amount, String merchant, String time) {
                                                                                    this.id = id;
                                                                                    this.amount = amount;
                                                                                    this.merchant = merchant;
                                                                                    this.time = time;
                                                                                    Asset(String n, double r) {
                                                                                        name = n;
                                                                                        ret = r;
                                                                                    }
                                                                                }

                                                                                static List<int[]> twoSum(List<Transaction> list, int target) {
                                                                                    Map<Integer, Integer> map = new HashMap<>();
                                                                                    List<int[]> res = new ArrayList<>();
                                                                                    for (Transaction t : list) {
                                                                                        if (map.containsKey(target - t.amount)) {
                                                                                            res.add(new int[]{map.get(target - t.amount), t.id});
                                                                                        }
                                                                                        map.put(t.amount, t.id);
                                                                                        static void mergeSortAssets(List<Asset> arr, int l, int r) {
                                                                                            if (l >= r) return;
                                                                                            int m = (l + r) / 2;
                                                                                            mergeSortAssets(arr, l, m);
                                                                                            mergeSortAssets(arr, m + 1, r);
                                                                                            mergeAssets(arr, l, m, r);
                                                                                        }

                                                                                        static void mergeAssets(List<Asset> arr, int l, int m, int r) {
                                                                                            List<Asset> temp = new ArrayList<>();
                                                                                            int i = l, j = m + 1;
                                                                                            while (i <= m && j <= r) {
                                                                                                if (arr.get(i).ret <= arr.get(j).ret)
                                                                                                    temp.add(arr.get(i++));
                                                                                                else temp.add(arr.get(j++));
                                                                                            }
                                                                                            return res;
                                                                                            while (i <= m) temp.add(arr.get(i++));
                                                                                            while (j <= r) temp.add(arr.get(j++));
                                                                                            for (int k = 0; k < temp.size(); k++) arr.set(l + k, temp.get(k));
                                                                                        }

                                                                                        static List<List<Integer>> kSum(int[] nums, int target, int k) {
                                                                                            List<List<Integer>> res = new ArrayList<>();
                                                                                            Arrays.sort(nums);
                                                                                            kSumHelper(nums, target, k, 0, new ArrayList<>(), res);
                                                                                            return res;
                                                                                            static void quickSortAssets(List<Asset> arr, int low, int high) {
                                                                                                if (low < high) {
                                                                                                    int pi = partitionAssets(arr, low, high);
                                                                                                    quickSortAssets(arr, low, pi - 1);
                                                                                                    quickSortAssets(arr, pi + 1, high);
                                                                                                }
                                                                                            }

                                                                                            static void kSumHelper(int[] nums, int target, int k, int start,
                                                                                            List<Integer> path, List<List<Integer>> res) {
                                                                                                if (k == 2) {
                                                                                                    int l = start, r = nums.length - 1;
                                                                                                    while (l < r) {
                                                                                                        int sum = nums[l] + nums[r];
                                                                                                        if (sum == target) {
                                                                                                            List<Integer> temp = new ArrayList<>(path);
                                                                                                            temp.add(nums[l]);
                                                                                                            temp.add(nums[r]);
                                                                                                            res.add(temp);
                                                                                                            l++; r--;
                                                                                                        } else if (sum < target) l++;
                                                                                                        else r--;
                                                                                                    }
                                                                                                } else {
                                                                                                    for (int i = start; i < nums.length; i++) {
                                                                                                        path.add(nums[i]);
                                                                                                        kSumHelper(nums, target - nums[i], k - 1, i + 1, path, res);
                                                                                                        path.remove(path.size() - 1);
                                                                                                        static int partitionAssets(List<Asset> arr, int low, int high) {
                                                                                                            double pivot = arr.get(high).ret;
                                                                                                            int i = low - 1;
                                                                                                            for (int j = low; j < high; j++) {
                                                                                                                if (arr.get(j).ret > pivot) {
                                                                                                                    i++;
                                                                                                                    Collections.swap(arr, i, j);
                                                                                                                }
                                                                                                            }
                                                                                                            Collections.swap(arr, i + 1, high);
                                                                                                            return i + 1;
                                                                                                        }

                                                                                                        // ===================== PROBLEM 10: MULTI LEVEL CACHE =====================
                                                                                                        static class LRUCache {
                                                                                                            int capacity;
                                                                                                            LinkedHashMap<String, String> map;

                                                                                                            LRUCache(int capacity) {
                                                                                                                this.capacity = capacity;
                                                                                                                this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
                                                                                                                    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                                                                                                                        return size() > capacity;
                                                                                                                    }
                                                                                                                };
                                                                                                            }
                                                                                                            // ===================== PROBLEM 5 =====================
                                                                                                            static int linearSearch(String[] arr, String target) {
                                                                                                                for (int i = 0; i < arr.length; i++)
                                                                                                                    if (arr[i].equals(target)) return i;
                                                                                                                return -1;
                                                                                                            }

                                                                                                            String get(String key) {
                                                                                                                return map.getOrDefault(key, null);
                                                                                                                static int binarySearch(String[] arr, String target) {
                                                                                                                    int l = 0, r = arr.length - 1;
                                                                                                                    while (l <= r) {
                                                                                                                        int m = (l + r) / 2;
                                                                                                                        if (arr[m].equals(target)) return m;
                                                                                                                        else if (arr[m].compareTo(target) < 0) l = m + 1;
                                                                                                                        else r = m - 1;
                                                                                                                    }
                                                                                                                    return -1;
                                                                                                                }

                                                                                                                void put(String key, String value) {
                                                                                                                    map.put(key, value);
                                                                                                                    // ===================== PROBLEM 6 =====================
                                                                                                                    static int floor(int[] arr, int target) {
                                                                                                                        int l = 0, r = arr.length - 1, res = -1;
                                                                                                                        while (l <= r) {
                                                                                                                            int m = (l + r) / 2;
                                                                                                                            if (arr[m] <= target) {
                                                                                                                                res = arr[m];
                                                                                                                                l = m + 1;
                                                                                                                            } else r = m - 1;
                                                                                                                        }
                                                                                                                        return res;
                                                                                                                    }

                                                                                                                    static class MultiLevelCache {
                                                                                                                        LRUCache l1 = new LRUCache(10000);
                                                                                                                        LRUCache l2 = new LRUCache(100000);
                                                                                                                        Map<String, String> db = new HashMap<>();

                                                                                                                        String get(String key) {
                                                                                                                            String val = l1.get(key);
                                                                                                                            if (val != null) return "L1 HIT";

                                                                                                                            val = l2.get(key);
                                                                                                                            if (val != null) {
                                                                                                                                l1.put(key, val);
                                                                                                                                return "L2 HIT -> Promoted";
                                                                                                                            }

                                                                                                                            val = db.get(key);
                                                                                                                            if (val != null) {
                                                                                                                                l2.put(key, val);
                                                                                                                                return "DB HIT -> Added to L2";
                                                                                                                            }

                                                                                                                            return "MISS";
                                                                                                                            static int ceil(int[] arr, int target) {
                                                                                                                                int l = 0, r = arr.length - 1, res = -1;
                                                                                                                                while (l <= r) {
                                                                                                                                    int m = (l + r) / 2;
                                                                                                                                    if (arr[m] >= target) {
                                                                                                                                        res = arr[m];
                                                                                                                                        r = m - 1;
                                                                                                                                    } else l = m + 1;
                                                                                                                                }
                                                                                                                                return res;
                                                                                                                            }

                                                                                                                            // ===================== MAIN =====================
                                                                                                                            public static void main(String[] args) {

                                                                                                                                RateLimiter rl = new RateLimiter();
                                                                                                                                System.out.println("RateLimiter: " + rl.check("abc"));
                                                                                                                                List<Transaction> tx = Arrays.asList(
                                                                                                                                        new Transaction(1, 10.5, "10:00"),
                                                                                                                                        new Transaction(2, 25.0, "09:30"),
                                                                                                                                        new Transaction(3, 5.0, "10:15")
                                                                                                                                );
                                                                                                                                bubbleSortFee(tx);
                                                                                                                                insertionSortTime(tx);

                                                                                                                                Autocomplete ac = new Autocomplete();
                                                                                                                                ac.insert("tutorial");
                                                                                                                                ac.insert("script");
                                                                                                                                ac.insert("download");
                                                                                                                                System.out.println("Autocomplete: " + ac.search("tu"));
                                                                                                                                List<Client> clients = Arrays.asList(
                                                                                                                                        new Client("A", 20, 1000),
                                                                                                                                        new Client("B", 50, 2000),
                                                                                                                                        new Client("C", 80, 500)
                                                                                                                                );
                                                                                                                                bubbleRiskAsc(clients);
                                                                                                                                insertionRiskDesc(clients);

                                                                                                                                ParkingLot pl = new ParkingLot(500);
                                                                                                                                int spot = pl.park("ABC123");
                                                                                                                                System.out.println("Parked at: " + spot);
                                                                                                                                List<Trade> trades = Arrays.asList(new Trade(500), new Trade(100), new Trade(300));
                                                                                                                                mergeSort(trades, 0, trades.size() - 1);
                                                                                                                                quickSort(trades, 0, trades.size() - 1);

                                                                                                                                List<Transaction> tx = Arrays.asList(
                                                                                                                                        new Transaction(1, 500, "A", "10:00"),
                                                                                                                                        new Transaction(2, 300, "B", "10:15"),
                                                                                                                                        new Transaction(3, 200, "C", "10:30")
                                                                                                                                        List<Asset> assets = Arrays.asList(
                                                                                                                                                new Asset("AAPL", 12),
                                                                                                                                                new Asset("TSLA", 8),
                                                                                                                                                new Asset("GOOG", 15)
                                                                                                                                        );
                                                                                                                                System.out.println("TwoSum: " + twoSum(tx, 500).size());
                                                                                                                                mergeSortAssets(assets, 0, assets.size() - 1);
                                                                                                                                quickSortAssets(assets, 0, assets.size() - 1);

                                                                                                                                String[] logs = {"accA", "accB", "accC"};
                                                                                                                                System.out.println(linearSearch(logs, "accB"));
                                                                                                                                System.out.println(binarySearch(logs, "accB"));

                                                                                                                                MultiLevelCache cache = new MultiLevelCache();
                                                                                                                                cache.db.put("video1", "data");
                                                                                                                                System.out.println("Cache: " + cache.get("video1"));
                                                                                                                                int[] risks = {10, 25, 50, 100};
                                                                                                                                System.out.println(floor(risks, 30));
                                                                                                                                System.out.println(ceil(risks, 30));
                                                                                                                            }
                                                                                                                        }