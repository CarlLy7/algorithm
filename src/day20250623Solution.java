import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.23
 * @Since: 1.0
 */

public class day20250623Solution {
    // [1026] 节点与其祖先之间的最大差值
    int res = 0;

    public int maxAncestorDiff(TreeNode root) {
        getMinMax(root);
        return res;
    }

    /**
     * 以root为根的二叉树的最小值和最大值，第一个值为最小值，第二个值为最大值
     * 
     * @param root
     * @return
     */
    private int[] getMinMax(TreeNode root) {
        if (root == null) {
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        // 分解问题：计算左子树
        int[] leftMinMax = getMinMax(root.left);
        // 分解问题：计算右子树
        int[] rightMinMax = getMinMax(root.right);

        int min = min(root.val, leftMinMax[0], rightMinMax[0]);
        int max = max(root.val, leftMinMax[1], rightMinMax[1]);
        res = max(res, max - root.val, root.val - min);
        return new int[] {min, max};
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    // [1339] 分裂二叉树的最大乘积
    long ans = 0;
    int treeSum = 0;

    public int maxProduct(TreeNode root) {
        treeSum = getSum(root);
        getSum(root);
        return (int)(ans % (1e9 + 7));
    }

    /**
     * 计算二叉树节点之和
     * 
     * @param root
     * @return
     */
    private int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = getSum(root.left);
        int rightSum = getSum(root.right);
        int rootSum = leftSum + rightSum + root.val;
        // rootSum是当前二叉树的和、treeSum是整个二叉树的和，treeSum-rootSum就是剩下的二叉树的和
        ans = Math.max(ans, (long)rootSum * (treeSum - rootSum));
        return rootSum;
    }

    // [1443] 收集树上所有苹果的最少时间
    List<Boolean> hasApple;
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    HashSet<Integer> visited = new HashSet<>();

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        this.hasApple = hasApple;
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        int res = collect(0);
        return res == -1 ? 0 : res;
    }

    /**
     * 从root这个节点出发，返回所有苹果的最小路径，如果不存在苹果返回-1
     * 
     * @param root
     * @return
     */
    private int collect(int root) {
        //避免重复计算
        if (visited.contains(root)) {
            return -1;
        }
        visited.add(root);
        int res = 0;
        for (Integer next : graph.get(root)) {
            // 分解问题：递归计算子树
            int sub = collect(next);
            // 如果子树有苹果
            if (sub != -1) {
                res += sub + 2;
            }
        }
        if (res > 0) {
            return res;
        }
        // 子树没有苹果，但是当前根节点是苹果
        if (res == 0 && hasApple.get(root)) {
            return 0;
        }
        return -1;
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
