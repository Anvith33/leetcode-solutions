import java.util.*;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {

        HashSet<Integer>[] graph = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
        }

        int[] degree = new int[n + 1];

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph[u].add(v);
            graph[v].add(u);

            degree[u]++;
            degree[v]++;
        }

        List<Integer> odd = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) {
                odd.add(i);
            }
        }

        if (odd.size() == 0)
            return true;

        if (odd.size() == 2) {
            int a = odd.get(0);
            int b = odd.get(1);

            if (!graph[a].contains(b))
                return true;

            for (int i = 1; i <= n; i++) {
                if (i != a && i != b &&
                    !graph[a].contains(i) &&
                    !graph[b].contains(i)) {
                    return true;
                }
            }
            return false;
        }

        if (odd.size() == 4) {
            int a = odd.get(0);
            int b = odd.get(1);
            int c = odd.get(2);
            int d = odd.get(3);

            if (!graph[a].contains(b) && !graph[c].contains(d))
                return true;

            if (!graph[a].contains(c) && !graph[b].contains(d))
                return true;

            if (!graph[a].contains(d) && !graph[b].contains(c))
                return true;
        }

        return false;
    }
}