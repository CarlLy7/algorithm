import java.util.*;

/**
 * @author: carl
 * @date: 2025/1/7
 */

public class day20250107Solution {

    //1631
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] distTo = new int[m][n];
        for (int[] to : distTo) {
            Arrays.fill(to, Integer.MAX_VALUE);
        }
        distTo[0][0] = 0;
        Queue<State> queue = new PriorityQueue<>((a, b) -> a.distFromStart - b.distFromStart);
        queue.offer(new State(0, 0, 0));
        while (!queue.isEmpty()) {
            State curState = queue.poll();
            int curX = curState.x;
            int curY = curState.y;
            int curDistFromStart=curState.distFromStart;
            if (curX==m-1 && curY==n-1){
                return curDistFromStart;
            }
            if (curDistFromStart>distTo[curX][curY]){
                continue;
            }
            for (int[] neighbors : adj(heights, curX, curY)) {
                int nextX = neighbors[0];
                int nextY = neighbors[1];
                int nextDistFromStart=Math.max(curDistFromStart,
                        Math.abs(heights[curX][curY]-heights[nextX][nextY]));
                if (nextDistFromStart<distTo[nextX][nextY]){
                    distTo[nextX][nextY]=nextDistFromStart;
                    queue.offer(new State(nextX,nextY,nextDistFromStart));
                }
            }
        }
        return -1;
    }

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private List<int[]> adj(int[][] martix, int x, int y) {
        int m = martix.length;
        int n = martix[0].length;
        List<int[]> res = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                continue;
            }
            res.add(new int[]{nx, ny});
        }
        return res;
    }

    //743
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
//
//        int[] distTo = dijkstra(k, graph);
//        int res = 0;
//        for (int i = 0; i < distTo.length; i++) {
//            if (distTo[i] == Integer.MAX_VALUE) {
//                return -1;
//            }
//            res = Math.max(res, distTo[i]);
//        }
//        return res;
//    }
//
//    private int[] dijkstra(int start, List<int[]>[] graph) {
//        int[] distTo = new int[graph.length];
//        Arrays.fill(distTo, Integer.MAX_VALUE);
//        distTo[start] = 0;
//        Queue<State> queue = new PriorityQueue<>((a, b) -> a.distFromStart - b.distFromStart);
//        queue.offer(new State(start, 0));
//        while (!queue.isEmpty()) {
//            State curState = queue.poll();
//            int curNodeId = curState.nodeId;
//            int curDistFromStart = curState.distFromStart;
//            if (curDistFromStart > distTo[curNodeId]) {
//                continue;
//            }
//            for (int[] nextNode : graph[curNodeId]) {
//                int nextNodeId = nextNode[0];
//                int nextDistFromStart = curDistFromStart + nextNode[1];
//                if (nextDistFromStart < distTo[nextNodeId]) {
//                    distTo[nextNodeId] = nextDistFromStart;
//                    queue.offer(new State(nextNodeId, nextDistFromStart));
//                }
//            }
//        }
//        return distTo;
//    }

    //    private class State {
//        private int nodeId;
//        private int distFromStart;
//
//        public State(int nodeId, int distFromStart) {
//            this.nodeId = nodeId;
//            this.distFromStart = distFromStart;
//        }
//    }
    private class State {
        private int x;
        private int y;
        private int distFromStart;

        public State(int x, int y, int distFromStart) {
            this.x = x;
            this.y = y;
            this.distFromStart = distFromStart;
        }
    }
}
