public class A6searchRotatedSortedArray {

    /**
     * Iterative Binary Search in Rotated Sorted Array
     * Time Complexity: O(log n) because we halve the search space each iteration.
     * Space Complexity: O(1) since we use only a few extra variables.
     * This is the BEST approach among the given ones because it handles rotation
     * and uses efficient binary search logic.
     */
    public int searchInRotatedArrayIterative(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check which side is sorted
            if (nums[start] <= nums[mid]) { // Left side is sorted
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1; // Target lies in left half
                } else {
                    start = mid + 1; // Target lies in right half
                }
            } else { // Right side is sorted
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1; // Target lies in right half
                } else {
                    end = mid - 1; // Target lies in left half
                }
            }
        }
        return -1; // Target not found
    }

    /**
     * Recursive Binary Search (NOT handling rotation properly)
     * Time Complexity: O(log n) because of binary search recursion.
     * Space Complexity: O(log n) due to recursive call stack.
     * This approach is NOT ideal for rotated arrays unless combined with pivot
     * logic.
     */
    public int searchInSortedArrayRecursive(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (target < nums[mid]) {
            return searchInSortedArrayRecursive(nums, left, mid - 1, target);
        } else {
            return searchInSortedArrayRecursive(nums, mid + 1, right, target);
        }
    }

    /**
     * Find Pivot in Rotated Sorted Array
     * we use binary search to find pivot.
     * Space Complexity: O(1) since only a few variables are used.
     * Useful if you want to split array and then apply binary search on each part.
     */
    public int findPivotIndex(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[end] < nums[mid]) {
                start = mid + 1; // Pivot is in right half
            } else {
                end = mid; // Pivot is in left half
            }
        }
        return end; // Pivot index
    }

    public static void main(String[] args) {
        A6searchRotatedSortedArray obj = new A6searchRotatedSortedArray();

        int[] sortedArray = { 1, 2, 3, 4, 7, 8, 9 };
        int target = 8;
        System.out.println("Iterative Search (Rotated Logic): Index of " + target + " = "
                + obj.searchInSortedArrayRecursive(sortedArray, 0, sortedArray.length - 1, target));
        System.out.println(
                "Recursive Search (Normal Sorted): Index of " + target + " = " +
                        obj.searchInSortedArrayRecursive(sortedArray, 0, sortedArray.length - 1, target));

        int[] rotatedArray = { 4, 5, 6, 7, 1, 3 };
        System.out.println("Pivot Index in Rotated Array = " + obj.findPivotIndex(rotatedArray));
    }
}