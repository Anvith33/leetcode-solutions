class Solution {
    static final int MOD = 1_000_000_007;
    static final int MAXV = 200;

    public int subsequencePairCount(int[] nums) {
        // precompute gcd table once: gcd[a][b] for a,b in [0, MAXV]
        int[][] gcdTable = new int[MAXV + 1][MAXV + 1];
        for (int a = 0; a <= MAXV; a++) {
            for (int b = 0; b <= MAXV; b++) {
                gcdTable[a][b] = gcd(a, b);
            }
        }

        long[][] dp = new long[MAXV + 1][MAXV + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            long[][] ndp = new long[MAXV + 1][MAXV + 1];
            for (int g1 = 0; g1 <= MAXV; g1++) {
                for (int g2 = 0; g2 <= MAXV; g2++) {
                    long c = dp[g1][g2];
                    if (c == 0) continue;

                    // leave x unused
                    ndp[g1][g2] = (ndp[g1][g2] + c) % MOD;

                    // assign x to seq1
                    int ng1 = (g1 == 0) ? x : gcdTable[g1][x];
                    ndp[ng1][g2] = (ndp[ng1][g2] + c) % MOD;

                    // assign x to seq2
                    int ng2 = (g2 == 0) ? x : gcdTable[g2][x];
                    ndp[g1][ng2] = (ndp[g1][ng2] + c) % MOD;
                }
            }
            dp = ndp;
        }

        long total = 0;
        for (int g = 1; g <= MAXV; g++) {
            total = (total + dp[g][g]) % MOD;
        }
        return (int) total;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}