import java.util.*;

class Solution {
    public String findSmallestRegion(List<List<String>> regions,
                                     String region1,
                                     String region2) {

        Map<String, String> parent = new HashMap<>();

        // Build child -> parent mapping
        for (List<String> region : regions) {
            String p = region.get(0);

            for (int i = 1; i < region.size(); i++) {
                parent.put(region.get(i), p);
            }
        }

        // Store region1 and all its ancestors
        Set<String> ancestors = new HashSet<>();

        String current = region1;

        while (current != null) {
            ancestors.add(current);
            current = parent.get(current);
        }

        // Move upward from region2
        current = region2;

        while (!ancestors.contains(current)) {
            current = parent.get(current);
        }

        return current;
    }
}