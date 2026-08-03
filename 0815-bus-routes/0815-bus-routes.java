import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        
        int n = routes.length;
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();
        
        for (int busId = 0; busId < n; busId++) {
            for (int stop : routes[busId]) {
                stopToBuses.computeIfAbsent(stop, k -> new ArrayList<>()).add(busId);
            }
        }
        
        boolean[] visitedBus = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        for (int busId : stopToBuses.getOrDefault(source, Collections.emptyList())) {
            if (!visitedBus[busId]) {
                visitedBus[busId] = true;
                queue.offer(busId);
            }
        }
        
        int busCount = 0;
        Set<Integer> stopVisited = new HashSet<>();
        stopVisited.add(source);
        
        while (!queue.isEmpty()) {
            busCount++;
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int busId = queue.poll();
                
                for (int stop : routes[busId]) {
                    if (stop == target) {
                        return busCount;
                    }
                    
                    if (!stopVisited.contains(stop)) {
                        stopVisited.add(stop);
                 
                        for (int nextBus : stopToBuses.getOrDefault(stop, Collections.emptyList())) {
                            if (!visitedBus[nextBus]) {
                                visitedBus[nextBus] = true;
                                queue.offer(nextBus);
                            }
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}