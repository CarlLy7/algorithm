package test;

/**
 * @description: 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * @author: lyq
 * @createDate: 4/3/2023
 * @version: 1.0
 */
public class myPow {
    //思想是使用二分幂的思想，如果指数是偶数的话，n次方就等于n/2*n/2  如果指数是奇数的话 (n-1)/2*(n-1)/2*底数
    public static double getPow(double base, int exponent) {
        boolean invalidFlag = false;//无效标志位
        if (equal(base, 0.0) && exponent < 0) { //因为double类型不能直接用==所以自己写一个比较的方法
            invalidFlag = true;
            return 0.0;
        }
        int exp=exponent;
        if(exponent<0){
            exp=-exponent;//保证我们在进行运算的时候指数都是正数
        }
        double ans=getPower(base,exp);
        if(exponent<0){
            ans=1.0/ans;
        }
        return ans;
    }

    private static double getPower(double base, int exp) {
        if(exp==0){
            return 1.0;
        }
        if(exp==1){
            return base;
        }
        double result = getPower(base, (exp >> 1));
        result*=result;
        //如果指数是奇数的话
        if((exp&1)==1){
            //多乘以一次底数
            result*=base;
        }
        return result;
    }

    private static boolean equal(double v1, double v2) {
        if ((v1 - v2) > -0.000001 && (v1 - v2) < 0.000001) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(getPow(1.00000, -1));
    }
}
