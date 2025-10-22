import java.util.Arrays;
import java.util.Comparator;

public class S5LongestCommonPrefix {

    /**
     * Approach 1: Vertical Scanning
     * - Compare characters column by column across all strings.
     * - Time Complexity: O(n * m) (n = number of strings, m = length of shortest
     * string)
     * - Space Complexity: O(m) for StringBuilder
     */
    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        StringBuilder ans = new StringBuilder();
        out: for (int i = 0; i < strs[0].length(); i++) {
            char currentChar = strs[0].charAt(i);
            for (String s : strs) {
                if (i >= s.length() || s.charAt(i) != currentChar) {
                    break out;
                }
            }
            ans.append(currentChar);
        }
        return ans.toString();
    }

    /**
     * Approach 2: Sort by Length and Compare
     * - Sort strings by length, then compare characters of shortest string with
     * others.
     * - Time Complexity: O(n log n + n * m)
     * - Space Complexity: O(n) for sorting
     * - Less efficient than Approach 1 because sorting adds overhead.
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() < o2.length() ? -1 : 1;
            }
        });
        int i = 0;
        outerLoop: for (i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != strs[0].charAt(i)) {
                    break outerLoop;
                }
            }
        }
        return strs[0].substring(0, i);
    }

    /**
     * Approach 3: Optimized Lexicographical Sort
     * - Sort strings lexicographically and compare only first and last.
     * - Time Complexity: O(n log n + m)
     * - Space Complexity: O(n) for sorting
     * - Most optimized among these three approaches.
     */
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        Arrays.sort(strs);
        String firstWord = strs[0];
        String lastWord = strs[strs.length - 1];
        int i = 0;
        while (i < firstWord.length() && i < lastWord.length() && firstWord.charAt(i) == lastWord.charAt(i)) {
            i++;
        }
        return firstWord.substring(0, i);
    }

    public static void main(String[] args) {
        String[][] testCases = {
                { "flower", "flow", "flight" },
                { "dog", "racecar", "car" },
                { "interview", "internet", "internal" },
                { "a" },
                { "", "" },
                { "prefix", "prefixes", "pref" }
        };

        System.out.println("Testing Longest Common Prefix Approaches:");
        for (String[] test : testCases) {
            System.out.println("\nInput: " + Arrays.toString(test));
            System.out.println("Approach 1 (Vertical Scan): " + longestCommonPrefix1(test));
            System.out.println("Approach 2 (Sort by Length): " + longestCommonPrefix2(test));
            System.out.println("Approach 3 (Lexicographical Sort): " + longestCommonPrefix3(test));
        }
    }
}