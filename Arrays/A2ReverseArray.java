import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A2ReverseArray {

    /**
     * Approach 1: Reverse using arithmetic swap (no extra variable)
     * - Swap elements by addition and subtraction.
     * Time Complexity: O(n/2) ≈ O(n)
     * Space Complexity: O(1) (in-place)
     * Notes:
     * - Risk of integer overflow for large numbers.
     * - Efficient but less readable.
     */
    public int[] reverseArray1(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            arr[i] = arr[i] + arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = arr[i] - arr[arr.length - 1 - i];
            arr[i] = arr[i] - arr[arr.length - 1 - i];
        }
        return arr;
    }

    /**
     * Approach 2: Reverse using two-pointer technique with arithmetic swap
     * - Use left and right pointers to swap elements.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * Notes:
     * - Same overflow risk as Approach 1.
     * - More intuitive than Approach 1.
     */
    public int[] reverseArray2(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            arr[left] = arr[left] + arr[right];
            arr[right] = arr[left] - arr[right];
            arr[left] = arr[left] - arr[right];
            left++;
            right--;
        }
        return arr;
    }

    /**
     * Approach 3: Reverse using Collections.reverse()
     * - Convert array to List, use built-in reverse, then copy back.
     * Time Complexity: O(n)
     * Space Complexity: O(n) (extra list)
     * Notes:
     * - Very readable and safe.
     * - Uses extra memory, not in-place.
     */
    public int[] reverseArray3(int[] arr) {
        List<Integer> li = new ArrayList<>();
        for (int a : arr) {
            li.add(a);
        }
        Collections.reverse(li);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = li.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] myArray = { 1, 2, 3, 4, 5, 6, 7 };
        A2ReverseArray obj = new A2ReverseArray();

        System.out.println("Approach 1 (Arithmetic Swap):");
        for (int a : obj.reverseArray1(myArray)) {
            System.out.print(a + ", ");
        }
        System.out.println("\n*********************************");

        System.out.println("Approach 2 (Two-Pointer Swap):");
        for (int a : obj.reverseArray2(myArray)) {
            System.out.print(a + ", ");
        }
        System.out.println("\n*********************************");

        System.out.println("Approach 3 (Collections.reverse):");
        for (int a : obj.reverseArray3(myArray)) {
            System.out.print(a + ", ");
        }
        System.out.println("\n*********************************");
    }
}