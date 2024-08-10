package day20240810;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-10 16:13
 * @version: 1.0
 */
public class Solution {
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

//    LinkedList<String> res = new LinkedList<>();
//    LinkedList<String> track = new LinkedList<>();
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
//        if (root.left == null && root.right == null) {
//            track.addLast(String.valueOf(root.val));
//            res.add(String.join("->", track));
//            track.removeLast();
//            return;
//        }
//        track.addLast(root.val + "");
//        traverse(root.left);
//        traverse(root.right);
//        track.removeLast();
//    }

//    int res = 0;
//    StringBuilder track = new StringBuilder();
//
//    public int sumNumbers(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        if (root.left == null && root.right == null) {
//            track.append(root.val);
//            res += Integer.parseInt(track.toString());
//            track.deleteCharAt(track.length() - 1);
//            return;
//        }
//        track.append(root.val);
//        traverse(root.left);
//        traverse(root.right);
//        track.deleteCharAt(track.length() - 1);
//    }

    List<Integer> res = new ArrayList<>();
    int depth = 0;

    public List<Integer> rightSideView(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //前序位置
        depth++;
        if (res.size() < depth) {
            res.add(root.val);
        }
        traverse(root.right);
        traverse(root.left);
        depth--;
    }
}
