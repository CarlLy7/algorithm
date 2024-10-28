/**
 * @description:
 * @author: Carl
 * @createDate: 2024-10-28 21:20
 * @version: 1.0
 */
public class day20241028Solution {
    // 并查集
    class UF {
        // 连通分量
        private int count;
        // 各个节点的根节点
        private int[] parent;

        public UF(int n) {
            this.count = n;
            parent = new int[n];
            // 初始化每个节点的根节点是自己
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // 连接两个节点
        public void union(int p, int q) {
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            if (pRoot == qRoot) {
                return;
            }
            parent[pRoot] = qRoot;
            count--;
        }

        // 判断两个节点是否连通
        public boolean connected(int p, int q) {
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            return pRoot == qRoot;
        }

        //返回图中的连通分量
        public int count() {
            return count;
        }

        // 查找x节点的根节点
        private int findRoot(int x) {
            if (parent[x] != x) {
                parent[x] = findRoot(parent[x]);
            }
            return parent[x];
        }
    }

    public void solve(char[][] board) {
        if (board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        UF uf = new UF(m * n + 1);
        int dummy = m * n;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                uf.union(dummy, i * n);
            }
            if (board[i][n - 1] == 'O') {
                uf.union(dummy, i * n + n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                uf.union(dummy, j);
            }
            if (board[m - 1][j] == 'O') {
                uf.union(dummy, (m - 1) * n + j);
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    // 上
                    if (board[i - 1][j] == 'O') {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                    // 下
                    if (board[i + 1][j] == 'O') {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    // 左
                    if (board[i][j - 1] == 'O') {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                    // 右
                    if (board[i][j + 1] == 'O') {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(i * n + j, dummy)) {
                    board[i][j] = 'X';
                }
            }
        }
    }


    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UF uf = new UF(n + 1);
        for (int[] edge : edges) {
            int p = edge[0];
            int q = edge[1];
            int rootP = uf.findRoot(p);
            int rootQ = uf.findRoot(q);
            if (rootP != rootQ) {
                uf.union(p, q);
            } else {
                return edge;
            }
        }
        return new int[]{};
    }

}
