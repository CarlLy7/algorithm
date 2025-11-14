package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.14
 * @Since: 1.0
 */

public class day20251114Solution {
    // [559] N 叉树的最大深度
    int res = 0;
    int depth = 0;

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        traverse(root);
        return res;
    }

    /**
     * 返回深度
     * 
     * @param root
     * @return
     */
    private int traverse(Node root) {
        if (root == null) {
            return 0;
        }
        depth++;
        if (root.children.size() == 0) {
            res = Math.max(res, depth);
            return depth;
        }
        for (Node child : root.children) {
            traverse(child);
            depth--;
        }
        return depth;
    }

    // [112] 路径总和
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // [113] 路径总和 II
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int targetSum;
    int pathSum = 0;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        if (root == null) {
            return new ArrayList<>();
        }
        traverse(root);
        return ans;
    }

    /**
     * 遍历二叉树找到路径和等于targetSum的路径
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        pathSum += root.val;
        path.addLast(root.val);
        if (root.left == null && root.right == null && pathSum == targetSum) {
            ans.add(new ArrayList<>(path));
        }
        traverse(root.left);
        traverse(root.right);
        pathSum -= root.val;
        path.removeLast();
    }

    // *********************
    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

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
