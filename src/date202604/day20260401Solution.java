package date202604;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.01
 * @Since: 1.0
 */

public class day20260401Solution {
    // [11] 盛最多水的容器
    public int maxArea(int[] height) {
        // 借助左右指针从两边往中间收缩
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, area);
            // 因为往中间收缩的时候，长肯定短了，所以如果想要让面积变大就要保证宽度大
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    // [1124] 表现良好的最长时间段
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        int res = 0;
        // 前缀和->索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (hours[i - 1] <= 8 ? -1 : 1);
            // 如果这个前缀和是第一次出现，则直接记录到哈希表中
            if (!map.containsKey(preSum[i])) {
                map.put(preSum[i], i);
            }
            // 如果这个前缀和不是第一次出现，因为要找最大长度，所以不需要放进去

            // 如果当前这个位置的前缀和大于0，则代表已经是表现良好的天数大于正常的天数了
            if (preSum[i] > 0) {
                res = Math.max(res, i);
            } else {
                // preSum[i]-preSum[j]>0
                if (map.containsKey(preSum[i] - 1)) {
                    res = Math.max(res, i - map.get(preSum[i] - 1));
                }
            }
        }
        return res;
    }
}
