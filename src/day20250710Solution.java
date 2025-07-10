import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.10
 * @Since: 1.0
 */

public class day20250710Solution {
    // [924] 尽量减少恶意软件的传播
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        HashSet<Integer> initialSet = new HashSet<>();
        for (int i : initial) {
            initialSet.add(i);
        }
        boolean[] visited = new boolean[n];
        Arrays.sort(initial);
        int targetNode = initial[0];
        int badCount = 0;
        for (int badNode : initial) {
            if (visited[badNode]) {
                continue;
            }
            int[] count = bfs(graph, badNode, visited, initialSet);
            if (count[1] == 1) {
                if (count[0] > badCount) {
                    targetNode = badNode;
                    badCount = count[0];
                }
            }
        }
        return targetNode;
    }

    /**
     * 在邻接矩阵中找到联通路径
     * 
     * @param graph
     * @param badNode
     * @param visited
     * @param initialSet
     * @return int[0]:联通节点个数 int[1]:在联通的过程中遇到的坏节点个数
     */
    private int[] bfs(int[][] graph, int badNode, boolean[] visited, HashSet<Integer> initialSet) {
        int n = graph.length;
        // 从badNode出发可以达到的节点个数
        int count = 0;
        // 遍历的路上遇到的坏节点的个数
        int badCount = 0;
        visited[badNode] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(badNode);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            count++;
            if (initialSet.contains(cur)) {
                badCount++;
            }
            for (int i = 0; i < n; i++) {
                if (graph[cur][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        return new int[] {count, badCount};
    }

    // [721] 账户合并
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // key:email value:索引列表
        HashMap<String, List<Integer>> emailToIndexMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> emailList = accounts.get(i);
            for (int j = 1; j < emailList.size(); j++) {
                String email = emailList.get(j);
                List<Integer> indexList = emailToIndexMap.getOrDefault(email, new ArrayList<>());
                indexList.add(i);
                emailToIndexMap.put(email, indexList);
            }
        }
        List<List<String>> res = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        for (String email : emailToIndexMap.keySet()) {
            // 避免重复访问
            if (visited.contains(email)) {
                continue;
            }
            // 与当前的email相连的email
            LinkedList<String> mergeEmail = new LinkedList<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(email);
            visited.add(email);
            while (!queue.isEmpty()) {
                String curEmail = queue.poll();
                mergeEmail.addLast(curEmail);
                List<Integer> indexList = emailToIndexMap.get(curEmail);
                for (Integer index : indexList) {
                    List<String> emailList = accounts.get(index);
                    for (int j = 1; j < emailList.size(); j++) {
                        String nextEmail = emailList.get(j);
                        if (!visited.contains(nextEmail)) {
                            queue.offer(nextEmail);
                            visited.add(nextEmail);
                        }
                    }
                }
            }
            // 拿到用户名
            String userName = accounts.get(emailToIndexMap.get(email).get(0)).get(0);
            // 排序
            Collections.sort(mergeEmail);
            mergeEmail.addFirst(userName);
            res.add(mergeEmail);
        }
        return res;
    }
}
