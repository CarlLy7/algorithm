package tree;

/**
 * @description: 二叉树的直径 https://leetcode.cn/problems/diameter-of-binary-tree/
 * @author: lyq
 * @createDate: 11/5/2023
 * @version: 1.0
 */
public class diameterOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int res=0;
    public int diameterOfBinaryTree(TreeNode root) {
      maxDepth(root);
      return res;
    }

    private int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftMax=maxDepth(root.left);
        int rightMax=maxDepth(root.right);
        int curMax=leftMax+rightMax;
        res=Math.max(res,curMax);
        return Math.max(leftMax,rightMax)+1;
    }
}
