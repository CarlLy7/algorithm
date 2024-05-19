package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: 最低成本联通所有城市 https://leetcode.cn/problems/connecting-cities-with-minimum-cost/
 * @author: lyq
 * @createDate: 31/5/2023
 * @version: 1.0
 */
public class connectingCitiesWithMinimumCost {
    //下面是使用了克鲁斯卡尔算法
//    int minimumCost(int n, int[][] connections) {
//        //因为城市的编号是从1开始的，所以初始化大小的时候要初始化未n+1
//        UF uf = new UF(n + 1);
//        int res=0;//最后返回的权重
//        //将所有城市之间的权重进行从小到达排序
//        Arrays.sort(connections,(a,b)->(a[2]-b[2]));
//        for (int[] connection : connections) {
//            int from=connection[0];
//            int to=connection[1];
//            if(uf.connected(from,to)){
//                //如果这两个点已经联通了，那么就不能再联通了，不然就有环了
//                continue;
//            }
//            uf.union(from,to);
//            res+=connection[2];
//        }
//        //为什么连通分量要等于2呢，因为初始化之后的0这个节点没有用到，所以连通分量为2
//        return uf.count==2?res:-1;
//    }
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
    //-------------------------
    //下面是使用Prim算法来求最小生成树的算法
    int minimumCost(int n, int[][] connections) {
        List<int[]>[] graph=buildGraph(n,connections);
        Prim prim = new Prim(graph);
        return prim.allVisited()? prim.getWeighSum() : -1;
    }

    private List<int[]>[] buildGraph(int n, int[][] connections) {
       List<int[]>[] graph=new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i]=new LinkedList<>();
        }
        for (int[] connection : connections) {
            int from=connection[0]-1;
            int to=connection[1]-1;
            int weight=connection[2];
            graph[from].add(new int[]{from,to,weight});
            graph[to].add(new int[]{to,from,weight});
        }
        return graph;
    }

    class Prim{
        private PriorityQueue<int[]> queue;
        private List<int[]>[] graph;
        private int weighSum=0;
        private boolean[] visited;

        public Prim(List<int[]>[] graph) {
            this.graph = graph;
            this.queue=new PriorityQueue<>((a,b)->{return a[2]-b[2];});
            int n= graph.length;
            this.visited=new boolean[n];
            visited[0]=true;
            cut(0);
            while(!queue.isEmpty()){
                int[] edges = queue.poll();
                int to=edges[1];
                int weight=edges[2];
                if(visited[to]){
                    continue;
                }
                visited[to]=true;
                weighSum+=weight;
                cut(to);
            }
        }
        public void cut(int p){
            for (int[] ints : graph[p]) {
                int to=ints[1];
                if(visited[to]){
                    continue;
                }
                queue.offer(ints);
            }
        }
        public int getWeighSum(){
            return  weighSum;
        }
        public boolean allVisited(){
            for (int i = 0; i < visited.length; i++) {
                if(!visited[i]){
                    return false;
                }
            }
            return true;
        }
    }
}
