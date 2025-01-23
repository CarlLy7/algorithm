import java.util.ArrayList;
import java.util.List;

/**
 * @author: carl
 * @date: 2025/1/22
 */

public class day20250112Solution {

    //543
    int res=0;
    public int diameterOfBinaryTree(TreeNode root){
        maxDepth(root);
        return res;
    }

    private int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        int curMax=leftDepth+rightDepth;
        res=Math.max(res,curMax);
        return 1+Math.max(leftDepth,rightDepth);
    }

    //144
//    List<Integer> res=new ArrayList<>();
//    public List<Integer> preorderTraversal(TreeNode root){
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root==null){
//            return;
//        }
//        res.add(root.val);
//        traverse(root.left);
//        traverse(root.right);
//    }


    //104


//    int res=0;
//    int depth=0;
//    public int maxDepth(TreeNode root){
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root==null){
//            return;
//        }
//        depth++;
//        if (root.left==null && root.right==null){
//            res=Math.max(res,depth);
//        }
//        traverse(root.left);
//        traverse(root.right);
//        depth--;
//    }

    private class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
