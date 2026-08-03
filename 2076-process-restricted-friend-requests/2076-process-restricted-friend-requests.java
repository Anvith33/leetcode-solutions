class Solution {
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int m = requests.length;
        boolean[] result = new boolean[m];
        
        for (int j = 0; j < m; j++) {
            int u = requests[j][0];
            int v = requests[j][1];
            int rootU = find(parent, u);
            int rootV = find(parent, v);
            
            if (rootU == rootV) {
          
                result[j] = true;
                continue;
            }
            
            boolean violates = false;
            for (int[] restriction : restrictions) {
                int rootX = find(parent, restriction[0]);
                int rootY = find(parent, restriction[1]);
                
                if ((rootX == rootU && rootY == rootV) || (rootX == rootV && rootY == rootU)) {
                    violates = true;
                    break;
                }
            }
            
            if (violates) {
                result[j] = false;
            } else {
                result[j] = true;
                parent[rootU] = rootV;
            }
        }
        
        return result;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}