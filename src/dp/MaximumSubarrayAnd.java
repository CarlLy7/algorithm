package dp;

/**
 * @description: 最大子数组和
 * @author: lyq
 * @createDate: 9/4/2023
 * @version: 1.0
 */
public class MaximumSubarrayAnd {
    //先用滑动窗口解决问题，因为这个问题是一个子数组问题，所以可以考虑滑动窗口算法的
//    public int maxSubArray(int[] nums) {
//        int left = 0, right = 0;
//        int window = 0, maxSum = Integer.MIN_VALUE;
//        while (right < nums.length) {
//            //扩大窗口
//            window += nums[right];
//            right++;
//            //更新数据
//            maxSum = maxSum > window ? maxSum : window;
//
//            //缩小窗口
//            while (window < 0) {
//                window-=nums[left];
//                left++;
//            }
//        }
//        return maxSum;
//    }


    //=================================
    //下面使用动态规划来解决这个问题
//    public int maxSubArray(int[] nums) {
//        //定义的dp数组的定义是dp[i]表示以nums[i]结尾的最大子数组和
//        int[] dp=new int[nums.length];
//        //base case
//        dp[0]=nums[0];
//        for (int i = 1; i < nums.length ; i++) {
//            dp[i]=Math.max(nums[i],nums[i]+dp[i-1]);
//        }
//        int res=Integer.MIN_VALUE;
//        for (int i = 0; i < dp.length; i++) {
//            res=Math.max(res,dp[i]);
//        }
//        return res;
//    }

    //========================================
    //对上面的这个动态规划的空间进行一个压缩
//    public int maxSubArray(int[] nums) {
//        int dp_0=nums[0];
//        int dp_1=0;
//        int res=dp_0;
//        for (int i = 1; i < nums.length; i++) {
//            dp_1=Math.max(nums[i],nums[i]+dp_0);
//            dp_0=dp_1;
//            res=Math.max(res,dp_1);
//        }
//        return res;
//    }

    //==============================
    //使用前缀和来解决上面的问题
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        //构造前缀和数组
        for (int i = 1; i <= n; i++) {
            preSum[i]=preSum[i-1]+nums[i-1];
        }
        int minValue=Integer.MAX_VALUE;
        int res=Integer.MIN_VALUE;
        for (int i = 0; i <n ; i++) {
            //以nums[i]结尾的最大子数组之和为preSum[i]-前面最小的前缀和※
            minValue=Math.min(minValue,preSum[i]);
            //因为preSum中的第一个位置没用，整体发生了右移一个，所以我们要右移一位
            res=Math.max(res,preSum[i+1]-minValue);
        }
        return res;
    }
}
