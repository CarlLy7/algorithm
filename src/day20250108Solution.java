import java.util.*;

/**
 * @author: carl
 * @date: 2025/1/8
 */

public class day20250108Solution {

    //787
    public int findCheapestPrice(int n, int[][] flights, int src, int dist, int k) {
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            graph[from].add(new int[]{to, price});
        }
        k++;
        return dijkstra(graph, src, dist, k);
    }

    private int dijkstra(List<int[]>[] graph, int src, int dist, int k) {
        int[] distTo = new int[graph.length];
        int[] nodeNumDistTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        Arrays.fill(nodeNumDistTo, Integer.MAX_VALUE);
        Queue<State> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a.distFromStart, b.distFromStart);
        });
        distTo[src] = 0;
        nodeNumDistTo[src] = 0;
        queue.offer(new State(src, 0, 0));
        while (!queue.isEmpty()) {
            State curState = queue.poll();
            int curNodeId = curState.nodeId;
            int curDistFromStart = curState.distFromStart;
            int curNodeNumFromStart = curState.nodeNumFromStart;
            if (curNodeId == dist) {
                return curDistFromStart;
            }
            if (curDistFromStart > distTo[curNodeId]) {
                continue;
            }
            if (curNodeNumFromStart == k) {
                continue;
            }
            for (int[] neighbors : graph[curNodeId]) {
                int nextNodeId = neighbors[1];
                int nextDistFromStart = neighbors[1] + curDistFromStart;
                int nextNodeNumFromStart = curNodeNumFromStart + 1;
                if (nextDistFromStart < distTo[nextNodeId]) {
                    distTo[nextNodeId] = nextDistFromStart;
                    nodeNumDistTo[nextNodeId] = nextNodeNumFromStart;
                }
                // 如果花的又多，中转的还多，直接枝减
                if (nextDistFromStart > distTo[nextNodeId] && nextNodeNumFromStart > nodeNumDistTo[nextNodeId]) {
                    continue;
                }
                queue.offer(new State(nextNodeId, nextDistFromStart, nextNodeNumFromStart));
            }
        }
        return -1;
    }

    private class State {
        private int nodeId;
        private int distFromStart;
        private int nodeNumFromStart;

        public State(int nodeId, int distFromStart, int nodeNumFromStart) {
            this.nodeId = nodeId;
            this.distFromStart = distFromStart;
            this.nodeNumFromStart = nodeNumFromStart;
        }
    }

    //1514
//    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
//        List<double[]>[] graph = new LinkedList[n];
//        for (int i = 0; i < n; i++) {
//            graph[i] = new LinkedList<>();
//        }
//        for (int i = 0; i < edges.length; i++) {
//            int from = edges[i][0];
//            int to = edges[i][1];
//            double weight = succProb[i];
//            graph[from].add(new double[]{to, weight});
//            graph[to].add(new double[]{from, weight});
//        }
//        Queue<State> queue = new PriorityQueue<>((a, b) -> {
//            return Double.compare(b.distFromStart, a.distFromStart);
//        });
//        double[] distTo = new double[n];
//        Arrays.fill(distTo, -1);
//        distTo[start] = 1;
//        queue.offer(new State(start, 1));
//        while (!queue.isEmpty()) {
//            State curState = queue.poll();
//            int curNodeId = curState.nodeId;
//            double curDistFromStart = curState.distFromStart;
//            if (curNodeId == end) {
//                return curDistFromStart;
//            }
//            if (curDistFromStart > distTo[curNodeId]) {
//                continue;
//            }
//            for (double[] neighbors : graph[curNodeId]) {
//                int nextNodeId = (int) neighbors[0];
//                double nextDistFromStart = neighbors[1] * curDistFromStart;
//                if (nextDistFromStart < distTo[nextNodeId]) {
//                    distTo[nextNodeId] = nextDistFromStart;
//                    queue.offer(new State(nextNodeId, nextDistFromStart));
//                }
//            }
//        }
//        return 0.0;
//    }
//
//    private class State {
//        private int nodeId;
//        private double distFromStart;
//
//        public State(int nodeId, double distFromStart) {
//            this.nodeId = nodeId;
//            this.distFromStart = distFromStart;
//        }
//    }

}
