package date202604;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.29
 * @Since: 1.0
 */

public class day20260429Solution {
    // [84] 柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        // 存放索引下标
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        // 记录每个位置左边最近的比它矮的索引
        int[] left = new int[n];
        // 记录每个位置右边最近的比它矮的索引
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        // 找右边矮的
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 找到左右两边最近的最矮的就是这个范围的边界了
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }

    // [933] 最近的请求次数
    private class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.offer(t);
            // 将所有[t-3000,t]这个范围内的元素都出队
            while (queue.peek() < (t - 3000)) {
                queue.poll();
            }
            return queue.size();
        }
    }

    // [2073] 买票需要的时间
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        int n = tickets.length;
        for (int i = 0; i < n; i++) {
            // 指定位置之前最多卖出去tickets[k]张票
            if (i <= k) {
                res += Math.min(tickets[k], tickets[i]);
            } else {
                // 指定位置之后，最多卖出去tickets[k]-1张票
                res += Math.min(tickets[k] - 1, tickets[i]);
            }
        }
        return res;
    }
}
