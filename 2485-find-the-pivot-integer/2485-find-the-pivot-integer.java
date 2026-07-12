class Solution {
    public int pivotInteger(int n) {
        long s = (long) n * (n + 1) / 2;
        long r = (long) Math.sqrt(s);
        if (r * r == s) return (int) r;
        return -1;
    }
}
