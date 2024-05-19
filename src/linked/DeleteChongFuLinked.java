package linked;

/**
 * @description:
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class DeleteChongFuLinked {
     public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null){
            if(slow.val!=fast.val){
                slow.next=fast;
                slow=slow.next;
            }
            fast=fast.next;
        }
        //需要断开的
        slow.next=null;
        return head;
    }
}
