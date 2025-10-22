import java.util.Arrays;

public class A5chocalateDist {

    /**
     * Merge function for Merge Sort
     * - Combines two sorted halves into one sorted array.
     * Time Complexity: O(n) for merging two halves.
     * Space Complexity: O(n) for temporary arrays.
     */
    public int[] merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] l = new int[n1];
        int[] r = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            l[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            r[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;

        // Merge two sorted arrays
        while (i < n1 && j < n2) {
            if (l[i] < r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }

        // Copy remaining elements
        while (i < n1) {
            arr[k++] = l[i++];
        }
        while (j < n2) {
            arr[k++] = r[j++];
        }

        return arr;
    }

    /**
     * Merge Sort implementation
     * Time Complexity: O(n log n)
     * Space Complexity: O(n) due to temporary arrays
     * Notes: Stable sort, good for large arrays.
     */
    public int[] mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        return arr;
    }

    /**
     * Chocolate Distribution Problem:
     * - Given an array of packet sizes and number of students (m),
     * distribute packets so that difference between max and min chocolates is
     * minimized.
     *
     * Steps:
     * 1. Sort array.
     * 2. Find the minimum difference between arr[i+m-1] and arr[i] for all valid i.
     *
     * Time Complexity: O(n log n) for sorting + O(n) for scanning = O(n log n)
     * Space Complexity: O(n) due to merge sort.
     */
    public int findMinDiff(int[] arr, int m) {
        mergeSort(arr, 0, arr.length - 1); // Sort packets
        int minDiff = Integer.MAX_VALUE;

        // Slide a window of size m and compute difference
        for (int i = 0; i + m - 1 < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i + m - 1] - arr[i]);
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] arr = { 7, 3, 2, 4, 9, 12, 56 };
        int students = 3;

        A5chocalateDist obj = new A5chocalateDist();

        System.out.println("Minimum difference for 3 students: " + obj.findMinDiff(arr, students));
        students = 5;
        System.out.println("Minimum difference for 5 students: " + obj.findMinDiff(arr, students));
    }
}