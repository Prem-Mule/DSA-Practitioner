public class S1IsValidPalindrome {
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (!Character.isLetter(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetter(s.charAt(right))) {
                right--;
                continue;
            }

            if (Character.toLowerCase(s.charAt(left++)) != Character.toLowerCase(s.charAt(right--))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] testStrings = { "yeiey", "Ra;ce Car", "hello", "madam", "12321" };

        for (String str : testStrings) {
            boolean result = isPalindrome(str);
            System.out.println("Is \"" + str + "\" a palindrome? " + result);
        }
    }
}
