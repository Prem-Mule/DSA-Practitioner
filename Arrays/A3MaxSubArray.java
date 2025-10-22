public class A3MaxSubArray {

    /**
     * Approach 1: Brute Force
     * - Check all possible subarrays and compute their sum.
     * Time Complexity: O(n^2) because of two nested loops.
     * Space Complexity: O(1) (no extra space).
     * Notes: Very slow for large arrays, only good for small inputs.
     */
    public int maxSubarray1(int[] arr) {
        int sum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int sm = 0;
            for (int j = i; j < arr.length; j++) {
                sm += arr[j];
                if (sum < sm) {
                    sum = sm;
                }
            }
        }
        return sum;
    }

    /**
     * Approach 2: Modified Kadane's Algorithm (with reset to zero)
     * - Accumulate sum, reset to zero if it becomes negative.
     * Time Complexity: O(n) (single pass).
     * Space Complexity: O(1).
     * Notes: Works for arrays with positive numbers, but fails if all numbers are
     * negative
     * because it resets to zero.
     */
    public int maxSubarray2(int[] arr) {
        int maxSum = arr[0];
        int currentSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            currentSum += arr[i];
            if (currentSum < 0) {
                currentSum = 0; // Reset when sum becomes negative
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    /**
     * Approach 3: Kadane's Algorithm (Standard)
     * - At each step, decide whether to start a new subarray or continue the
     * current one.
     * Time Complexity: O(n) (single pass).
     * Space Complexity: O(1).
     * Notes: Best approach. Handles negative numbers correctly.
     */
    public int maxSubarray3(int[] arr) {
        int maxSum = arr[0];
        int maxSumEndingHere = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxSumEndingHere = Math.max(arr[i], maxSumEndingHere + arr[i]);
            maxSum = Math.max(maxSum, maxSumEndingHere);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        // Example arrays:
        // int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 }; // Expected: 6
        int[] arr = { 5, 4, -1, 7, 8 }; // Expected: 23

        A3MaxSubArray obj = new A3MaxSubArray();

        System.out.println("Brute Force: " + obj.maxSubarray1(arr));
        System.out.println("************************");
        System.out.println("Modified Kadane: " + obj.maxSubarray2(arr));
        System.out.println("************************");
        System.out.println("Kadane's Algorithm: " + obj.maxSubarray3(arr));
        System.out.println("************************");
    }
}