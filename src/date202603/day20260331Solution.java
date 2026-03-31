package date202603;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.31
 * @Since: 1.0
 */

public class day20260331Solution {
    // [283] 移动零
    public void moveZeroes(int[] nums) {
        // 将原始数组中的所有0删除掉
        int p = removeZero(nums, 0);
        // 删除之后，此时后面的所有元素都应该置为0
        for (; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    private int removeZero(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    // [523] 连续的子数组和
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        // 前缀和%k->索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            int val = preSum[i] % k;
            if (!map.containsKey(val)) {
                map.put(val, i);
            }
            // 如果之前就存在过了，为了求最长距离，所以不需要更新
        }
        for (int i = 1; i < preSum.length; i++) {
            int need = preSum[i] % k;
            if (map.containsKey(need)) {
                if (i - map.get(need) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    // [560] 和为 K 的子数组
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 前缀和->出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            int need = preSum[i] - k;
            if (map.containsKey(need)) {
                res += map.get(need);
            }
            if (!map.containsKey(preSum[i])) {
                map.put(preSum[i], 1);
            } else {
                map.put(preSum[i], map.get(preSum[i]) + 1);
            }
        }
        return res;
    }
}
