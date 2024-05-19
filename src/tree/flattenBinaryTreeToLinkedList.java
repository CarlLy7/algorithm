package tree;

/**
 * @description: 二叉树展开为链表 https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 * @author: lyq
 * @createDate: 12/5/2023
 * @version: 1.0
 */
public class flattenBinaryTreeToLinkedList {
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
  //函数的定义：给定一个节点root,以root为根，将这个二叉树拉成一个链表
    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        //后序位置
        TreeNode left=root.left;
        TreeNode right=root.right;
        root.left=null;
        root.right=left;
        //将右子树拉成的链表连接在当前这个链表的右边
        TreeNode p=root;
        while(p.right!=null){
            p=p.right;
        }
        p.right=right;
    }
}
