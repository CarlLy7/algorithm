/**
 * @author: carl
 * @date: 2025/1/14
 */

public class day20250114Solution {

    //283
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        int slow = 0, fast = 0;
        while (fast < n) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        for (; slow < n; slow++) {
            nums[slow] = 0;
        }
    }

    //27
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        if (n == 0) {
            return n;
        }
        int slow = 0, fast = 0;
        while (fast < n) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    //83
//    public ListNode deleteDuplicates(ListNode head){
//        if (head==null ||head.next==null){
//            return head;
//        }
//        ListNode slow=head,fast=head;
//        while(fast!=null){
//            if (slow.val!= fast.val){
//                slow.next=fast;
//                slow=slow.next;
//            }
//            fast=fast.next;
//        }
//        slow.next=null;
//        return head;
//    }

    //26
//    public int removeDuplicates(int[] nums){
//        int n=nums.length;
//        if (n<=1){
//            return n;
//        }
//        int slow=0,fast=0;
//        while(fast<n){
//            if (nums[slow]!=nums[fast]){
//                slow++;
//                nums[slow]=nums[fast];
//            }
//            fast++;
//        }
//        return slow+1;
//    }

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
//
//    //160
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        ListNode p1 = headA;
//        ListNode p2 = headB;
//        while (p1 != p2) {
//            if (p1 == null) {
//                p1 = headB;
//            } else {
//                p1 = p1.next;
//            }
//            if (p2 == null) {
//                p2 = headA;
//            } else {
//                p2 = p2.next;
//            }
//        }
//        return p1;
//    }
}
