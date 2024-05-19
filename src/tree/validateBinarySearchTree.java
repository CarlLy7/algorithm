package tree;

/**
 * @description: 验证二叉搜索树 https://leetcode.cn/problems/validate-binary-search-tree/
 * @author: lyq
 * @createDate: 20/5/2023
 * @version: 1.0
 */
public class validateBinarySearchTree {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
        public boolean isValidBST(TreeNode root) {
              return isValidBST(root,null,null);
        }

        private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
              if(root==null){
                  return true;
              }
              if(min!=null && root.val<=min.val) return false;
              if(max!=null && root.val>=max.val) return false;
              return isValidBST(root.left,min,root) && isValidBST(root.right,root,max);
        }
}
