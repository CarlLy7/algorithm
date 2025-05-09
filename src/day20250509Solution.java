import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.09
 * @Since: 1.0
 */

public class day20250509Solution {
    // 300 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]以nums[i]为结尾的数组的递增子序列的最大长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 符合递增条件
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 322 零钱兑换
    public int coinChange(int[] coins, int amount) {
        // dp[i]表示凑出i金额所需要的最小的硬币数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 如果当前要凑的金额不能用剩下的硬币凑出来了
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    // 347 前 K 个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // method2 计数排序
        // 记录频次->频次下的元素
        ArrayList<Integer>[] freqVal = new ArrayList[nums.length + 1];
        for (Integer key : map.keySet()) {
            Integer freq = map.get(key);
            if (freqVal[freq] == null) {
                freqVal[freq] = new ArrayList<>();
            }
            freqVal[freq].add(key);
        }
        int[] res = new int[k];
        int count = 0;
        // 频次数组从后往前存放着出现频次最多的元素
        for (int i = freqVal.length - 1; i > 0; i--) {
            ArrayList<Integer> vals = freqVal[i];
            if (vals == null || vals.size() < 1) {
                continue;
            }
            for (int j = 0; j < vals.size(); j++) {
                res[count] = vals.get(j);
                count++;
                if (count == k) {
                    return res;
                }
            }
        }
        return null;
    }
    // method1:优先级队列
    // PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((entry1, entry2) -> {
    // return entry1.getValue().compareTo(entry2.getValue());
    // });
    // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    // queue.offer(entry);
    // // 只存放k个元素，所以最后就是剩下了频次最大的k个元素
    // if (queue.size() > k) {
    // queue.poll();
    // }
    // }
    // int[] res = new int[k];
    // for (int i = k - 1; i >= 0; i--) {
    // res[i] = queue.poll().getKey();
    // }
    // return res;
    // }
}
