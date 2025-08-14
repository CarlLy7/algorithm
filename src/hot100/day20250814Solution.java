package hot100;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.14
 * @Since: 1.0
 */

public class day20250814Solution {
    // [79] 单词搜索
    private boolean found = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, word, 0);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从board(i,j)开始寻找，是否找到word的start位置字母
     * 
     * @param board
     * @param i
     * @param j
     * @param word
     * @param start
     */
    private void dfs(char[][] board, int i, int j, String word, int start) {
        if (start == word.length()) {
            found = true;
            return;
        }
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] != word.charAt(start)) {
            return;
        }
        // 将已经访问过的元素前面添加一个负号
        board[i][j] = (char)-board[i][j];
        dfs(board, i - 1, j, word, start + 1);
        dfs(board, i + 1, j, word, start + 1);
        dfs(board, i, j - 1, word, start + 1);
        dfs(board, i, j + 1, word, start + 1);
        board[i][j] = (char)-board[i][j];
    }

    // [94] 二叉树的中序遍历
    LinkedList<Integer> res = new LinkedList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        traverse(root);
        return res;
    }

    /**
     * 遍历一遍二叉树实现返回中序遍历
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        res.add(root.val);
        traverse(root.right);
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
    // [98] 验证二叉搜索树

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 以root为根的二叉树，是否满足二叉搜索树 因为只有一个参数的时候肯定不能判断，因为我们不知道左子树的最大值，也不知道右子树的最小值，所以需要增加参数列表
     * 
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) {
            return false;
        }
        if (max != null && max.val <= root.val) {
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
}
