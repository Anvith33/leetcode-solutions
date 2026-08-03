import java.util.*;

class Solution {
    private int maxQuality = 0;
    private int[] values;
    private Map<Integer, List<int[]>> graph;
    private boolean[] visited;
    
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        this.values = values;
        int n = values.length;
        graph = new HashMap<>();
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], t = edge[2];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, t});
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, t});
        }
        
        visited = new boolean[n];
        visited[0] = true;
        
        dfs(0, maxTime, values[0]);
        
        return maxQuality;
    }
    
    private void dfs(int node, int remainingTime, int currentQuality) {
        if (node == 0) {
            maxQuality = Math.max(maxQuality, currentQuality);
        }
        
        List<int[]> neighbors = graph.getOrDefault(node, Collections.emptyList());
        for (int[] next : neighbors) {
            int nextNode = next[0];
            int time = next[1];
            
            if (time > remainingTime) {
                continue;
            }
            
            boolean wasVisited = visited[nextNode];
            int addedValue = wasVisited ? 0 : values[nextNode];
            
            visited[nextNode] = true;
            dfs(nextNode, remainingTime - time, currentQuality + addedValue);
            visited[nextNode] = wasVisited; // backtrack
        }
    }
}