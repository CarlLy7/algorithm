package graph;

/**
 * @description: 被围绕的区域 https://leetcode.cn/problems/surrounded-regions/
 * @author: lyq
 * @createDate: 28/5/2023
 * @version: 1.0
 */
public class surroundedRegions {
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        UF uf = new UF(m * n + 1);
        //让n*m这个位置作为虚拟的根节点
        int dummy = m * n;
        //遍历四边把四边的O和这个虚拟的根节点相连
        for (int i = 0; i < m; i++) {
            //第一列
            if (board[i][0] == 'O') {
                uf.union(dummy, i * n);
            }
            //最后一列
            if (board[i][n - 1] == 'O') {
                uf.union(dummy, i * n + n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            //第一行
            if(board[0][j]=='O'){
                uf.union(dummy,j);
            }
            if(board[m-1][j]=='O'){
                uf.union(dummy,n*(m-1)+j);
            }
        }
        //构造一个辅助的二维数组，用来搜索上下左右的方向
        int[][] d=new int[][]{{1,0},{0,1},{0,-1},{-1,0}};
        for (int i = 1; i <m-1 ; i++) {
            for (int j = 1; j <n-1 ; j++) {
                if(board[i][j]=='O'){
                    for (int k = 0; k < 4; k++) {
                        int x=i+d[k][0];
                        int y=j+d[k][1];
                        if(board[x][y]=='O'){
                            uf.union(x*n+y,i*n+j);
                        }
                    }
                }
            }
        }
        //所有不和dummy相连的节点都替换成X
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!uf.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    class UF {
        private int count;
        private int[] parent;

        public UF(int n) {
            this.count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        //将两个节点进行连接
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            } else {
                parent[rootQ] = rootP;
            }
            count--;
        }

        //找到一个节点的根节点,在这里进行了路径压缩
        public int find(int p) {
            if (parent[p] != p) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }

        //判断这两个节点是不是联通的
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        //返回连通分量的数量
        public int count() {
            return count;
        }
    }
}
