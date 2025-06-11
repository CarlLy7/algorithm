import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.11
 * @Since: 1.0
 */

public class day20250611Solution {
    // [310] 最小高度树
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        // 邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        // 类似无向图放进去节点
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 存放叶子节点
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).size() == 1) {
                queue.add(i);
            }
        }
        int nodeCount = n;
        while (nodeCount > 2) {
            int size = queue.size();
            nodeCount -= size;
            for (int i = 0; i < size; i++) {
                Integer cur = queue.poll();
                for (Integer neigh : graph.get(cur)) {
                    graph.get(neigh).remove(cur);
                    if (graph.get(neigh).size() == 1) {
                        queue.offer(neigh);
                    }
                }
            }
        }
        return queue;
    }

    // [332] 重新安排行程
    List<List<String>> tickets;
    List<String> res = null;
    Map<String, List<String>> graph = new HashMap<>();
    // 比如 graph["JFK"][2] = true 说明从机场 JFK 出发的第 3 张机票已经用过了
    Map<String, boolean[]> used = new HashMap<>();
    LinkedList<String> track = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        this.tickets = tickets;
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<String>());
            }
            graph.get(from).add(to);
        }
        // 将value值按照字典的顺序进行排序
        for (List<String> value : graph.values()) {
            Collections.sort(value);
        }
        // 初始化used
        for (String from : graph.keySet()) {
            int size = graph.get(from).size();
            used.put(from, new boolean[size]);
        }
        track.add("JFK");
        backTrack("JFK");
        return res;
    }

    private void backTrack(String airport) {
        if (res != null) {
            return;
        }
        if (track.size() == tickets.size() + 1) {
            res = new ArrayList<>(track);
            return;
        }
        if (!graph.containsKey(airport)) {
            return;
        }
        List<String> nextAirports = graph.get(airport);
        for (int i = 0; i < nextAirports.size(); i++) {
            String nextAirport = nextAirports.get(i);
            // 如果当前这个机票已经使用过了，则跳过
            if (used.get(airport)[i]) {
                continue;
            }
            track.addLast(nextAirport);
            used.get(airport)[i] = true;
            backTrack(nextAirport);
            track.removeLast();
            used.get(airport)[i] = false;
        }
    }

    // [386] 字典序排数
    List<Integer> ans = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        for (int i = 1; i <= 9; i++) {
            traverse(i, n);
        }
        return ans;
    }

    /**
     * 前序遍历root为根，小于等于n的树
     * 
     * @param root
     * @param n
     */
    private void traverse(int root, int n) {
        if (root > n) {
            return;
        }
        ans.add(root);
        // 0-9这9个数，往下延伸10叉树
        for (int child = root * 10; child < root * 10 + 10; child++) {
            traverse(child, n);
        }
    }

    // [388] 文件的最长绝对路径
    public int lengthLongestPath(String input) {
        Deque<String> stack = new LinkedList<>();
        int maxLen = 0;
        for (String part : input.split("\n")) {
            int level = part.lastIndexOf("\t") + 1;
            // 保证栈中记录的是自己的目录
            while (level < stack.size()) {
                stack.removeLast();
            }
            stack.addLast(part.substring(level));
            // 如果是文件
            if (part.contains(".")) {
                // 将整个栈中的长度都计算出来，栈中是不包含/分隔符的
                int len = stack.stream().mapToInt(String::length).sum();
                // 加上分隔符/
                len += stack.size() - 1;
                maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }
}
