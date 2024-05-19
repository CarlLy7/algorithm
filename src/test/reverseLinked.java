package test;

/**
 * @description: 反转链表
 * @author: lyq
 * @createDate: 28/3/2023
 * @version: 1.0
 */
public class reverseLinked {
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
        ListNode pre=null;
        ListNode cur=head;
        ListNode next=head.next;
        while(next.next!=null){
            cur.next=pre;
            pre=cur;
            cur=next;
            next=next.next;
        }
        cur.next=pre;
        next.next=cur;
        return next;
    }
}
