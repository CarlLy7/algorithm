package tree;

/**
 * @description: 二叉搜索树中的插入操作 https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 * @author: lyq
 * @createDate: 20/5/2023
 * @version: 1.0
 */
public class insertIntoABinarySearchTree {
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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            return new TreeNode(val);
        }
        if(root.val>val){
            root.left=insertIntoBST(root.left,val);
        }
        if(root.val<val){
            root.right=insertIntoBST(root.right,val);
        }
        return root;
    }
}
