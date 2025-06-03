import java.util.Arrays;
import java.util.TreeMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.03
 * @Since: 1.0
 */

public class day20250603Solution {
    // [1049] 最后一块石头的重量 II
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        // dp[i][j]:对于前i个石头，背包剩余容量为j的时候，可以装下的最大重量
        int[][] dp = new int[n + 1][sum / 2 + 1];
        for (int i = 1; i <= n; i++) {
            int stoneWeight = stones[i - 1];
            for (int j = 0; j <= sum / 2; j++) {
                // 可以装下
                if (j >= stoneWeight) {
                    // 可以选择装或者不装
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stoneWeight] + stoneWeight);
                } else {
                    // 装不下
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int maxWeight = dp[n][sum / 2];
        // sum-maxWeight就是剩下的另一半石头的重量
        return sum - maxWeight - maxWeight;
    }

    // [1235] 规划兼职工作
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        // 初始化每份工作
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        // 根据结束时间进行排序
        Arrays.sort(jobs, (a, b) -> {
            return Integer.compare(a[1], b[1]);
        });
        // dp[i]：从0~i时间内可以获得的最多的报酬
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        // base case
        dp.put(0, 0);
        for (int[] job : jobs) {
            int start = job[0];
            int end = job[1];
            int value = job[2];
            dp.put(end, Math.max(
                // 选择当前这份工作;floorEntry() 返回小于或等于给定键的最大键关联的键值对
                // 获得当前这份工作开始时间之前的最大报酬
                dp.floorEntry(start).getValue() + value,
                // 不选择
                dp.lastEntry().getValue()));
        }
        return dp.lastEntry().getValue();
    }

    // [1262] 可被三整除的最大和
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        // dp[i][j]:前i个元素，与3相除之后余数为j的最大元素和
        int[][] dp = new int[n + 1][3];
        // base case
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int cur = nums[i - 1];
            // 当前数与3相除余数为0
            if (cur % 3 == 0) {
                // 加到元素和或者不加到元素和
                dp[i][0] = Math.max(dp[i - 1][0] + cur, dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][1] + cur, dp[i - 1][1]);
                dp[i][2] = Math.max(dp[i - 1][2] + cur, dp[i - 1][2]);
            } else if (cur % 3 == 1) {
                // 加到元素和或者不加到元素和
                dp[i][0] = Math.max(dp[i - 1][2] + cur, dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][0] + cur, dp[i - 1][1]);
                dp[i][2] = Math.max(dp[i - 1][1] + cur, dp[i - 1][2]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + cur);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + cur);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + cur);
            }
        }
        return dp[n][0];
    }
}
