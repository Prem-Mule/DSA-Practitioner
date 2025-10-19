import java.util.HashMap;
import java.util.Map;

public class A25LongestSubarraywithSumDivisibleByK {

    public static int subArraySumDivisiblebyK(int[] arr, int k) {
        int res = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefIndexMap = new HashMap<>(); // Stores first occurrence of each remainder

        for (int i = 0; i < arr.length; i++) {
            // Calculate prefix sum modulo k (handle negatives by adding k and mod again)
            prefixSum = (((prefixSum + arr[i]) % k) + k) % k;

            // ✅ Condition 1: If prefixSum == 0
            // This means the sum of subarray from index 0 to i is divisible by k
            if (prefixSum == 0) {
                res = i + 1; // Entire subarray from start is valid
            }
            // ✅ Condition 2: If this remainder was seen before
            // Then subarray between previous index and current index is divisible by k
            else if (prefIndexMap.containsKey(prefixSum)) {
                // Calculate length of subarray and update result if it's longer
                res = Math.max(res, i - prefIndexMap.get(prefixSum));
            }
            // ✅ Condition 3: If this remainder is new
            // Store its index for future reference (only first occurrence matters)
            else {
                prefIndexMap.put(prefixSum, i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = { 2, 7, 6, 1, 4, 5 };
        int k1 = 3;
        System.out.println("Longest subarray length (Example 1): " +
                subArraySumDivisiblebyK(arr1, k1)); // Expected: 4

        // Example 2
        int[] arr2 = { 5, -2, 2, -8, 1, 7, 10 };
        int k2 = 10;
        System.out.println("Longest subarray length (Example 2): " +
                subArraySumDivisiblebyK(arr2, k2)); // Expected: 6

        // Example 3
        int[] arr3 = { 4, 5, 0, -2, -3, 1 };
        // int[] arr3 = { 1, 2, 3, 4, 5 };
        int k3 = 5;
        System.out.println("Longest subarray length (Example 3): " +
                subArraySumDivisiblebyK(arr3, k3)); // Expected: 5
    }
}