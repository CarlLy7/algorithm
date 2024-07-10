package day20240710;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-10 14:03
 * @version: 1.0
 */
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if ('(' == c || '[' == c || '{' == c) {
                stack.push(c);
            } else if (')' == c) {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (']' == c) {
                if (stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if ('}' == c) {
                if (stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || minStack.peek() > val) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (stack.peek().equals(minStack.peek())) {
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

    int index = 0;

    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        while (index < s.length()) {
            char cur = s.charAt(index);
            //如果是数字，压入栈中
            if (Character.isDigit(cur)) {
                stack.addLast(getDigits(s));
                //如果是字母和[，压入栈中
            } else if (Character.isLetter(cur) || '[' == cur) {
                stack.addLast(String.valueOf(s.charAt(index++)));
            } else {
                ++index;
                //不是数字，不是字母，不是[，是]，所以需要进行弹出了
                LinkedList<String> englishStr = new LinkedList<>();
                while (!"[".equals(stack.peekLast())) {
                    englishStr.addLast(stack.removeLast());
                }
                Collections.reverse(englishStr);
                // 弹出[
                stack.removeLast();
                int repeatCount = Integer.parseInt(stack.removeLast());
                String repeatEnglish = getString(englishStr);
                StringBuilder repeatStr = new StringBuilder();
                for (int i = 0; i < repeatCount; i++) {
                    repeatStr.append(repeatEnglish);
                }
                stack.addLast(repeatStr.toString());
            }
        }
        return getString(stack);
    }

    private String getDigits(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        while (Character.isDigit(s.charAt(index))) {
            stringBuilder.append(s.charAt(index++));
        }
        return stringBuilder.toString();
    }

    public String getString(LinkedList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }
        return sb.toString();
    }


    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, (right[i] - left[i] + 1) * heights[i]);
        }
        return res;
    }
}
