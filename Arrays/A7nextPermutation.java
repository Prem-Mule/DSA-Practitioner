public class A7nextPermutation {

    /**
     * Swap two elements in the array without using a temporary variable.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static void helperSwap(int[] arr, int a, int b) {
        if (a == b) {
            return; // No need to swap if indices are same
        }
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    /**
     * Generates the next lexicographical permutation of the given array.
     * Steps:
     * 1. Find the first decreasing element from the right (pivot).
     * 2. Find the element just larger than pivot and swap.
     * 3. Reverse the suffix to get the smallest order.
     *
     * Time Complexity: O(n) (single pass + reverse)
     * Space Complexity: O(1) (in-place)
     */
    public static int[] nextPermutation(int[] arr) {
        int i = arr.length - 2;

        // Step 1: Find pivot (first decreasing element from right)
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        // Step 2: If pivot exists, find element just larger and swap
        if (i >= 0) {
            int j = arr.length - 1;
            while (arr[j] <= arr[i]) {
                j--;
            }
            helperSwap(arr, i, j);
        }

        // Step 3: Reverse the suffix
        int start = i + 1;
        int end = arr.length - 1;
        while (start < end) {
            helperSwap(arr, start++, end--);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 3, 1, 4, 2 };

        System.out.println("Original Array:");
        printArray(arr);

        int[] next = nextPermutation(arr);

        System.out.println("\nNext Permutation:");
        printArray(next);
    }

    /**
     * Utility method to print array in readable format.
     */
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}