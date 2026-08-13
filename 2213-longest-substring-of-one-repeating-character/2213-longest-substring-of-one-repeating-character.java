class Solution {

    static class Node {
        int len;

        char leftChar;
        char rightChar;

        int leftCount;
        int rightCount;

        int best;

        Node(int len, char c) {
            this.len = len;
            this.leftChar = c;
            this.rightChar = c;
            this.leftCount = len;
            this.rightCount = len;
            this.best = len;
        }
    }

    private Node[] tree;
    private char[] arr;

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();
        int q = queryIndices.length;

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index);

            answer[i] = tree[1].best;
        }

        return answer;
    }

    private void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(1, arr[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int left, int right, int index) {

        if (left == right) {
            tree[node] = new Node(1, arr[index]);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, right, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {

        Node res = new Node(a.len + b.len, a.leftChar);

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Prefix
        res.leftCount = a.leftCount;

        if (a.leftCount == a.len && a.rightChar == b.leftChar) {
            res.leftCount = a.len + b.leftCount;
        }

        // Suffix
        res.rightCount = b.rightCount;

        if (b.rightCount == b.len && a.rightChar == b.leftChar) {
            res.rightCount = b.len + a.rightCount;
        }

        // Best inside either half
        res.best = Math.max(a.best, b.best);

        // Best substring crossing the middle
        if (a.rightChar == b.leftChar) {
            res.best = Math.max(
                res.best,
                a.rightCount + b.leftCount
            );
        }

        return res;
    }
}