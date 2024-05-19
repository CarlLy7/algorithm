package tree;

/**
 * @description:  二叉搜索树中的搜索 https://leetcode.cn/problems/search-in-a-binary-search-tree/
 * @author: lyq
 * @createDate: 20/5/2023
 * @version: 1.0
 */
public class searchInABinarySearchTree {
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
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null){
            return null;
        }
        if(root.val>val){
            return searchBST(root.left,val);
        }
        if(root.val<val){
            return searchBST(root.right,val);
        }
        return root;
    }
}
