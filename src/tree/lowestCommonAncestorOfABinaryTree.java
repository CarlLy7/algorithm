package tree;

/**
 * @description: 二叉树的最近公共祖先 https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * @author: lyq
 * @createDate: 24/5/2023
 * @version: 1.0
 */
public class lowestCommonAncestorOfABinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return find(root, p.val, q.val);
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        if (root.val == val1 || root.val == val2) {
            return root;
        }
        //去找根节点的左右子树
        TreeNode leftNode = find(root.left, val1, val2);
        TreeNode rightNode = find(root.right, val1, val2);
        if(leftNode!=null && rightNode!=null){
            return root;
        }
        return leftNode!=null?leftNode:rightNode;
    }

}
