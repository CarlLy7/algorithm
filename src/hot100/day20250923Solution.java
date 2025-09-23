package hot100;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.23
 * @Since: 1.0
 */

public class day20250923Solution {
    // [994] 腐烂的橘子
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[] {x, y});
                    }
                }
            }
            step++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return step == 0 ? 0 : step - 1;
    }

    // [71] 简化路径
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : split) {
            if (s.equals(".") || s.isEmpty()) {
                continue;
            }
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(s);
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    // [75] 颜色分类
    public void sortColors(int[] nums) {
        int p0 = 0, p = 0, p2 = nums.length - 1;
        while (p <= p2) {
            if (nums[p] == 0) {
                swap(nums, p0, p);
                p0++;
            } else if (nums[p] == 2) {
                swap(nums, p, p2);
                p2--;
            } else if (nums[p] == 1) {
                p++;
            }
            if (p < p0) {
                p = p0;
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
