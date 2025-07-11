import java.util.Arrays;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.11
 * @Since: 1.0
 */

public class day20250711Solution {
    // [853] 车队
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        // 按照每辆车的起始位置进行递增排序
        Arrays.sort(cars, (int[] a, int[] b) -> {
            return Integer.compare(a[0], b[0]);
        });
        double[] times = new double[n];
        for (int i = 0; i < n; i++) {
            int[] car = cars[i];
            times[i] = (double)(target - car[0]) / car[1];
        }
        // 不使用单调栈实现
        // int res = 0;
        // double max = 0;
        // // 倒序遍历，递增子序列的个数就是结果
        // for (int i = n - 1; i >= 0; i--) {
        // if (times[i] > max) {
        // res++;
        // max = times[i];
        // }
        // }
        // return res;
        // 使用单调栈实现，也就是单调递增栈
        Stack<Double> stack = new Stack<>();
        for (double time : times) {
            while (!stack.isEmpty() && time >= stack.peek()) {
                stack.pop();
            }
            stack.push(time);
        }
        return stack.size();
    }

    // [965] 单值二叉树
    public boolean isUnivalTree(TreeNode root) {
        if (root.left != null) {
            boolean leftSubResult = isUnivalTree(root.left);
            if (!leftSubResult) {
                return false;
            }
            if (root.val != root.left.val) {
                return false;
            }
        }
        if (root.right != null) {
            boolean rightSubResult = isUnivalTree(root.right);
            if (!rightSubResult) {
                return false;
            }
            if (root.val != root.right.val) {
                return false;
            }
        }
        return true;
    }

    // [1123] 最深叶节点的最近公共祖先
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        ResultNode result = maxDepth(root);
        return result.node;
    }

    /**
     * 找到root为根的二叉树的最深叶节点的最近公共祖先
     * 
     * @param root
     * @return
     */
    private ResultNode maxDepth(TreeNode root) {
        if (root == null) {
            return new ResultNode(null, 0);
        }
        // 分解问题
        ResultNode leftResult = maxDepth(root.left);
        ResultNode rightResult = maxDepth(root.right);
        // 如果左右孩子的最大深度一样，说明当前节点就是最近公共祖先，否则结果肯定在深度大的一侧
        if (leftResult.depth == rightResult.depth) {
            return new ResultNode(root, leftResult.depth + 1);
        }
        ResultNode res = leftResult.depth > rightResult.depth ? leftResult : rightResult;
        res.depth++;
        return res;
    }

    private class ResultNode {
        // 最深叶子节点的最近公共祖先节点
        private TreeNode node;
        // 最大深度
        private int depth;

        public ResultNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    // [236] 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (root == p || root == q) {
            return root;
        }
        // 分解问题
        TreeNode leftSub = lowestCommonAncestor(root.left, p, q);
        TreeNode rightSub = lowestCommonAncestor(root.right, p, q);
        // 左右子树中都有结果，说明当前这个节点就是结果
        if (leftSub != null && rightSub != null) {
            return root;
        }
        return leftSub == null ? rightSub : leftSub;
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
