package date202603;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.03
 * @Since: 1.0
 */

public class day20260303Solution {
    // [542] 01 矩阵
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] res = new int[m][n];
        for (int[] re : res) {
            Arrays.fill(re, -1);
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                    res[i][j] = 0;
                }
            }
        }
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] curMat = queue.poll();
            int x = curMat[0];
            int y = curMat[1];
            for (int[] dir : dirs) {
                int nextX = dir[0] + x;
                int nextY = dir[1] + y;
                // 下一个位置是索引合理且没有访问过的
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && res[nextX][nextY] == -1) {
                    queue.offer(new int[] {nextX, nextY});
                    // 因为是从当前x,y过去的，所以移动的距离就是+1
                    res[nextX][nextY] = res[x][y] + 1;
                }
            }
        }
        return res;
    }

    // [417] 太平洋大西洋水流问题
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        // 可以流入太平洋的位置
        boolean[][] visitedP = new boolean[m][n];
        Queue<int[]> queueP = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queueP.offer(new int[] {0, i});
            visitedP[0][i] = true;
        }
        for (int i = 0; i < m; i++) {
            queueP.offer(new int[] {i, 0});
            visitedP[i][0] = true;
        }
        bfs(queueP, visitedP, heights);
        // 可以流入大西洋的位置
        boolean[][] visitedA = new boolean[m][n];
        Queue<int[]> queueA = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queueA.offer(new int[] {m - 1, i});
            visitedA[m - 1][i] = true;
        }
        for (int i = 0; i < m; i++) {
            queueA.offer(new int[] {i, n - 1});
            visitedA[i][n - 1] = true;
        }
        bfs(queueA, visitedA, heights);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedP[i][j] && visitedA[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(Queue<int[]> queue, boolean[][] visited, int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] curLocation = queue.poll();
            int x = curLocation[0];
            int y = curLocation[1];
            for (int[] dir : dirs) {
                int nextX = dir[0] + x;
                int nextY = dir[1] + y;
                // 可以流入的位置
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && heights[nextX][nextY] >= heights[x][y]
                    && !visited[nextX][nextY]) {
                    queue.offer(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    // [365] 水壶问题
    public boolean canMeasureWater(int x, int y, int target) {
        Queue<int[]> queue = new LinkedList<>();
        // 将两个水壶的水量变成一个一维数字，来标记是否已经访问过
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(new int[] {0, 0});
        visited.add(0);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 如果当前的分配可以达到target直接返回true
            if (cur[0] == target || cur[1] == target || cur[1] + cur[0] == target) {
                return true;
            }
            // 记录之后可能得水量分配
            List<int[]> nextWater = new ArrayList<>();
            // 将第一个水壶倒掉
            nextWater.add(new int[] {0, cur[1]});
            // 将第二个水壶倒掉
            nextWater.add(new int[] {cur[0], 0});
            // 将第一个水壶加满
            nextWater.add(new int[] {x, cur[1]});
            // 将第二个水壶加满
            nextWater.add(new int[] {cur[0], y});
            // 将第一个水壶中的水倒入水壶二，要不第一个水壶全倒入了，要不第二个水壶满了
            nextWater.add(new int[] {cur[0] - Math.min(cur[0], y - cur[1]), cur[1] + Math.min(cur[0], y - cur[1])});
            // 将第二个水壶的水倒入水壶一，要不第二个水壶全部倒入，要不第一个水壶满了
            nextWater.add(new int[] {cur[0] + Math.min(cur[1], x - cur[0]), cur[1] - Math.min(cur[1], x - cur[0])});

            for (int[] water : nextWater) {
                int first = water[0];
                int second = water[1];
                if (visited.contains(first * (y + 1) + second)) {
                    continue;
                }
                queue.offer(water);
                // 可以理解为二维坐标转一维坐标
                visited.add(first * (y + 1) + second);
            }
        }
        return false;
    }
}
