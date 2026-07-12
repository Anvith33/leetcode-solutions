import java.util.*;

class Solution {
    HashMap<Character,String> map=new HashMap<>();
    HashSet<String> used=new HashSet<>();

    public boolean wordPatternMatch(String pattern, String s) {
        return backtrack(pattern,0,s,0);
    }
    private boolean backtrack(String pattern, int i, String s, int j) {
        if (i==pattern.length() && j==s.length()) {
            return true;
        }
        if (i==pattern.length()||j==s.length()) {
            return false;
        }
        char ch = pattern.charAt(i);
        if (map.containsKey(ch)) {
            String word=map.get(ch);
            if (!s.startsWith(word,j)) {
                return false;
            }
            return backtrack(pattern,i+1,s,j+word.length());
        }
        for (int k=j;k<s.length();k++) {
            String word=s.substring(j,k+1);
            if (used.contains(word)) {
                continue;
            }
            map.put(ch,word);
            used.add(word);
            if (backtrack(pattern,i+1,s,k+1)) {
                return true;
            }
            map.remove(ch);
            used.remove(word);
        }
        return false;
    }
}