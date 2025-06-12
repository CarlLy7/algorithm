import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.12
 * @Since: 1.0
 */

public class day20250612Solution {
    // [968] 监控二叉树
    int res = 0;

    public int minCameraCover(TreeNode root) {
        setCamera(root, false);
        return res;
    }

    /**
     *
     * @param root
     * @param hasParent
     * @return -1:节点为空，0没有被监控，1被监控了，2增加了监视器
     */
    private int setCamera(TreeNode root, boolean hasParent) {
        if (root == null) {
            return -1;
        }
        int left = setCamera(root.left, true);
        int right = setCamera(root.right, true);
        // 如果左右孩子都是空，说明当前节点是叶子结点
        if (left == -1 && right == -1) {
            // 有父节点让父节点监视自己就可以了
            if (hasParent) {
                return 0;
            }
            res++;
            return 2;
        }
        // 如果左右孩子有一个没有被监视，那么我们当前这个父节点就要进行监视
        if (left == 0 || right == 0) {
            res++;
            return 2;
        }

        // 如果左右孩子有一个是监视器
        if (left == 2 || right == 2) {
            return 1;
        }

        // 左右孩子都被监视
        if (hasParent) {
            return 0;
        } else {
            res++;
            return 2;
        }

    }

    // [2320] 统计放置房子的方式数
    int mod = 1000000007;
    int[] memo;

    public int countHousePlacements(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int res = dp(0, n);
        return (int)((long)res * res % mod);
    }

    // dp(i,n):在[i,n]的地块中，最多的放置房子方式
    private int dp(int i, int n) {
        if (i >= n) {
            // 不放也是一种方式
            return 1;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = (dp(i + 1, n) + dp(i + 2, n)) % mod;
        memo[i] = res;
        return res;
    }

    // [508] 出现次数最多的子树元素和
    // sum->count
    Map<Integer, Integer> sumToCount = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[] {};
        }
        sum(root);
        int maxCount = 0;
        for (Integer count : sumToCount.values()) {
            maxCount = Math.max(count, maxCount);
        }
        List<Integer> res = new ArrayList<>();
        for (Integer sum : sumToCount.keySet()) {
            if (sumToCount.get(sum) == maxCount) {
                res.add(sum);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    /**
     * 计算以root为根的二叉树的子树元素和
     * 
     * @param root
     */
    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        sumToCount.put(leftSum + rightSum + root.val, sumToCount.getOrDefault(leftSum + rightSum + root.val, 0) + 1);
        return leftSum + rightSum + root.val;
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
