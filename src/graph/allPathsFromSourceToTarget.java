package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 所有可能的路径 https://leetcode.cn/problems/all-paths-from-source-to-target/
 * @author: lyq
 * @createDate: 26/5/2023
 * @version: 1.0
 */
public class allPathsFromSourceToTarget {
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> track=new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        traverse(graph,0);
        return res;
    }

    private void traverse(int[][] graph, int s) {
        track.addLast(s);
        int n= graph.length;
        if(s==n-1){
            //如果访问到最后一个元素了，可以直接返回了
            res.add(new LinkedList<>(track));
            track.removeLast();
            return;
        }
        for (int i : graph[s]) {
            traverse(graph,i);
        }
        track.removeLast();
    }
}
