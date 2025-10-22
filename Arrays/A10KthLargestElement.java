import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class A10KthLargestElement {

    /**
     * Approach 1: Using Min-Heap for Kth Largest
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     * Best for streaming data or large arrays.
     */
    public static int findKthLargestHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * Approach 1: Using Max-Heap for Kth Smallest
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     */
    public static int findKthSmallestHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    /**
     * QuickSelect for Kth Largest
     * Average Time Complexity: O(n)
     * Worst Case: O(n^2)
     * Space Complexity: O(n) due to recursive sublists.
     */
    public static int quickSelectLargest(List<Integer> arr, int k) {
        if (arr.size() == 1)
            return arr.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();

        int pivot = arr.get(arr.size() - 1);
        for (int num : arr) {
            if (num > pivot)
                left.add(num);
            else if (num < pivot)
                right.add(num);
            else
                mid.add(num);
        }

        if (k <= left.size())
            return quickSelectLargest(left, k);
        else if (k > left.size() + mid.size())
            return quickSelectLargest(right, k - left.size() - mid.size());
        return pivot;
    }

    public static int findKthLargestQuickSelect(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr)
            list.add(num);
        return quickSelectLargest(list, k);
    }

    /**
     * QuickSelect for Kth Smallest
     * Average Time Complexity: O(n)
     * Worst Case: O(n^2)
     */
    public static int quickSelectSmallest(List<Integer> arr, int k) {
        if (arr.size() == 1)
            return arr.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();

        int pivot = arr.get(arr.size() - 1);
        for (int num : arr) {
            if (num < pivot)
                left.add(num);
            else if (num > pivot)
                right.add(num);
            else
                mid.add(num);
        }

        if (k <= left.size())
            return quickSelectSmallest(left, k);
        else if (k > left.size() + mid.size())
            return quickSelectSmallest(right, k - left.size() - mid.size());
        return pivot;
    }

    public static int findKthSmallestQuickSelect(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr)
            list.add(num);
        return quickSelectSmallest(list, k);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 5, 7, 1, 8, 9 };
        int k = 3;

        System.out.println("Array: ");
        printArray(arr);

        System.out.println("\nUsing Heap:");
        System.out.println(k + "rd Largest Element = " + findKthLargestHeap(arr, k));
        System.out.println(k + "rd Smallest Element = " + findKthSmallestHeap(arr, k));

        System.out.println("\nUsing QuickSelect:");
        System.out.println(k + "rd Largest Element = " + findKthLargestQuickSelect(arr, k));
        System.out.println(k + "rd Smallest Element = " + findKthSmallestQuickSelect(arr, k));
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println("]");
    }
}