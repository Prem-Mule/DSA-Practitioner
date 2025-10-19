import java.util.HashMap;
import java.util.Map;

public class A26SubarraysCountwithSumDivisibleByK {
    public static int countOfSubarraysWithSumDivisibleByK1(int[] arr, int k) {
        int res = 0;
        int remainderTillNow = 0;
        Map<Integer, Integer> remainderIndexCount = new HashMap<>();
        for (int a : arr) {
            remainderTillNow = ((remainderTillNow + a) % k + k) % k;
            if (remainderTillNow == 0) {
                res = res + 1;
            }
            res += remainderIndexCount.getOrDefault(remainderTillNow, 0);
            remainderIndexCount.put(remainderTillNow, remainderIndexCount.getOrDefault(remainderTillNow, 0) + 1);
        }
        return res;
    }

    public static int countOfSubarraysWithSumDivisibleByK2(int[] arr, int k) {
        int count = 0;
        int sum = 0;
        int[] remainderIndexCounter = new int[k];
        remainderIndexCounter[0] = 1;
        for (int a : arr) {
            sum += a;
            int mod = ((sum % k) + k) % k;
            count += remainderIndexCounter[mod]++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 5, 0, -2, -3, 1 };
        System.out.println(
                "Count of subarrays with sum divisible by " + 5 + " is "
                        + countOfSubarraysWithSumDivisibleByK1(arr, 5));
        System.out.println(
                "Count of subarrays with sum divisible by " + 5 + " is "
                        + countOfSubarraysWithSumDivisibleByK2(arr, 5));
    }
}
