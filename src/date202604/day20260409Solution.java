package date202604;

import java.util.HashMap;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.09
 * @Since: 1.0
 */

public class day20260409Solution {
    // [3] 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        // 字母->出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int n = s.length();
        int len = 0;
        while (right < n) {
            // 扩大窗口
            char c = s.charAt(right);
            right++;
            map.put(c, map.getOrDefault(c, 0) + 1);
            // 收缩窗口
            while (map.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                map.put(d, map.get(d) - 1);
            }
            if (len < right - left) {
                len = right - left;
            }
        }
        return len;
    }

    // [2] 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位
        int digist = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (p1 != null || p2 != null || digist > 0) {
            int val = digist;
            if (p1 != null) {
                val += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                val += p2.val;
                p2 = p2.next;
            }
            digist = val / 10;
            val = val % 10;
            ListNode newNode = new ListNode(val);
            p.next = newNode;
            p = p.next;
        }
        return dummy.next;
    }

    // [445] 两数相加 II
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        // 存放第一个链表中的数
        Stack<Integer> stack1 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        // 存放第二个链表中的数
        Stack<Integer> stack2 = new Stack<>();
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        // 进位
        int digiest = 0;
        ListNode dummy = new ListNode(-1);
        while (!stack1.isEmpty() || !stack2.isEmpty() || digiest > 0) {
            int val = digiest;
            if (!stack1.isEmpty()) {
                val += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                val += stack2.pop();
            }
            digiest = val / 10;
            val = val % 10;
            ListNode newNode = new ListNode(val);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        return dummy.next;
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
