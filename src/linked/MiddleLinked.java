package linked;

/**
 * @description: 单链表的中点  https://leetcode.cn/problems/middle-of-the-linked-list/
 * @author: lyq
 * @createDate: 20/3/2023
 * @version: 1.0
 */
public class MiddleLinked {
      public class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode middleNode(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        //其中fast!=null当节点个数为偶数的时候    fast.next!=null 用来处理当节点个数为奇数的时候
        while(fast!=null && fast.next!=null){
           slow=slow.next;
           fast=fast.next.next;
        }
        return slow;
    }

}
