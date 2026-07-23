class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // Smallest power of 2 strictly greater than n
        int bitLength = 32 - Integer.numberOfLeadingZeros(n);
        return 1 << bitLength;
    }
}