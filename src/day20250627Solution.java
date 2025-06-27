import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.27
 * @Since: 1.0
 */

public class day20250627Solution {

    // [958] 二叉树的完全性检验
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 是否是最后一个节点
        boolean end = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode == null) {
                    end = true;
                } else {
                    // 如果之前已经访问到了一个null的节点，但是后面还有其他节点，说明不是完全二叉树
                    if (end) {
                        return false;
                    }
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }
            }
        }
        return true;
    }

    // [1161] 最大层内元素和
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        int depth = 1;
        int maxSum = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int curSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curSum += curNode.val;
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            if (curSum > maxSum) {
                maxSum = curSum;
                res = depth;
            }
            depth++;
        }
        return res;
    }

    // [1302] 层数最深叶子节点的和
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        int maxDepth = -1;
        int res = 0;
        if (root == null) {
            return 0;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                levelSum += curNode.val;
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            if (depth > maxDepth) {
                maxDepth = depth;
                res = levelSum;
            }
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
