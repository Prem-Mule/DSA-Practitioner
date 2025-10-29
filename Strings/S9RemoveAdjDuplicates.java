import java.util.Arrays;

public class S9RemoveAdjDuplicates {
    public static String removeDuplicates(String s) {
        int previousChar = -1;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            if (previousChar >= 0 && ans.charAt(previousChar) == s.charAt(i)) {

                ans.deleteCharAt(previousChar--);
            } else {
                ans.append(s.charAt(i));
                previousChar++;
            }
        }
        return ans.toString();
    }

    public static String removeDuplicates2(String s) {
        int index = -1;
        char[] ans = new char[s.length()];
        for (char c : s.toCharArray()) {
            if (index >= 0 && ans[index] == c) {
                index--;
            } else {
                ans[++index] = c;
            }
        }
        return String.copyValueOf(ans, 0, index + 1);
    }

    public static void main(String[] args) {
        String s = "abbaca";
        String result = removeDuplicates(s);
        System.out.println("Result after removing adjacent duplicates: " + result);
        String result2 = removeDuplicates2(s);
        System.out.println("Result after removing adjacent duplicates: " + result2);
    }
}
