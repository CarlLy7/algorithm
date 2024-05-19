package tree;

/**
 * @description: 修剪二叉搜索树 https://leetcode.cn/problems/trim-a-binary-search-tree/
 * @author: lyq
 * @createDate: 24/3/2023
 * @version: 1.0
 */
public class xiuJianTree {
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

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null){
            return null;
        }
        if(root.val<low){
            return trimBST(root.right,low,high);
        }else if(root.val>high){
            return trimBST(root.left,low,high);
        }else{
            root.left=trimBST(root.left,low,high);
            root.right=trimBST(root.right,low,high);
            return root;
        }
    }
}
