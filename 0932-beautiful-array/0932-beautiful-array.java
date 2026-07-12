import java.util.*;

class Solution {
    private Map<Integer, int[]> memo = new HashMap<>();
    
    public int[] beautifulArray(int n) {
        return f(n);
    }
    
    private int[] f(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        if (n == 1) {
            int[] base = {1};
            memo.put(1, base);
            return base;
        }
        
        int[] left = f((n + 1) / 2);   
        int[] right = f(n / 2);       
        
        int[] result = new int[n];
        int idx = 0;
        
        for (int x : left) {
            result[idx++] = 2 * x - 1;
        }
        for (int x : right) {
            result[idx++] = 2 * x;
        }
        
        memo.put(n, result);
        return result;
    }
}