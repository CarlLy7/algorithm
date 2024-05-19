package linked;

/**
 * @description:
 * @author: lyq
 * @createDate: 19/3/2023
 * @version: 1.0
 */
public class FenGeLinked {

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

    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1), p1 = dummy1;
        ListNode dummy2 = new ListNode(-1), p2 = dummy2;
        ListNode p=head;
        while(p!=null){
            if(p.val<x){
                p1.next=p;
                p1=p1.next;
            }else{
                p2.next=p;
                p2=p2.next;
            }
            p=p.next;
            //注意要将原来的链表中的结点断开
//            ListNode temp=p.next;
//            p.next=null;
//            p=temp;
        }
        p1.next=dummy2.next;
        return dummy1.next;
    }
}
