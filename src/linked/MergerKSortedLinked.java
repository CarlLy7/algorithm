package linked;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: 合并 k 个有序链表 https://leetcode.cn/problems/merge-k-sorted-lists/
 * @author: lyq
 * @createDate: 19/3/2023
 * @version: 1.0
 */
public class MergerKSortedLinked {
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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length <= 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length,(a,b)->(a.val-b.val));
        ListNode dummy = new ListNode(-1), p1 = dummy;
        //下面这个循环存放的是每个链表的头结点
        for (ListNode node : lists) {
            if(node!=null){
                queue.add(node);
            }
        }
        while(!queue.isEmpty()){
            ListNode node = queue.poll();//这个节点就是最小的节点
            p1.next=node;
            p1=p1.next;
            //如果这个节点后面有节点的话，将后面这个节点添加到这个优先级队列中
            if(node.next!=null){
                queue.add(node.next);
            }
        }
        return dummy.next;
    }
}
