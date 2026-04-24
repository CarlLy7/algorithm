package date202604;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.24
 * @Since: 1.0
 */

public class day20260424Solution {
    // [48] 旋转图像
    public void rotate(int[][] matrix) {
        // 1.按照对角线互换
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 2.每行翻转
        for (int i = 0; i < m; i++) {
            reverse(matrix[i]);
        }
    }

    /**
     * 翻转数组
     * 
     * @param matrix
     */
    private void reverse(int[] matrix) {
        int n = matrix.length;
        int i = 0, j = n - 1;
        while (i < j) {
            int temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
            i++;
            j--;
        }
    }

    // [155] 最小栈
    private class MinStack {
        private Stack<Integer> stack;
        // 存放最小元素
        private Stack<Integer> minStack;

        /**
         * 初始化堆栈对象
         */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            // 操作最小元素
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            Integer cur = stack.pop();
            // 当前要删除的元素恰好是最小元素
            if (cur.equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // [901] 股票价格跨度
    private class StockSpanner {
        // 存放二元组:当前元素->之前小于等于当前元素的元素个数
        private Stack<int[]> stack;

        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price) {
            // 自己默认是一个数
            int count = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                int[] top = stack.pop();
                count += top[1];
            }
            stack.push(new int[] {price, count});
            return count;
        }
    }
}
