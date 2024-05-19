package linked;

/**
 * @description: 单链表的倒数第 k 个节点
 * @author: lyq
 * @createDate: 20/3/2023
 * @version: 1.0
 */
public class ReciprocalKLinked {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null){
            return null;
        }
        ListNode p1=head;
        ListNode p2=head;
        for (int i = 0; i < k; i++) {
            p1=p1.next;
        }
        while(p1!=null){
            p1=p1.next;
            p2=p2.next;
        }
        return p2;
    }
}
