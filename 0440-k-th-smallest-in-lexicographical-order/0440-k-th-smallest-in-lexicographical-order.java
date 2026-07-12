class Solution {
    public int findKthNumber(int n, int k) {
        long curr = 1;
        k--; 
        
        while (k > 0) {
            long count = countSteps(n, curr, curr + 1);
            
            if (count <= k) {

                curr++;
                k -= count;
            } else {
          
                curr *= 10;
                k--;
            }
        }
        
        return (int) curr;
    }
    
  
    private long countSteps(int n, long prefix1, long prefix2) {
        long count = 0;
        long first = prefix1;
        long last = prefix2;
        
        while (first <= n) {
            count += Math.min(n + 1, last) - first;
            first *= 10;
            last *= 10;
        }
        
        return count;
    }
}