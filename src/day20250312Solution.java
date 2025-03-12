import java.util.*;

/**
 * @author: carl
 * @date: 2025.03.12
 */

public class day20250312Solution {
    // 399
    class Edge {
        String node;
        double weight;

        public Edge(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);
            double weight = values[i];
            if (!graph.containsKey(a)) {
                graph.put(a, new ArrayList<>());
            }
            graph.get(a).add(new Edge(b, weight));
            if (!graph.containsKey(b)) {
                graph.put(b, new ArrayList<>());
            }
            graph.get(b).add(new Edge(a, 1.0 / weight));
        }
        double[] res = new double[queries.size()];
        for (int j = 0; j < res.length; j++) {
            List<String> query = queries.get(j);
            String start = query.get(0);
            String end = query.get(1);
            res[j] = bfs(graph, start, end);
        }
        return res;
    }

    private double bfs(Map<String, List<Edge>> graph, String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1.0;
        }
        if (start.equals(end)) {
            return 1.0;
        }
        HashSet<String> visited = new HashSet<>();
        // key:节点 value:从start到这个节点的权重
        Map<String, Double> weightMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        weightMap.put(start, 1.0);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (Edge neighbor : graph.get(cur)) {
                if (visited.contains(neighbor.node)) {
                    continue;
                }
                weightMap.put(neighbor.node, weightMap.get(cur) * neighbor.weight);
                if (neighbor.node.equals(end)) {
                    return weightMap.get(neighbor);
                }
                queue.offer(neighbor.node);
                visited.add(neighbor.node);
            }
        }
        // 不可达
        return -1.0;
    }

    // 329
    int[][] memo;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, i, j));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int res = 1;
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (matrix[x][y] > matrix[i][j]) {
                res = Math.max(res, dfs(matrix, x, y) + 1);
            }
        }
        memo[i][j] = res;
        return res;
    }

    //
}
