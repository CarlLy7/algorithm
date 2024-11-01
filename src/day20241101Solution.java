import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-11-01 20:18
 * @version: 1.0
 */
public class day20241101Solution {
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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHigh = maxDepth(root.left);
        int rightHigh = maxDepth(root.right);
        return Math.max(leftHigh, rightHigh) + 1;
    }

//    List<Integer> ans = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
//        traverse(root);
//        return ans;
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> leftList = preorderTraversal(root.left);
        List<Integer> rightList = preorderTraversal(root.right);
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        ans.addAll(leftList);
        ans.addAll(rightList);
        return ans;
    }

//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        ans.add(root.val);
//        traverse(root.left);
//        traverse(root.right);
//    }
}
