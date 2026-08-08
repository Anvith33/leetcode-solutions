import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        Arrays.fill(last, -1);

        // Find the latest positions in word1 that can match
        // each character of word2 from right to left.
        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        int[] ans = new int[m];

        boolean canChange = true;
        j = 0;

        for (i = 0; i < n && j < m; i++) {

            // Exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Use the one allowed character change
            else if (canChange &&
                     (j == m - 1 || i < last[j + 1])) {

                ans[j] = i;
                j++;
                canChange = false;
            }
        }

        // Could not form the complete sequence
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}