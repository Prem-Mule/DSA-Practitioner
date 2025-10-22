public class A13MaxProductSubArray {

    /**
     * Approach 1: Brute Force
     * - Compute product of all subarrays.
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * Not efficient for large arrays.
     */
    public static int maxProductSubarray1(int[] arr) {
        int maxProduct = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int product = arr[i];
            maxProduct = Math.max(maxProduct, product);
            for (int j = i + 1; j < arr.length; j++) {
                product *= arr[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }

    /**
     * Approach 2: Dynamic Programming (Kadane-like)
     * - Track max and min product ending at current index.
     * - Handles negative numbers by swapping roles of max/min.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Best approach for this problem.
     */
    public static int maxProductSubarray2(int[] arr) {
        int maxProduct = arr[0];
        int maxEndingHere = arr[0];
        int minEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int tempMax = maxEndingHere;

            maxEndingHere = Math.max(current, Math.max(current * maxEndingHere, current * minEndingHere));
            minEndingHere = Math.min(current, Math.min(current * tempMax, current * minEndingHere));

            maxProduct = Math.max(maxProduct, maxEndingHere);
        }
        return maxProduct;
    }

    /**
     * Approach 3: Two-pass (Left-to-Right and Right-to-Left)
     * - Reset product when encountering zero.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Simpler but less intuitive than DP approach.
     */
    public static int maxProductSubarray3(int[] arr) {
        int leftToRight = 1;
        int rightToLeft = 1;
        int maxProduct = arr[0];

        for (int i = 0; i < arr.length; i++) {
            leftToRight = (leftToRight == 0) ? 1 : leftToRight;
            rightToLeft = (rightToLeft == 0) ? 1 : rightToLeft;

            leftToRight *= arr[i];
            rightToLeft *= arr[arr.length - 1 - i];

            maxProduct = Math.max(maxProduct, Math.max(leftToRight, rightToLeft));
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        int[] arr = { -2, 6, -3, -10, 0, 2 };

        System.out.println("Array:");
        printArray(arr);

        System.out.println("\nApproach 1 (Brute Force): " + maxProductSubarray1(arr));
        System.out.println("Approach 2 (Dynamic Programming): " + maxProductSubarray2(arr));
        System.out.println("Approach 3 (Two-pass): " + maxProductSubarray3(arr));
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}