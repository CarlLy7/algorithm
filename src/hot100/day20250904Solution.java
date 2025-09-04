package hot100;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.04
 * @Since: 1.0
 */

public class day20250904Solution {
    // [300] 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]:以nums[i]结尾的最长递增子序列长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // [295] 数据流的中位数
    class MedianFinder {
        // 左边数比较小的部分
        PriorityQueue<Integer> small;
        // 右边数比较大的部分
        PriorityQueue<Integer> large;

        public MedianFinder() {
            // 小的部分是一个大顶堆
            small = new PriorityQueue<>((a, b) -> {
                return b - a;
            });
            // 大的部分是一个小顶堆
            large = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // 先将元素放到多的一边，然后再从多的一边弹出一个放到少的一边
            // 来维持两边队列数据大小不超过1，这样才能来找中位数
            if (small.size() >= large.size()) {
                small.offer(num);
                large.offer(small.poll());
            } else {
                large.offer(num);
                small.offer(large.poll());
            }
        }

        public double findMedian() {
            if (small.size() > large.size()) {
                return small.peek();
            } else if (small.size() < large.size()) {
                return large.peek();
            } else {
                return (small.peek() + large.peek()) / 2.0;
            }
        }
    }

    // [322] 零钱兑换
    public int coinChange(int[] coins, int amount) {
        // dp[i]:凑成i金额，需要的最小的硬币数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        if (amount == 0) {
            return 0;
        }
        for (int money = 1; money <= amount; money++) {
            for (int coin : coins) {
                if (money - coin < 0) {
                    continue;
                }
                dp[money] = Math.min(dp[money], dp[money - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
