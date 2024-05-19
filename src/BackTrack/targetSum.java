package BackTrack;

/**
 * @description: 目标和 https://leetcode.cn/problems/target-sum/
 * @author: lyq
 * @createDate: 13/4/2023
 * @version: 1.0
 */
public class targetSum {
    //    int res=0;
//    public int findTargetSumWays(int[] nums, int target) {
//        if(nums.length<=0){
//            return 0;
//        }
//        backTrack(nums,0,target);
//        return res;
//    }
//
//    //这里remainder是剩余，和我们之前是总和是类似的，只不过是反过来了而已
//    private void backTrack(int[] nums, int start, int remainder) {
//        //base case
//        if(start== nums.length){
//            if(remainder==0){
//                res++;
//            }
//            return;
//        }
//        //选择+号
//        remainder-=nums[start];
//        backTrack(nums,start+1,remainder);
//        remainder+=nums[start];
//        //选择-号
//        remainder+=nums[start];
//        backTrack(nums,start+1,remainder);
//        remainder-=nums[start];
//    }
    //===============================
    public int findTargetSumWays(int[] nums, int target) {
        int sum=0;
        for (int num : nums) {
            sum+=num;
        }
        if(sum<Math.abs(target)||(sum+target)%2!=0){
            return 0;
        }
        return subsets(nums,(sum+target)/2);
    }

    private int subsets(int[] nums, int sum) {
        int[] dp=new int[sum+1];
        //base case
        dp[0]=1;
        for (int i = 1; i <=nums.length ; i++) {
            for (int j = sum; j >=0 ; j--) {
                if(j-nums[i-1]>=0){
                    dp[j]=dp[j]+dp[j-nums[i-1]];
                }else{
                    dp[j]=dp[j];
                }
            }
        }
        return dp[sum];
    }
}
