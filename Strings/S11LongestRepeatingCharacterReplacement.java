import java.util.HashMap;
import java.util.Map;

public class S11LongestRepeatingCharacterReplacement {

    /**
     * Approach: Sliding Window + Frequency Map
     * -----------------------------------------
     * - Use two pointers (left and right) to maintain a window.
     * - Track frequency of characters in the current window using a HashMap.
     * - Maintain `maxCount` = count of the most frequent character in the window.
     * - If (window size - maxCount) > k, shrink the window from the left.
     * - Update maxLength after each iteration.
     *
     * Why (window size - maxCount)?
     * - It represents the number of characters we need to replace to make all
     * characters in the window the same.
     *
     * Time Complexity: O(n) → Each character processed once.
     * Space Complexity: O(26) → For uppercase letters (constant space).
     */
    public static int characterReplacement(String s, int k) {
        int left = 0, maxLength = 0, maxCount = 0;
        Map<Character, Integer> charCount = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            // Update maxCount for the most frequent character in the window
            maxCount = Math.max(maxCount, charCount.get(currentChar));

            // If replacements needed exceed k, shrink window
            while ((right - left + 1) - maxCount > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                left++;
            }

            // Update maxLength
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    /**
     * Demo method: Same logic, written for clarity and debugging.
     */
    public static int characterReplacement2(String s, int k) {
        int left = 0, maxLength = 0, maxCount = 0;
        Map<Character, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            maxCount = Math.max(maxCount, freqMap.get(ch));

            int windowSize = right - left + 1;
            if (windowSize - maxCount > k) {
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static int characterReplacement3(String s, int k) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int maxFrequentCharCount = 0;
        int[] charsCount = new int[26];
        for (right = 0; right < s.length(); right++) {
            int currentCharCount = ++charsCount[s.charAt(right) - 'A'];
            maxFrequentCharCount = Math.max(currentCharCount, maxFrequentCharCount);
            int windowSize = right - left + 1;
            if (windowSize - maxFrequentCharCount > k) {
                charsCount[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String[] testCases = { "ABAB", "AABABBA", "AAAA", "ABBB", "ABCABC", "" };
        int k = 2;

        System.out.println("=== Longest Repeating Character Replacement ===");
        for (String test : testCases) {
            System.out.println("\nInput: \"" + test + "\", k = " + k);
            System.out.println("Approach 1 (characterReplacement): " + characterReplacement(test, k));
            System.out.println("Approach 2 (demo): " + characterReplacement2(test, k));
            System.out.println("Approach 3 (sdfdemo): " + characterReplacement3(test, k));
        }
    }
}