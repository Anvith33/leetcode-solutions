class Solution {
    public String shortestPalindrome(String s) {
        if (s.isEmpty()) return s;
        
        String rev = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + rev;
        
        int n = combined.length();
        int[] fail = new int[n];
        
        for (int i = 1; i < n; i++) {
            int j = fail[i - 1];
            while (j > 0 && combined.charAt(i) != combined.charAt(j)) {
                j = fail[j - 1];
            }
            if (combined.charAt(i) == combined.charAt(j)) {
                j++;
            }
            fail[i] = j;
        }
        
        int longestPalinPrefixLen = fail[n - 1];
        String suffixToAdd = s.substring(longestPalinPrefixLen);
        String reversedSuffix = new StringBuilder(suffixToAdd).reverse().toString();
        
        return reversedSuffix + s;
    }
}