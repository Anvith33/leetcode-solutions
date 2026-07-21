class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";
        int n = t.length();

        ArrayList<Character> ch = new ArrayList<>();
        ArrayList<Integer> len = new ArrayList<>();

        int i = 0;
        while (i < n) {
            char c = t.charAt(i);
            int j = i;
            while (j < n && t.charAt(j) == c) j++;
            ch.add(c);
            len.add(j - i);
            i = j;
        }

        int ans = ones;

        // Pattern: 0-run, 1-run, 0-run
        for (i = 1; i < ch.size() - 1; i++) {
            if (ch.get(i) == '1' &&
                ch.get(i - 1) == '0' &&
                ch.get(i + 1) == '0') {

                ans = Math.max(ans,
                        ones + len.get(i - 1) + len.get(i + 1));
            }
        }

        return ans;
    }
}