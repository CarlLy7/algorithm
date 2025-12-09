package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.09
 * @Since: 1.0
 */

public class day20251209Solution {
    // [141] 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 相遇
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // [142] 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 有环，相遇之后两个人同步频移动，再次相遇就是第一个环点
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    // [876] 链表的中间结点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
