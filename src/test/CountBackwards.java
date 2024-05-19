package test;

/**
 * @description: 输入一个链表，输出该链表中倒数第k个结点
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class CountBackwards {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode getListNode(ListNode head, int index) {
        if (head == null || index <= 0) {
            return null;
        }
        ListNode p1 = head;//p1是后走的指针
        ListNode p2=p1;//p2是先走的指针
        for (int i = 0; i < index-1; i++) {
            p2=p2.next;
        }
        while(p2.next!=null){
            p2=p2.next;
            p1=p1.next;
        }
        return p1;
    }

}
