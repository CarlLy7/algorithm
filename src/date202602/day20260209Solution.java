package date202602;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.09
 * @Since: 1.0
 */

public class day20260209Solution {
    // [22] 括号生成
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder track = new StringBuilder();
        backTrack(n, n, track, ans);
        return ans;
    }

    private void backTrack(int leftNum, int rightNum, StringBuilder track, List<String> ans) {
        // 合法的括号从左到右肯定是左括号大于等于右括号的
        if (rightNum < leftNum) {
            return;
        }
        if (leftNum < 0 || rightNum < 0) {
            return;
        }
        if (leftNum == 0 && rightNum == 0) {
            ans.add(track.toString());
            return;
        }
        // 放一个左括号
        track.append("(");
        backTrack(leftNum - 1, rightNum, track, ans);
        track.deleteCharAt(track.length() - 1);
        // 放一个右括号
        track.append(")");
        backTrack(leftNum, rightNum - 1, track, ans);
        track.deleteCharAt(track.length() - 1);
    }

    // [698] 划分为k个相等的子集
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        if (k > nums.length) {
            return false;
        }
        // 使用位图来记录已经访问过的元素
        int used = 0;
        int target = sum / k;
        return backTrack(k, 0, nums, 0, used, target);
    }

    Map<Integer, Boolean> memo = new HashMap<>();

    /**
     *
     * @param k:K号桶
     * @param bucketSum:桶中的容量
     * @param nums
     * @param start
     * @param used
     * @param target
     * @return
     */
    private boolean backTrack(int k, int bucketSum, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucketSum == target) {
            //递归处理下一个桶
            boolean subRes = backTrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, subRes);
            return subRes;
        }
        if (memo.containsKey(used)) {
            return memo.get(used);
        }
        for (int i = start; i < nums.length; i++) {
            // 这个桶放不下了
            if (bucketSum + nums[i] > target) {
                continue;
            }
            // 已经访问过了
            if (((used >> i) & 1) == 1) {
                continue;
            }
            // 做选择
            used |= 1 << i;
            bucketSum += nums[i];
            // 递归穷举下一个数字
            if (backTrack(k, bucketSum, nums, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            used ^= 1 << i;
            bucketSum -= nums[i];
        }
        return false;
    }
}
