import java.util.Arrays;

public class A12ProductExceptSelf {
    public static int[] productExceptSelf(int[] arr) {
        int[] result = new int[arr.length];
        int totalProduct = 1;
        for (int a : arr) {
            totalProduct *= a;
        }
        for (int i = 0; i < arr.length; i++) {
            result[i] = totalProduct / arr[i];
        }
        return result;
    }

    public static int[] productExceptSelf2(int[] arr) {
        int[] result = new int[arr.length];
        int leftProduct = 1;
        int rightProduct = 1;
        for (int i = 0; i < arr.length; i++) {
            int j = i + 1;
            while (j < arr.length) {
                rightProduct *= arr[j];
                j++;
            }
            rightProduct *= leftProduct;
            leftProduct *= arr[i];

            result[i] = rightProduct;
            rightProduct = 1;
        }
        return result;
    }

    /**
     * Approach 3: Prefix and Suffix arrays
     * Complexity: O(n)
     * Space Complexity: O(n)
     * Handles zeros correctly.
     */

    public static int[] productExceptSelf3(int[] arr) {
        int[] prefProduct = new int[arr.length];
        int[] suffProduct = new int[arr.length];
        int result[] = new int[arr.length];
        prefProduct[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            prefProduct[i] = prefProduct[i - 1] * arr[i - 1];
        }
        suffProduct[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            suffProduct[i] = suffProduct[i + 1] * arr[i + 1];
        }
        for (int i = 0; i < arr.length; i++) {
            result[i] = prefProduct[i] * suffProduct[i];
        }
        return result;
    }

    /**
     * Approach 4: Handles zeros explicitly
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Best for arrays with zeros.
     */

    public static int[] productExceptSelf4(int[] arr) {
        int result[] = new int[arr.length];
        int zeros = 0;
        int product = 1;
        int zeroIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeros++;
                zeroIndex = i;
            } else {
                product *= arr[i];
            }
        }
        if (zeros == 0) {
            for (int i = 0; i < arr.length; i++) {
                result[i] = product / arr[i];
            }
        } else if (zeros > 1) {
            Arrays.fill(result, 0);
        } else {
            result[zeroIndex] = product;
        }
        return result;
    }

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4 };

        System.out.println("Original Array:");
        printArray(arr);

        System.out.println("\nApproach 1 (Division):");
        printArray(productExceptSelf(arr));

        System.out.println("\nApproach 2 (Brute Force):");
        printArray(productExceptSelf2(arr));

        System.out.println("\nApproach 3 (Prefix & Suffix):");
        printArray(productExceptSelf3(arr));

        System.out.println("\nApproach 4 (Handles Zeros):");
        printArray(productExceptSelf4(arr));
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}