import java.util.*;

class Solution {
    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        long target = sum % p;
        if (target == 0) {
            return 0;
        }
        
        int n = nums.length;
        Map<Long, Integer> lastIndex = new HashMap<>();
        lastIndex.put(0L, -1);
        long prefix = 0;
        int minLen = n; 
        
        for (int i = 0; i < n; i++) {
            prefix = (prefix + nums[i]) % p;
            
            long needed = (prefix - target + p) % p;
            
            if (lastIndex.containsKey(needed)) {
                int len = i - lastIndex.get(needed);
                minLen = Math.min(minLen, len);
            }
            
            lastIndex.put(prefix, i);
        }
        
        return minLen < n ? minLen : -1;
    }
}