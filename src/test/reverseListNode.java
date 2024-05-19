package test;

/**
 * @description:
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class reverseListNode {
    class ListNode{
        int val;
        ListNode next=null;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public static ListNode reverse(ListNode head){
        if(head==null){
            return null;
        }
        if(head.next==null){
            return head;
        }
        ListNode pre=null;
        ListNode p1=head;
        ListNode p2=head.next;
        while(p2.next!=null){
            p1.next=pre;
            pre=p1;
            p1=p2;
            p2=p2.next;
        }
        p1.next=pre;
        p2.next=p1;
        return p2;
    }
}
