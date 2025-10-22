public class A11CalculateTrappingWater {

    /**
     * Calculates the total trapped water between bars.
     * Approach: Two-pointer technique
     * - Maintain leftMax and rightMax while moving inward.
     * - Water trapped at a position = min(leftMax, rightMax) - height[i].
     *
     * Time Complexity: O(n) (single pass)
     * Space Complexity: O(1) (constant extra space)
     */
    public static int calculateTrappingWater(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int leftMax = heights[left];
        int rightMax = heights[right];
        int trappedWater = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                if (heights[left] > leftMax) {
                    leftMax = heights[left];
                } else {
                    trappedWater += leftMax - heights[left];
                }
            } else {
                right--;
                if (heights[right] > rightMax) {
                    rightMax = heights[right];
                } else {
                    trappedWater += rightMax - heights[right];
                }
            }
        }
        return trappedWater;
    }

    public static void main(String[] args) {
        int[] heights = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };

        System.out.println("Bar Heights:");
        printArray(heights);

        int result = calculateTrappingWater(heights);
        System.out.println("\nTotal Trapped Water: " + result + " units");
    }

    /**
     * Utility method to print array in readable format.
     */
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("]");
    }
}