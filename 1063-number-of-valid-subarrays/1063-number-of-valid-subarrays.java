class Solution{
    public int validSubarrays(int[] nums){
        Stack<Integer> stack=new Stack<>();
        int ans=0;
        for(int i=0;i<nums.length;i++){
            while(!stack.isEmpty() && nums[stack.peek()]>nums[i]){
                ans+=i-stack.pop();
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            ans+=nums.length-stack.pop();
        }
        return ans;
    }
}
