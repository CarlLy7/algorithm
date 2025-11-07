package hot100;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.07
 * @Since: 1.0
 */

public class day20251107Solution {
    // [1448] 统计二叉树中好节点的数目
    // int count = 0;
    //
    // public int goodNodes(TreeNode root) {
    // traverse(root, root.val);
    // return count;
    // }
    //
    // private void traverse(TreeNode root, int maxVal) {
    // if (root == null) {
    // return;
    // }
    // if (maxVal <= root.val) {
    // count++;
    // maxVal = root.val;
    // }
    // traverse(root.left, maxVal);
    // traverse(root.right, maxVal);
    // }

    // [437] 路径总和 III
    HashMap<Long, Integer> pathSumToCount = new HashMap<>();
    long pathSum, targetSum;
    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        this.targetSum = targetSum;
        pathSumToCount.put(0l, 1);
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        pathSum += root.val;
        /**
         * 假设：
         *当前节点的路径和是 pathSum
         *我们在某个祖先节点时的路径和是 prevSum
         *如果：
         *pathSum - prevSum == targetSum
         *说明从这个祖先节点的下一个节点到当前节点这一段路径的和正好是目标值。
         * prevSum=pathSum-targetSum
         */
        res += pathSumToCount.getOrDefault(pathSum - targetSum, 0);
        pathSumToCount.put(pathSum, pathSumToCount.getOrDefault(pathSum, 0) + 1);
        traverse(root.left);
        traverse(root.right);
        pathSumToCount.put(pathSum, pathSumToCount.get(pathSum) - 1);
        pathSum -= root.val;
    }

    // [513] 找树左下角的值

    /**
     * int maxDepth = 0; int depth = 0; TreeNode res = null;
     *
     * public int findBottomLeftValue(TreeNode root) { traverse(root); return res.val; }
     *
     * private void traverse(TreeNode root) { if (root == null) { return; } depth++; // 如果当前的深度大于最大深度，那么就更新res if (depth
     * > maxDepth) { maxDepth = depth; res = root; } // 因为要找最左边，所以先遍历左子树 traverse(root.left); traverse(root.right); //
     * 回退到父节点的时候要记得depth-- depth--; }
     */

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
