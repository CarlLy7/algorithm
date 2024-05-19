package tree;

/**
 * @description: 翻转二叉树 https://leetcode.cn/problems/invert-binary-tree/
 * @author: lyq
 * @createDate: 12/5/2023
 * @version: 1.0
 */
public class invertBinaryTree {
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
  //这个方法的定义是：将以root为根的二叉树进行交换然后返回交换之后的根节点
    public TreeNode invertTree(TreeNode root) {
          //遍历的方法
//        traverse(root);

        //递归的方法
        if(root==null){
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left=right;
        root.right=left;
        return root;
    }


//    private void traverse(TreeNode root) {
//          if(root==null){
//              return;
//          }
//          TreeNode temp=root.left;
//          root.left=root.right;
//          root.right=temp;
//          traverse(root.left);
//          traverse(root.right);
//    }
}
