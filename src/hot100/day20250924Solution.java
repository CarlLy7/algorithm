package hot100;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.24
 * @Since: 1.0
 */

public class day20250924Solution {
    // [139] 单词拆分
    int[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dp(s, 0, wordDict);
    }

    /**
     * 能否用wordDict中的字符串拼出s[start...]
     * 
     * @param s
     * @param start
     * @param wordDict
     * @return
     */
    private boolean dp(String s, int start, List<String> wordDict) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != -1) {
            return memo[start] == 1;
        }
        for (String word : wordDict) {
            int len = word.length();
            if (start + len > s.length()) {
                continue;
            }
            if (!s.substring(start, start + len).equals(word)) {
                continue;
            }
            // 如果可以拼出来
            if (dp(s, start + len, wordDict)) {
                memo[start] = 1;
                return true;
            }
        }
        memo[start] = 0;
        return false;
    }

    // [162] 寻找峰值
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 趋势下行，峰值是mid或者mid左侧，所以收缩right
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                // 趋势上行，峰值mid右侧，所以收缩left
                left = mid + 1;
            }
        }
        return left;
    }

    // [面试题 01.01] 判定字符是否唯一
    public boolean isUnique(String astr) {
        int[] charNum = new int[26];
        for (char c : astr.toCharArray()) {
            charNum[c - 'a']++;
        }
        for (int i = 0; i < charNum.length; i++) {
            if (charNum[i] > 1) {
                return false;
            }
        }
        return true;
    }

    // [200] 岛屿数量
    int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果是陆地，淹没所有和它相连的
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    /**
     * 深度优先遍历算法淹没所有相连的陆地
     * 
     * @param grid
     * @param i
     * @param j
     */
    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        // 淹没
        grid[i][j] = '0';
        // 向周围
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                dfs(grid, x, y);
            }
        }
    }
}
