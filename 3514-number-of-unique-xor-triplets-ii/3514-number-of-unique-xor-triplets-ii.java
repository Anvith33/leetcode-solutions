import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // nums[i] <= 1500 per constraints -> use MAX = 2048 (2^11)
        final int MAX = 1 << 11; // 2048
        int[] freq = new int[MAX];
        for (int x : nums) freq[x]++;

        // result marks achievable XOR values
        boolean[] result = new boolean[MAX];

        // Case 1 & 2: every number itself is achievable (x^x^x = x and x^x^y = y)
        for (int v = 0; v < MAX; v++) {
            if (freq[v] > 0) result[v] = true;
        }

        // Build pairwise XORs from two distinct indices (i < j)
        boolean[] pair = new boolean[MAX];
        for (int a = 0; a < MAX; a++) {
            if (freq[a] == 0) continue;
            // b starts from a to avoid duplicate pairs; handle a==b with freq>=2
            for (int b = a; b < MAX; b++) {
                if (freq[b] == 0) continue;
                if (a == b && freq[a] < 2) continue; // need two distinct indices for pair
                pair[a ^ b] = true;
            }
        }

        // Combine each pairXor with every c (third element) present
        for (int c = 0; c < MAX; c++) {
            if (freq[c] == 0) continue;
            for (int px = 0; px < MAX; px++) {
                if (pair[px]) result[px ^ c] = true;
            }
        }

        // Count distinct achievable values
        int count = 0;
        for (int v = 0; v < MAX; v++) if (result[v]) count++;
        return count;
    }
}
