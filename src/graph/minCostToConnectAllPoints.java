package graph;

import java.util.*;

/**
 * @description: 连接所有点的最小费用 https://leetcode.cn/problems/min-cost-to-connect-all-points/
 * @author: lyq
 * @createDate: 31/5/2023
 * @version: 1.0
 */
public class minCostToConnectAllPoints {
    //下面的算法是使用的克鲁斯卡尔算法
//    public int minCostConnectPoints(int[][] points) {
//        int n=points.length;//多少个点
//        int res=0;
//        //构造一个[起点，终点，权重]的数组
//        ArrayList<int[]> edges=new ArrayList<>();
//        //将起点和终点进行简化，用它们的序号就可以了，反正最后只需要返回最小权重值
//        for (int i = 0; i < n; i++) {
//            for (int j = i+1; j <n ; j++) {
//                int xi=points[i][0],yi=points[i][1];//起点的x和y坐标
//                int xj=points[j][0],yj=points[j][1];//终点的x和y坐标
//                int weight=Math.abs(xj-xi)+Math.abs(yj-yi);
//                edges.add(new int[]{i,j,weight});
//            }
//        }
//        Collections.sort(edges,(a,b)->{return a[2]-b[2];});
//        UF uf = new UF(n);
//        for (int[] edge : edges) {
//            int from=edge[0];
//            int to=edge[1];
//            int weight=edge[2];
//            if(uf.connected(from,to)){
//                continue;
//            }
//            uf.union(from,to);
//            res+=weight;
//        }
//        return uf.count==1?res:-1;
//    }
//
//    class UF{
//        private int count;
//        private int[] parent;
//
//        public UF(int count) {
//            this.count = count;
//            parent=new int[count];
//            for (int i = 0; i < count; i++) {
//                parent[i]=i;
//            }
//        }
//        public void union(int p,int q){
//            int rootP = find(p);
//            int rootQ = find(q);
//            if(rootQ==rootP){
//                return;
//            }
//            parent[rootQ]=rootP;
//            count--;
//        }
//
//        public int find(int p){
//            if(parent[p]!=p){
//                parent[p]=find(parent[p]);
//            }
//            return parent[p];
//        }
//
//        public boolean connected(int p,int q){
//            return find(p)==find(q);
//        }
//
//        public int count(){
//            return count;
//        }
//    }

    ///--------------------------
    //使用Prim算法来求最小生成树
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]>[] graph = buildGraph(n, points);
        return new Prim(graph).weightSum();
    }

    private List<int[]>[] buildGraph(int n, int[][] points) {
        List<int[]>[] graph=new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i]=new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <n ; j++) {
                int xi=points[i][0],yi=points[i][1];
                int xj=points[j][0],yj=points[j][1];
                int weight=Math.abs(xi-xj)+Math.abs(yi-yj);
                graph[i].add(new int[]{i,j,weight});
                graph[j].add(new int[]{j,i,weight});
            }
        }
        return graph;
    }

    private class Prim {
        private PriorityQueue<int[]> queue;
        private List<int[]>[] graph;
        private boolean[] visited;
        private int weightSum = 0;

        public Prim(List<int[]>[] graph) {
            this.graph = graph;
            int n = graph.length;
            this.queue = new PriorityQueue<>((a, b) -> {
                return a[2] - b[2];
            });
            this.visited = new boolean[n];
            visited[0] = true;
            cut(0);
            while (!queue.isEmpty()) {
                int[] edge = queue.poll();
                int to = edge[1];
                int weight = edge[2];
                if (visited[to]) {
                    continue;
                }
                weightSum += weight;
                visited[to]=true;
                cut(to);
            }
        }

        public void cut(int p) {
            for (int[] edges : graph[p]) {
                int to = edges[1];
                if (visited[to]) {
                    continue;
                }
                queue.offer(edges);
            }
        }

        public int weightSum() {
            return weightSum;
        }

        public boolean allVisited() {
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
