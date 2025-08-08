package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.08
 * @Since: 1.0
 */
public class day20250808Solution {
    // [51] N 皇后
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(".");
            }
            board.add(row.toString());
        }
        backTrack(board, 0);
        return res;
    }

    /**
     * 从棋盘的第row行开始选择放置皇后
     * 
     * @param board
     * @param row
     */
    private void backTrack(List<String> board, int row) {
        if (row == board.size()) {
            res.add(new ArrayList<>(board));
            return;
        }
        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // 不合法
            if (!valid(board, row, col)) {
                continue;
            }
            char[] charArray = board.get(row).toCharArray();
            // 做选择
            charArray[col] = 'Q';
            board.set(row, new String(charArray));
            // 回溯
            backTrack(board, row + 1);
            // 撤销选择
            charArray[col] = '.';
            board.set(row, new String(charArray));
        }
    }

    /**
     * 放置N皇后是否合法
     * 
     * @param board
     * @param row
     * @param col
     * @return
     */
    private boolean valid(List<String> board, int row, int col) {
        int n = board.size();
        // 同一列
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        // 左上角线上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }

        // 右上角
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }

    // [53] 最大子数组和
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        // dp[i]: 以nums[i]结尾的元素的最大子数组和
        int[] dp = new int[n];
        // base case
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // 要么自成一派，要么和前面的加在一起
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // [54] 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        while (res.size() < m * n) {
            if (top <= bottom) {
                for (int col = left; col <= right; col++) {
                    res.add(matrix[top][col]);
                }
                top++;
            }
            if (left <= right) {
                for (int row = top; row <= bottom; row++) {
                    res.add(matrix[row][right]);
                }
                right--;
            }
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    res.add(matrix[bottom][col]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    res.add(matrix[row][left]);
                }
                left++;
            }
        }
        return res;
    }
}
