import java.util.*;

class Solution {
    public int largestComponentSize(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int n = nums.length;
        UnionFind uf = new UnionFind(maxVal + n + 1);
        
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int temp = num;
            
            for (int p = 2; (long) p * p <= temp; p++) {
                if (temp % p == 0) {
                    uf.union(maxVal + 1 + i, p);
                    while (temp % p == 0) {
                        temp /= p;
                    }
                }
            }
            if (temp > 1) {
                uf.union(maxVal + 1 + i, temp);
            }
        }
        
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxCount = 0;
        
        for (int i = 0; i < n; i++) {
            int root = uf.find(maxVal + 1 + i);
            int count = countMap.merge(root, 1, Integer::sum);
            maxCount = Math.max(maxCount, count);
        }
        
        return maxCount;
    }
    
    class UnionFind {
        int[] parent;
        int[] rank;
        
        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            
            if (rank[rootX] < rank[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            if (rank[rootX] == rank[rootY]) {
                rank[rootX]++;
            }
        }
    }
}