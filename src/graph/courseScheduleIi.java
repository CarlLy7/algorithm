package graph;

import java.util.*;

/**
 * @description: 课程表 II https://leetcode.cn/problems/course-schedule-ii/
 * @author: lyq
 * @createDate: 26/5/2023
 * @version: 1.0
 */
public class courseScheduleIi {
    //下面的算法是DFS算法
//    List<Integer> postOrder=new ArrayList<>();//后序遍历的数组
//    boolean[] visited;
//    boolean[] onPath;//递归数组，也是用来判断是否有环的关键数组
//    boolean hasCycle;
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        int[] res=new int[numCourses];
//        List<Integer>[] graph=buildGraph(numCourses,prerequisites);
//        visited=new boolean[numCourses];
//        onPath=new boolean[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            traverse(graph,i);
//        }
//        if(hasCycle){
//            return new int[]{};
//        }
//        //如果没有环的话
//        Collections.reverse(postOrder);
//        for (int i = 0; i < postOrder.size(); i++) {
//            res[i]=postOrder.get(i);
//        }
//        return res;
//    }
//
//    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph=new List[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            graph[i]=new LinkedList<>();
//        }
//        for (int[] prerequisite : prerequisites) {
//            int from=prerequisite[1];
//            int to=prerequisite[0];
//            graph[from].add(to);
//        }
//        return graph;
//    }
//
//    private void traverse(List<Integer>[] graph, int s) {
//        if(onPath[s]){
//            hasCycle=true;
//        }
//        if(hasCycle || visited[s]){
//            return;
//        }
//        visited[s]=true;
//        onPath[s]=true;
//        for (Integer e : graph[s]) {
//            traverse(graph,e);
//        }
//        //后序位置
//        postOrder.add(s);
//        onPath[s]=false;
//    }
    //--------------------------------
    //下面的算法是BFS算法
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //构造图
        List<Integer>[] graph=buildGraph(numCourses,prerequisites);
        int[] indegree=new int[numCourses];//入度数组
        //构造入度数组
        for (int[] prerequisite : prerequisites) {
            int to=prerequisite[0];
            indegree[to]++;
        }
        Queue<Integer> queue=new LinkedList<>();
        //将入度为0的节点放到队列中
        for (int i = 0; i < numCourses; i++) {
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        int count=0;
        int[] res=new int[numCourses];
        while(!queue.isEmpty()){
            Integer poll = queue.poll();
            //DFS算法中每次出队的那个元素其实就是拓扑排序的顺序
            res[count]=poll;
            count++;
            //将这个出队的节点指向的所有节点的入度减一
            for (Integer e : graph[poll]) {
                indegree[e]--;
                if(indegree[e]==0){
                    queue.offer(e);
                }
            }
        }
        return count==numCourses?res:new int[]{};
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph=new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i]=new LinkedList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int from=prerequisite[1];
            int to=prerequisite[0];
            graph[from].add(to);
        }
        return graph;
    }
}
