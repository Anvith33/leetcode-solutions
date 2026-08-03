import java.util.*;

class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        visited[id] = true;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        
        int currentLevel = 0;
        while (currentLevel < level && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int person = queue.poll();
                for (int friend : friends[person]) {
                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.offer(friend);
                    }
                }
            }
            currentLevel++;
        }
        
    
        Map<String, Integer> freqMap = new HashMap<>();
        for (int person : queue) {
            for (String video : watchedVideos.get(person)) {
                freqMap.merge(video, 1, Integer::sum);
            }
        }
        
        List<String> result = new ArrayList<>(freqMap.keySet());
        result.sort((a, b) -> {
            int freqA = freqMap.get(a);
            int freqB = freqMap.get(b);
            if (freqA != freqB) {
                return freqA - freqB;
            }
            return a.compareTo(b);
        });
        
        return result;
    }
}