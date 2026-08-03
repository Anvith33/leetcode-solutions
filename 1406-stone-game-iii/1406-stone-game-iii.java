class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] = best score difference (current player - other player) achievable
        // starting from index i to the end, with optimal play.
        int[] dp = new int[n + 1];
        // dp[n] = 0 (no stones left)

        for (int i = n - 1; i >= 0; i--) {
            int best = Integer.MIN_VALUE;
            int sum = 0;
            for (int k = 0; k < 3 && i + k < n; k++) {
                sum += stoneValue[i + k];
                // taking stones i..i+k gives 'sum', then opponent plays optimally from i+k+1
                int candidate = sum - dp[i + k + 1];
                best = Math.max(best, candidate);
            }
            dp[i] = best;
        }

        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}