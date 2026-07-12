import java.util.Random;

class Solution {
    private final Random random = new Random();
    
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int targetIndex = n - k; 
        return quickSelect(nums, 0, n - 1, targetIndex);
    }
    
    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        while (left < right) {
            int pivotValue = nums[left + random.nextInt(right - left + 1)];
            
           
            int lt = left, gt = right, i = left;
            while (i <= gt) {
                if (nums[i] < pivotValue) {
                    swap(nums, lt++, i++);
                } else if (nums[i] > pivotValue) {
                    swap(nums, i, gt--);
                } else {
                    i++;
                }
            }
            
            if (targetIndex < lt) {
                right = lt - 1;
            } else if (targetIndex > gt) {
                left = gt + 1;
            } else {
        
                return pivotValue;
            }
        }
        
        return nums[left];
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}