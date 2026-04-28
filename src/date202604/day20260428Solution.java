package date202604;

import java.util.Arrays;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.28
 * @Since: 1.0
 */

public class day20260428Solution {
    // [240] 搜索二维矩阵 II
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    // [402] 移掉 K 位数字
    public String removeKdigits(String num, int k) {
        // 在一个数字字符串中删除数字，得到最小数字的技巧：
        // 先将数字变成递增
        // 再从末尾删除元素
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            // 构造一个从小到大的单调队列
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }

            if (stack.isEmpty() && c == '0') {
                continue;
            }

            stack.push(c);
        }
        // 如果已经遍历完之后，k依然不为0，则需要删除末尾的元素
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }
        if (stack.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    // [853] 车队
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        // cars[0]:位置 cars[1]:速度
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        // 根据位置进行升序排序
        Arrays.sort(cars, (int[] a, int[] b) -> {
            return Integer.compare(a[0], b[0]);
        });
        // 计算每辆车到达终点的时间
        double[] times = new double[n];
        for (int i = 0; i < n; i++) {
            int[] car = cars[i];
            times[i] = (double)(target - car[0]) / car[1];
        }
        int res = 0;
        // 倒序找到递增的元素个数就是答案
        double maxTime = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (times[i] > maxTime) {
                maxTime = times[i];
                res++;
            }
        }
        return res;
    }
}
