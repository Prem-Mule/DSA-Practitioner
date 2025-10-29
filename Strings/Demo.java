import java.util.Arrays;

public class Demo {
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int generateSubsequence(String s, int index, String curr) {
        if (index == s.length()) {
            return isPalindrome(curr) && curr.length() > 0 ? 1 : 0;
        }
        int res1 = generateSubsequence(s, index + 1, curr + s.charAt(index));
        int res2 = generateSubsequence(s, index + 1, curr);
        return res1 + res2;
    }

    public static int memoizationCount(String s, int i, int j, int[][] memo) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = 1 + memoizationCount(s, i + 1, j, memo) + memoizationCount(s, i, j - 1, memo);
        } else {
            memo[i][j] = memoizationCount(s, i + 1, j, memo) + memoizationCount(s, i, j - 1, memo)
                    - memoizationCount(s, i + 1, j - 1, memo);
        }
        return memo[i][j];
    }

    public static int countPal(String s) {
        int[][] memo = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        return memoizationCount(s, 0, s.length() - 1, memo);
    }

    public static int dpCount(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int size = 2; size <= s.length(); size++) {
            for (int i = 0; i <= s.length() - size; i++) {
                int j = i + size - 1;
                if (s.charAt(i) == s.charAt(j)) {

                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        String[] testCases = { "abc", "aba", "aaa", "abca", "geeksforgeeks", "103301" };

        System.out.println("=== Count Palindromic Subsequences ===");
        for (String test : testCases) {
            int bruteForceResult = generateSubsequence(test, 0, "");
            int memoizedResult = countPal(test);
            int dpresult = dpCount(test);

            System.out.println("\nInput: " + test);
            System.out.println("Brute Force Count: " + bruteForceResult);
            System.out.println("Memoized DP Count: " + memoizedResult);
            System.out.println("DP Count: " + dpresult);
        }
    }
}
