import java.util.*;

class Solution {
    public int equalDigitFrequency(String s) {

        HashSet<String> set = new HashSet<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {

            int[] freq = new int[10];

            for (int j = i; j < n; j++) {

                freq[s.charAt(j) - '0']++;

                int count = 0;
                int same = -1;
                boolean ok = true;

                for (int f : freq) {
                    if (f > 0) {
                        if (same == -1)
                            same = f;
                        else if (same != f) {
                            ok = false;
                            break;
                        }
                        count++;
                    }
                }

                if (ok) {
                    set.add(s.substring(i, j + 1));
                }
            }
        }

        return set.size();
    }
}