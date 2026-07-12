import java.util.*;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> lastIndex = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (lastIndex.containsKey(nums[i])) {
                int prevIndex = lastIndex.get(nums[i]);
                if (i - prevIndex <= k) {
                    return true;
                }
            }
            lastIndex.put(nums[i], i);
        }
        
        return false;
    }
}