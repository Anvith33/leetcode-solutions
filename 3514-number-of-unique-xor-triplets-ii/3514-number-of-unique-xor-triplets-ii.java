import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
    
        final int MAX = 1 << 11;
        int[] freq = new int[MAX];
        for (int x : nums) freq[x]++;


        boolean[] result = new boolean[MAX];

    
        for (int v = 0; v < MAX; v++) {
            if (freq[v] > 0) result[v] = true;
        }


        boolean[] pair = new boolean[MAX];
        for (int a = 0; a < MAX; a++) {
            if (freq[a] == 0) continue;

            for (int b = a; b < MAX; b++) {
                if (freq[b] == 0) continue;
                if (a == b && freq[a] < 2) continue;
                pair[a ^ b] = true;
            }
        }


        for (int c = 0; c < MAX; c++) {
            if (freq[c] == 0) continue;
            for (int px = 0; px < MAX; px++) {
                if (pair[px]) result[px ^ c] = true;
            }
        }


        int count = 0;
        for (int v = 0; v < MAX; v++) if (result[v]) count++;
        return count;
    }
}
