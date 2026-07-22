class Solution {
    public String boldWords(String[] words, String s) {
        int n = s.length();
        boolean[] bold = new boolean[n];


        for (int i = 0; i < n; i++) {
            for (String word : words) {
                if (i + word.length() <= n && s.startsWith(word, i)) {
                    for (int j = i; j < i + word.length(); j++) {
                        bold[j] = true;
                    }
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        int i = 0;

        while (i < n) {
            if (!bold[i]) {
                ans.append(s.charAt(i));
                i++;
            } else {
                ans.append("<b>");
                while (i < n && bold[i]) {
                    ans.append(s.charAt(i));
                    i++;
                }
                ans.append("</b>");
            }
        }

        return ans.toString();
    }
}