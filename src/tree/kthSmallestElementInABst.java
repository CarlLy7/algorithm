package tree;

/**
 * @description: 二叉搜索树中第K小的元素 https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 * @author: lyq
 * @createDate: 20/5/2023
 * @version: 1.0
 */
public class kthSmallestElementInABst {
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

    int count = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if(root==null){
            return;
        }
        traverse(root.left,k);
        count++;
        if(count==k){
            res=root.val;
            return;
        }
        traverse(root.right,k);
    }
}
