package linked;

/**
 * @description: 判断链表中是否有环，以及如果有环，返回环的起点
 * @author: lyq
 * @createDate: 20/3/2023
 * @version: 1.0
 */
public class isHasCycle {
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
    public ListNode getStart(ListNode head){
        if(head==null){
            return null;
        }
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow)
                break;
        }
        if(fast.next==null || fast==null){
            return null;
        }
        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
}
