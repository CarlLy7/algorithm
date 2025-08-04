package hot100;

import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.04
 * @Since: 1.0
 */

public class day20250804Solution {
    // [24] 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        ListNode other = head.next.next;
        second.next = first;
        first.next = swapPairs(other);
        return second;
    }

    // [25] K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            // 最后不够k个了，直接返回
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverseNode(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * 反转[a,b)区间内的节点元素
     * 
     * @param a
     * @param b
     * @return
     */
    private ListNode reverseNode(ListNode a, ListNode b) {
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

    // [32] 最长有效括号
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // dp[i]:以s[i-1]结尾的字符串中最长有效括号的长度
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                dp[i + 1] = 0;
            } else {
                if (!stack.isEmpty()) {
                    int leftIndex = stack.pop();
                    // 如果有合法的右括号的时候，要顺便记录以s[leftIndex-1]结尾的子结果
                    int len = i - leftIndex + 1 + dp[leftIndex];
                    dp[i + 1] = len;
                } else {
                    dp[i + 1] = 0;
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
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
