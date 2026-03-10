package date202603;

import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.10
 * @Since: 1.0
 */

public class day20260310Solution {
    // [71] 简化路径
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] split = path.split("/");
        for (String s : split) {
            // 多个连续的/是需要去重的
            if (s.isEmpty() || s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(s);
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    // [143] 重排链表
    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        p = head;
        while (!stack.isEmpty()) {
            ListNode lastNode = stack.pop();
            ListNode next = p.next;
            // next==lastNode是偶数节点结束标志，lastNode.next==next是奇数节点结束标志
            if (next == lastNode || lastNode.next == next) {
                lastNode.next = null;
                break;
            }
            lastNode.next = next;
            p.next = lastNode;
            p = next;
        }
    }

    // [20] 有效的括号
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            }
            if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            }
            if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
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
