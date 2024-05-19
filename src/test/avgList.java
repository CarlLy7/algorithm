package test;

/**
 * @description: 合并两个排序的链表
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class avgList {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode avg(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = null; //最后要返回的头结点
        ListNode cur = null;//当前节点的位置
        while (list1 != null && list2 != null) {
            if(list1.val<= list2.val){
                if(head==null){
                    head=cur=list1;
                }else{
                    cur.next=list1;
                    cur=list1;
                }
                list1=list1.next;
            }else{
                if(head==null){
                    head=cur=list2;
                }else{
                    cur.next=list2;
                    cur=list2;
                }
                list2=list2.next;
            }
        }
        if(list1!=null){
            cur.next=list1;
        }
        if(list2!=null){
            cur.next=list2;
        }
        return head;
    }
}
