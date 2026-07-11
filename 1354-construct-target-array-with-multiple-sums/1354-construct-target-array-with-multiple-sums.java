import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public boolean isPossible(int[] target){
        int n=target.length;
        if (n==1) {
            return target[0]==1;
        }
        long total=0;
        PriorityQueue<Long> maxHeap=new PriorityQueue<>(Collections.reverseOrder());
        for(int t : target){
            total+=t;
            maxHeap.offer((long)t);
        }
        while (true){
            long x=maxHeap.poll();
            long rest=total-x;
            if (x==1||rest==1) {
                return true;
            }
        
            if (rest == 0 || x <= rest){
                return false;
            }

            long newX = x % rest;
            if (newX == 0) {
                return false;
            }
            
            total = newX + rest;
            maxHeap.offer(newX);
        }
    }
}