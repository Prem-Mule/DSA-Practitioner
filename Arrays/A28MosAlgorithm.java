import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Query class to represent a range [left, right]
class Query {
    int left;
    int right;

    Query(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

public class A28MosAlgorithm {

    /**
     * Processes queries using Mo's Algorithm to compute sum of elements in each
     * range.
     * 
     * @param arr     Input array
     * @param queries List of queries with left and right indices
     * @return Array of results for each query
     */
    public static int[] queryResults(int[] arr, List<Query> queries) {
        int[] result = new int[queries.size()];

        // Block size for Mo's Algorithm (sqrt decomposition)
        int block = (int) Math.sqrt(arr.length);

        // Sort queries by block of left index, then by right index
        Collections.sort(queries, new Comparator<Query>() {
            @Override
            public int compare(Query a, Query b) {
                if (a.left / block != b.left / block) {
                    return Integer.compare(a.left, b.left);
                }
                return Integer.compare(a.right, b.right);
            }
        });

        System.out.println(queries.get(2).left);
        // Initialize pointers and current sum
        int currentLeft = 0;
        int currentRight = 0; // NOTE: This should ideally start at -1 for empty range
        int currentSum = 0;

        // Process each query in sorted order
        for (int i = 0; i < queries.size(); i++) {
            int L = queries.get(i).left;
            int R = queries.get(i).right;

            // Move currentLeft to L (shrink or expand from left)
            while (currentLeft < L) {
                currentSum -= arr[currentLeft]; // Remove element going out of range
                currentLeft++;
            }
            while (currentLeft > L) {
                currentSum += arr[currentLeft - 1]; // Add element coming into range
                currentLeft--;
            }

            // Move currentRight to R (expand or shrink from right)
            while (currentRight <= R) {
                currentSum += arr[currentRight]; // Add element coming into range
                currentRight++;
            }
            while (currentRight > R + 1) {
                currentSum -= arr[currentRight - 1]; // Remove element going out of range
                currentRight--;
            }
            System.out.println("Sum of [" + L +
                    ", " + R + "] is " + currentSum);
            // Store result for this query
            result[i] = currentSum;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 1, 2, 1, 3, 4, 5, 2, 8 };

        // Define queries
        ArrayList<Query> q = new ArrayList<>();
        q.add(new Query(0, 4)); // Sum of arr[0..4]
        q.add(new Query(1, 3)); // Sum of arr[1..3]
        q.add(new Query(2, 4)); // Sum of arr[2..4]

        // Execute Mo's Algorithm and print results
        queryResults(arr, q);
    }
}