package linked;

/**
 * @description: 两个链表的第一个重合节点 https://leetcode.cn/problems/3u1WK4/
 * @author: lyq
 * @createDate: 20/3/2023
 * @version: 1.0
 */
public class GetFirstIntesectorNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        int lenA=0,lenB=0;
//      for(ListNode p1=headA;p1!=null;p1=p1.next){
//          lenA++;
//      }
//        for(ListNode p2=headB;p2!=null;p2=p2.next){
//            lenB++;
//        }
//        ListNode p1=headA;
//        ListNode p2=headB;
//        if(lenA>lenB){
//            for (int i = 0; i < lenA-lenB; i++) {
//                p1=p1.next;
//            }
//        }else{
//            for (int i = 0; i < lenB-lenA; i++) {
//                p2=p2.next;
//            }
//        }
//        while(p1!=p2){
//            p1=p1.next;
//            p2=p2.next;
//        }
//        return p1;
//    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
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
}
