package tree;

/**
 * @description: 二叉搜索树的最近公共祖先 https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * @author: lyq
 * @createDate: 24/5/2023
 * @version: 1.0
 */
public class lowestCommonAncestorOfABinarySearchTree {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //我们要利用好二叉搜索树的左小右大的特点
        int val1=Math.min(p.val,q.val);
        int val2=Math.max(p.val,q.val);
        return find(root,val1,val2);
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
          if(root==null){
              return null;
          }
          if(root.val>val2){
              //去左子树找
              return find(root.left,val1,val2);
          }
          if(root.val<val1){
              //去右子树找
              return find(root.right,val1,val2);
          }
          //如果 val1<=root.val<=val2，说明root就是最近父节点了
        return root;
    }
}
