import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.24
 * @Since: 1.0
 */

public class day20250724Solution {
    // [LCR 159] 库存管理 III
    public int[] inventoryManagement(int[] stock, int cnt) {
        if (stock.length == 0 || cnt == 0) {
            return new int[] {};
        }
        Arrays.sort(stock);
        int[] res = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            res[i] = stock[i];
        }
        return res;
    }

    // [面试题 04.08] 首个共同祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (root == p || root == q) {
            return root;
        }
        // 分解问题，去左子树、右子树判断是不是有p、q的根节点
        TreeNode leftSub = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSub = lowestCommonAncestor(root.right, p, q);
        // 如果左右子树都有找到对应的公共祖先，说明根就是
        if (leftSub != null && rightSub != null) {
            return root;
        }
        return leftSub == null ? rightSub : leftSub;
    }

    // [面试题 04.12] 求和路径
    public int pathSum(TreeNode root, int sum) {
        // 前缀和map key:前缀和 value:这个前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);
        // 当前前缀和
        int curSum = 0;
        return dfs(root, curSum, sum, preSum);
    }

    /**
     * 遍历思路查找求和路径
     * 
     * @param root
     * @param curSum
     * @param sum
     * @param preSum
     * @return
     */
    private int dfs(TreeNode root, int curSum, int sum, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        curSum += root.val;
        // 先找到看看有没有需要的前缀和
        int need = preSum.getOrDefault(curSum - sum, 0);

        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        // 分解问题
        int leftSub = dfs(root.left, curSum, sum, preSum);
        int rightSub = dfs(root.right, curSum, sum, preSum);

        // 撤销选择
        preSum.put(curSum, preSum.get(curSum) - 1);

        return need + leftSub + rightSub;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
