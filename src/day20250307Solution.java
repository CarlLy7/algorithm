import java.util.*;

/**
 * @author: carl
 * @date: 2025.03.07
 */

public class day20250307Solution {
    // 433
    public int minMutation(String startGene, String endGene, String[] bank) {
        HashSet<String> bankSet = new HashSet<>();
        for (String valid : bank) {
            bankSet.add(valid);
        }
        if (!bankSet.contains(endGene)) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        int step = 0;
        queue.offer(startGene);
        visited.add(startGene);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(endGene)) {
                    return step;
                }
                List<String> allMutation = getAllMutation(cur);
                for (String next : allMutation) {
                    if (!visited.contains(next) && bankSet.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> getAllMutation(String cur) {
        List<String> res = new ArrayList<>();
        char[] charArray = cur.toCharArray();
        for (int i = 0; i < cur.length(); i++) {
            char oldChar = cur.charAt(i);
            for (char newChar : new char[] {'A', 'G', 'C', 'T'}) {
                charArray[i] = newChar;
                res.add(new String(charArray));
            }
            charArray[i] = oldChar;
        }
        return res;
    }

    // 1926
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    // 走不通
                    if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || maze[x][y] == '+') {
                        continue;
                    }
                    if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                        return step;
                    }
                    queue.offer(new int[] {x, y});
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }

    // 1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        queue.offer(new int[] {0, 0});
        visited[0][0] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == m - 1 && cur[1] == n - 1) {
                    return step;
                }
                for (int[] dir : dirs) {
                    int nextX = cur[0] + dir[0];
                    int nextY = cur[1] + dir[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]
                        && grid[nextX][nextY] == 0) {
                        queue.offer(new int[] {nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
        return -1;
    }

    // 310
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < graph.size(); i++) {
            // 将叶子节点放在队列中
            if (graph.get(i).size() == 1) {
                queue.offer(i);
            }
        }
        int nodeCount = n;
        while (nodeCount > 2) {
            int size = queue.size();
            nodeCount -= size;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int neighbor : graph.get(cur)) {
                    graph.get(neighbor).remove(cur);
                    if (graph.get(neighbor).size() == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return queue;
    }

}
