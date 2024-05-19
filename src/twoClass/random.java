package twoClass;

/**
 * @description: 这是学习的第二个知识切片，主要就是讲了一下java中的random函数以及一些面试题
 * java中的random函数，这个函数就是等概率的返回[0,1)范围内的一个double类型的数，如果我们强转成整型就是返回一个[0,n-1]范围的数
 * <p>
 * 下面说一下面试题：1.给定一个函数f()等概率的返回1-5中的一个数，现在要求我们等概率的返回1-7的数，并且只能使用f()函数，不能使用random这种函数
 * @author: lyq
 * @createDate: 18/8/2022
 * @version: 1.0
 */
public class random {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("第 " + (i + 1) + " 次的输出结果是 " + result());
        }
    }

    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 思路整理：
     * 首先我们对给定的这个函数f（）构造一个01等概率发生器
     * 第二步我们要通过对这个01等概率发生器进行左位移操作来得到0-6这个范围的数
     * 第三步调整第二步的结果让他满足我们的要求即可
     */
    public static int buildGenerator() {
        int ans = 0;
        /**
         * 如果f返回的数据的范围是奇数的话我们对半分将中间那个数闪出来，如果是中间那个数就跳过进入下一次循环，然后如果在前半个范围就是0，后半个范围就是1
         */
        do {
            ans = f();  //如果是等于3就循环重做
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    /**
     * .
     * 第二步进行左移
     */
    public static int leftMove() {
        /**
         * 因为要求我们最终等概率的返回1-7，所以我们要通过左移得到一个0-6之间的数，因为2³-1=7,所以最少移动三位
         */
        return (buildGenerator() << 2) + (buildGenerator() << 1) + buildGenerator();//这个方法可以等概率的返回0-7之间的数
    }

    /**
     * 第三步通过调整第二步等到的数据达到我们的要求即可
     */
    public static int result() {
        int ans = 0;
        do {
            //如果得到的是0-6之间的数我们就让他+1，如果得到7就重新做
            ans = leftMove();
        } while (ans == 7);
        return ans + 1;
    }

    /**
     * 下面做一个改变，如果我们知道一个函数只返回0或者1，但是返回0和1的概率不是相等的，这个时候如果我们想要得到等概率的0和1应该怎么做
     * 首先，0和1的组合有四种0和0，0和1， 1和0， 1和1
     * 因为我们要获得0和1等概率，所以只能从0和1，1和0这两种情况中取
     * 假设取0的概率是p，取1的概率是1-p，如果我们取两次都是0的话概率是p²，不是等概率的，如果一次是0另一次是1,或者一次是1另一次是0的概率都是p(1-p)是等概率的
     */

    public static int noEquality0and1() {
        return Math.random() < 0.84 ? 0 : 1;//84% 的概率返回0，16%的概率返回1
    }

    public static int equality0and1(){
        int ans=0;
        do {
            ans=noEquality0and1();
        }while (ans==noEquality0and1()); //如果第一次和第二次的结果是一样的，就重做，必须是不一样的才可以
        return ans;
    }

}
