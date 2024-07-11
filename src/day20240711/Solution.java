package day20240711;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-11 09:27
 * @version: 1.0
 */
public class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }


    public int closedIsland(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int col = 0; col < n; col++) {
            dfs(grid, 0, col);
            dfs(grid, m - 1, col);
        }
        for (int row = 0; row < m; row++) {
            dfs(grid, row, 0);
            dfs(grid, row, n - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    //封闭岛屿的面积大小

    /**
     * 思路：只需要将四周的岛屿进行淹没，因为四周岛屿是肯定可以到达边界的，所以淹没之后，剩下的岛屿不用淹没了，只需要计算数量就可以了
     *
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int col = 0; col < n; col++) {
            dfs(grid, 0, col);
            dfs(grid, m - 1, col);
        }
        for (int row = 0; row < m; row++) {
            dfs(grid, row, 0);
            dfs(grid, row, n - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) {
                    res += 1;
                }
            }
        }
        return res;
    }

//    public int maxAreaOfIsland(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        int res = 0;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1) {
//                    res = Math.max(res, dfs(grid, i, j));
//                }
//            }
//        }
//        return res;
//    }

//    private int dfs(int[][] grid, int i, int j) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || i >= m || j < 0 || j >= n) {
//            return 0;
//        }
//        if (grid[i][j] == 0) {
//            return 0;
//        }
//        grid[i][j] = 0;
//        return 1 + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
//    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //如果2中的岛屿在1中是海洋，那么2中的这个岛屿肯定不是子岛，进行淹没即可
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    dfs(grid2, i, j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 1) {
                    res++;
                    dfs(grid2, i, j);
                }
            }
        }
        return res;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> valToFre = new HashMap<>();
        for (int num : nums) {
            valToFre.put(num, valToFre.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((entry1, entry2) -> {
            return entry1.getValue().compareTo(entry2.getValue());
        });
        // 所有频次放到队列中，但是当超过k个后就将最小的删掉，最后只会剩下k个最大的
        for (Map.Entry<Integer, Integer> entry : valToFre.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().getKey();
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i][0]:第i天，没有持有股票的最大利润
        int[][] dp = new int[n][2];
        // base case
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    class MedianFinder {
        PriorityQueue<Integer> small;
        PriorityQueue<Integer> large;

        public MedianFinder() {
            small = new PriorityQueue<>((a, b) -> {
                return b - a;
            });
            large = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (small.size() >= large.size()) {
                small.offer(num);
                large.offer(small.poll());
            } else {
                large.offer(num);
                small.offer(large.poll());
            }
        }

        public double findMedian() {
            if (small.size() > large.size()) {
                return small.peek();
            } else if (small.size() < large.size()) {
                return large.peek();
            } else {
                return (small.peek() + large.peek()) / 2.0;
            }
        }
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farest = 0;
        for (int i = 0; i < n - 1; i++) {
            farest = Math.max(farest, nums[i] + i);
            if (farest <= i) {
                return false;
            }
        }
        return farest >= n - 1;
    }
}
