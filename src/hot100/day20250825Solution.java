package hot100;

import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.25
 * @Since: 1.0
 */

public class day20250825Solution {
    // [155] 最小栈
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            if (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.push(val);
            }
            stack.push(val);
        }

        public void pop() {
            if (minStack.peek().equals(stack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // [160] 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    // [169] 多数元素
    public int majorityElement(int[] nums) {
        // 众数
        int target = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                target = nums[i];
                count = 1;
            } else if (nums[i] != target) {
                count--;
            } else if (nums[i] == target) {
                count++;
            }
        }
        return target;
    }


    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
