import java.util.*;

class StreamChecker {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }
    
    private TrieNode root;
    private StringBuilder streamBuffer;
    private int maxWordLength;
    
    public StreamChecker(String[] words) {
        root = new TrieNode();
        maxWordLength = 0;
        
        for (String word : words) {
            maxWordLength = Math.max(maxWordLength, word.length());
            insertReversed(word);
        }
        
        streamBuffer = new StringBuilder();
    }
    
    private void insertReversed(String word) {
        TrieNode curr = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }
    
    public boolean query(char letter) {
        streamBuffer.append(letter);
        
   
        if (streamBuffer.length() > maxWordLength) {
            streamBuffer.delete(0, streamBuffer.length() - maxWordLength);
        }
        
        TrieNode curr = root;
        for (int i = streamBuffer.length() - 1; i >= 0; i--) {
            int idx = streamBuffer.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
            if (curr.isEnd) {
                return true;
            }
        }
        
        return false;
    }
}


