public class S14PalindromeSubstrings {

    public static int countSubstrings(String s) {
        int count = s.length();
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }

            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }
        }
        return count;
    }

    public static int countSubstrings2(String s) {
        int count = 0;
        char c = s.charAt(0);
        int j = 1;
        while (j < s.length() && c == s.charAt(j)) {
            j++;
        }
        if (j == s.length()) {
            return (s.length() * s.length() + s.length()) / 2;
        }
        for (int i = 0; i < s.length(); i++) {
            int left = i;
            int right = i + 1;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }
            left = i;
            right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }
        }
        return count;

    }

    public static void main(String[] args) {

        String[] testCases = { "abc", "aaa", "abba", "racecar", "noon", "abcd", "zvquhctuvpdcumqtvdjvzbodockbic" };

        System.out.println("=== Palindromic Substrings Count ===");
        for (String test : testCases) {
            System.out.println("\nInput: " + test);
            System.out.println("Count (Method 1): " + countSubstrings(test));
            System.out.println("Count (Method 2): " + countSubstrings2(test));
        }

    }
}
