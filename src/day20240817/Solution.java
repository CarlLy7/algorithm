package day20240817;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-17 13:24
 * @version: 1.0
 */
public class Solution {
//    boolean res = true;
//    boolean[] color;
//    boolean[] visited;
//
//    public boolean isBipartite(int[][] graph) {
//        int n = graph.length;
//        color = new boolean[n];
//        visited = new boolean[n];
//        for (int v = 0; v < n; v++) {
//            if (!visited[v]) {
//                traverse(graph, v);
//            }
//        }
//        return res;
//    }
//
//    private void traverse(int[][] graph, int v) {
//        if (!res) {
//            return;
//        }
//        visited[v] = true;
//        for (int i : graph[v]) {
//            if (!visited[i]) {
//                color[i] = !color[v];
//                traverse(graph, i);
//            } else {
//                if (color[i] == color[v]) {
//                    res = false;
//                    return;
//                }
//            }
//        }
//    }

    boolean res = true;
    boolean[] visited;
    boolean[] color;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = buildGraph(n, dislikes);
        visited = new boolean[n + 1];
        color = new boolean[n + 1];
        for (int v = 1; v <= n; v++) {
            traverse(graph, v);
        }
        return res;
    }

    private void traverse(List<Integer>[] graph, int v) {
        if (!res) {
            return;
        }
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[v] == color[w]) {
                    res = false;
                    return;
                }
            }
        }
    }

    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] dislike : dislikes) {
            int v = dislike[0];
            int w = dislike[1];
            graph[v].add(w);
            graph[w].add(v);
        }
        return graph;
    }
}
