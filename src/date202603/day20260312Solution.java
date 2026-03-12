package date202603;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.12
 * @Since: 1.0
 */

public class day20260312Solution {
    // [394] 字符串解码
    public String decodeString(String s) {
        // 存放字符串的栈
        Stack<String> strStack = new Stack<>();

        // 存放出现次数的栈
        Stack<Integer> countStack = new Stack<>();

        int k = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            // 如果是数字
            if (Character.isDigit(c)) {
                k = k * 10 + c - '0';
            } else if (c == '[') {
                // 如果遇到[应该入栈了
                countStack.push(k);
                k = 0;
                strStack.push(sb.toString());
                sb = new StringBuilder();
            } else if (c == ']') {
                // 如果遇到]，应该出栈然后进行重复了
                int times = countStack.pop();
                String preStr = strStack.pop();
                StringBuilder preSub = new StringBuilder(preStr);
                String curStr = sb.toString();
                for (int i = 0; i < times; i++) {
                    preSub.append(curStr);
                }
                sb = preSub;
            } else {
                // 如果是单纯的字母则加到sb中
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // [933] 最近的请求次数
    private class RecentCounter {
        // 用队列来记录请求时间，队列元素个数就是请求数
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.offer(t);
            // 老请求肯定在队头
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

}
