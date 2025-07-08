import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.08
 * @Since: 1.0
 */

public class day20250708Solution {
    // [1361] 验证二叉树
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // 记录每个节点的入度
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                indegree[leftChild[i]]++;
            }
            if (rightChild[i] != -1) {
                indegree[rightChild[i]]++;
            }
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] > 1) {
                return false;
            }
            if (indegree[i] == 0) {
                if (root != -1) {
                    return false;
                }
                root = i;
            }
        }
        // 没有根节点
        if (root == -1) {
            return false;
        }
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            int left = leftChild[i];
            int right = rightChild[i];
            if (left != -1) {
                if (uf.connected(i, left)) {
                    return false;
                }
                uf.union(i, left);
            }
            if (right != -1) {
                if (uf.connected(i, right)) {
                    return false;
                }
                uf.union(i, right);
            }
        }
        return uf.getCount() == 1;
    }

    /**
     * 并查集
     */
    private class UF {
        // 联通分量
        private int count;
        // 父节点
        private int[] parent;
        // 重量
        private int[] size;

        // 构造函数
        public UF(int n) {
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 寻找根节点
         * 
         * @param x
         * @return
         */
        public int findRoot(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        /**
         * 判断两个节点之间是否联通
         * 
         * @param p
         * @param q
         * @return
         */
        public boolean connected(int p, int q) {
            return findRoot(p) == findRoot(q);
        }

        /**
         * 将两个节点连接
         * 
         * @param p
         * @param q
         */
        public void union(int p, int q) {
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            if (pRoot == qRoot) {
                return;
            }
            // 将小重量的树，接到大重量的树下面
            if (size[pRoot] > size[qRoot]) {
                parent[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            } else {
                parent[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
            count--;
        }

        /**
         * 返回联通分量的个数
         * 
         * @return
         */
        public int getCount() {
            return this.count;
        }
    }

    // [947] 移除最多的同行或同列石头
    public int removeStones(int[][] stones) {
        int n = stones.length;
        // 记录一维坐标和id的映射
        Map<Integer, Integer> codeToId = new HashMap<>();
        for (int i = 0; i < n; i++) {
            codeToId.put(encoding(stones[i]), i);
        }
        // 记录每一列都有哪些元素
        HashMap<Integer, List<Integer>> colIndexToCode = new HashMap<>();
        // 记录每一行都有哪些元素
        HashMap<Integer, List<Integer>> rowIndexToCode = new HashMap<>();
        for (int[] stone : stones) {
            int x = stone[0];
            int y = stone[1];
            colIndexToCode.putIfAbsent(y, new ArrayList<>());
            rowIndexToCode.putIfAbsent(x, new ArrayList<>());
            colIndexToCode.get(y).add(encoding(stone));
            rowIndexToCode.get(x).add(encoding(stone));
        }
        UF uf = new UF(n);
        for (Integer key : colIndexToCode.keySet()) {
            // 拿到某一列的所有元素
            List<Integer> col = colIndexToCode.get(key);
            Integer first = codeToId.get(col.get(0));
            for (int i = 1; i < col.size(); i++) {
                Integer other = codeToId.get(col.get(i));
                uf.union(first, other);
            }
        }

        for (Integer key : rowIndexToCode.keySet()) {
            // 某一行的所有元素
            List<Integer> row = rowIndexToCode.get(key);
            int first = codeToId.get(row.get(0));
            for (int i = 1; i < row.size(); i++) {
                Integer other = codeToId.get(row.get(i));
                uf.union(first, other);
            }
        }
        return n - uf.getCount();
    }

    /**
     * 将二维坐标转成一维坐标
     * 
     * @param point
     * @return
     */
    private int encoding(int[] point) {
        return point[0] * 10000 + point[1];
    }

    // [2101] 引爆最多的炸弹
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        // 邻接表存储图
        List<Integer>[] grah = new List[n];
        for (int i = 0; i < n; i++) {
            grah[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (Math.pow(bombs[i][0] - bombs[j][0], 2) + Math.pow(bombs[i][1] - bombs[j][1], 2) <= Math
                    .pow(bombs[i][2], 2)) {
                    grah[i].add(j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, bfs(grah, i));
        }
        return res;
    }

    /**
     * 从start开始遍历，最多有多少个可达节点
     * 
     * @param grah
     * @param start
     * @return
     */
    private int bfs(List<Integer>[] grah, int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[grah.length];
        int count = 0;
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            count++;
            // 找到当前这个节点所有的邻接节点，然后遍历
            for (Integer next : grah[node]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        return count;
    }
}
