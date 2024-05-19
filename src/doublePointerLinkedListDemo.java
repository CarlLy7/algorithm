import java.util.PriorityQueue;

/**
 * @description: 双指针技巧秒杀七道链表题目
 * @author: lyq
 * @createDate: 9/5/2023
 * @version: 1.0
 */
public class doublePointerLinkedListDemo {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode p1 = list1, p2 = list2;
        ListNode dummy = new ListNode(-1), p = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }

    //------------------------------
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode p1, p2, p;
        p1 = dummy1;
        p2 = dummy2;
        p = head;
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            //断开原链表中的所有节点
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }
        p1.next = dummy2.next;
        return dummy1.next;
    }

    //--------------------------------------
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(
                lists.length, (a, b) -> (a.val - b.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            if (node.next != null) {
                queue.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    //-------------------------------
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //------------------------------------------------
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //创建一个辅助节点，防止删除的是第一个元素，发生空指针异常
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        if (head == null || n == 0) {
            return head;
        }
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    //-------------------------
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    //---------------------------
    public boolean hasCycle(ListNode head) {
        if(head==null || head.next==null){
            return false;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }

    //-------------------
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)break;
        }
        if(fast==null||fast.next==null){
            return null;
        }
        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }

    //--------------------------
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1=headA;
        ListNode p2=headB;
        while(p1!=p2){
           if(p1==null){
               p1=headB;
           }else{
               p1=p1.next;
           }
           if(p2==null){
               p2=headA;
           }else{
               p2=p2.next;
           }
        }
        return p1;
    }

    //-----------------
    ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){
            return null;
        }
        int lenA=0,lenB=0;
        for (ListNode p1=headA;p1.next!=null;p1=p1.next){
            lenA++;
        }
        for (ListNode p2=headB;p2.next!=null;p2=p2.next){
            lenB++;
        }
        ListNode p1=headA;
        ListNode p2=headB;
        if(lenA>lenB){
            for (int i = 0; i < lenA - lenB; i++) {
                p1=p1.next;
            }
        }else{
            for (int i = 0; i < lenB-lenA; i++) {
                p2=p2.next;
            }
        }
        while(p1!=p2){
            p1=p1.next;
            p2=p2.next;
        }
        return p1;
    }


}
