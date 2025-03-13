import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2025.03.13
 */

public class day20250313Solution {
    class TreeNode {
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

    // 257
//    LinkedList<String> res = new LinkedList<>();
//    LinkedList<String> path = new LinkedList<>();
//
//    public List<String> binaryTreePaths(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        // 到达叶子节点
//        if (root.left == null && root.right == null) {
//            path.addLast(root.val + "");
//            res.addLast(String.join("->", path));
//            path.removeLast();
//            return;
//        }
//        path.addLast(root.val + "");
//        traverse(root.left);
//        traverse(root.right);
//        path.removeLast();
//    }

    //129
    StringBuilder path=new StringBuilder();
    int res=0;
    public int sumNumbers(TreeNode root){
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root==null){
            return;
        }
        path.append(root.val);
        if (root.left==null && root.right==null){
            res+=Integer.parseInt(path.toString());
        }
        traverse(root.left);
        traverse(root.right);
        path.deleteCharAt(path.length()-1);
    }
}
