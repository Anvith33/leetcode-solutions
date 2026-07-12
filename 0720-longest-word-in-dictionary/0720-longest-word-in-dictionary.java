import java.util.*;

class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        });
        
        Set<String> buildable = new HashSet<>();
        buildable.add(""); 
        
        String result = "";
        
        for (String word : words) {
            String prefix = word.substring(0, word.length() - 1);
            if (buildable.contains(prefix)) {
                buildable.add(word);
                if (word.length() > result.length()) {
                    result = word;
                }
            }
        }
        
        return result;
    }
}