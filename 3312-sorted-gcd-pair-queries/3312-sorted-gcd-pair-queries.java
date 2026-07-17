import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Step 1: cnt[v] = how many numbers in nums equal v
        int[] cnt = new int[maxVal + 1];
        for (int num : nums) {
            cnt[num]++;
        }

        // Step 2: multiples[d] = how many numbers in nums are divisible by d
        int[] multiples = new int[maxVal + 1];
        for (int d = 1; d <= maxVal; d++) {
            for (int multiple = d; multiple <= maxVal; multiple += d) {
                multiples[d] += cnt[multiple];
            }
        }

        // Step 3: pairsDivisibleBy[d] = C(multiples[d], 2)
        long[] pairsDivisibleBy = new long[maxVal + 1];
        for (int d = 1; d <= maxVal; d++) {
            long m = multiples[d];
            pairsDivisibleBy[d] = m * (m - 1) / 2;
        }

        // Step 4: inclusion-exclusion, from largest d down to smallest,
        // to get exactGcdCount[d] = pairs whose gcd is EXACTLY d
        long[] exactGcdCount = new long[maxVal + 1];
        for (int d = maxVal; d >= 1; d--) {
            long subtract = 0;
            for (int multiple = 2 * d; multiple <= maxVal; multiple += d) {
                subtract += exactGcdCount[multiple];
            }
            exactGcdCount[d] = pairsDivisibleBy[d] - subtract;
        }

        long[] prefixCount = new long[maxVal + 1];
        prefixCount[0] = 0;
        for (int d = 1; d <= maxVal; d++) {
            prefixCount[d] = prefixCount[d - 1] + exactGcdCount[d];
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long k = queries[i]; 
            answer[i] = binarySearchSmallestD(prefixCount, k, maxVal);
        }

        return answer;
    }
    private int binarySearchSmallestD(long[] prefixCount, long k, int maxVal) {
        int lo = 1, hi = maxVal;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (prefixCount[mid] > k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}