package date202604;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.15
 * @Since: 1.0
 */

public class day20260415Solution {
    // [239] 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 先将前k-1个元素放入
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res.add(window.max());
                window.pop(nums[i - k + 1]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    // [234] 回文链表
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 1->2->3->4
        // 1->2->3->4->5
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 奇数需要中点再前移一个
        if (fast != null) {
            slow = slow.next;
        }
        ListNode right = reverse(slow);
        ListNode left = head;
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    /**
     * 翻转链表
     * 
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = head.next;
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = nxt;
            if (nxt != null) {
                nxt = nxt.next;
            }
        }
        return pre;
    }

    private class MonotonicQueue {
        private LinkedList<Integer> queue = new LinkedList<>();

        // 加入元素，让队列中的元素保持降序排列
        public void push(int val) {
            while (!queue.isEmpty() && queue.getLast() < val) {
                queue.pollLast();
            }
            queue.addLast(val);
        }

        /**
         * 获取最大值
         *
         * @return
         */
        public int max() {
            return queue.getFirst();
        }

        /**
         * 删除指定元素
         *
         * @param val
         * @return
         */
        public void pop(int val) {
            if (queue.getFirst() == val) {
                queue.pollFirst();
            }
        }
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
}
