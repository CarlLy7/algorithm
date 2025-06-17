import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.17
 * @Since: 1.0
 */

public class day20250617Solution {
    // [442] 数组中重复的数据
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new LinkedList<>();
        for (int num : nums) {
            // 如果出现过，我们将对应索引位置的元素乘以负数,如果不是负数则说明第一次出现
            if (nums[Math.abs(num) - 1] < 0) {
                res.add(Math.abs(num));
            } else {
                nums[Math.abs(num) - 1] *= -1;
            }
        }
        return res;
    }

    // [445] 两数相加 II
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 用栈的先进后出的特性来模拟链表翻转，从而从低位进行相加
        Stack<Integer> stack1 = new Stack<>();
        while (l1 != null) {
            stack1.add(l1.val);
            l1 = l1.next;
        }

        Stack<Integer> stack2 = new Stack<>();
        while (l2 != null) {
            stack2.add(l2.val);
            l2 = l2.next;
        }
        ListNode dummy = new ListNode(-1);
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int val = carry;
            if (!stack1.isEmpty()) {
                val += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                val += stack2.pop();
            }
            carry = val / 10;
            val = val % 10;
            ListNode newNode = new ListNode(val);
            // 使用头插法,高位是在左边的
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        return dummy.next;
    }

    // [515] 在每个树行中找最大值
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int curMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curMax = Math.max(curMax, curNode.val);
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            res.add(curMax);
        }
        return res;
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
