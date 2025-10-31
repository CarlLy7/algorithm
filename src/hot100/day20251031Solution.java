package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.31
 * @Since: 1.0
 */

public class day20251031Solution {
    // [257] 二叉树的所有路径
    // LinkedList<String> path = new LinkedList<>();
    // LinkedList<String> res = new LinkedList<>();
    //
    // public List<String> binaryTreePaths(TreeNode root) {
    // traverse(root);
    // return res;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // if (root.left == null && root.right == null) {
    // path.addLast(root.val + "");
    // res.addLast(String.join("->", path));
    // path.removeLast();
    // return;
    // }
    // // 前序位置
    // path.addLast(root.val + "");
    // traverse(root.left);
    // traverse(root.right);
    // path.removeLast();
    // }

    // [129] 求根节点到叶节点数字之和
    // int res = 0;
    // StringBuilder path = new StringBuilder();
    //
    // public int sumNumbers(TreeNode root) {
    // traverse(root);
    // return res;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // if (root.left == null && root.right == null) {
    // path.append(root.val);
    // res += Integer.parseInt(path.toString());
    // path.deleteCharAt(path.length() - 1);
    // return;
    // }
    // path.append(root.val);
    // traverse(root.left);
    // traverse(root.right);
    // path.deleteCharAt(path.length() - 1);
    // }

    // [199] 二叉树的右视图
    // public List<Integer> rightSideView(TreeNode root) {
    // Queue<TreeNode> queue = new LinkedList<>();
    // List<Integer> res = new ArrayList<>();
    // if (root == null) {
    // return res;
    // }
    // queue.offer(root);
    // while (!queue.isEmpty()) {
    // int size = queue.size();
    // for (int i = 0; i < size; i++) {
    // TreeNode curNode = queue.poll();
    // if (i == 0) {
    // res.add(curNode.val);
    // }
    // if (curNode.right != null) {
    // queue.offer(curNode.right);
    // }
    // if (curNode.left != null) {
    // queue.offer(curNode.left);
    // }
    // }
    // }
    // return res;
    // }

    List<Integer> res = new ArrayList<>();
    int depth = 0;

    public List<Integer> rightSideView(TreeNode root) {
        traverse(root);
        return res;
    }

    /**
     * 二叉树遍历的思路可以理解为回溯算法
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        depth++;
        // res中的元素个数应该和二叉树的层数是一样的
        if (res.size() < depth) {
            res.add(root.val);
        }
        traverse(root.right);
        traverse(root.left);
        // 后序位置，要往回走，让父节点去处理另一个分支了，所以depth--
        depth--;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
