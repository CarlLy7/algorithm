package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 判断二分图 https://leetcode.cn/problems/is-graph-bipartite/
 * @author: lyq
 * @createDate: 28/5/2023
 * @version: 1.0
 */
public class isGraphBipartite {
//    boolean res=true;//返回最后的结果
//    boolean[] color;//颜色数组，true和false是两种颜色
//    boolean[] visited;//用来判断这个节点是不是访问过了
//    public boolean isBipartite(int[][] graph) {
//        int n= graph.length;//节点数
//        color=new boolean[n];
//        visited=new boolean[n];
//        //因为可能有非连通图，所以我们需要遍历每一个节点来进行判断
//        for (int i = 0; i < n; i++) {
//            if(!visited[i]){
//                traverse(graph,i);
//            }
//        }
//        return res;
//    }
//
//    private void traverse(int[][] graph, int v) {
//        if(!res){
//            //如果不是二分图，直接返回
//            return;
//        }
//        visited[v]=true;
//        for (int e : graph[v]) {
//            //如果这个节点没有访问过，需要设置为和v不同的颜色
//            if(!visited[e]){
//                color[e]=!color[v];
//                traverse(graph, e);
//            }else{
//                //如果这个节点访问过，需要判断是不是和v这个节点不同色
//                if(color[e]==color[v]){
//                    res=false;
//                    return;
//                }
//            }
//        }
//    }

    //--------------------
    //下面使用BFS来进行图的遍历
    boolean res=true;
    boolean[] visited;
    boolean[] color;
    public boolean isBipartite(int[][] graph){
        int n= graph.length;
        visited=new boolean[n];
        color=new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                bfs(graph,i);
            }
        }
        return res;
    }

    private void bfs(int[][] graph, int v) {
        Queue<Integer> queue=new LinkedList<>();
        visited[v]=true;
        queue.offer(v);
        while(!queue.isEmpty() && res){
            Integer e = queue.poll();
            for (int w : graph[e]) {
                if(!visited[w]){
                    color[w]=!color[e];
                    visited[w]=true;
                    queue.offer(w);
                }else{
                    if(color[w]==color[e]){
                        res=false;
                        return;
                    }
                }
            }
        }
    }
}
