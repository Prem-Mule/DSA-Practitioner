public class S4RemoveConsecutiveCharacters {

    /**
     * Approach 1: Iterative using StringBuilder
     * - Time Complexity: O(n)
     * - Space Complexity: O(n)
     * - Efficient and simple, uses StringBuilder for fast concatenation.
     */
    public static String removeConsCharacters1(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            // Skip if current char is same as previous
            if (i > 0 && s.charAt(i - 1) == s.charAt(i)) {
                continue;
            } else {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    /**
     * Approach 2: Recursive using StringBuilder
     * - Time Complexity: O(n)
     * - Space Complexity: O(n) + O(n) recursion stack
     * - Less efficient due to recursion overhead, but demonstrates recursion.
     */
    public static String removeConsCharacters2(String s, int index, StringBuilder ans) {
        if (index == s.length()) {
            return ans.toString();
        }
        if (index == 0) {
            ans.append(s.charAt(index));
        } else {
            if (s.charAt(index) != s.charAt(index - 1)) {
                ans.append(s.charAt(index));
            }
        }
        return removeConsCharacters2(s, index + 1, ans);
    }

    /**
     * Approach 3: In-place using char array
     * - Time Complexity: O(n)
     * - Space Complexity: O(n) (char array)
     * - Most optimized: No extra dynamic structure, fewer allocations.
     */
    public static String removeConsCharacters3(String s) {
        char[] arr = s.toCharArray();
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                arr[j++] = arr[i]; // Overwrite duplicates
            }
        }
        return String.copyValueOf(arr, 0, j);
    }

    public static void main(String[] args) {
        String[] testCases = {
                "abcddcba", // Normal case
                "aabbcc", // Multiple consecutive duplicates
                "aaaaa", // All same characters
                "ababa", // No consecutive duplicates
                "", // Empty string
                "a" // Single character
        };

        System.out.println("Testing Remove Consecutive Characters:");
        for (String str : testCases) {
            System.out.println("\nInput: " + str);
            System.out.println("Approach 1 (Iterative): " + removeConsCharacters1(str));
            System.out.println("Approach 2 (Recursive): " + removeConsCharacters2(str, 0, new StringBuilder()));
            System.out.println("Approach 3 (In-place):  " + removeConsCharacters3(str));
        }
    }
}