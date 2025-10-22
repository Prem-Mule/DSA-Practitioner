public class A8StockMarketMaxProfit {

    /**
     * Calculates the maximum profit from a single buy-sell transaction.
     * Logic:
     * - Track the minimum price (buy point) as we iterate.
     * - For each price, calculate potential profit and update maxProfit.
     *
     * Time Complexity: O(n) (single pass through array)
     * Space Complexity: O(1) (constant extra space)
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buyIndex = 0; // Index of minimum price so far

        for (int sellIndex = 1; sellIndex < prices.length; sellIndex++) {
            if (prices[sellIndex] <= prices[buyIndex]) {
                // Found a new lower price → update buy point
                buyIndex = sellIndex;
            } else {
                // Calculate profit if sold today
                int profit = prices[sellIndex] - prices[buyIndex];
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] stockPrices = { 4, 3, 10, 6, 3, 4, 5, 2, 3 };

        System.out.println("Stock Prices:");
        printArray(stockPrices);

        int result = maxProfit(stockPrices);
        System.out.println("\nMaximum Profit from One Transaction: " + result);
    }

    /**
     * Utility method to print array in readable format.
     */
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int price : arr) {
            System.out.print(price + " ");
        }
        System.out.println("]");
    }
}