import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.09
 * @Since: 1.0
 */

public class day20250709Solution {

    // [1306] 跳跃游戏 III
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            Integer curIndex = queue.poll();
            if (arr[curIndex] == 0) {
                return true;
            }
            // 往左跳
            int left = curIndex - arr[curIndex];
            if (left >= 0 && !visited[left]) {
                queue.offer(left);
                visited[left] = true;
            }
            // 往右跳
            int right = curIndex + arr[curIndex];
            if (right < arr.length && !visited[right]) {
                queue.offer(right);
                visited[right] = true;
            }
        }
        return false;
    }

    // [1926] 迷宫中离入口最近的出口
    public int nearestExit(char[][] maze, int[] entrance) {
        // 记录坐标
        Queue<int[]> queue = new LinkedList<>();
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int nextX = dir[0] + cur[0];
                    int nextY = dir[1] + cur[1];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]
                        || maze[nextX][nextY] == '+') {
                        continue;
                    }
                    // 这里只是判断到了出口就可以了，不用实际去到出口，所以在开始先step++
                    if (nextX == 0 || nextX == m - 1 || nextY == 0 || nextY == n - 1) {
                        return step;
                    }
                    queue.offer(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
        return -1;
    }

    // [1091] 二进制矩阵中的最短路径
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                // 达到终点，这里是要实际跳过去的，所以step在for循环外面++
                if (cur[0] == m - 1 && cur[1] == n - 1) {
                    return step;
                }
                // 向八个方向进行扩散
                for (int[] dir : dirs) {
                    int nextX = dir[0] + cur[0];
                    int nextY = dir[1] + cur[1];
                    // 不合法
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]
                        || grid[nextX][nextY] == 1) {
                        continue;
                    }
                    queue.offer(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            step++;
        }
        return -1;
    }
}
