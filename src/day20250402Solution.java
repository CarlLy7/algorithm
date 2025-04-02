import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: 括号生成
 * @author: carl
 * @date: 2025.04.02
 * @Since: 1.0
 */

public class day20250402Solution {
    // 22
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder track = new StringBuilder();
        backTrack(n, n, track, ans);
        return ans;
    }

    /**
     * 
     * @param left 左括号可以使用的数量
     * @param right 右括号可以使用的数量
     * @param track 路径
     * @param ans 结果
     */
    private void backTrack(int left, int right, StringBuilder track, List<String> ans) {
        if (left > right) {
            return;
        }
        if (left < 0 || right < 0) {
            return;
        }
        if (left == 0 && right == 0) {
            ans.add(track.toString());
            return;
        }
        // 放左括号
        track.append('(');
        backTrack(left - 1, right, track, ans);
        // 撤销选择
        track.deleteCharAt(track.length() - 1);

        // 放右括号
        track.append(')');
        backTrack(left, right - 1, track, ans);
        track.deleteCharAt(track.length() - 1);
    }

    // 23 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 小顶堆优先级队列
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!queue.isEmpty()) {
            ListNode curNode = queue.poll();
            p.next = curNode;
            if (curNode.next != null) {
                queue.offer(curNode.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    // 24 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        ListNode other = head.next.next;
        second.next = first;
        // 递归处理下一个子问题
        first.next = swapPairs(other);
        return second;
    }

    // 25 K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode a = head, b = head;
        // 左闭右开区间
        for (int i = 0; i < k; i++) {
            // 剩下的不够进行分组了，直接返回head
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
     * 翻转链表
     * 
     * @param a
     * @param b
     * @return
     */
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null;
        ListNode cur = a, nxt = a;
        // 当 当前节点到达边界的时候就不去翻转了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 翻转整个链表
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head, nxt = head;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
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
