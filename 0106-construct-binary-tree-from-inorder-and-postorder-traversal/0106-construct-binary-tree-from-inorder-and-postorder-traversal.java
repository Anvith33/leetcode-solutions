import java.util.*;

class Solution {

    int postIndex;
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        postIndex = postorder.length - 1;

        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {

        if (left > right)
            return null;

        int value = postorder[postIndex--];

        TreeNode root = new TreeNode(value);

        int index = map.get(value);

    
        root.right = build(postorder, index + 1, right);

      
        root.left = build(postorder, left, index - 1);

        return root;
    }
}