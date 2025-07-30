package hot100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.30
 * @Since: 1.0
 */

public class day20250730Solution {
    // [17] 电话号码的字母组合
    String[] strings = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    LinkedList<String> res = new LinkedList<>();
    StringBuilder track = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return res;
        }
        backTrack(digits, 0);
        return res;
    }

    /**
     * 从start索引开始回溯整个digits
     * 
     * @param digits
     * @param start
     */
    private void backTrack(String digits, int start) {
        if (start >= digits.length()) {
            res.add(track.toString());
            return;
        }
        int index = digits.charAt(start) - '0';
        for (char c : strings[index].toCharArray()) {
            // 做选择
            track.append(c);
            backTrack(digits, start + 1);
            track.deleteCharAt(track.length() - 1);
        }
    }

    // [19] 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode lastN = findLastN(dummy, n + 1);
        lastN.next = lastN.next.next;
        return dummy.next;
    }

    /**
     * 从链表中找到倒数第N个节点
     * 
     * @param head
     * @param n
     * @return
     */
    private ListNode findLastN(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // [20] 有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() != map.get(c)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty() ? true : false;
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
