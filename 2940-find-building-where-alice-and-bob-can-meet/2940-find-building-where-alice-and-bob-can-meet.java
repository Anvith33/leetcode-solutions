import java.util.*;

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        int n = heights.length;
        int m = queries.length;
        int[] ans = new int[m];
        Arrays.fill(ans, -1);

        List<int[]>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {

            int a = queries[i][0];
            int b = queries[i][1];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (a == b || heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                list[b].add(new int[]{Math.max(heights[a], heights[b]), i});
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);

        for (int i = 0; i < n; i++) {

            while (!pq.isEmpty() && pq.peek()[0] < heights[i]) {
                int[] cur = pq.poll();
                ans[cur[1]] = i;
            }

            for (int[] q : list[i]) {
                pq.offer(q);
            }
        }

        return ans;
    }
}