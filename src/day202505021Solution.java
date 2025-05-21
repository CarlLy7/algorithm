import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.21
 * @Since: 1.0
 */

public class day202505021Solution {
    // 91 解码方法
    public int numDecodings(String s) {
        int n = s.length();
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            char c = s.charAt(i - 1), d = s.charAt(i - 2);
            if ('1' <= c && c <= '9') {
                dp[i] += dp[i - 1];
            }
            if (d == '1' || d == '2' && c <= '6') {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    // 99 恢复二叉搜索树
    TreeNode first = null, second = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    /**
     * 因为二叉搜索树的中序遍历是有序的，所以无序的就是需要交换的两个节点
     * 
     * @param root
     */
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (pre.val > root.val) {
            // 第一次出现错误的时候，将first设置为pre
            if (first == null) {
                first = pre;
            }
            // 后序更新second为root
            second = root;
        }
        pre = root;
        inorder(root.right);
    }

    // 107 二叉树的层序遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                level.add(curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            // 每一层放到头部，最后的结果就是从下往上了
            res.addFirst(level);
        }
        return res;
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
