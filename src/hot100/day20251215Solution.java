package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.15
 * @Since: 1.0
 */

public class day20251215Solution {
    // [25] K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // base case: 剩下的元素不够k个了，就不翻转了
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * 翻转[a,b)区间
     * 
     * @param a
     * @param b
     * @return
     */
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null;
        ListNode cur = a;
        ListNode nxt = a;
        while (cur != b) {
            nxt = nxt.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // [26] 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                // 因为第一个元素肯定要有，所以这里先++
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    // [92] 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    ListNode successor = null;

    /**
     * 递归翻转链表前n个节点
     * 
     * @param head
     * @param n
     * @return
     */
    // private ListNode reverseN(ListNode head, int n) {
    // // base case
    // if (n == 1) {
    // successor = head.next;
    // return head;
    // }
    // ListNode lastNode = reverseN(head.next, n - 1);
    // head.next.next = head;
    // head.next = successor;
    // return lastNode;
    // }

    /**
     * 使用迭代方法返回链表的【left,right】节点
     * 
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        // 如果left=1就变成了翻转链表前n个节点
        if (left == 1) {
            return reverseN(head, right);
        }
        // 如果left!=1,那么需要先找到第一个节点，然后再转成翻转链表前n个节点
        ListNode first = head;
        for (int i = 1; i < left - 1; i++) {
            first = first.next;
        }
        first.next = reverseN(first.next, right - left + 1);
        return head;
    }

    /**
     * 迭代方法翻转链表前n个节点
     * 
     * @param head
     * @param n
     * @return
     */
    private ListNode reverseN(ListNode head, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = head.next;
        while (n > 0) {
            cur.next = pre;
            pre = cur;
            cur = nxt;
            if (nxt != null) {
                nxt = nxt.next;
            }
            n--;
        }
        head.next = cur;
        return pre;
    }

    private class ListNode {
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
