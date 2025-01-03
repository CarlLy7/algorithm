/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-11-11 20:29
 * @version: 1.0
 */
public class day20241111Solution {
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

    // a->b->c
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode successor = null;

    // 翻转前N个节点
    private ListNode reverseN(ListNode head, int n) {
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
        if (head == null) return head;
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            // 不够k个直接返回
            if (b == null) return head;
            b = b.next;
        }
        ListNode newHead = reverseN(head, k);
        a.next = reverseKGroup(b, k);
        return newHead;
    }
}
