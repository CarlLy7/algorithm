package tree;

/**
 * @description: 二叉树的最大深度  https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 * @author: lyq
 * @createDate: 11/5/2023
 * @version: 1.0
 */
public class maximumDepthOfBinaryTree {
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

//    int res = 0;
//    int depth = 0;

    public int maxDepth(TreeNode root) {
        //解法1：通过遍历一次二叉树得到结果
//        traverse(root);
//        return res;
        //解法2：使用递归来解决
        int res=0;
        if(root==null){
            return 0;
        }
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);
        res=Math.max(left,right)+1;
        return res;
    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        //前序位置
//        depth++;
//        if (root.left == null && root.right == null) {
//            res = Math.max(res, depth);
//        }
//        traverse(root.left);
//        traverse(root.right);
//        //后序位置
//        depth--;
//    }
}
