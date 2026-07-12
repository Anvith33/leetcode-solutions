class Solution {
    public int largestAltitude(int[] gain) {

        int current = 0;
        int highest = 0;

        for (int x : gain) {
            current += x;
            highest = Math.max(highest, current);
        }

        return highest;
    }
}