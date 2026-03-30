package date202603;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.30
 * @Since: 1.0
 */

public class day20260330Solution {
    // [128] 最长连续序列
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (Integer num : set) {
            // 判断当前数是否可以作为第一个数
            if (set.contains(num - 1)) {
                continue;
            }
            int curNum = num;
            int curLen = 1;
            while (set.contains(curNum + 1)) {
                curNum = curNum + 1;
                curLen = curLen + 1;
            }
            res = Math.max(res, curLen);
        }
        return res;
    }

    // [1352] 最后 K 个数的乘积
    private class ProductOfNumbers {
        // 前缀积数组
        private List<Integer> preMuitl;

        /**
         * 用一个空的流初始化对象
         */
        public ProductOfNumbers() {
            preMuitl = new ArrayList<>();
            preMuitl.add(1);
        }

        /**
         * 将数字 num 添加到当前数字列表的最后面
         * 
         * @param num
         */
        public void add(int num) {
            // 如果当前元素为0，那么前缀积需要清0
            if (num == 0) {
                preMuitl.clear();
                preMuitl.add(1);
                return;
            }
            int n = preMuitl.size();
            preMuitl.add(preMuitl.get(n - 1) * num);
        }

        /**
         * 返回当前数字列表中，最后 k 个数字的乘积。你可以假设当前列表中始终 至少 包含 k 个数字
         * 
         * @param k
         * @return
         */
        public int getProduct(int k) {
            int n = preMuitl.size();
            if (k > n - 1) {
                return 0;
            }
            return preMuitl.get(n - 1) / preMuitl.get(n - k - 1);
        }
    }

    // [525] 连续数组
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
        }
        int res = 0;
        // 存储前缀和和对应的索引
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < preSum.length; i++) {
            // 如果这个前缀和第一次出现，咋直接塞入
            if (!map.containsKey(preSum[i])) {
                map.put(preSum[i], i);
            } else {
                // 如果这个前缀和不是第一次出现，那么上次出现的索引和当前索引之间的元素和肯定=0
                res = Math.max(res, i - map.get(preSum[i]));
            }
        }
        return res;
    }
}
