import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.18
 * @Since: 1.0
 */

public class day20250618Solution {
    // [547] 省份数量
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count;
    }

    /**
     * 并查集类
     */
    private class UF {
        // 联通分量
        private int count;

        // 每个节点的父节点
        private int[] parent;

        public UF(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * 将两个节点联通
         * 
         * @param p
         * @param q
         */
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            parent[rootQ] = rootP;
            this.count--;
        }

        /**
         * 找到指定节点的父节点
         * 
         * @param x
         * @return
         */
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public int count() {
            return this.count;
        }
    }

    // [581] 最短无序连续子数组
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        // 单调递增栈
        Stack<Integer> incrStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 如果前面有比我还大的，说明乱序了
            while (!incrStack.isEmpty() && nums[incrStack.peek()] > nums[i]) {
                left = Math.min(left, incrStack.pop());
            }
            incrStack.push(i);
        }

        // 单调递减栈
        Stack<Integer> decStack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            // 如果前面有比我小的，说明乱序了
            while (!decStack.isEmpty() && nums[decStack.peek()] < nums[i]) {
                right = Math.max(right, decStack.pop());
            }
            decStack.push(i);
        }
        if (left == Integer.MAX_VALUE && right == Integer.MIN_VALUE) {
            return 0;
        }
        return right - left + 1;
    }

    // [687] 最长同值路径
    int res = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxLen(root, root.val);
        return res;
    }

    /**
     * 从root为根的二叉树开始找到值为parentVal的最大长度
     * 
     * @param root
     * @param parentVal
     * @return
     */
    private int maxLen(TreeNode root, int parentVal) {
        if (root == null) {
            return 0;
        }
        // 分解问题：左子树
        int leftLen = maxLen(root.left, root.val);
        // 分解问题：右子树
        int rightLen = maxLen(root.right, root.val);

        res = Math.max(res, leftLen + rightLen);
        if (root.val != parentVal) {
            return 0;
        }
        return 1 + Math.max(leftLen, rightLen);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
