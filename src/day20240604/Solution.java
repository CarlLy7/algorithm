package day20240604;

import java.util.Random;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-04 13:50
 * @version: 1.0
 */
public class Solution {
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

//    ListNode left;
//
//    public boolean isPalindrome(ListNode head) {
//        if (head == null || head.next == null) {
//            return true;
//        }
//        left = head;
//        return traverse(head);
//    }
//
//    private boolean traverse(ListNode right) {
//        if (right == null) {
//            return true;
//        }
//        boolean res = traverse(right.next);
//        res = res && (left.val == right.val);
//        left = left.next;
//        return res;
//    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
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

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = cur;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    private int[] preSum;
    private Random random = new Random();

    public Solution(int[] w) {
        int n = w.length;
        preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + w[i - 1];
        }
    }

    public int pickIndex() {
        int n = preSum.length;
        // 随机生成一个[1,preSum[n-1]]之间的数
        int target = random.nextInt(preSum[n - 1]) + 1;
        return leftBound(preSum, target) - 1;
    }

    private int leftBound(int[] preSum, int target) {
        int left = 0, right = preSum.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (preSum[mid] == target) {
                right = mid - 1;
            } else if (preSum[mid] < target) {
                left = mid + 1;
            } else if (preSum[mid] > target) {
                right = mid - 1;
            }
        }
        return left;
    }
}
