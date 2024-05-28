package day20240528;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-27 22:49
 * @version: 1.0
 */
public class Solution {
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        if (mountain.length <= 2) {
            return res;
        }
        int n = mountain.length;
        for (int i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                res.add(i);
            }
        }
        return res;
    }

//    public int longestSubarray(int[] nums, int limit) {
//        int n = nums.length;
//        MonotonicQueue window = new MonotonicQueue();
//        int left = 0, right = 0;
//        int windowSize = 0, res = 0;
//        while (right < n) {
//            window.push(nums[right]);
//            right++;
//            windowSize++;
//            //收缩
//            while (window.getMax() - window.getMin() > limit) {
//                window.pop(nums[left]);
//                left++;
//                windowSize--;
//            }
//            res = Math.max(res, windowSize);
//        }
//        return res;
//    }

    class MonotonicQueue {
        private LinkedList<Integer> maxQueue = new LinkedList<>();
        private LinkedList<Integer> minQueue = new LinkedList<>();
        private LinkedList<Integer> queue = new LinkedList<>();

        public void push(int x) {
            queue.addLast(x);
            while (!maxQueue.isEmpty() && maxQueue.getLast() < x) {
                maxQueue.removeLast();
            }
            maxQueue.addLast(x);
            while (!minQueue.isEmpty() && minQueue.getLast() > x) {
                minQueue.removeLast();
            }
            minQueue.addLast(x);
        }

        public void pop() {
            int del = queue.removeFirst();
            if (del == maxQueue.getFirst()) {
                maxQueue.removeFirst();
            }
            if (del == minQueue.getFirst()) {
                minQueue.removeFirst();
            }
        }

        public int getMax() {
            return maxQueue.getFirst();
        }

        public int getMin() {
            return minQueue.getFirst();
        }

        public int size() {
            return queue.size();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] pre = new int[2 * n + 1];
        pre[0] = 0;
        for (int i = 1; i < pre.length; i++) {
            pre[i] = pre[i - 1] + nums[(i - 1) % n];
        }
        int res = Integer.MIN_VALUE;
        MonotonicQueue window = new MonotonicQueue();
        window.push(0);
        for (int i = 1; i < pre.length; i++) {
            res = Math.max(res, pre[i] - window.getMin());
            if (window.size() == n) {
                window.pop();
            }
            window.push(pre[i]);
        }
        return res;
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxHere = nums[0];
        int maxRes = nums[0];
        for (int i = 1; i < n; i++) {
            maxHere = Math.max(nums[i], maxHere + nums[i]);
            maxRes = Math.max(maxRes, maxHere);
        }
        return maxRes;
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        // 最小的
        int[] dp1 = new int[n];
        //最大的
        int[] dp2 = new int[n];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp1[i] = Math.min(dp1[i - 1] * nums[i], Math.min(dp2[i - 1] * nums[i], nums[i]));
            dp2[i] = Math.max(dp2[i - 1] * nums[i], Math.max(dp1[i - 1] * nums[i], nums[i]));
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp2.length; i++) {
            res = Math.max(res, dp2[i]);
        }
        return res;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right - left + 1);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a = head, b = head;
        // 分组
        for (int i = 0; i < k; i++) {
            // base case: 如何当前这个不够一组，就结束了，返回head
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    // 翻转[a,b) 之间的元素，左闭右开
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null;
        ListNode cur = a;
        ListNode nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
