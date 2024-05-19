package linked;

/**
 * @description: 反转链表 II https://leetcode.cn/problems/reverse-linked-list-ii/
 * @author: lyq
 * @createDate: 8/5/2023
 * @version: 1.0
 */
public class reverseLinkedListIi {
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
    ListNode suffix=null;//后驱节点
    private ListNode reverseFirstN(ListNode head,int n){
        if(n==1){
            //只反转一个节点
            suffix=head.next;
            return head;
        }
        ListNode last = reverseFirstN(head.next, n - 1);
        head.next.next=head;
        head.next=suffix;
        return last;
    }
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left==1){
            //如果是从第一个节点开始反转，相当于反转开头的n个节点
            int n=right-left+1;
            return reverseFirstN(head, n);
        }else{
            //如果不是从开头开始反转，就让指针往后移动,往后移动一个节点，那么起始索引和终止索引相对于这个节点来说就是-1了
            head.next=reverseBetween(head.next,left-1,right-1);
            return head;
        }
    }
}
