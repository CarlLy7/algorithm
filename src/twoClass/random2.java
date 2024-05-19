package twoClass;

/**
 * @description: 对random1的扩展主要是另一个面试题
 * 下面说一下面试题：1.给定一个函数f()等概率的返回3-19中的一个数，现在要求我们等概率的返回20-56的数，并且只能使用f()函数，不能使用random这种函数
 * @author: lyq
 * @createDate: 18/8/2022     3-19 之间一共19-3+1=17个数，然后对半分，3-10 11 12-19
 * @version: 1.0
 */
public class random2 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("第 " + (i + 1) + " 次的输出结果是 " + result());
        }
    }

    public static int f() {
        return (int) (Math.random() * 17) + 3;
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
        } while (ans == 11);
        return ans < 11 ? 0 : 1;
    }

    /**
     * .
     * 第二步进行左移
     */
    public static int leftMove() {
        /**
         * 因为要求我们最终等概率的返回1-7，所以我们要通过左移得到一个20-56之间的数，因为2⁶-1=63>56,所以最少移动6位
         */
        return (buildGenerator() << 6) + (buildGenerator() << 5) + (buildGenerator()<<4)+
        (buildGenerator()<<3)+(buildGenerator()<<2)+(buildGenerator()<<1)+buildGenerator();//这个方法可以等概率的返回0-63之间的数
    }

    /**
     * 第三步通过调整第二步等到的数据达到我们的要求即可
     */
    public static int result() {
        int ans = 0;
        do {
            //如果得到的是0-36之间的数我们就让他+20，如果得到其他的数就重做
            ans = leftMove();
        } while (ans >36);
        return ans + 20;
    }
}
