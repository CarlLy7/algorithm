package hot100;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.31
 * @Since: 1.0
 */

public class day20250731Solution {
    // [21] 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                ListNode nxt = p1.next;
                p1.next = null;
                p1 = nxt;
            } else {
                p.next = p2;
                ListNode nxt = p2.next;
                p2.next = null;
                p2 = nxt;
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

    // [23] 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 优先级队列，用来按顺序(从小到大)存储节点
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        while (!queue.isEmpty()) {
            ListNode curNode = queue.poll();
            ListNode newNode = new ListNode(curNode.val);
            p.next = newNode;
            p = p.next;
            if (curNode.next != null) {
                queue.offer(curNode.next);
            }
        }
        return dummy.next;
    }

    // [22] 括号生成
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder track = new StringBuilder();
        backTrack(n, n, track, res);
        return res;
    }

    /**
     * 
     * @param left:剩下多少个( 可以用
     * @param right: 剩下多少个) 可以用
     * @param track
     * @param res
     */
    private void backTrack(int left, int right, StringBuilder track, List<String> res) {
        // 因为从左到右，左括号的数量肯定大于等于右括号的数量，所以左括号的剩余数量不可能大于右括号数量
        if (left > right) {
            return;
        }
        if (left < 0 || right < 0) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(track.toString());
            return;
        }
        // 做选择
        track.append('(');
        backTrack(left - 1, right, track, res);
        // 撤销
        track.deleteCharAt(track.length() - 1);

        // 做选择
        track.append(')');
        backTrack(left, right - 1, track, res);
        track.deleteCharAt(track.length() - 1);
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
