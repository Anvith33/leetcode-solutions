import java.util.*;

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        
        dfs(graph, 0, path, result);
        
        return result;
    }
    
    private void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> result) {
        int target = graph.length - 1;
        
        if (node == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int next : graph[node]) {
            path.add(next);
            dfs(graph, next, path, result);
            path.remove(path.size() - 1);
        }
    }
}