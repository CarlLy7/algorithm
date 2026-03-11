package date202603;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.11
 * @Since: 1.0
 */

public class day20260311Solution {
    // [150] 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                stack.push(Integer.parseInt(token));
            } else {
                if (token.equals("+")) {
                    Integer left = stack.pop();
                    Integer right = stack.pop();
                    stack.push(left + right);
                }
                if (token.equals("-")) {
                    Integer left = stack.pop();
                    Integer right = stack.pop();
                    stack.push(right - left);
                }
                if (token.equals("*")) {
                    Integer left = stack.pop();
                    Integer right = stack.pop();
                    stack.push(left * right);
                }
                if (token.equals("/")) {
                    Integer left = stack.pop();
                    Integer right = stack.pop();
                    stack.push(right / left);
                }
            }
        }
        return stack.peek();
    }

    // [225] 用队列实现栈
    private class MyStack {
        Queue<Integer> queue;
        // 栈顶元素
        int topEle;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            topEle = x;
        }

        public int pop() {
            int size = queue.size();
            // 留下队尾的两个元素,倒数第二个是用来更新栈顶元素，最后一个元素是要弹出的
            while (size > 2) {
                // 让队首的元素出去然后重新入队
                queue.offer(queue.poll());
                size--;
            }
            topEle = queue.peek();
            // 倒数第二个也要重新入队
            queue.offer(queue.poll());
            return queue.poll();
        }

        public int top() {
            return topEle;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    // [388] 文件的最长绝对路径
    public int lengthLongestPath(String input) {
        // 存放当前目录或者文件的完整路径长度(不包含/分隔符)
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (String s : input.split("\n")) {
            // 记录当前文件或者目录的层级
            int index = s.lastIndexOf("\t") + 1;
            // 如果当前目录中记录了其他目录或者文件的父目录则需要删除
            while (index < stack.size()) {
                stack.pop();
            }
            stack.push(s.substring(index).length());
            // 如果当前是文件则需要更新最终结果
            if (s.contains(".")) {
                // 记录当前栈中该目录的长度
                int sum = 0;
                for (Integer i : stack) {
                    sum += i;
                }
                // 需要增加/分隔符长度
                sum += stack.size() - 1;
                res = Math.max(res, sum);
            }
        }
        return res;
    }

}
