public class S16CountPalindromeSubsequences {
    static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    static int generateSequence(String s, int index, String currSeq) {
        if (index == s.length()) {
            return isPalindrome(currSeq) && currSeq.length() > 0 ? 1 : 0;
        }
        int res1 = generateSequence(s, index + 1, currSeq + s.charAt(index));
        int res2 = generateSequence(s, index + 1, currSeq);
        return res1 + res2;
    }

    static int countPalindromeSubsequences1(String s) {
        return generateSequence(s, 0, "");
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

    public static int countPalindromeSubsequences2(String s) {
        int[][] memo = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return memoizationCount(s, 0, s.length() - 1, memo);
    }

    public static int countPalindromeSubsequences3(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int length = 2; length <= s.length(); length++) {
            for (int i = 0; i <= s.length() - length; i++) {
                int j = i + length - 1;
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
            int bruteForceResult = countPalindromeSubsequences1(test);
            int memoizedResult = countPalindromeSubsequences2(test);
            int dpResult = countPalindromeSubsequences3(test);

            System.out.println("\nInput: " + test);
            System.out.println("Brute Force Count: " + bruteForceResult);
            System.out.println("Memoized DP Count: " + memoizedResult);
            System.out.println("DP Count: " + dpResult);
        }

    }
}
