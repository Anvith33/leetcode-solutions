class Solution {
    int maxi = 0;

    private int pathLen(TreeNode root, int val) {
        if (root == null || root.val != val) return 0;

        return 1 + Math.max(
            pathLen(root.left, val),
            pathLen(root.right, val)
        );
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        int left = pathLen(root.left, root.val);
        int right = pathLen(root.right, root.val);

        maxi = Math.max(maxi, left + right);

        dfs(root.left);
        dfs(root.right);
    }

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxi;
    }
}