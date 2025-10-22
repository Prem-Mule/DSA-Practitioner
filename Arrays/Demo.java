import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Query {
    int left;
    int right;

    public Query(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

public class Demo {
    public static int findPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            // if (arr[start] <= arr[mid]) {
            // start = mid + 1;
            // } else {
            // end = mid;
            // }
            if (arr[end] < arr[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return arr[end];
    }

    public static int findTarget(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[start] <= arr[mid]) {

                if (arr[start] <= target && target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

            } else {
                if (arr[mid] < target && target <= arr[end]) {
                    start = mid + 1;

                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int findTarget2(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] < target) {
            return findTarget2(arr, target, mid + 1, end);
        } else {
            return findTarget2(arr, target, start, mid - 1);
        }
    }

    public static int[] nextPermutation(int[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = arr.length - 1;
            while (arr[i] >= arr[j]) {
                j--;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            int start = i + 1;
            int end = arr.length - 1;
            while (start < end) {
                int t1 = arr[start];
                arr[start] = arr[end];
                arr[end] = t1;
                start++;
                end--;
            }
        }
        return arr;
    }

    public static int quickSelectLargest(List<Integer> arr, int k) {
        if (arr.size() == 1) {
            return arr.get(0);
        }
        List<Integer> leftArr = new ArrayList<>();
        List<Integer> rightArr = new ArrayList<>();
        List<Integer> midArr = new ArrayList<>();
        int pivot = arr.getLast();
        for (int a : arr) {
            if (a > pivot) {
                leftArr.add(a);
            } else if (a < pivot) {
                rightArr.add(a);
            } else {
                midArr.add(a);
            }
        }
        if (leftArr.size() >= k) {
            return quickSelectLargest(leftArr, k);
        } else if (leftArr.size() + midArr.size() < k) {
            return quickSelectLargest(rightArr, k - leftArr.size() - midArr.size());
        }
        return pivot;
    }

    public static int kthlargest(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();
        for (int a : arr) {
            list.add(a);
        }
        return quickSelectLargest(list, k);
    }

    public static int[] queryResults(int[] arr, List<Query> queries) {
        int[] result = new int[queries.size()];
        int block = (int) Math.sqrt(arr.length);
        Collections.sort(queries, new Comparator<Query>() {
            @Override
            public int compare(Query a, Query b) {
                if (a.left / block != b.left / block) {
                    return a.left < b.left ? -1 : 1;
                }
                return a.right < b.right ? -1 : 1;

            }
        });

        int currentLeft = 0;
        int currentRight = 0;
        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            int l = queries.get(i).left;
            int r = queries.get(i).right;
            System.out.println("cl" + currentLeft + " cr" + currentRight);

            while (currentLeft < l) {
                sum -= arr[currentLeft];
                currentLeft++;
            }
            while (currentLeft > l) {
                sum += arr[currentLeft - 1];
                currentLeft--;
            }
            while (currentRight <= r) {
                sum += arr[currentRight];
                System.out.println("j" + sum);
                currentRight++;
            }
            while (currentRight > r + 1) {
                sum -= arr[currentRight];
                currentRight--;
            }
            System.out.println("cl" + currentLeft + " cr" + currentRight);
            result[i] = sum;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 5, 6, 7, 8, 1, 2, 3 };
        System.out.println(findPivot(arr));
        System.out.println(findTarget(arr, 6));
        System.out.println(findTarget2(arr, 6, 0, arr.length - 1));
        int[] arr1 = { 1, 1, 3, 1, 3, 2, 1 };
        for (int a : nextPermutation(arr1)) {
            System.out.print(" " + a);
        }
        System.out.println("Kth largest is " + kthlargest(arr, 1));
        int[] arra = { 1, 1, 2, 1, 3, 4, 5, 2, 8 };

        // Define queries
        ArrayList<Query> q = new ArrayList<>();
        q.add(new Query(0, 4)); // Sum of arr[0..4]
        q.add(new Query(1, 3)); // Sum of arr[1..3]
        q.add(new Query(2, 4)); // Sum of arr[2..4]

        // Execute Mo's Algorithm and print results
        for (int a : queryResults(arra, q)) {
            System.out.print(" " + a);
        }
    }
}
