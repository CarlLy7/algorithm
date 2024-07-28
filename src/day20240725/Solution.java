package day20240725;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-24 13:55
 * @version: 1.0
 */
public class Solution {
    class LFUCache {
        int capacity;
        HashMap<Integer, Integer> keyToVal;
        HashMap<Integer, Integer> keyToFre;
        HashMap<Integer, LinkedHashSet<Integer>> freToKeys;
        int minFre;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.keyToVal = new HashMap<>();
            this.keyToFre = new HashMap<>();
            this.freToKeys = new HashMap<>();
            this.minFre = 0;
        }

        public int get(int key) {
            if (!keyToVal.containsKey(key)) {
                return -1;
            }
            //增加频次
            increaseFre(key);
            return keyToVal.get(key);
        }

        private void increaseFre(int key) {
            int fre = keyToFre.get(key);
            //更新keyToFre
            keyToFre.put(key, fre + 1);
            //更新freToKeys
            freToKeys.putIfAbsent(fre + 1, new LinkedHashSet<>());
            freToKeys.get(fre + 1).add(key);
            freToKeys.get(fre).remove(key);
            if (freToKeys.get(fre).isEmpty()) {
                freToKeys.remove(fre);
                if (this.minFre == fre) {
                    this.minFre++;
                }
            }
        }

        public void put(int key, int value) {
            if (keyToVal.containsKey(key)) {
                keyToVal.put(key, value);
                increaseFre(key);
                return;
            }
            if (this.capacity <= keyToVal.size()) {
                removeMinFre(this.minFre);
            }
            keyToVal.put(key, value);
            keyToFre.put(key, 1);
            this.minFre = 1;
            freToKeys.putIfAbsent(this.minFre, new LinkedHashSet<>());
            freToKeys.get(this.minFre).add(key);
        }

        //满了，删除最小频率的key
        private void removeMinFre(int minFre) {
            int key = freToKeys.get(minFre).iterator().next();
            //更新keyToVal
            keyToVal.remove(key);
            //更新keyToFre
            keyToFre.remove(key);
            //更新freToKeys
            freToKeys.get(minFre).remove(key);
            if (freToKeys.get(minFre).isEmpty()) {
                freToKeys.remove(minFre);
            }
        }
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        //记录每个单词出现的最后位置
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }

//    public int maxSubArray(int[] nums) {
//        int n = nums.length;
//        //dp[i]:以nums[i]结尾得最大子数组和
//        int[] dp = new int[n];
//        dp[0] = nums[0];
//        for (int i = 1; i < n; i++) {
//            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
//        }
//        int res = Integer.MIN_VALUE;
//        for (int i = 0; i < dp.length; i++) {
//            res = Math.max(res, dp[i]);
//        }
//        return res;
//    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int res = Integer.MIN_VALUE;
        //记录前缀和的最小值
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            //维护minSum是preSum[0...i]的最小值
            minSum = Math.min(minSum, preSum[i]);
            res = Math.max(res, preSum[i + 1] - minSum);
        }
        return res;
    }
}
