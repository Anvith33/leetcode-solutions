import java.util.*;

class SummaryRanges {
    private TreeMap<Integer, Integer> intervals; 
    
    public SummaryRanges() {
        intervals = new TreeMap<>();
    }
    
    public void addNum(int value) {
       
        Map.Entry<Integer, Integer> floorEntry = intervals.floorEntry(value);
        if (floorEntry != null && floorEntry.getValue() >= value) {
            return; 
        }
        
        boolean mergeWithLeft = floorEntry != null && floorEntry.getValue() == value - 1;
        
        Map.Entry<Integer, Integer> ceilingEntry = intervals.ceilingEntry(value);
        boolean mergeWithRight = ceilingEntry != null && ceilingEntry.getKey() == value + 1;
        
        if (mergeWithLeft && mergeWithRight) {
           
            intervals.put(floorEntry.getKey(), ceilingEntry.getValue());
            intervals.remove(ceilingEntry.getKey());
        } else if (mergeWithLeft) {
       
            intervals.put(floorEntry.getKey(), value);
        } else if (mergeWithRight) {
            
            intervals.put(value, ceilingEntry.getValue());
            intervals.remove(ceilingEntry.getKey());
        } else {
       
            intervals.put(value, value);
        }
    }
    
    public int[][] getIntervals() {
        int[][] result = new int[intervals.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }
        return result;
    }
}

