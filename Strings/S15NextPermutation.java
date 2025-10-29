public class S15NextPermutation {

    public static void helperSwap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    public static int[] nextPermutation(int[] arr) {
        int i = arr.length - 2;

        while (i >= 0 && arr[i + 1] <= arr[i]) {
            i--;
        }
        if (i >= 0) {
            int j = arr.length - 1;
            while (j >= 0 && arr[j] <= arr[i]) {
                j--;
            }
            helperSwap(arr, i, j);

        }
        int start = i + 1;
        int end = arr.length - 1;
        while (start <= end) {
            helperSwap(arr, start++, end--);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                { 1, 2, 3 },
                { 3, 2, 1 },
                { 1, 1, 5 },
                { 1, 3, 2 },
                { 2, 3, 1 }
        };

        System.out.println("=== Next Permutation ===");
        for (int[] test : testCases) {
            System.out.print("\nInput: ");
            printArray(test);

            int[] result = nextPermutation(test);
            System.out.print("Next Permutation: ");
            printArray(result);
        }
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
