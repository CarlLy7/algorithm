/**
 * @author: carl
 * @date: 2024/12/5
 */

public class day20241205Solution {
    //494
    //*----------回溯算法
//    int res=0;
//    public int findTargetSumWays(int[] nums, int target) {
//        if (nums.length==0){
//            return 0;
//        }
//        backTrack(nums,0,target);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start, int remain) {
//        if (start==nums.length){
//            if (remain==0){
//                res+=1;
//            }
//            return;
//        }
//        // 添加正号，剩余的数就会变小
//        remain-=nums[start];
//        backTrack(nums,start+1,remain);
//        remain+=nums[start];
//        //添加负号，剩余的数就会变大
//        remain+=nums[start];
//        backTrack(nums,start+1,remain);
//        remain-=nums[start];
//    }


    //*------- 动态规划
//    HashMap<String, Integer> memo = new HashMap<>();
//
//    public int findTargetSumWays(int[] nums, int target) {
//        if (nums.length == 0) {
//            return 0;
//        }
//        return dp(nums, 0, target);
//    }
//
//    private int dp(int[] nums, int start, int remain) {
//        if (start == nums.length) {
//            if (remain == 0) {
//                return 1;
//            }
//            return 0;
//        }
//        String key = start + "," + remain;
//        if (memo.containsKey(key)) {
//            return memo.get(key);
//        }
//        int ans = dp(nums, start + 1, remain - nums[start]) + dp(nums, start, remain + nums[start]);
//        memo.put(key, ans);
//        return ans;
//    }

    //*----------------背包类型动态规划
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < Math.abs(target) || (sum + target) % 2 != 0) {
            return 0;
        }
        return subsets(nums, (sum + target) / 2);
    }

    public int subsets(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        // base case
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    // 不放
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }
}
