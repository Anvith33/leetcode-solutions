import java.util.*;

public class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int num = left; num <= right; num++) {
            if (isSelfDividing(num)) res.add(num);
        }
        return res;
    }

    private boolean isSelfDividing(int x) {
        int n = x;
        while (n > 0) {
            int d = n % 10;
            if (d == 0 || x % d != 0) return false;
            n /= 10;
        }
        return true;
    }
}
