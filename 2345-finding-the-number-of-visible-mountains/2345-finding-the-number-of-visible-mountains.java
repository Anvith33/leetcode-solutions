class Solution{
    public int visibleMountains(int[][] peaks){

        Arrays.sort(peaks,(a,b)->{
            int left1=a[0]-a[1];
            int left2=b[0]-b[1];
            if(left1!=left2)
                return left1-left2;
            int right1=a[0]+a[1];
            int right2=b[0]+b[1];
            return right2-right1;
        });
         int ans=0;
        int maxRight=Integer.MIN_VALUE;

        for (int i=0;i<peaks.length;i++){

            int left=peaks[i][0]-peaks[i][1];
            int right=peaks[i][0]+peaks[i][1];
            boolean duplicate=false;
            if (i+1<peaks.length){
                int nextLeft=peaks[i+1][0]-peaks[i+1][1];
                int nextRight=peaks[i+1][0]+peaks[i+1][1];
                if (left==nextLeft&&right==nextRight){
                    duplicate=true;
                }
            }
            if(right>maxRight&&!duplicate){
                ans++;
            }
            maxRight=Math.max(maxRight,right);
        }
        return ans;
    }
}
