package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 课程表  https://leetcode.cn/problems/course-schedule/
 * @author: lyq
 * @createDate: 26/5/2023
 * @version: 1.0
 */
public class courseSchedule {
    //下面的算法是DFS算法
//    boolean[] onPath;//用来标识访问的路径
//    boolean[] visited;//用来标识已经访问的节点
//    boolean hasCycle;//用来标识是否有环
//
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        onPath = new boolean[numCourses];
//        visited = new boolean[numCourses];
//        //构造图
//        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
//        //遍历图中的每一个节点，让每一个节点作为起点进行DFS算法
//        for (int i = 0; i < numCourses; i++) {
//            traverse(graph, i);
//        }
//        return !hasCycle;
//    }
//
//    private void traverse(List<Integer>[] graph, int s) {
//        //如果当前这个节点已经访问了，说明有环
//        if (onPath[s]) {
//            hasCycle = true;
//        }
//        //如果当前这个节点已经访问过，直接返回
//        if (hasCycle || visited[s]) {
//            return;
//        }
//        visited[s] = true;
//        onPath[s] = true;
//        for (Integer e : graph[s]) {
//            traverse(graph, e);
//        }
//        onPath[s] = false;
//    }
//
//    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph = new List[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            graph[i] = new LinkedList<>();
//        }
//        for (int[] prerequisite : prerequisites) {
//            int from = prerequisite[1];
//            int to = prerequisite[0];
//            graph[from].add(to);
//        }
//        return graph;
//    }

    //------------------------------------
    //下面的算法是BFS算法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //构造图
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        //构造入度的数组
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            indegree[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        //将入度为0的节点放到队列中
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            count++;
            //将这个出队的节点指向的所有节点的入度减一
            for (Integer e : graph[poll]) {
                indegree[e]--;
                if(indegree[e]==0){
                    queue.offer(e);
                }
            }
        }
        return count==numCourses;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
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
}
