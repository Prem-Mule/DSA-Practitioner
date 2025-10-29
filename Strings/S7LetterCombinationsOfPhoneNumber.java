import java.util.ArrayList;
import java.util.List;


public class S7LetterCombinationsOfPhoneNumber {
    public static List<String> backtrack(String digits, String[] digitChars, int index, StringBuilder combination,
            List<String> result) {
        if (index == digits.length()) {
            result.add(combination.toString());
            return result;
        }

        String letters = digitChars[(int) digits.charAt(index) - '2'];
        for (char c : letters.toCharArray()) {
            backtrack(digits, digitChars, index + 1, combination.append(c), result);
            combination.deleteCharAt(combination.length() - 1);
        }
        return result;
    }

    public static List<String> letterCombinations(String s) {
        List<String> result = new ArrayList<>();
        String[] digitChars = new String[] { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        backtrack(s, digitChars, 0, new StringBuilder(), result);
        return result;
    }

    public static void main(String[] args) {

        String[] testCases = { "23", "7", "234", "" };

        System.out.println("Testing Letter Combinations of Phone Number:");
        for (String digits : testCases) {
            System.out.println("\nInput: " + digits);
            List<String> combinations = letterCombinations(digits);
            System.out.println("Output: " + combinations);
        }

    }
}
