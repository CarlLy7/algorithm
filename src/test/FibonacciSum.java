package test;

/**
 * @description:
 * @author: lyq
 * @createDate: 4/3/2023
 * @version: 1.0
 */
public class FibonacciSum {
    public static int getFibonacciSum(int n){
        if(n<=0){
            return 1;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int first=1,second=2,third=0;
        int sum=0;
        for (int i = 3; i <=n ; i++) {
            third=(first+second)%1000000007;
            first=second;
            second=third;
//            sum+=third;
        }
        return third;
    }

    public static void main(String[] args) {
        System.out.println(getFibonacciSum(7));

    }
}
