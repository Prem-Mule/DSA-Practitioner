import java.util.*;

/**
 * S12GroupAnagrams
 * 
 * This class demonstrates three approaches to group anagrams:
 * 1. Sorting-based grouping
 * 2. HashMap with sorted string as key
 * 3. HashMap with character frequency as key
 */
public class S12GroupAnagrams {

    /**
     * Approach 1: Sort input array and group adjacent anagrams
     * Time Complexity: O(n * k log k) for sorting each string and array
     * Space Complexity: O(n * k) for storing groups
     */
    public static List<List<String>> groupAnagramsBySorting(String[] words) {
        List<List<String>> result = new ArrayList<>();

        // Sort entire array based on sorted characters of each word
        Arrays.sort(words, (w1, w2) -> {
            char[] c1 = w1.toCharArray();
            char[] c2 = w2.toCharArray();
            Arrays.sort(c1);
            Arrays.sort(c2);
            return Arrays.compare(c1, c2);
        });

        // Group adjacent anagrams
        for (int i = 0; i < words.length; i++) {
            char[] currentChars = words[i].toCharArray();
            Arrays.sort(currentChars);

            List<String> group = new ArrayList<>();
            group.add(words[i]);

            while (i + 1 < words.length && words[i].length() == words[i + 1].length()) {
                char[] nextChars = words[i + 1].toCharArray();
                Arrays.sort(nextChars);

                if (Arrays.compare(currentChars, nextChars) == 0) {
                    group.add(words[i + 1]);
                    i++;
                } else {
                    break;
                }
            }
            result.add(group);
        }
        return result;
    }

    /**
     * Approach 2: Use HashMap with sorted string as key
     * Time Complexity: O(n * k log k)
     * Space Complexity: O(n * k)
     */
    public static List<List<String>> groupAnagramsUsingSortedKey(String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Approach 3: Use HashMap with character frequency as key
     * Time Complexity: O(n * k) (no sorting, just counting)
     * Space Complexity: O(n * k)
     */
    // Approach 3 (Frequency Array) is the best in terms of performance:
    public static List<List<String>> groupAnagramsUsingFrequencyKey(String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            // Build key from frequency array
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : freq) {
                keyBuilder.append('#').append(count);
            }
            String key = keyBuilder.toString();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] testCases = { "eat", "tea", "tan", "ate", "nat", "bat" };

        System.out.println("=== Group Anagrams ===");
        System.out.println("Input: " + Arrays.toString(testCases));

        // Approach 1
        System.out.println("\nApproach 1: Sorting-based");
        List<List<String>> result1 = groupAnagramsBySorting(testCases);
        result1.forEach(System.out::println);

        // Approach 2
        System.out.println("\nApproach 2: HashMap with Sorted Key");
        List<List<String>> result2 = groupAnagramsUsingSortedKey(testCases);
        result2.forEach(System.out::println);

        // Approach 3
        System.out.println("\nApproach 3: HashMap with Frequency Key");
        List<List<String>> result3 = groupAnagramsUsingFrequencyKey(testCases);
        result3.forEach(System.out::println);

    }
}