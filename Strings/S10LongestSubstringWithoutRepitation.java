import java.util.ArrayList;
import java.util.List;

public class S10LongestSubstringWithoutRepitation {

    /**
     * Approach 1: Sliding Window using List
     * --------------------------------------
     * - Maintain a dynamic list to store characters of the current substring.
     * - If a duplicate character is found, remove characters from the start until
     * the duplicate is removed.
     * - Update maxLength after each iteration.
     *
     * Time Complexity: O(n^2) -> Because 'contains' and 'remove' operations on List
     * take O(n)
     * Space Complexity: O(n) -> For storing substring characters
     */
    public static int findLongestSubstringUsingList(String input) {
        if (input.isEmpty())
            return 0;

        int maxLength = 0;
        List<Character> currentWindow = new ArrayList<>();

        for (char ch : input.toCharArray()) {
            if (currentWindow.contains(ch)) {
                int index = currentWindow.indexOf(ch);
                for (int i = 0; i <= index; i++) {
                    currentWindow.remove(0);
                }
            }
            currentWindow.add(ch);
            maxLength = Math.max(maxLength, currentWindow.size());
        }
        return maxLength;
    }

    /**
     * Approach 2: Two Pointers (Brute Force)
     * ---------------------------------------
     * - Use two pointers (left and current) to define the window.
     * - Move 'left' forward when a duplicate is found.
     * - Track the longest substring.
     *
     * Time Complexity: O(n^2) -> Inner loop checks duplicates
     * Space Complexity: O(1)
     */
    public static int findLongestSubstringUsingTwoPointers(String input) {
        if (input.isEmpty())
            return 0;

        int left = 0, maxLength = 1;
        String longestSubstring = "";

        for (int current = 1; current < input.length(); current++) {
            for (int i = left; i < current; i++) {
                if (input.charAt(current) == input.charAt(i)) {
                    left = i + 1;
                    break;
                }
            }
            if (maxLength < current - left + 1) {
                longestSubstring = input.substring(left, current + 1);
                maxLength = current - left + 1;
            }
        }
        System.out.println("Longest Substring (Two Pointers): " + longestSubstring);
        return maxLength;
    }

    /**
     * Approach 3: Optimized Sliding Window using Index Array
     * -------------------------------------------------------
     * - Use an array to store last seen positions of characters.
     * - Move 'left' pointer to skip duplicates efficiently.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(128) -> For ASCII characters
     */
    public static int findLongestSubstringUsingSlidingWindow(String input) {
        if (input.isEmpty())
            return 0;

        int maxLength = 0, left = 0;
        int[] charPositions = new int[128];

        for (int right = 0; right < input.length(); right++) {
            left = Math.max(left, charPositions[input.charAt(right)]);
            maxLength = Math.max(maxLength, right - left + 1);
            charPositions[input.charAt(right)] = right + 1;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String[] testCases = { "abcdabcbb", "pwwkew", "au", "", "abcabcbb" };

        System.out.println("=== Longest Substring Without Repetition ===");
        for (String test : testCases) {
            System.out.println("\nInput: " + test);
            System.out.println("Approach 1 (List): " + findLongestSubstringUsingList(test));
            System.out.println("Approach 2 (Two Pointers): " + findLongestSubstringUsingTwoPointers(test));
            System.out.println("Approach 3 (Sliding Window): " + findLongestSubstringUsingSlidingWindow(test));
        }
    }
}