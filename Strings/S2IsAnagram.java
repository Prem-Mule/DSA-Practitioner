import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class S2IsAnagram {
    static {
        for (int i = 0; i < 500; i++) {
            isAnagram1("", "a");
            isAnagram2("", "a");
            isAnagram3("", "a");
            isAnagram4("", "a");
        }
    }

    public static boolean isAnagram1(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (char a : s1.toCharArray()) {
            map1.put(a, map1.getOrDefault(a, 0) + 1);
        }
        for (char a : s2.toCharArray()) {
            map2.put(a, map2.getOrDefault(a, 0) + 1);
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Character a : map1.keySet()) {
            if (Integer.compare(map1.get(a), map2.get(a)) != 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnagram2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> count = new HashMap<>();
        for (Character c : s1.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for (Character c : s2.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) - 1);
        }

        for (Integer a : count.values()) {
            if (a != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram3(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    public static boolean isAnagram4(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 != l2) {
            return false;
        }
        int[] chars = new int[26];
        for (int i = 0; i < l1; i++) {
            chars[s1.charAt(i) - 'a']++;
            chars[s2.charAt(i) - 'a']--;
        }
        for (int a : chars) {
            if (a != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "casr";
        String s2 = "racs";
        System.out.println(isAnagram1(s1, s2));
        System.out.println(isAnagram2(s1, s2));
        System.out.println(isAnagram3(s1, s2));
        System.out.println(isAnagram4(s1, s2));
    }
}
