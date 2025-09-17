package hot100;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.17
 * @Since: 1.0
 */

public class day20250917Solution {
    // [128] 最长连续序列
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (Integer cur : set) {
            // 如果当前元素不是第一个元素,直接跳过
            if (set.contains(cur - 1)) {
                continue;
            }
            int curNum = cur;
            int curLen = 1;
            // 如果有连续的数
            while (set.contains(curNum + 1)) {
                curNum++;
                curLen++;
            }
            // 更新答案
            res = Math.max(res, curLen);
        }
        return res;
    }

    // [169] 多数元素
    public int majorityElement(int[] nums) {
        int target = 0;
        // 电性
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                target = nums[i];
                count = 1;
                // 找到众数了假设呈现正电
            } else if (target == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return target;
    }

    // [207] 课程表
    // 此时已经在路径上的节点
    boolean[] onPath;
    // 已经访问过的节点，防止走回头路
    boolean[] visited;
    boolean hasCycle;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
    }

    /**
     * 遍历图，判断是否有环
     * 
     * @param graph
     * @param i
     */
    private void traverse(List<Integer>[] graph, int i) {
        if (onPath[i]) {
            hasCycle = true;
        }
        if (visited[i] || hasCycle) {
            return;
        }
        // 前序位置
        onPath[i] = true;
        visited[i] = true;
        for (Integer neighbor : graph[i]) {
            traverse(graph, neighbor);
        }
        // 后序位置
        onPath[i] = false;
        // 注意，这里不能将visited[i]=false,因为这个节点确实访问过了，后序位置的时候只是不在路径上了而已
    }

    /**
     * 构造图结构
     * 
     * @param numCourses
     * @param prerequisites
     * @return
     */
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

}
