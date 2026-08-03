import java.util.*;

class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<List<long[]>> graph = new ArrayList<>();
        List<List<long[]>> reverseGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new long[]{v, w});
            reverseGraph.get(v).add(new long[]{u, w});
        }
        
        long[] dist1 = dijkstra(graph, src1, n);
        long[] dist2 = dijkstra(graph, src2, n);
        long[] dist3 = dijkstra(reverseGraph, dest, n);
        
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist1[i] != Long.MAX_VALUE && dist2[i] != Long.MAX_VALUE && dist3[i] != Long.MAX_VALUE) {
                ans = Math.min(ans, dist1[i] + dist2[i] + dist3[i]);
            }
        }
        
        return ans == Long.MAX_VALUE ? -1 : ans;
    }
    
    private long[] dijkstra(List<List<long[]>> graph, int start, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{start, 0});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int node = (int) curr[0];
            long d = curr[1];
            
            if (d > dist[node]) {
                continue;
            }
            
            for (long[] edge : graph.get(node)) {
                int next = (int) edge[0];
                long weight = edge[1];
                long newDist = d + weight;
                
                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pq.offer(new long[]{next, newDist});
                }
            }
        }
        
        return dist;
    }
}