import java.util.Arrays;

/**
 * @author: carl
 * @date: 2024/12/27
 */

public class day20241227Solution {
    //887
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        int m = 0;
        while (dp[k][m] < n) {
            m++;
            for (int i = 1; i <= k; i++) {
                dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1;
            }
        }
        return m;
    }

//    int[][] memo;
//
//    public int superEggDrop(int k, int N) {
//        memo = new int[k + 1][N + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -666);
//        }
//        return dp(k, N);
//    }
//
//    private int dp(int k, int n) {
//        if (k == 1) {
//            return n;
//        }
//        if (n == 0) {
//            return 0;
//        }
//        if (memo[k][n] != -666) {
//            return memo[k][n];
//        }
//        int res = Integer.MAX_VALUE;
//        int left = 1, right = n;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            int broken = dp(k - 1, mid - 1);
//            int noBroken = dp(k, n - mid);
//            if (broken > noBroken) {
//                right = mid - 1;
//                res = Math.min(res, broken + 1);
//            } else {
//                left = mid + 1;
//                res = Math.min(res, noBroken + 1);
//            }
//        }
//        memo[k][n] = res;
//        return res;
//    }

//    int[][] memo;
//
//    public int superEggDrop(int k, int N) {
//        memo = new int[k + 1][N + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -666);
//        }
//        // k个鸡蛋，N层楼找到恰好摔碎鸡蛋的楼层的次数
//        return dp(k, N);
//    }
//
//    private int dp(int k, int n) {
//        //base case
//        if (n == 0) {
//            return 0;
//        }
//        if (k == 1) {
//            return n;
//        }
//        if (memo[k][n] != -666) {
//            return memo[k][n];
//        }
//        int res=Integer.MAX_VALUE;
//        for (int i = 1; i <= n; i++) {
//            res=Math.min(res,
//                    Math.max(dp(k,n-i),dp(k-1,i-1))+1);
//        }
//        memo[k][n]=res;
//        return res;
//    }
}
