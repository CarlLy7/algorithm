package day20240825;

import java.util.*;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-25 16:56
 * @version: 1.0
 */
public class Solution {
    public int slidingPuzzle(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] neigbors = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        String target = "123450";
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return step;
                }
                //找到数字0的索引
                int index = 0;
                for (; cur.charAt(index) != '0'; index++) ;
                //交换位置
                for (int adj : neigbors[index]) {
                    String newStr = swap(cur.toCharArray(), index, adj);
                    if (!visited.contains(newStr)) {
                        queue.offer(newStr);
                        visited.add(newStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(char[] toCharArray, int index, int adj) {
        char temp = toCharArray[index];
        toCharArray[index] = toCharArray[adj];
        toCharArray[adj] = temp;
        return new String(toCharArray);
    }

//    int[][] memo;
//    HashMap<Integer, List<int[]>> map = new HashMap<>();
//    int src;
//    int dst;
//
//    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//        k++;
//        memo = new int[n][k + 1];
//        this.src = src;
//        this.dst = dst;
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -888);
//        }
//        for (int[] flight : flights) {
//            int from = flight[0];
//            int to = flight[1];
//            int price = flight[2];
//            map.putIfAbsent(to, new LinkedList<>());
//            map.get(to).add(new int[]{from, price});
//        }
//        return dp(dst, k);
//    }
//
//    //dp(dst,k)：从src出发到dst在k步中最小花费
//    private int dp(int s, int k) {
//        if (s == src) {
//            return 0;
//        }
//        if (k == 0) {
//            return -1;
//        }
//        if (memo[s][k] != -888) {
//            return memo[s][k];
//        }
//        int res = Integer.MAX_VALUE;
//        if (map.containsKey(s)) {
//            for (int[] adj : map.get(s)) {
//                int from = adj[0];
//                int price = adj[1];
//                int subRes = dp(from, k - 1);
//                if (subRes != -1) {
//                    res = Math.min(res, subRes + price);
//                }
//            }
//        }
//        memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
//        return memo[s][k];
//    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
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
        return dijkstra(graph, src, dst, k);
    }

    private int dijkstra(List<int[]>[] graph, int src, int dst, int k) {
        int[] distTo = new int[graph.length];
        int[] distNumTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        Arrays.fill(distNumTo, Integer.MAX_VALUE);
        distTo[src] = 0;
        distNumTo[src] = 0;
        PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });
        queue.offer(new State(src, 0, 0));
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                State curNode = queue.poll();
                int curId = curNode.id;
                int curDistFromStart = curNode.distFromStart;
                int curDistNumFromStart = curNode.nodeNumFromStart;
                if (curId == dst) {
                    return distTo[curId];
                }
                if (curDistNumFromStart == k) {
                    continue;
                }
                for (int[] neighbors : graph[curId]) {
                    int nextId = neighbors[0];
                    int nextDistFromStart = curDistFromStart + neighbors[1];
                    int nextDistNumFromStart = curDistNumFromStart + 1;
                    if (nextDistFromStart < distTo[nextId]) {
                        distTo[nextId] = nextDistFromStart;
                        distNumTo[nextId] = nextDistNumFromStart;
                    }
                    if (nextDistFromStart > distTo[nextId] && nextDistNumFromStart > distNumTo[nextId]) {
                        continue;
                    }
                    queue.offer(new State(nextId, nextDistFromStart, nextDistNumFromStart));
                }
            }
        }
        return -1;
    }

    class State {
        int id;
        int distFromStart;
        //从start到这里经过的节点树
        int nodeNumFromStart;

        public State(int id, int distFromStart, int nodeNumFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
            this.nodeNumFromStart = nodeNumFromStart;
        }
    }
}
