import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class S8PrintAllDuplicatesInString {
    public static void printAllDuplicates1(String str) {
        int[] charCount = new int[256];
        for (char c : str.toCharArray()) {
            charCount[c]++;
        }
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 1) {
                System.out.println((char) i + " " + charCount[i]);
            }
        }
    }

    public static void printAllDuplicates2(String str) {
        int[] charCount = new int[26];
        for (char c : str.toCharArray()) {
            charCount[Character.toLowerCase(c) - 'a']++;
        }
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 1) {
                System.out.println((char) (i + 'a') + ": " + charCount[i]);
            }
        }
    }

    public static void printAllDuplicatesOptimized(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Print duplicates sorted by frequency in descending order
        freqMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }

    public static void main(String[] args) {

        String str1 = "geeksforgeeks";
        printAllDuplicates1(str1);
        System.out.println("******************");
        printAllDuplicates2(str1);
        System.out.println("******************");
        printAllDuplicatesOptimized(str1);

    }
}
