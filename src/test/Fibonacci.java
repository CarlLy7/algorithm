package test;

/**
 * @description: 得到斐波那契数组的前n项和
 * @author: lyq
 * @createDate: 7/3/2023
 * @version: 1.0
 */
public class Fibonacci {
    public static int getSum(int n){
        int first=1;
        int second=1;
        int third=0;
        int sum=2;
        for (int i = 3; i <=n; i++) {
            third=first+second;
            first=second;
            second=third;
            sum+=third;
        }
        return sum;
    }

    public static void main(String[] args) {
        //1 1 2 3 5 8
        System.out.println(getSum(5));
    }
}
