public class A9RepeatingMissingNumber {
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }

    /**
     * Swap two elements in the array without using a temporary variable.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static void helperSwap(int[] arr, int a, int b) {
        if (a != b) {
            arr[a] = arr[a] + arr[b];
            arr[b] = arr[a] - arr[b];
            arr[a] = arr[a] - arr[b];
        }
    }

    /**
     * Partition method for QuickSort.
     * Time Complexity: O(n) for partition step.
     */
    public static int partition(int[] arr, int left, int right) {
        int i = left - 1;
        int pivot = arr[right];
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                helperSwap(arr, i, j);
            }
        }
        helperSwap(arr, i + 1, right);
        return i + 1;
    }

    /**
     * QuickSort implementation.
     * Time Complexity: O(n log n) average case.
     * Space Complexity: O(log n) due to recursion.
     */
    public static int[] quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int p = partition(arr, start, end);
            quickSort(arr, start, p - 1);
            quickSort(arr, p + 1, end);
        }
        return arr;
    }

    /**
     * Approach 1: After sorting, find missing and repeating numbers.
     * Time Complexity: O(n log n) (due to sorting)
     * Space Complexity: O(1)
     */
    public static int[] findRepeatingMissingNumberSorted(int[] arr) {
        int[] result = new int[2]; // [missing, repeating]
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                result[1] = arr[i]; // repeating
            }
            if (arr[i + 1] - arr[i] > 1) {
                result[0] = arr[i] + 1; // missing
            }
        }
        return result;
    }

    /**
     * Approach 2: Using frequency count.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] findRepeatingMissingNumberCount(int[] arr) {
        int[] count = new int[arr.length];
        int[] result = new int[2]; // [missing, repeating]

        for (int num : arr) {
            count[num - 1]++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                result[0] = i + 1; // missing
            }
            if (count[i] > 1) {
                result[1] = i + 1; // repeating
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 5 };

        System.out.println("Original Array:");
        printArray(arr);

        // Sort array for first approach
        quickSort(arr, 0, arr.length - 1);
        System.out.println("\nSorted Array:");
        printArray(arr);

        int[] result1 = findRepeatingMissingNumberSorted(arr);
        System.out.println("\nApproach 1 (Sorted): Missing = " + result1[0] + ", Repeating = " + result1[1]);

        int[] arr2 = { 1, 2, 2, 3, 5 };
        int[] result2 = findRepeatingMissingNumberCount(arr2);
        System.out.println("Approach 2 (Count): Missing = " + result2[0] + ", Repeating = " + result2[1]);
    }

    /**
     * Utility method to print array in readable format.
     */

}