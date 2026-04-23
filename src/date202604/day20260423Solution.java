package date202604;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.23
 * @Since: 1.0
 */

public class day20260423Solution {
    // [54] 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int up = 0;
        int down = m - 1;
        int left = 0;
        int right = n - 1;
        List<Integer> res = new ArrayList<>();
        while (res.size() < m * n) {
            if (up <= down) {
                // →
                for (int j = left; j <= right; j++) {
                    res.add(matrix[up][j]);
                }
                up++;
            }
            // 下
            if (right >= left) {
                for (int i = up; i <= down; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            }
            // 左
            if (down >= up) {
                for (int j = right; j >= left; j--) {
                    res.add(matrix[down][j]);
                }
                down--;
            }
            // 上
            if (left <= right) {
                for (int i = down; i >= up; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }

    // [150] 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")) {
                stack.push(Integer.valueOf(token));
                continue;
            }
            Integer right = stack.pop();
            Integer left = stack.pop();
            if (token.equals("+")) {
                stack.push(left + right);
            }
            if (token.equals("-")) {
                stack.push(left - right);
            }
            if (token.equals("*")) {
                stack.push(left * right);
            }
            if (token.equals("/")) {
                stack.push(left / right);
            }
        }
        return stack.peek();
    }

    // [394] 字符串解码
    public String decodeString(String s) {
        // 记录出现次数的栈
        Stack<Integer> countStack = new Stack<>();
        // 记录需要重复的字符串
        Stack<String> strStack = new Stack<>();
        // 进位
        int k = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            // 如果当前是数字
            if (Character.isDigit(c)) {
                k = k * 10 + c - '0';
            } else if (c == '[') {
                // 说明需要进行入栈了
                countStack.push(k);
                k = 0;
                strStack.push(sb.toString());
                sb = new StringBuilder();
            } else if (c == ']') {
                // 如果遇到]说明需要进行出栈了
                Integer times = countStack.pop();
                String preStr = strStack.pop();
                StringBuilder ap = new StringBuilder(preStr);
                for (int i = 0; i < times; i++) {
                    ap.append(sb.toString());
                }
                sb = ap;
            } else {
                // 如果是普通的字母就拼接到sb中
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
