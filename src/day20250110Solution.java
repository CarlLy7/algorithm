import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: carl
 * @date: 2025/1/10
 */

public class day20250110Solution {
    private class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    //23
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        Queue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        while (!queue.isEmpty()) {
            ListNode curNode = queue.poll();
            p.next = curNode;
            p = p.next;
            if (curNode.next != null) {
                queue.offer(curNode.next);
            }
        }
        return dummy.next;
    }

    //86
//    public ListNode partition(ListNode head, int x) {
//        ListNode dummy1 = new ListNode(-1);
//        ListNode dummy2 = new ListNode(-1);
//        ListNode p = head;
//        //小于x的节点
//        ListNode p1 = dummy1;
//        //大于等于x的节点
//        ListNode p2 = dummy2;
//        while (p != null) {
//            if (p.val < x) {
//                p1.next = p;
//                p1 = p1.next;
//            } else {
//                p2.next = p;
//                p2 = p2.next;
//            }
//            ListNode temp = p.next;
//            p.next = null;
//            p = temp;
//        }
//        p1.next = dummy2.next;
//        return dummy1.next;
//    }

    //21
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode(-1);
//        ListNode p = dummy;
//        ListNode p1 = l1, p2 = l2;
//        while (p1 != null && p2 != null) {
//            if (p1.val <= p2.val) {
//                p.next = p1;
//                p1 = p1.next;
//            } else {
//                p.next = p2;
//                p2 = p2.next;
//            }
//            p = p.next;
//        }
//        if (p1 != null) {
//            p.next = p1;
//        }
//        if (p2 != null) {
//            p.next = p2;
//        }
//        return dummy.next;
//    }
}
