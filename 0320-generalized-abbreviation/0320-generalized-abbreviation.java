import java.util.*;

class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), word, 0, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String word, int pos, int count) {
        int len = current.length();
        
        if (pos == word.length()) {
            if (count > 0) current.append(count);
            result.add(current.toString());
        } else {
           
            backtrack(result, current, word, pos + 1, count + 1);

            
            if (count > 0) current.append(count);
            current.append(word.charAt(pos));
            backtrack(result, current, word, pos + 1, 0);
        }
        
        current.setLength(len); 
    }
}
