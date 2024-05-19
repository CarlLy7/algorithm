package linked;

/**
 * @description: 相交链表  https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * @author: lyq
 * @createDate: 20/3/2023
 * @version: 1.0
 */
public class GetIntersectLinked {
    public class ListNode {
      int val;
     ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
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
}
