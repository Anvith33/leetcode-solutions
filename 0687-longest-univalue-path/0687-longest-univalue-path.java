class Solution {
    private int maxLength = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxLength;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftLength = dfs(node.left);
        int rightLength = dfs(node.right);
        
        int leftArm = 0, rightArm = 0;
        
        if (node.left != null && node.left.val == node.val) {
            leftArm = leftLength + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            rightArm = rightLength + 1;
        }
        
        maxLength = Math.max(maxLength, leftArm + rightArm);
        
        return Math.max(leftArm, rightArm);
    }
}