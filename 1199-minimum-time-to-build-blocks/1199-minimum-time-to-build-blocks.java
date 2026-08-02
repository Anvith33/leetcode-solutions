import java.util.PriorityQueue;

class Solution {
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int block : blocks) {
            pq.offer(block);
        }

        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            pq.offer(second + split);
        }

        return pq.poll();
    }
}