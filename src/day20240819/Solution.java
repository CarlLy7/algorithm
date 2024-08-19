package day20240819;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-19 09:49
 * @version: 1.0
 */
public class Solution {
//    public int networkDelayTime(int[][] times, int n, int k) {
//        List<int[]>[] graph = new LinkedList[n + 1];
//        for (int i = 1; i <= n; i++) {
//            graph[i] = new LinkedList<>();
//        }
//        for (int[] time : times) {
//            int from = time[0];
//            int to = time[1];
//            int weight = time[2];
//            graph[from].add(new int[]{to, weight});
//        }
//        int[] distTo = dijksta(k, graph);
//        int res = 0;
//        // 找到最短路径中最大的那个
//        for (int i = 1; i < distTo.length; i++) {
//            if (distTo[i] == Integer.MAX_VALUE) {
//                return -1;
//            }
//            res = Math.max(res, distTo[i]);
//        }
//        return res;
//    }
//
//    public class State {
//        public int id;
//        public int distFromStart;
//
//        public State(int id, int distFromStart) {
//            this.id = id;
//            this.distFromStart = distFromStart;
//        }
//    }
//
//    private int[] dijksta(int start, List<int[]>[] graph) {
//        int n = graph.length;
//        int[] distTo = new int[n];
//        Arrays.fill(distTo, Integer.MAX_VALUE);
//        // base case
//        distTo[start] = 0;
//        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> {
//            return a.distFromStart - b.distFromStart;
//        });
//        queue.offer(new State(start, 0));
//        while (!queue.isEmpty()) {
//            State cur = queue.poll();
//            int curId = cur.id;
//            int curDistFromStart = cur.distFromStart;
//            if (curDistFromStart > distTo[curId]) {
//                continue;
//            }
//            for (int[] ints : graph[curId]) {
//                int nextId = ints[0];
//                int distToNextId = distTo[curId] + ints[1];
//                if (distToNextId < distTo[nextId]) {
//                    distTo[nextId] = distToNextId;
//                    queue.offer(new State(nextId, distToNextId));
//                }
//            }
//        }
//        return distTo;
//    }

//    public int minimumEffortPath(int[][] heights) {
//        int m = heights.length;
//        int n = heights[0].length;
//        int[][] distTo = new int[m][n];
//        for (int[] dist : distTo) {
//            Arrays.fill(dist, Integer.MAX_VALUE);
//        }
//        distTo[0][0] = 0;
//        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> {
//            return a.distFromStart - b.distFromStart;
//        });
//        queue.offer(new State(0, 0, 0));
//        while (!queue.isEmpty()) {
//            State cur = queue.poll();
//            int curX = cur.x;
//            int curY = cur.y;
//            int dist = cur.distFromStart;
//            if (curX == m - 1 && curY == n - 1) {
//                return distTo[curX][curY];
//            }
//            if (dist > distTo[curX][curY]) {
//                continue;
//            }
//            for (int[] neighbor : adj(heights, curX, curY)) {
//                int nx = neighbor[0];
//                int ny = neighbor[1];
//                int nextDistance = Math.max(distTo[curX][curY], Math.abs(heights[curX][curY] - heights[nx][ny]));
//                if (nextDistance < distTo[nx][ny]) {
//                    distTo[nx][ny] = nextDistance;
//                    queue.offer(new State(nx, ny, nextDistance));
//                }
//            }
//        }
//        return -1;
//    }
//
//    // 返回(x,y)的相邻节点的坐标
//    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//
//    List<int[]> adj(int[][] matrix, int x, int y) {
//        int m = matrix.length;
//        int n = matrix[0].length;
//        List<int[]> neighbor = new ArrayList<>();
//        for (int[] dir : dirs) {
//            int nx = x + dir[0];
//            int ny = y + dir[1];
//            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
//                continue;
//            }
//            neighbor.add(new int[]{nx, ny});
//        }
//        return neighbor;
//    }
//
//    public class State {
//        int x, y;
//        int distFromStart;
//
//        public State(int x, int y, int distFromStart) {
//            this.x = x;
//            this.y = y;
//            this.distFromStart = distFromStart;
//        }
//    }

    class State {
        int id;
        double distFromStart;

        public State(int id, double distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            graph[from].add(new double[]{to, weight});
            graph[to].add(new double[]{from, weight});
        }
        return dijkstra(graph, start_node, end_node);

    }

    private double dijkstra(List<double[]>[] graph, int start, int end) {
        int m = graph.length;
        double[] distTo = new double[m];
        Arrays.fill(distTo, -1);
        distTo[start] = 1;
        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> {
            return Double.compare(b.distFromStart, a.distFromStart);
        });
        queue.offer(new State(start, 1));
        while (!queue.isEmpty()) {
            State cur = queue.poll();
            int curId = cur.id;
            double dist = cur.distFromStart;
            if (curId == end) {
                return dist;
            }
            if (dist < distTo[curId]) {
                continue;
            }
            for (double[] neighbor : graph[curId]) {
                int nextId = (int) neighbor[0];
                double nextDist = distTo[curId] * neighbor[1];
                if (nextDist > distTo[nextId]) {
                    distTo[nextId] = nextDist;
                    queue.offer(new State(nextId, nextDist));
                }
            }
        }
        return 0.0;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<Integer> res = new LinkedList<>();
    int depth = 0;

    public List<Integer> rightSideView(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        if (res.size() < depth) {
            res.add(root.val);
        }
        traverse(root.right);
        traverse(root.left);
        depth--;
    }
}
