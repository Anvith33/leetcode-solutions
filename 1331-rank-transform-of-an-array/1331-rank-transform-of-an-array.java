import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];

        int n = arr.length;
        int[] sorted = Arrays.copyOf(arr, n);
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();
        int r = 1;
        for (int v : sorted) {
            if (!rank.containsKey(v)) {
                rank.put(v, r++);
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = rank.get(arr[i]);
        }
        return res;
    }
}
