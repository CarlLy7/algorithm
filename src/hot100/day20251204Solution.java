package hot100;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.04
 * @Since: 1.0
 */

public class day20251204Solution {
    // [1305] 两棵二叉搜索树中的所有元素
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        BSTIterator bstIterator = new BSTIterator(root1);
        BSTIterator bstIterator2 = new BSTIterator(root2);
        List<Integer> res = new ArrayList<>();
        while (bstIterator.hasNext() && bstIterator2.hasNext()) {
            if (bstIterator.stack.peek().val > bstIterator2.stack.peek().val) {
                res.add(bstIterator2.next());
            } else {
                res.add(bstIterator.next());
            }
        }
        while (bstIterator.hasNext()) {
            res.add(bstIterator.next());
        }
        while (bstIterator2.hasNext()) {
            res.add(bstIterator2.next());
        }
        return res;
    }

    private class BSTIterator {
        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            this.stack = new Stack<>();
            pushLeftBranch(root);
        }

        private void pushLeftBranch(TreeNode root) {
            if (root == null) {
                return;
            }
            TreeNode p = root;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Integer next() {
            TreeNode pop = stack.pop();
            pushLeftBranch(pop.right);
            return pop.val;
        }
    }

    // [215] 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    // [23] 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!queue.isEmpty()) {
            ListNode curListNode = queue.poll();
            p.next = curListNode;
            p = p.next;
            if (curListNode.next != null) {
                queue.offer(curListNode.next);
            }
        }
        return dummy.next;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
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
