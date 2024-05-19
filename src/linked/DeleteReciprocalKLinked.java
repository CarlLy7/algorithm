package linked;

/**
 * @description: 删除链表的倒数第 N 个结点 https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * @author: lyq
 * @createDate: 20/3/2023
 * @version: 1.0
 */
public class DeleteReciprocalKLinked {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode removeNthFromEnd(ListNode head, int n) {
            if(head==null){
                return null;
            }
            ListNode dummy=new ListNode(-1);
            dummy.next=head;
            ListNode p1=dummy;
            ListNode p2=dummy;
        for (int i = 0; i <=n; i++) {
            p1=p1.next;
        }
        while(p1!=null){
            p1=p1.next;
            p2=p2.next;
        }
        p2.next=p2.next.next;
        return dummy.next;
    }
}
