class Solution {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        
     
        long[] suffixEven = new long[n + 1];
        long[] suffixOdd = new long[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            suffixEven[i] = suffixEven[i + 1];
            suffixOdd[i] = suffixOdd[i + 1];
            if (i % 2 == 0) {
                suffixEven[i] += nums[i];
            } else {
                suffixOdd[i] += nums[i];
            }
        }
        
        long prefixEven = 0, prefixOdd = 0;
        int count = 0;
        
        for (int i = 0; i < n; i++) {

            long afterEven = suffixEven[i + 1];
            long afterOdd = suffixOdd[i + 1];
            
       
            long newEven = prefixEven + afterOdd;
            long newOdd = prefixOdd + afterEven;
            
            if (newEven == newOdd) {
                count++;
            }
            
            if (i % 2 == 0) {
                prefixEven += nums[i];
            } else {
                prefixOdd += nums[i];
            }
        }
        
        return count;
    }
}