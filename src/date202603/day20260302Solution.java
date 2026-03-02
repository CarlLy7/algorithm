package date202603;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.02
 * @Since: 1.0
 */

public class day20260302Solution {
    // [1926] 迷宫中离入口最近的出口
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[m][n];
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] curLocation = queue.poll();
                int x = curLocation[0];
                int y = curLocation[1];
                for (int[] dir : dirs) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]
                        || maze[nextX][nextY] == '+') {
                        continue;
                    }
                    if (nextX == 0 || nextX == m - 1 || nextY == 0 || nextY == n - 1) {
                        return step;
                    }
                    visited[nextX][nextY] = true;
                    queue.offer(new int[] {nextX, nextY});
                }
            }
        }
        return -1;
    }

    // [1091] 二进制矩阵中的最短路径
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[n - 1][n - 1] == 1) {
            return -1;
        }
        if (grid[0][0] == 1) {
            return -1;
        }
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        int step = 1;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                if (x == n - 1 && y == n - 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];
                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextX][nextY]
                        || grid[nextX][nextY] == 1) {
                        continue;
                    }
                    visited[nextX][nextY] = true;
                    queue.offer(new int[] {nextX, nextY});
                }
            }
            step++;
        }
        return -1;
    }

    // [2101] 引爆最多的炸弹
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        // 邻接表
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        // 根据距离来维护任意两个相邻的炸弹图
        for (int i = 0; i < bombs.length; i++) {
            for (int j = 0; j < bombs.length; j++) {
                if (i == j) {
                    continue;
                }
                // 直角三角形，2个直角边平方和等于斜边平方和
                if (Math.pow(bombs[i][0] - bombs[j][0], 2) + Math.pow(bombs[i][1] - bombs[j][1], 2) <= Math
                    .pow(bombs[i][2], 2)) {
                    graph[i].add(j);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < bombs.length; i++) {
            max = Math.max(max, bfs(graph, i));
        }
        return max;
    }

    /**
     * 从start位置的炸弹开始引爆，最多可以引爆多少炸弹
     * 
     * @param graph
     * @param start
     * @return
     */
    private int bfs(List<Integer>[] graph, int start) {
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            // 因为这个题目要计算的是所有可达的节点数量，计数不是来记录层数，所以计数更新和通用模板不一样
            Integer curIndex = queue.poll();
            count++;
            for (Integer nextIndex : graph[curIndex]) {
                if (visited[nextIndex]) {
                    continue;
                }
                queue.offer(nextIndex);
                visited[nextIndex] = true;
            }
        }
        return count;
    }
}
