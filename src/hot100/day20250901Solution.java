package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.29
 * @Since: 1.0
 */

public class day20250901Solution {
    // [236] 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftSub = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSub = lowestCommonAncestor(root.right, p, q);
        if (leftSub != null && rightSub != null) {
            return root;
        }
        return leftSub != null ? leftSub : rightSub;
    }

    // [238] 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // 前缀积
        int[] prefix = new int[n];
        // 后缀积
        int[] suffix = new int[n];
        prefix[0] = nums[0];
        suffix[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        int[] res = new int[n];
        res[0] = suffix[1];
        res[n - 1] = prefix[n - 2];
        for (int i = 1; i < n - 1; i++) {
            res[i] = prefix[i - 1] * suffix[i + 1];
        }
        return res;
    }

    // [239] 滑动窗口最大值
    private class MonotonicQueue {
        private LinkedList<Integer> queue = new LinkedList<>();

        // 维护一个单调递减队列
        public void push(int val) {
            while (!queue.isEmpty() && queue.getLast() < val) {
                queue.pollLast();
            }
            queue.addLast(val);
        }

        public int getMax() {
            return queue.getFirst();
        }

        public void pop(int n) {
            if (n == queue.getFirst()) {
                queue.pollFirst();
            }
        }

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue monotonicQueue = new MonotonicQueue();
        List<Integer> track = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                monotonicQueue.push(nums[i]);
            } else {
                monotonicQueue.push(nums[i]);
                track.add(monotonicQueue.getMax());
                monotonicQueue.pop(nums[i - k + 1]);
            }
        }
        int[] res = new int[track.size()];
        for (int i = 0; i < track.size(); i++) {
            res[i] = track.get(i);
        }
        return res;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
