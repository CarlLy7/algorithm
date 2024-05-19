package linked;

/**
 * @description:
 * @author: lyq
 * @createDate: 19/3/2023
 * @version: 1.0
 */
public class MergerSortedLinked {
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
        //与链表相关的题目，因为要返回一个头结点，所以我们一般就是创建一个辅助的头结点，到时候直接利用这个辅助头结点来返回就可以了
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if(p1.val> p2.val){
                //将p连向小的那个结点
                p.next=p2;
                //p2要往后移动
                p2=p2.next;
            }else{
                p.next=p1;
                //p1都要往后移动
                p1=p1.next;
            }
            //p指针需要后移来进行拼接
            p=p.next;
        }
        if(p1!=null){
            p.next=p1;
        }
        if(p2!=null){
            p.next=p2;
        }
        return dummy.next;
    }
}
