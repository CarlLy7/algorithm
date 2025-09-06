package hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.06
 * @Since: 1.0
 */

public class day20250906Solution {
    // [438] 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        int left = 0, right = 0;
        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        int valid = 0;
        List<Integer> res = new ArrayList<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 窗口收缩
            while (right - left >= p.length()) {
                if (need.size() == valid) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (windows.containsKey(d)) {
                    if (windows.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }
        }
        return res;
    }

    // [543] 二叉树的直径
    int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return res;
    }

    /**
     * 从root出发找最大深度
     * 
     * @param root
     * @return
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSub = maxDepth(root.left);
        int rightSub = maxDepth(root.right);
        // 后序位置更新最大直径
        res = Math.max(res, leftSub + rightSub);
        return Math.max(leftSub, rightSub) + 1;
    }

    // [560] 和为 K 的子数组
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 前缀和->前缀和出现的次数
        HashMap<Integer, Integer> preSumToCountMap = new HashMap<>();
        preSumToCountMap.put(0, 1);
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            // 如果之前有前缀和为preSum[i]-k的比如preSum[j]，那么(j,i]之间就是满足条件的
            int need = preSum[i] - k;
            if (preSumToCountMap.containsKey(need)) {
                res += preSumToCountMap.get(need);
            }
            preSumToCountMap.put(preSum[i], preSumToCountMap.getOrDefault(preSum[i], 0) + 1);
        }
        return res;
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
