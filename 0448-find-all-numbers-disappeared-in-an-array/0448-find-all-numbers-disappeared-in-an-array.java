class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] freq=new int[nums.length+1];
        for(int i=0;i<nums.length;i++) {
            freq[nums[i]]++;
        }
        List<Integer> ans=new ArrayList<>();
        for (int i=1;i<=nums.length;i++) {
            if (freq[i]==0) {
                ans.add(i);
            }
        }
        return ans;
    }
}