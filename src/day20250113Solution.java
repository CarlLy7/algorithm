/**
 * @author: carl
 * @date: 2025/1/13
 */

public class day20250113Solution {
    private class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //142
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


//    public boolean hasCycle(ListNode head) {
//        ListNode slow=head,fast=head;
//        while(fast!=null && fast.next!=null){
//            slow=slow.next;
//            fast=fast.next.next;
//            if (slow==fast){
//                return true;
//            }
//        }
//        return false;
//    }

    //876
    // 1 2 3 4
    // 1 2 3
//    public ListNode middleNode(ListNode head){
//        ListNode slow=head,fast=head;
//        while(fast!=null && fast.next!=null){
//            slow=slow.next;
//            fast=fast.next.next;
//        }
//        return slow;
//    }

    // 19
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode dummy=new ListNode(-1);
//        dummy.next=head;
//        ListNode beforeOneNode = findFromEnd(dummy, n + 1);
//        beforeOneNode.next=beforeOneNode.next.next;
//        return dummy.next;
//    }
//
//    public ListNode findFromEnd(ListNode head, int k) {
//        ListNode p1 = head;
//        for (int i = 0; i < k; i++) {
//            p1 = p1.next;
//        }
//        ListNode p = head;
//        while (p1 != null) {
//            p1 = p1.next;
//            p = p.next;
//        }
//        return p;
//    }

}
