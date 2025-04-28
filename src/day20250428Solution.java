import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.28
 * @Since: 1.0
 */

public class day20250428Solution {
    // 200 岛屿数量
    public int numIslands(char[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    // 发现这是一个陆地之后使用dfs算法将和这个陆地相连的其他陆地都用水淹没
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    // 206 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        while (next != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 207 课程表
    // 选择的路径节点
    boolean[] path;
    // 我访问过，但是我不一定会选择这个节点进入我的路径,防止走回头路
    boolean[] visited;
    // 是否包含环
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        path = new boolean[numCourses];
        visited = new boolean[numCourses];
        List<Integer>[] graph = build(numCourses, prerequisites);
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
    }

    /**
     * 从图中个节点开始作为起点进行遍历，判断是否存在环
     * 
     * @param graph:图
     * @param i:起点
     */
    private void traverse(List<Integer>[] graph, int i) {
        if (path[i]) {
            hasCycle = true;
            return;
        }
        if (visited[i] || hasCycle) {
            return;
        }
        path[i] = true;
        visited[i] = true;
        for (Integer next : graph[i]) {
            traverse(graph, next);
        }
        path[i] = false;
    }

    /**
     * 创建有向图：邻接表
     * 
     * @param numCourses:节点数
     * @param prerequisites:连接关系
     * @return
     */
    private List<Integer>[] build(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            graph[from].add(to);
        }
        return graph;
    }

    //138 随机链表的复制
    HashMap<Node, Node> map = new HashMap<>();
    HashSet<Node> used = new HashSet<>();

    public Node copyRandomList(Node head) {
        traverse(head);
        return map.get(head);
    }

    /**
     * 遍历复制链表节点
     * 
     * @param head
     */
    private void traverse(Node head) {
        if (head == null) {
            return;
        }
        if (used.contains(head)) {
            return;
        }
        used.add(head);
        Node newHead = new Node(head.val);
        if (!map.containsKey(head)) {
            map.put(head, newHead);
        }
        // 遍历构建next节点的哈希映射
        traverse(head.next);
        newHead.next = map.get(head.next);
        // 遍历构建random节点的哈希映射
        traverse(head.random);
        newHead.random = map.get(head.random);
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
