import java.util.HashMap;
import java.util.Map;

public class S6ConvertStringToNumPadEquaivalent {

    public static String convertStringToNumpad1(String s) {
        StringBuilder ans = new StringBuilder();
        s = s.toUpperCase();

        for (char c : s.toCharArray()) {
            if (c < 'A' || c > 'Z')
                continue; // Ignore non-alphabet characters

            int as = c - 'A'; // Position in alphabet
            int key;
            int count;

            if (as <= 14) { // A–O (keys 2–6)
                key = 2 + (as / 3);
                count = (as % 3) + 1;
            } else if (as <= 18) { // P–S
                key = 7;
                count = as - 14;
            } else if (as <= 21) { // T–V
                key = 8;
                count = as - 18;
            } else { // W–Z
                key = 9;
                count = as - 21;
            }

            ans.append(String.valueOf(key).repeat(count));
        }
        return ans.toString();
    }

    public static String convertStringToNumpad2(String s) {
        StringBuilder ans = new StringBuilder();
        Map<Character, String> numpadMap = Map.ofEntries(
                Map.entry('A', "2"), Map.entry('B', "22"), Map.entry('C', "222"),
                Map.entry('D', "3"), Map.entry('E', "33"), Map.entry('F', "333"),
                Map.entry('G', "4"), Map.entry('H', "44"), Map.entry('I', "444"),
                Map.entry('J', "5"), Map.entry('K', "55"), Map.entry('L', "555"),
                Map.entry('M', "6"), Map.entry('N', "66"), Map.entry('O', "666"),
                Map.entry('P', "7"), Map.entry('Q', "77"), Map.entry('R', "777"), Map.entry('S', "7777"),
                Map.entry('T', "8"), Map.entry('U', "88"), Map.entry('V', "888"),
                Map.entry('W', "9"), Map.entry('X', "99"), Map.entry('Y', "999"), Map.entry('Z', "9999"));

        for (char c : s.toCharArray()) {
            if (numpadMap.containsKey(c)) {
                ans.append(numpadMap.get(c));
            }
        }
        return ans.toString();

    }

    public static String convertStringToNumpad3(String s) {
        StringBuilder ans = new StringBuilder();
        String[] numpadMap = new String[] { "2", "22", "222",
                "3", "33", "333",
                "4", "44", "444",
                "5", "55", "555",
                "6", "66", "666",
                "7", "77", "777", "7777",
                "8", "88", "888",
                "9", "99", "999", "9999" };
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                ans.append(numpadMap[c - 'A']);
            }
        }
        return ans.toString();

    }

    public static void main(String[] args) {
        System.out.println(convertStringToNumpad1("GEEKSFORGEEKS"));
        System.out.println(convertStringToNumpad2("GEEKSFORGEEKS"));
        System.out.println(convertStringToNumpad3("GEEKSFORGEEKS"));

    }
}
