import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.29
 * @Since: 1.0
 */

public class day20250429Solution {

    // 230 二叉搜索树中第 K 小的元素
    int res = -1;
    // 当前是第几个数
    int rank = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        // 中序位置
        rank++;
        if (rank == k) {
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }


    // 215 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return a - b;
        });
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            // 只保留K个元素，所以最后是最大的K个元素，而堆顶又是这K个最大元素中最小的
            while (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    // 226 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 分解子问题：翻转左子树
        TreeNode left = invertTree(root.left);
        // 分解子问题：翻转右子树
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public class TreeNode {
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
