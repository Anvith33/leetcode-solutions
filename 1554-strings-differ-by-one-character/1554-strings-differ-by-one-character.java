import java.util.*;

class Solution {
    public boolean differByOne(String[] dict) {

        int n = dict.length;
        int m = dict[0].length();

        long MOD = 1000000007L;
        long BASE = 27;

        long[] power = new long[m];
        power[0] = 1;
        for (int i = 1; i < m; i++) {
            power[i] = (power[i - 1] * BASE) % MOD;
        }

        long[] hash = new long[n];

        for (int i = 0; i < n; i++) {
            long h = 0;
            for (int j = 0; j < m; j++) {
                h = (h * BASE + (dict[i].charAt(j) - 'a' + 1)) % MOD;
            }
            hash[i] = h;
        }

        for (int col = 0; col < m; col++) {

            HashSet<Long> set = new HashSet<>();

            for (int row = 0; row < n; row++) {

                long removed = ((dict[row].charAt(col) - 'a' + 1) * power[m - col - 1]) % MOD;
                long newHash = (hash[row] - removed + MOD) % MOD;

                if (set.contains(newHash))
                    return true;

                set.add(newHash);
            }
        }

        return false;
    }
}