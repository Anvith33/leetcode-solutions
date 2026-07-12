import java.util.*;

class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        int[][] indexedEdges = new int[m][4];
        for (int i = 0; i < m; i++) {
            indexedEdges[i] = new int[]{edges[i][0], edges[i][1], edges[i][2], i};
        }
        
        Arrays.sort(indexedEdges, (a, b) -> a[2] - b[2]);
        
        int baseWeight = kruskal(n, indexedEdges, -1, -1);
        
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            int originalIndex = indexedEdges[i][3];
            
        
            int weightExcluded = kruskal(n, indexedEdges, i, -1);
            if (weightExcluded > baseWeight || weightExcluded == -1) {
                critical.add(originalIndex);
                continue;
            }
            
          
            int weightIncluded = kruskal(n, indexedEdges, -1, i);
            if (weightIncluded == baseWeight) {
                pseudoCritical.add(originalIndex);
            }
        }
        
        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudoCritical);
        return result;
    }

    private int kruskal(int n, int[][] edges, int skipIdx, int forceIdx) {
        UnionFind uf = new UnionFind(n);
        int weight = 0;
        int edgeCount = 0;
        
        if (forceIdx != -1) {
            uf.union(edges[forceIdx][0], edges[forceIdx][1]);
            weight += edges[forceIdx][2];
            edgeCount++;
        }
        
        for (int i = 0; i < edges.length; i++) {
            if (i == skipIdx || i == forceIdx) {
                continue;
            }
            
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            if (uf.union(u, v)) {
                weight += w;
                edgeCount++;
            }
        }
        
        return (edgeCount == n - 1) ? weight : -1;
    }
    
    class UnionFind {
        int[] parent, rank;
        
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            
            if (rank[rootX] < rank[rootY]) {
                int temp = rootX; rootX = rootY; rootY = temp;
            }
            parent[rootY] = rootX;
            if (rank[rootX] == rank[rootY]) rank[rootX]++;
            
            return true;
        }
    }
}