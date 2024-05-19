package dp;

/**
 * @description: 斐波那契数 https://leetcode.cn/problems/fibonacci-number/
 * @author: lyq
 * @createDate: 24/3/2023
 * @version: 1.0
 */
public class FibonacciNumber {
//    public int fib(int n) {
//        int[] memo=new int[n+1];
//        if(n==0||n==1){
//            return n;
//        }
//        if(memo[n]!=0){
//            return memo[n];
//        }
//        memo[n]=fib(n-1)+fib(n-2);
//        return memo[n];
//    }
public int fib(int n) {
    if(n==0){
        return n;
    }
   int[] dp=new int[n+1];
    //base case
    dp[0]=0;
    dp[1]=1;
    for (int i = 2; i <=n; i++) {
        dp[i]=dp[i-1]+dp[i-2];
    }
    return dp[n];
}

}
