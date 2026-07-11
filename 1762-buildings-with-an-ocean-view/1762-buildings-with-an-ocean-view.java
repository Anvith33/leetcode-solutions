class Solution {
    public int[] findBuildings(int[] heights) {
        ArrayList<Integer> list=new ArrayList<>();
        int maxHeight=0;
        for(int i=heights.length-1;i>=0;i--){
            if(heights[i]>maxHeight){
                list.add(i);
                maxHeight=heights[i];
            }
        }
         Collections.reverse(list);
        int[] ans=new int[list.size()];
        for (int i=0;i<list.size();i++) {
            ans[i]=list.get(i);
        }
        return ans;


        
    }
}