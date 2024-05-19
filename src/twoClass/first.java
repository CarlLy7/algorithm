package twoClass;

/**
 * @description:
 *   先说一下需求： 我们想要频繁的在一个数组中查询一个指定范围的数组的和
 *   解决思路： 方案一：（使用一维数组：前缀表）我们可以使用前缀和这种数据结构，何为前缀和。就是一个一维数组，第一个位置放第一个元素然后第二个位置就放第一个元素＋第二个元素的和，
 *   第三个位置就放前两个元素的和加第三个元素的和，一次类推
 *   方案二： 使用一个二位数组，创建一个n*n的数组，然后把所有可能的范围的和放到对应的位置上，然后查找的时候我们可以直接去对应的位置上查找，这个方法如果查询非常频繁的时候好用，
 *   因为使用方案一那种一位数组的方法的话还是有减法运算的，如果查询次数非常多的话效率就低了，方案二无非就是第一次建表的时候时间久点之后就快了直接查出来
 *     给定的数组中的数据【3,2,-1,6,7,2,-2】
 *       0  1  2  3  4  5  6
 *   0   3  5  4  10 17 19 17
 *   1   x  2  1  7  14 16 14
 *   2   x  x  -1 5  12 14 12
 *   3   x  x  x  6  13 15 13
 *   4   x  x  x  x  7  9  7
 *   5   x  x  x  x  x  2  0
 *   6   x  x  x  x  x  x  -2
 *
 * @author: lyq
 * @createDate: 17/8/2022
 * @version: 1.0
 */
public class first {
    public static void main(String[] args) {
        int[] a={3,2,-1,6,7,2,-2};
        rangeSum rangeSum = new rangeSum(a);
        System.out.println(rangeSum.getSum(0, 5));
    }
    public static class rangeSum{
        private int[] preSum;//前缀和数组
        public rangeSum(int[] array){
            int size=array.length;
            preSum=new int[size];//前缀和数组的大小和我们给的这个数组的大小是一样大的
            preSum[0]=array[0];//前缀和中的第一个元素就是我们给定的数组的第一个元素
            for (int i = 1; i < array.length ; i++) {
                preSum[i]=preSum[i-1]+array[i];
            }
        }
        //计算这个范围的元素的和并返回
        public int getSum(int l,int r){
            /**
             * 如果是想得到从a[0]到a[6]这个范围的和我们直接返回pre[6]就可以了
             * 如果想要得到的是a[2]到a[6]这个范围的和的话，我们就要用pre[6]-pre[1]
             */
            return l==0?preSum[r] :preSum[r]-preSum[l-1];
        }
    }
}
