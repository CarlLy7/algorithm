package linked;

/**
 * @description: K 个一组翻转链表 https://leetcode.cn/problems/reverse-nodes-in-k-group/
 * @author: lyq
 * @createDate: 9/5/2023
 * @version: 1.0
 */
public class reverseNodesInKGroup {
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

    //使用迭代的方法实现反转一个区间的链表的功能
    //这里就是反转[a,b)这个区间的节点
    private ListNode reverseRange(ListNode a, ListNode b) {
        //定义前驱、当前、后继节点指针
        ListNode pre, cur, next;
        pre = null;
        cur = next = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //因为是左闭右开区间，所以最后返回的是pre而不是cur
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null) {
            return head;
        }
        ListNode a, b;
        a = b = head;
        //这个循环就是去让指针往后移动我们要反转的个数来实现反转区间
        for (int i = 0; i < k; i++) {
            //如果最后不够k个节点了，就不反转了，直接结束
            if(b==null){
                return head;
            }
            b=b.next;
        }
        //最前面的这个反转后的新的头节点就是我们最后要返回的节点
        ListNode newHead = reverseRange(a, b);
        //反转之前的第一个节点变成了最后一个节点要和下一组反转的头节点连接起来
        a.next=reverseKGroup(b,k);
        return newHead;
    }
}
