package dp;

/**
 * @description: 分割等和子集
 * @author: lyq
 * @createDate: 11/4/2023
 * @version: 1.0
 */
public class SegmentationAndSubset {
    //将问题分解成了一个0-1背包问题，主要转换成了背包容量为sum/2 物品个数为n 每个物品的重量为 nums[i] 看看能不能正好将空间装满

    //下面对dp数组的含义来进行一个说明：dp[i][j]表示对于前i个物品，背包容量为j的情况下是否可以恰好装满
//    boolean[][] dp;
//    public boolean canPartition(int[] nums) {
//        int n=nums.length;
//        int sum=0;
//        for (int num : nums) {
//            sum+=num;
//        }
//        if(sum%2!=0){
//            return false;
//        }
//        sum=sum/2;
//        dp=new boolean[n+1][sum+1];
//        //base case:当容量为0的时候肯定就是装满了，所以应该是true
//        for (int i = 0; i <=n ; i++) {
//            dp[i][0]=true;
//        }
//        for (int i = 1; i <=n ; i++) {
//            for (int j = 1; j <= sum ; j++) {
//                //如果此时的容量放不下这个物品了，那就不放了
//                if(j-nums[i-1]<0){
//                    dp[i][j]=dp[i-1][j];
//                }else{
//                    //如果此时的容量可以放下这个物品，会考虑两种情况，放或者不放
//                    dp[i][j]=dp[i-1][j] || dp[i-1][j-nums[i-1]];
//                }
//            }
//        }
//        return dp[n][sum];
//    }

    //=================================

    //对空间进行一个压缩，将空间压缩到一维
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum=sum/2;
        boolean[] dp = new boolean[sum + 1];

        //base case
        dp[0] = true;
        //从上往下，从后往前进行遍历了，解答看的还不是很明白，https://labuladong.gitee.io/algo/di-er-zhan-a01c6/bei-bao-le-34bd4/jing-dian--43be3/
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }
        return dp[sum];
    }

}
