import java.util.*;

class Solution{
    public int countCompleteComponents(int n,int[][] edges){
        ArrayList<Integer>[] graph=new ArrayList[n];

        for(int i=0;i<n;i++)
            graph[i]=new ArrayList<>();

        for(int[] e:edges){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        boolean[] visited=new boolean[n];

        int answer=0;

        for(int i=0;i<n;i++){
            if (!visited[i]){
                int[] data=dfs(i,graph,visited);

                int vertices=data[0];
                int degreeSum=data[1];

                int actualEdges=degreeSum/2;
                int expectedEdges=vertices*(vertices-1)/2;
                if (actualEdges==expectedEdges)
                    answer++;
            }
        }
        return answer;
    }
    private int[] dfs(int node,ArrayList<Integer>[] graph,boolean[] visited){
        visited[node]=true;
        int vertices=1;
        int degreeSum=graph[node].size();
        for (int next:graph[node]){
            if (!visited[next]) {
                int[] temp=dfs(next, graph, visited);

                vertices+=temp[0];
                degreeSum+=temp[1];
            }
        }

        return new int[]{vertices,degreeSum};
    }
}