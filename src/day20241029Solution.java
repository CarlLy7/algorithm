/**
 * @description:
 * @author: Carl
 * @createDate: 2024-10-29 20:35
 * @version: 1.0
 */
public class day20241029Solution {
    class UF {
        private int count;
        private int[] parent;

        public UF(int n) {
            parent = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            count--;
        }

        public boolean connected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public int count() {
            return count;
        }
    }

    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char p = equation.charAt(0);
                char q = equation.charAt(3);
                uf.union(p - 'a', q - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (uf.connected(equation.charAt(0) - 'a', equation.charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }
}
