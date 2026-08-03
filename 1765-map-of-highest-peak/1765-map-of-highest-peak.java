import java.util.*;

class Solution {
    public int[][] highestPeak(int[][] isWater) {

        int m = isWater.length;
        int n = isWater[0].length;

        int[][] height = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(height[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];

            for (int[] d : dir) {

                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && height[nx][ny] == -1) {

                    height[nx][ny] = height[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return height;
    }
}