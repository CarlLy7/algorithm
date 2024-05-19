package linked;

/**
 * @description: 回文链表 https://leetcode.cn/problems/palindrome-linked-list/
 * @author: lyq
 * @createDate: 9/5/2023
 * @version: 1.0
 */
public class palindromeLinkedList {
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
    private ListNode reverse(ListNode head){
        ListNode pre,cur,next;
        pre=null;
        cur=next=head;
        while(next!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }
//破坏原始链表结构的代码
    public boolean isPalindrome(ListNode head) {
        //定义快慢指针来找到链表的中点
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            //如果上面的循环中是因为fast.next==null退出的，说明这个链表个数是一个奇数
            //这里的反转的起点还要后移一位
            slow = slow.next;
        }
        //反转后面的链表
        ListNode newHead = reverse(slow);
        ListNode left = head;
        ListNode right = newHead;
        while (right != null) {
            if(left.val!=right.val){
                return false;
            }
            left=left.next;
            right=right.next;
        }
        return true;
    }
    //没有破坏原始链表结构的代码
//public boolean isPalindrome(ListNode head) {
//    //定义快慢指针来找到链表的中点
//    ListNode slow, fast;
//    slow = fast = head;
//    ListNode p=new ListNode();
//    while (fast != null && fast.next != null) {
//        p.next=slow;
//        p=p.next;
//        slow = slow.next;
//        fast = fast.next.next;
//    }
//    if (fast != null) {
//        //如果上面的循环中是因为fast.next==null退出的，说明这个链表个数是一个奇数
//        //这里的反转的起点还要后移一位
//        p=slow;
//        slow = slow.next;
//    }
//    //反转后面的链表
//    ListNode newHead = reverse(slow);
//    ListNode left = head;
//    ListNode right = newHead;
//    while (right != null) {
//        if(left.val!=right.val){
//            return false;
//        }
//        left=left.next;
//        right=right.next;
//    }
//    p.next=reverse(newHead);
//    return true;
//}
}
