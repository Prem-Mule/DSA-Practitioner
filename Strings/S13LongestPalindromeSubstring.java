import java.util.ArrayList;
import java.util.List;

public class S13LongestPalindromeSubstring {

    public static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        String MaxString = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j) && MaxString.length() < (j - i + 1)) {
                    MaxString = s.substring(i, j + 1);
                }
            }
        }
        return MaxString;
    }

    public static String longestPalindrome2(String s) {

        if (s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }

        String maxString = s.charAt(0) + "";
        for (int i = 1; i < s.length() - 1; i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < s.length() && isPalindrome(s, left, right)) {
                if (maxString.length() < right - left + 1) {
                    maxString = s.substring(left, right + 1);
                }
                left--;
                right++;

            }
        }

        for (int i = 0; i < s.length() - 1; i++) {
            int left = i;
            int right = i + 1;
            while (left >= 0 && right < s.length() && isPalindrome(s, left, right)) {
                if (maxString.length() < right - left + 1) {
                    maxString = s.substring(left, right + 1);
                }
                left--;
                right++;

            }
        }
        return maxString;
    }

    public static String longestPalindrome3(String s) {
        String maxString = s.charAt(0) + "";
        for (int i = 0; i < s.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (maxString.length() < right - left + 1) {
                    maxString = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (maxString.length() < right - left + 1) {
                    maxString = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }
        return maxString;
    }

    public static void main(String[] args) {
        String[] testCases = { "babad", "cbbd", "a", "ac", "racecar", "noon", "forgeeksskeegfor" };

        System.out.println("=== Longest Palindromic Substring ===");
        for (String test : testCases) {
            System.out.println("\nInput: " + test);
            System.out.println("Longest Palindromic Substring: " + longestPalindrome(test));
            System.out.println("Longest Palindromic Substring: " + longestPalindrome2(test));
            System.out.println("Longest Palindromic Substring: " + longestPalindrome3(test));
        }
    }
}
