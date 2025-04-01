import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.01
 * @Since: 1.0
 */

public class day20250401Solution {

    // 19 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode lastNNode = findLastNNode(dummy, n + 1);
        lastNNode.next = lastNNode.next.next;
        return dummy.next;
    }

    /**
     * 找到倒数第n个节点
     * 
     * @param head
     * @param n
     * @return
     */
    private ListNode findLastNNode(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 20 有效的括号
    public boolean isValid(String s) {
        Stack<Character> left = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                left.push(c);
            } else {
                if (!left.isEmpty() && matchLeft(c) == left.peek()) {
                    left.pop();
                } else {
                    return false;
                }
            }
        }
        return left.isEmpty() ? true : false;
    }

    /**
     * 通过右括号匹配对应的左括号
     * 
     * @param right
     * @return
     */
    private Character matchLeft(Character right) {
        if (right == ')') {
            return '(';
        } else if (right == ']') {
            return '[';
        } else {
            return '{';
        }
    }

    /**
     * 21 合并两个有序链表<br>
     * 不需要断开原始节点之间的next指针的原因，我们的p会跑到新的节点，然后p的next是会变的，也就把原始的节点的next指针自动改了
     * 
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
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

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
