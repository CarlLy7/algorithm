/**
 * @author: carl
 * @date: 2024/12/26
 */

public class day20241226Solution {
    //337
    public int rob(day20241112Solution.TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    // 返回一个size=2的数组，res[0]表示不抢root的结果，res[1]表示抢root的结果
    private int[] dp(day20241112Solution.TreeNode root) {
        //base case
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        //抢root
        int rob = root.val + left[0] + right[0];
        //不抢rob
        int noRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{noRob, rob};
    }

    //213
//    public int rob(int[] nums){
//        int n=nums.length;
//        if (n==1){
//            return nums[0];
//        }
//        return Math.max(robRange(nums,0,n-2),robRange(nums,1,n-1));
//    }
//
//    private int robRange(int[] nums, int start, int end) {
//        int[] dp=new int[end+2];
//        for (int i = end; i >=start ; i--) {
//            dp[i]=Math.max(dp[i+1],dp[i+2]+nums[i]);
//        }
//        return dp[start];
//    }

    //198
//    int[] memo;
//
//    public int rob(int[] nums) {
//        memo = new int[nums.length];
//        Arrays.fill(memo, -1);
//        return dp(nums, 0);
//    }
//
//    private int dp(int[] nums, int start) {
//        if (start >= nums.length) {
//            return 0;
//        }
//        if (memo[start] != -1) {
//            return memo[start];
//        }
//        int res = Math.max(dp(nums, start + 1), nums[start] + dp(nums, start + 2));
//        memo[start] = res;
//        return res;
//    }
}
