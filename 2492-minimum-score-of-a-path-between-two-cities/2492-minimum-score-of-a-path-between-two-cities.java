import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int a = road[0], b = road[1], d = road[2];
            graph.get(a).add(new int[]{b, d});
            graph.get(b).add(new int[]{a, d});
        }
        
        boolean[] visited = new boolean[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        visited[1] = true;
        int minScore = Integer.MAX_VALUE;
        
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int[] edge : graph.get(curr)) {
                int next = edge[0], dist = edge[1];
                minScore = Math.min(minScore, dist);
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
        
        return minScore;
    }
}
