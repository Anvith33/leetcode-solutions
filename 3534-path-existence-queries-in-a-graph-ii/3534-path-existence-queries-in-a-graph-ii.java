import java.util.*;

class Solution{
    public int[] pathExistenceQueries(int n,int[] nums,int maxDiff,int[][] queries) {

        Integer[] order = new Integer[n];
        for (int i=0;i<n;i++)order[i]=i;

        Arrays.sort(order,(a,b)->Integer.compare(nums[a],nums[b]));
        int[] sv=new int[n];
        int[] pos=new int[n];
        for (int i=0;i<n;i++) {
            sv[i]=nums[order[i]];
            pos[order[i]]=i;
        }

        int[] far=new int[n];
        int j=0;

        for (int i=0;i<n;i++) {

            if (j<i)
                j=i;

            while(j+1<n&&sv[j+1]-sv[i]<=maxDiff)
                j++;
            far[i]=j;
        }
        int[] comp=new int[n];

        int c=0;

        for (int i=1;i<n;i++) {

            if (sv[i]-sv[i-1]>maxDiff)
                c++;

            comp[i]=c;
        }

        int LOG=18;

        int[][] up=new int[LOG][n];
        for (int i=0; i<n;i++)
            up[0][i]=far[i];

        for (int k=1;k<LOG;k++) {

            for (int i=0;i<n;i++) {

                up[k][i]=up[k-1][up[k-1][i]];
            }
        }

        int[] ans=new int[queries.length];

        for (int qid=0;qid<queries.length;qid++) {

            int u=queries[qid][0];
            int v=queries[qid][1];

            if (u==v) {
                ans[qid]=0;
                continue;
            }
            int pu=pos[u];
            int pv=pos[v];

            if (comp[pu]!=comp[pv]) {
                ans[qid]=-1;
                continue;
            }

            int left=Math.min(pu, pv);
            int right=Math.max(pu, pv);

            int cur=left;
            int steps=0;
            for(int k=LOG-1;k>=0;k--) {

                if(up[k][cur]<right) {
                    cur=up[k][cur];
                    steps+=(1<<k);
                }
            }

            if(cur<right)
                steps++;
            ans[qid]=steps;
        }
        return ans;
    }
}