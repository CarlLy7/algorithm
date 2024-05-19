package linked;

/**
 * @description: 反转前N个节点
 * @author: lyq
 * @createDate: 8/5/2023
 * @version: 1.0
 */
public class reverseFirstNLinkedList {
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

    ListNode suffix = null;//后缀节点

    public ListNode reverseN(ListNode head, int n) {
        if(n==1){
            suffix=head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next=head;
        head.next=suffix;
        return last;
    }
}
