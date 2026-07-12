class Solution {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;
        
        StringBuilder sb = new StringBuilder();
        sb.append("122"); 
        
        int idx = 2;
        int charToAppend = 1;
        
        while (sb.length() < n) {
            int groupLen = sb.charAt(idx) - '0';
            for (int i = 0; i < groupLen; i++) {
                sb.append((char) ('0' + charToAppend));
            }
            charToAppend = 3 - charToAppend;
            idx++;
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '1') {
                count++;
            }
        }
        
        return count;
    }
}