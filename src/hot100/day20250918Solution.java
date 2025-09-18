package hot100;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.18
 * @Since: 1.0
 */

public class day20250918Solution {
    // [234] 回文链表
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 奇数的时候，slow节点要再往前走一个
        if (fast != null) {
            slow = slow.next;
        }
        // 翻转链表
        ListNode newHead = reverse(slow);
        ListNode right = newHead;
        ListNode left = head;
        while (right != null) {
            if (right.val != left.val) {
                return false;
            }
            right = right.next;
            left = left.next;
        }
        return true;
    }

    /**
     * 翻转链表返回新的头结点
     * 
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = head;
        while (nxt != null) {
            nxt = nxt.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // [279] 完全平方数
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        // 金额
        for (int i = 1; i <= n; i++) {
            // 面值的硬币
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    // [295] 数据流的中位数
    private class MedianFinder {
        // 左边是大顶堆，这样堆顶是最大的
        PriorityQueue<Integer> left;
        // 右边是小顶堆，堆顶是最小的
        PriorityQueue<Integer> right;

        public MedianFinder() {
            left = new PriorityQueue<>((a, b) -> {
                return b - a;
            });
            right = new PriorityQueue<>();
        }

        /**
         * 添加元素：先放到多的一边，然后将多的这边多的弹出来放到少的一边，维持平衡，数量绝对值不要超过1
         * 
         * @param num
         */
        public void addNum(int num) {

            if (left.size() >= right.size()) {
                left.offer(num);
                right.offer(left.poll());
            } else {
                right.offer(num);
                left.offer(right.poll());
            }
        }

        public double findMedian() {
            if (left.size() == right.size()) {
                return (left.peek() + right.peek()) / 2.0;
            } else {
                return left.size() > right.size() ? left.peek() : right.peek();
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
