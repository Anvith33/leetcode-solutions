import java.util.*;

class Graph {
    private List<List<int[]>> adj;
    private int n;
    
    public Graph(int n, int[][] edges) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
    }
    
    public void addEdge(int[] edge) {
        adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
    }
    
    public int shortestPath(int node1, int node2) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[node1] = 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{node1, 0});
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int node = (int) curr[0];
            long d = curr[1];
            
            if (d > dist[node]) {
                continue;
            }
            
            if (node == node2) {
                return (int) d;
            }
            
            for (int[] edge : adj.get(node)) {
                int next = edge[0];
                int weight = edge[1];
                long newDist = d + weight;
                
                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pq.offer(new long[]{next, newDist});
                }
            }
        }
        
        return dist[node2] == Long.MAX_VALUE ? -1 : (int) dist[node2];
    }
}

