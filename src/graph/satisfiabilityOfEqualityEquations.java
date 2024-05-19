package graph;

/**
 * @description: 等式方程的可满足性 https://leetcode.cn/problems/satisfiability-of-equality-equations/
 * @author: lyq
 * @createDate: 28/5/2023
 * @version: 1.0
 */
public class satisfiabilityOfEqualityEquations {
    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for (String equation : equations) {
            if(equation.charAt(1)=='='){
                //如果这两个字母是相等的话，将他们连起来
                uf.union(equation.charAt(0)-'a',equation.charAt(3)-'a');
            }
        }
        for (String equation : equations) {
            if(equation.charAt(1)=='!'){
                //如果不相等判断是不是可以连，如果不相等还可以连的话说明不是可满足的
                if(uf.connected(equation.charAt(0) - 'a', equation.charAt(3) - 'a')){
                    return false;
                }
            }
        }
        return true;
    }
    class UF{
        private int count;
        private int[] parent;

        public UF(int count) {
            this.count = count;
            parent=new int[count];
            for (int i = 0; i < count; i++) {
                parent[i]=i;
            }
        }
        public void union(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootQ==rootP){
                return;
            }
            parent[rootQ]=rootP;
            count--;
        }

        public int find(int p){
            if(parent[p]!=p){
                parent[p]=find(parent[p]);
            }
            return parent[p];
        }

        public boolean connected(int p,int q){
            return find(p)==find(q);
        }

        public int count(){
            return count;
        }
    }
}
