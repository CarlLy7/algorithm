package tree;



/**
 * @description: 把二叉搜索树转换为累加树 https://leetcode.cn/problems/convert-bst-to-greater-tree/
 * @author: lyq
 * @createDate: 20/5/2023
 * @version: 1.0
 */
public class convertBstToGreaterTree {
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
    int sum=0;
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    //因为要转成累加树，所以我们利用从大到小输出的顺序将值从大到小输出然后累加起来赋值给当前这个节点就是所有大于等于这个值的节点的和
    private void traverse(TreeNode root) {
        if(root==null){
            return;
        }
        traverse(root.right);
        //中序位置
        sum+=root.val;
        root.val=sum;
        traverse(root.left);
    }
}
