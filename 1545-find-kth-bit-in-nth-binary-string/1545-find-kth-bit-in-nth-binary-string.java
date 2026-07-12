class Solution {

    public char findKthBit(int n, int k) {

        String s = "0";

        for (int i = 2; i <= n; i++) {

            StringBuilder temp = new StringBuilder();

            for (char c : s.toCharArray()) {
                if (c == '0')
                    temp.append('1');
                else
                    temp.append('0');
            }

            temp.reverse();

            s = s + "1" + temp.toString();
        }

        return s.charAt(k - 1);
    }
}