package dp;

/**
 * @description: 预测赢家 https://leetcode.cn/problems/predict-the-winner/
 * @author: lyq
 * @createDate: 22/4/2023
 * @version: 1.0
 */
public class predictTheWinner {
    class Pair {
        private int first;//先手
        private int second;//后手

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        //先对这个二维的dp数组进行一个声明：dp[i][j].first代表石子堆的索引为（i,j）的时候先手可以取到的最大石子数；dp[i][j].second代表石子堆的索引为(i,j)的时候后手可以取到的最大石子数
        Pair[][] dp = new Pair[n][n];
        //初始化，Pair数组
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        //base case：如果只有一个石子堆的话，先手的最大数量就是这个石子堆的数量，后手的最大石子数量就是0
        for (int i = 0; i < n; i++) {
            dp[i][i].first = nums[i];
            dp[i][i].second = 0;
        }

        //因为此时dp数组的索引和(j-1)以及（i+1）有关，所以我们从base case出发，向结果靠拢。所以从下到上，从左到右进行一个遍历
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                //如果先手先取左边的最大石子数，注意下次变成了后手
                int left=nums[i]+dp[i+1][j].second;
                //如果先手先取右边的最大石子数，注意下次变成了后手
                int right=nums[j]+dp[i][j-1].second;
                //因为这个人贼聪明，所以他肯定会选择取左边和取右边中最大石子数的那个
                if(left>right){
                    //如果取左边比取右边的石子数大的话
                    dp[i][j].first=left;
                    //这一次的后手的结果就是下一次索引从（i+1,j）的先手的最大石子数
                    dp[i][j].second=dp[i+1][j].first;
                }else{
                    //如果取右边比取左边的石子数大的话
                    dp[i][j].first=right;
                    //这一次的后手的结果就是下一次索引从（i,j-1）的先手的最大石子数
                    dp[i][j].second=dp[i][j-1].first;
                }
            }
        }
        Pair res=dp[0][n-1];
        return res.first>=res.second;
    }
}
