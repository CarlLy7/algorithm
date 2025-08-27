package hot100;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.27
 * @Since: 1.0
 */

public class day20250827Solution {
    // [206] 反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = head;
        while (cur != null) {
            nxt = nxt.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // [207] 课程表
    // 当前节点是否访问过
    boolean[] visited;
    // 当前节点是否在路径上
    boolean[] onPath;
    // 是否有环
    boolean hasCycle;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            graph[from].add(to);
        }
        return graph;
    }

    private void traverse(List<Integer>[] graph, int start) {
        if (onPath[start]) {
            hasCycle = true;
            return;
        }
        if (visited[start] || hasCycle) {
            return;
        }
        visited[start] = true;
        onPath[start] = true;
        for (Integer nxt : graph[start]) {
            traverse(graph, nxt);
        }
        onPath[start] = false;
    }

    // [215] 数组中的第K个最大元素

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            while (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    private class ListNode {
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
