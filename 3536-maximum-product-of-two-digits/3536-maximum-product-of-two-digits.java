class Solution {
    public int maxProduct(int n) {
        // Extract digits
        int[] digits = new int[10]; // frequency of digits 0-9
        while (n > 0) {
            digits[n % 10]++;
            n /= 10;
        }

        // Find the two largest digits
        int first = -1, second = -1;
        for (int d = 9; d >= 0; d--) {
            if (digits[d] > 0) {
                if (first == -1) {
                    first = d;
                    // If digit appears more than once, it can be used twice
                    if (digits[d] > 1) {
                        second = d;
                        break;
                    }
                } else {
                    second = d;
                    break;
                }
            }
        }

        return first * second;
    }
}
