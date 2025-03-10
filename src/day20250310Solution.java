import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * @author: carl
 * @date: 2025.03.10
 */

public class day20250310Solution {
    // 994
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int nextX = cur[0] + dir[0];
                    int nextY = cur[1] + dir[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                        grid[nextX][nextY] = 2;
                        queue.offer(new int[] {nextX, nextY});
                    }
                }
            }
            step++;
        }
        // 检查是否有新鲜的橘子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return step == 0 ? 0 : step - 1;
    }

    // 2101
    public int maximumDetonation(int[][] bombs) {
        int m = bombs.length;
        List<Integer>[] graph = new List[m];
        for (int i = 0; i < m; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    continue;
                }
                if (Math.pow(bombs[i][0] - bombs[j][0], 2) + Math.pow(bombs[i][1] - bombs[j][1], 2) <= Math
                    .pow(bombs[i][2], 2)) {
                    graph[i].add(j);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, bfs(graph, i));
        }
        return max;
    }

    private int bfs(List<Integer>[] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        int count = 0;
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            int cur = queue.poll();
            for (int neighbor : graph[cur]) {
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return count;
    }

    // 542
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
                    res[i][j] = 0;
                } else {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int nextX = dir[0] + cur[0];
                    int nextY = dir[1] + cur[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && res[nextX][nextY] == -1) {
                        queue.offer(new int[] {nextX, nextY});
                        res[nextX][nextY] = res[cur[0]][cur[1]] + 1;
                    }
                }
            }
        }
        return res;
    }
}
