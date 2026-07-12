import java.util.*;

class Solution {

    int count = 0;
    String ans = "";

    public String getHappyString(int n, int k) {
        backtrack(new StringBuilder(), n, k);
        return ans;
    }

    private void backtrack(StringBuilder sb, int n, int k) {

        if (sb.length() == n) {
            count++;
            if (count == k) {
                ans = sb.toString();
            }
            return;
        }

        char[] ch = {'a', 'b', 'c'};

        for (char c : ch) {

            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != c) {

                sb.append(c);
                backtrack(sb, n, k);

                if (!ans.equals(""))
                    return;

                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}