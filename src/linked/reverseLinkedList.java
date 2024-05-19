package linked;

/**
 * @description: 反转链表 https://leetcode.cn/problems/reverse-linked-list/
 * @author: lyq
 * @createDate: 8/5/2023
 * @version: 1.0
 */
public class reverseLinkedList {
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

    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode last=reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return last;
    }
}
