import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.15
 * @Since: 1.0
 */

public class day20250415Solution {
    // 78 子集
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    //
    // public List<List<Integer>> subsets(int[] nums) {
    // backTrack(nums, 0);
    // return res;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // res.add(new ArrayList<>(track));
    // for (int i = start; i < nums.length; i++) {
    // track.addLast(nums[i]);
    // backTrack(nums, i + 1);
    // track.removeLast();
    // }
    // }

    // 79 单词搜索
    boolean found = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, word, 0);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从board[i][j]开始匹配word[p...]
     * 
     * @param board
     * @param i
     * @param j
     * @param word
     * @param p
     */
    private void dfs(char[][] board, int i, int j, String word, int p) {
        int m = board.length;
        int n = board[0].length;
        if (p == word.length()) {
            found = true;
            return;
        }
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (found) {
            return;
        }
        if (board[i][j] != word.charAt(p)) {
            return;
        }
        // 标记为已经使用过
        board[i][j] = (char)-board[i][j];
        dfs(board, i - 1, j, word, p + 1);
        dfs(board, i + 1, j, word, p + 1);
        dfs(board, i, j - 1, word, p + 1);
        dfs(board, i, j + 1, word, p + 1);
        // 撤销使用
        board[i][j] = (char)-board[i][j];
    }

    // 84 柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        // 栈中存放的是索引下标
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        // 记录当前索引位置，左边最近的比它矮的索引
        int[] left = new int[n];
        // 记录当前索引位置，右边最近的比它矮的索引
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            // 如果比它高，就从栈中移除掉
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
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
            res = Math.max(res, (right[i] - left[i] - 1)* heights[i]) ;
        }
        return res;
    }
}
