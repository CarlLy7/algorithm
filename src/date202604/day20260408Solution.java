package date202604;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.08
 * @Since: 1.0
 */

public class day20260408Solution {
    // [42] 接雨水
    public int trap(int[] height) {
        int n = height.length;
        // 左侧最大的元素
        int[] leftMax = new int[n];
        // 右侧最大的元素
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    // [82] 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode q = head;
        while (q != null) {
            // 出现了重复元素
            if (q.next != null && q.next.val == q.val) {
                // 去掉重复元素区间
                while (q.next != null && q.val == q.next.val) {
                    q = q.next;
                }
                // 因为重复元素一个都不保留，所以还需要跳一次
                q = q.next;
                if (q == null) {
                    p.next = null;
                }
            } else {
                p.next = q;
                p = p.next;
                q = q.next;
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
}
