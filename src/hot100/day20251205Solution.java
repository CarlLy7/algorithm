package hot100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.05
 * @Since: 1.0
 */

public class day20251205Solution {
    // 使用栈来模拟二叉树的迭代遍历

    // 使用迭代的方法进行二叉树的后序遍历
    Stack<TreeNode> stack = new Stack<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode visited = new TreeNode(-1);
        pushLeftBranch(root);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            // 左子树遍历完了，但是右子树没有遍历
            if ((peek.left == null || peek.left == visited) && peek.right != visited) {
                // 中序位置
                pushLeftBranch(root.right);
            }
            // 左右子树都遍历完了
            if (peek.right == null || peek.right == visited) {
                // 后序位置
                res.add(peek.val);
                visited = stack.pop();
            }
        }
        return res;
    }

    private void pushLeftBranch(TreeNode root) {
        TreeNode p = root;
        while (p != null) {
            // 前序位置
            stack.push(p);
            p = p.left;
        }
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
