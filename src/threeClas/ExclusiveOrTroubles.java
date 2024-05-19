package threeClas;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description: 与异或相关的算法和面试题
 * 异或运算又叫做无进位加法，同一个位置的数相加，如果有进位我们不要
 * 异或运算的性质：  ① 0^N=N ② N^N=0
 * 补充一下与运算和或运算：
 * 与运算是之后两个1相与是1，其他都是0
 * 或运算是只要有一个1就是1，其他都是0
 * @author: lyq
 * @createDate: 23/8/2022
 * @version: 1.0
 */
public class ExclusiveOrTroubles {
    public static void main(String[] args) {
//        int[] a = {15, 30, 14, 2, 3};
//        int[] b = {15, 15, 3, 3, 2, 4};
//        swap(a,1,2);
//        for (int i : a) {
//            System.out.print(i+" ");
//        }
//        printAppearOddTimes(b);
//        getRightOne(33);
//        getAppearTwoOddTimes(b);
//        int[] c = {3, 4, 5, 8, 5, 3, 4, 8, 6};
//        System.out.println(getAppearKTimes(c, 1, 2));

        int maxSize = 20;
        int range = 1000;
        int testTimes = 1000000;
        int max = 9;//每个数最多出现9次
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (Math.random() * max) + 1;
            int b = (int) (Math.random() * max) + 1;
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {
                m++;
            }
            //上面就确定好了各种数随记出现的次数
            int[] array = randomArray(maxSize, range, k, m);
            int p1 = getAppearKTimes(array, k, m);
            int p2 = test(array, k, m);
            if (p1 != p2) {
                System.out.println("测试失败!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 第一个面试题： 不使用额外的变量实现两个数的交换
     * 补充:注意我们能这么写是在我们能确定交换的这两个数不是指向同一个位置的才可以，如果要交换的这两个数指向的位置是同一个位置话
     * 经过上面的操作会变成0
     */
    public static void swap(int[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    /**
     * 第二个面试题：一个数组中只有一种数出现了奇数次，其他的数都出现了偶数次，要求把出现了奇数次的那个数打印出来
     */
    public static void printAppearOddTimes(int[] a) {
        /**
         * 算法的思路是使用异或运算，因为如果是出现偶数次的数异或完之后是0，只有出现次数是奇数的异或完之后是它自己
         */
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            ans = ans ^ a[i];
        }
        System.out.println(ans);
    }

    /**
     * 第三个面试题：如何把一个int类型的数的二进制数的最右侧的1提取出来
     */
    public static int getRightOne(int a) {
        /**
         * 算法思路：这个数自己和它的相反数进行与运算
         */
        int ans = a & (-a);
//        System.out.println(ans);
        return ans;
    }

    /**
     * 第四个面试题：在一个数组中有两种数出现了奇数次，其他的数都出现了偶数次，要求把这两种出现奇数次的数打印出来
     */
    public static void getAppearTwoOddTimes(int[] a) {
        /**
         * 算法思路：
         * 首先所有数异或一遍，最终的结果一定是两个出现奇数次的数异或的结果
         * 第二步就是如果把他们分出来，对第一步的结果得到最右边的1位，因为如果两个数异或之后这个位为1说明这两个数在这一位上一个是0另一个是1
         * 第三步就是在使用一个变量来对第二步得到的最右边的1这个位是1的所有的数进行异或肯定能把这两个出现奇数次的数中的一个提取出来
         * 最后让第三步的结果和第一步的结果进行异或就可以将第二个数得出来
         */
        int ero = 0;
        for (int i = 0; i < a.length; i++) {
            ero = ero ^ a[i];
        }
        int ero2 = 0; //定义一个新的变量用来进行第二次的异或
        for (int j = 0; j < a.length; j++) {
            if ((getRightOne(ero) & (a[j])) != 0) {   //补充与运算：两位同时为“1”，结果才为“1”，否则为0
                ero2 = ero2 ^ a[j];
            }
        }
        System.out.print(ero2 + " " + (ero2 ^ ero));
    }

    /**
     * 对上面的异或运算进行一个推广
     * 第五个面试题：一个数组中有一个数出现了K次，其他数都出现了M次，M>1,K<M，找到出现了K次的数，并且要求空间复杂度为O(N)，时间复杂度为O(N)
     */
    public static int getAppearKTimes(int[] a, int k, int m) {
        /**
         * 算法思路：
         *   我们把每个整数写成二进制的型式，因为int类型的数据是32位，所以定义一个32位大小的数组，遍历一遍所有的数让这个32位的位置的数都相加
         *   如果这个数出现了M次那个相加之后的对应位置的结果肯定能被M整除，如果不能整除就是出现了K次的数的位置，最后把出现了K次的那个数还原回去
         */
        int[] b = new int[32]; //b[]中存放的其实是32位二进制的情况
        for (int num : a) {  //遍历给定数组的每一个元素
            for (int i = 0; i < 32; i++) { //把这个int数看成32位来对待
                /**
                 * 一个32位的int型整数，逐次右移32位以此和1与就可以得到每一位是0还是1
                 * 这里举个例子 011010 第一次右移0位和1与是0，就让b[0]+0 ； 右移1位和1与是1，就让b[1]+1,一次类推
                 */
                b[i] += ((num >> i) & 1);
            }
        }
        int ans = 0;
        //遍历完成之后b数组中存放每个数的每个位相加的结果
        for (int i = 0; i < 32; i++) { //遍历b数组，找到不能被m整除的数
            if (b[i] % m != 0) {  //如果不能被m整除就说明i位是1,
                //再把这种位还原成出现了k次的那个数
                ans |= (1 << i);
            }
        }
        return ans;
    }
    /**
     * 针对最后一个推广的题目写一个对数器进行测试
     */

    /**
     * @param maxKinds:最多有几种数字
     * @param range：数的范围       [-range,+range]
     * @param k：一种数出现k次
     * @param m：其他种数出现m次
     * @return ：返回一个数组
     * 算法思路：
     * 首先生成一个出现k次的数，然后确定种类至少是两种，数组的大小就是出现k次的一种数+剩下的种类的数*m
     * 先把出现k次的这种数填进去，同时种类数减一
     * 然后通过一个循环将剩下的种类数随机生成出来，注意因为每种都不同，所以用一个hashset来存放生成的种类，如果重了就继续生成
     * 再把这些出现m次的数据填进去
     * 最后让i和j位置的元素交换位置，打乱顺序 返回这个数组即可
     */
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int appearKTimesNum = (int) (Math.random() * range);
        int times = k;
        int numKinds = (int) (Math.random() * maxKinds) + 2; //数的种类，至少是两种数
        int[] array = new int[times + ((numKinds - 1) * m)];
        int index = 0;
        //先把出现了k次的数填进去
        for (; index < times; index++) {
            array[index] = appearKTimesNum;
        }
        numKinds--; //数的种类减一，因为每种数是不同的
        HashSet<Integer> set = new HashSet<>();
        set.add(appearKTimesNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = getRandomRangeNum(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                array[index++] = curNum;
            }
        }
        //打乱顺序
        //i位置的数随机和j位置的数交换
        for (int i = 0; i < array.length; i++) {
            int j = (int) (Math.random() * array.length);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }

    public static int getRandomRangeNum(int range) {
        return (int) (Math.random() * range) + 1 - (int) (Math.random() * range) + 1;
    }

    public static int test(int[] a, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>(); //建立一个hashmap做词频统计
        for (int i : a) {
            if (map.containsKey(i)) {
                map.put(i, (map.get(i) + 1));
            } else {
                map.put(i, 1);
            }
        }
        //遍历hash表
        for (Integer num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }
}
