package graph;

/**
 * @description: 以图判树 https://leetcode.cn/problems/graph-valid-tree/
 * @author: lyq
 * @createDate: 31/5/2023
 * @version: 1.0
 */
public class graphValidTree {
    // 判断输入的若干条边是否能构造出一棵树结构
    boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            int start=edge[0];
            int end=edge[1];
            if(uf.connected(start,end)){
                return false;
            }
            uf.union(start,end);
        }
        return uf.count==1;
    }

    private class UF {
        private int count;//连通分量
        private int[] parents;
        public UF(int n) {
            count=n;
            parents=new int[n];
            for (int i = 0; i < n; i++) {
                parents[i]=i;
            }
        }
        public void union(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootQ==rootQ){
                return;
            }
            parents[rootQ]=rootP;
            count--;
        }
        public int find(int p){
            parents[p]=find(parents[p]);
            return parents[p];
        }
        public int count(){
            return count;
        }
        public boolean connected(int p,int q){
            return find(p)==find(q);
        }
    }
}
