import java.util.*;

public class Problem4 {

    // n-gram size (e.g., 5 or 7 words)
    private final int N;
    // Map: n-gram -> set of document IDs containing it
    private final Map<String, Set<String>> ngramIndex = new HashMap<>();
    // Store documents for reference
    private final Map<String, List<String>> documents = new HashMap<>();

    // Constructor name must match class name
    public Problem4(int n) {
        this.N = n;
    }

    // Add a document to the database
    public void addDocument(String docId, String content) {
        List<String> words = tokenize(content);
        documents.put(docId, words);

        List<String> ngrams = extractNGrams(words);
        for (String ng : ngrams) {
            ngramIndex.computeIfAbsent(ng, k -> new HashSet<>()).add(docId);
        }
        System.out.println("Added " + docId + " with " + ngrams.size() + " n-grams.");
    }

    // Analyze a new document against the database
    public void analyzeDocument(String docId, String content) {
        List<String> words = tokenize(content);
        List<String> ngrams = extractNGrams(words);

        Map<String, Integer> matchCounts = new HashMap<>();

        for (String ng : ngrams) {
            if (ngramIndex.containsKey(ng)) {
                for (String existingDoc : ngramIndex.get(ng)) {
                    matchCounts.put(existingDoc, matchCounts.getOrDefault(existingDoc, 0) + 1);
                }
            }
        }

        System.out.println("Analyzing " + docId + ": Extracted " + ngrams.size() + " n-grams.");
        for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
            String existingDoc = entry.getKey();
            int matches = entry.getValue();
            double similarity = (matches * 100.0) / ngrams.size();
            System.out.printf("→ Found %d matching n-grams with %s. Similarity: %.2f%% %s%n",
                    matches, existingDoc, similarity,
                    similarity > 50 ? "(PLAGIARISM DETECTED)" : (similarity > 15 ? "(Suspicious)" : ""));
        }
    }

    // Tokenize text into words
    private List<String> tokenize(String text) {
        return Arrays.asList(text.toLowerCase().split("\\s+"));
    }

    // Extract n-grams
    private List<String> extractNGrams(List<String> words) {
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i <= words.size() - N; i++) {
            String ng = String.join(" ", words.subList(i, i + N));
            ngrams.add(ng);
        }
        return ngrams;
    }

    // Demo
    public static void main(String[] args) {
        Problem4 detector = new Problem4(5); // use 5-grams

        detector.addDocument("essay_089.txt", "Artificial intelligence is transforming industries rapidly and efficiently.");
        detector.addDocument("essay_092.txt", "The quick brown fox jumps over the lazy dog multiple times in the forest.");

        detector.analyzeDocument("essay_123.txt", "The quick brown fox jumps over a lazy dog in the park.");
    }
}