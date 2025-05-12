import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.12
 * @Since: 1.0
 */

public class day20250512Solution {
    // 416 分割等和子集
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        sum = sum / 2;
        // dp[i]:当前背包容量为i的时候，是否能够恰好装满背包
        boolean[] dp = new boolean[sum + 1];
        // base case
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                // 如果当前背包容量能放下
                if (j - nums[i] >= 0) {
                    // dp[j]:不将当前的元素放入背包中
                    // dp[j-nums[i]]将当前元素放入背包
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }

    // 437 路径总和 III
    HashMap<Long, Integer> pathSumCount = new HashMap<>();
    int res = 0;
    int targetSum;
    // 前缀和
    Long pathSum = 0L;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        this.targetSum = targetSum;
        pathSumCount.put(0L, 1);
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        pathSum += root.val;
        // 判断之前的路径上有没有和为pathSum-targetSum的节点，如果有的话，说明从那个节点到当前节前的和是targetSum,也就是符合题目要求的路径
        res += pathSumCount.getOrDefault(pathSum - targetSum, 0);
        pathSumCount.put(pathSum, pathSumCount.getOrDefault(pathSum, 0) + 1);
        traverse(root.left);
        traverse(root.right);
        pathSumCount.put(pathSum, pathSumCount.get(pathSum) - 1);
        pathSum -= root.val;
    }

    // 438 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int valid = 0;
        List<Integer> res = new ArrayList<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }

    public class TreeNode {
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
