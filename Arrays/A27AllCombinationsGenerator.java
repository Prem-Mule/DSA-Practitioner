import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A27AllCombinationsGenerator {

    /**
     * Generates all combinations of size r using backtracking.
     *
     * @param arr                Input array
     * @param index              Current index in arr
     * @param r                  Required combination size
     * @param currentCombination Temporary list storing current combination
     * @param allCombinations    Final list storing all valid combinations
     */
    public static void generateCombinationsBacktrack(int[] arr, int index, int r,
            List<Integer> currentCombination,
            List<List<Integer>> allCombinations) {
        // If current combination has reached size r, add it to result
        if (currentCombination.size() >= r) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        // Iterate through remaining elements
        for (int i = index; i < arr.length; i++) {
            currentCombination.add(arr[i]); // Include current element
            generateCombinationsBacktrack(arr, i + 1, r, currentCombination, allCombinations);
            currentCombination.remove(currentCombination.size() - 1); // Backtrack
        }
    }

    /**
     * Prints all combinations of size r using backtracking method.
     */
    public static void printCombinationsBacktrack(int[] arr, int r) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        generateCombinationsBacktrack(arr, 0, r, currentCombination, allCombinations);

        for (List<Integer> combination : allCombinations) {
            System.out.println(combination);
        }
    }

    /**
     * Generates all combinations of size r using include/exclude recursion.
     *
     * @param arr                Input array
     * @param index              Current index in arr
     * @param r                  Required combination size
     * @param allCombinations    Final list storing all valid combinations
     * @param currentCombination Temporary list storing current combination
     */
    public static void generateCombinationsIncludeExclude(int[] arr, int index, int r,
            List<List<Integer>> allCombinations,
            List<Integer> currentCombination) {
        // If current combination has reached size r, add it to result
        if (currentCombination.size() >= r) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        // If index exceeds array length, stop recursion
        if (index >= arr.length) {
            return;
        }

        // Include current element
        currentCombination.add(arr[index]);
        generateCombinationsIncludeExclude(arr, index + 1, r, allCombinations, currentCombination);
        currentCombination.remove(currentCombination.size() - 1);

        // Skip duplicates (optional for sorted arrays)
        if (index < arr.length - 1 && arr[index] == arr[index + 1]) {
            index++;
        }

        // Exclude current element
        generateCombinationsIncludeExclude(arr, index + 1, r, allCombinations, currentCombination);
    }

    /**
     * Prints all combinations of size r using include/exclude method.
     */
    public static void printCombinationsIncludeExclude(int[] arr, int r) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        Arrays.sort(arr); // Sort to handle duplicates
        generateCombinationsIncludeExclude(arr, 0, r, allCombinations, currentCombination);

        for (List<Integer> combination : allCombinations) {
            System.out.println(combination);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        System.out.println("Combinations using Backtracking:");
        printCombinationsBacktrack(arr, 3);

        System.out.println("************");

        System.out.println("Combinations using Include/Exclude:");
        printCombinationsIncludeExclude(arr, 3);
    }
}