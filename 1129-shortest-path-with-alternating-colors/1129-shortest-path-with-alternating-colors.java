import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<Integer>> redGraph = new ArrayList<>();
        List<List<Integer>> blueGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            redGraph.add(new ArrayList<>());
            blueGraph.add(new ArrayList<>());
        }
        for (int[] edge : redEdges) {
            redGraph.get(edge[0]).add(edge[1]);
        }
        for (int[] edge : blueEdges) {
            blueGraph.get(edge[0]).add(edge[1]);
        }
        
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
      
        boolean[][] visited = new boolean[n][2];
        
        Queue<int[]> queue = new LinkedList<>();
    
        queue.offer(new int[]{0, 0, 0}); 
        queue.offer(new int[]{0, 1, 0}); 
        visited[0][0] = true;
        visited[0][1] = true;
        
        answer[0] = 0;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int colorState = curr[1];
            int dist = curr[2];
            
            List<Integer> neighbors = (colorState == 0) ? redGraph.get(node) : blueGraph.get(node);
            int nextColorState = 1 - colorState;
            
            for (int next : neighbors) {
                if (!visited[next][colorState]) {
                    visited[next][colorState] = true;
                    if (answer[next] == -1) {
                        answer[next] = dist + 1;
                    }
                    queue.offer(new int[]{next, nextColorState, dist + 1});
                }
            }
        }
        
        return answer;
    }
}