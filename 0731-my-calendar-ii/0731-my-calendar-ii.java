import java.util.*;

class MyCalendarTwo {
    private List<int[]> bookings;
    private List<int[]> overlaps;
    
    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        overlaps = new ArrayList<>();
    }
    
    public boolean book(int startTime, int endTime) {
        for (int[] overlap : overlaps) {
            if (startTime < overlap[1] && endTime > overlap[0]) {
   
                return false;
            }
        }
        
        for (int[] booking : bookings) {
            int overlapStart = Math.max(startTime, booking[0]);
            int overlapEnd = Math.min(endTime, booking[1]);
            if (overlapStart < overlapEnd) {
                overlaps.add(new int[]{overlapStart, overlapEnd});
            }
        }
        
        bookings.add(new int[]{startTime, endTime});
        return true;
    }
}

