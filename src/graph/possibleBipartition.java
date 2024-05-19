package graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 可能的二分法 https://leetcode.cn/problems/possible-bipartition/
 * @author: lyq
 * @createDate: 28/5/2023
 * @version: 1.0
 */
public class possibleBipartition {
    boolean res=true;
    boolean[] visited;
    boolean[] color;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        visited=new boolean[n+1];
        color=new boolean[n+1];
        List<Integer>[] graph=build(n,dislikes);
        for (int i = 1; i <=n ; i++) {
            if(!visited[i]){
                traverse(graph,i);
            }
        }
        return res;
    }

    private void traverse(List<Integer>[] graph, int v) {
        if(!res){
            return;
        }
        visited[v]=true;
        for (Integer e : graph[v]) {
            if(!visited[e]){
                color[e]=!color[v];
                traverse(graph,e);
            }else{
                if(color[e]==color[v]){
                    res=false;
                    return;
                }
            }
        }
    }

    private List<Integer>[] build(int n, int[][] dislikes) {
        List<Integer>[] graph=new List[n+1];
        for (int i = 1; i <=n ; i++) {
            graph[i]=new LinkedList<>();
        }
        for (int[] dislike : dislikes) {
            int from=dislike[0];
            int to=dislike[1];
            //切记是无向图
            graph[from].add(to);
            graph[to].add(from);
        }
        return graph;
    }
}
