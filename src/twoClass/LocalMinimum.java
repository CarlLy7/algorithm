package twoClass;

/**
 * @description: 局部最小值问题
 * 描述： 一个无序的数组，这个数组必须保证相邻的两个数不相等，现在要求返回一个局部最小值  即比它的左右两边的值都小的值
 * 最关键的问题就是边界条件的判断
 * @author: lyq
 * @createDate: 21/8/2022
 * @version: 1.0
 */
public class LocalMinimum {
    public static void main(String[] args) {
        int testTime=500;
        int maxLen=50;
        int maxVal=1000;
        for (int i = 0; i < testTime; i++) {
            int[] array = randomArray(maxLen, maxVal);
            int localMin = localMin(array);
            if(!isLocalMin(array, localMin)){
                System.out.println("结果错误");
                print(array);
                break;
            }
        }
        System.out.println("成功");
    }

    /**
     * 返回局部最小值的位置，使用二分法， 二分法不一定只用在有序的数组中还可能用在无序的数组中
     *
     * @param a
     * @return
     */
    public static int localMin(int[] a) {
        int len = a.length;
        //先进行边界判断
        if (a.length == 0 || a == null) {
            return -1;
        }
        if (a.length==1){
            return 0;
        }

        //如果我们的第一个数比第二个数小的话，第一个数就是局部最小值，相对应的如果最后一个数比倒数第二个数小的话最后一个数就是局部最小值
        if (a[0] < a[1]) {
            return 0;
        }
        if (a[len - 1] < a[len - 2]) {
            return len - 1;
        }
        //下面就是其他的情况了，我们使用二分法来解决
        int low = 0;
        int high = len - 1;
        while (low < high - 1) {  //这个条件就是保证了数组中元素的个数大于等于3个，也就是二分的条件就是元素的个数大于等于3
            int mid = (low + high) / 2;
            if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) {
                //如果这个mid位置的元素比左右两边的元素都小的话，那么这个中间的元素就是局部最小值
                return mid;
            } else {  //mid不是局部最小值了
                if (a[mid] > a[mid - 1]) {  //如果mid比它左边的元素大的话，说明这里是一个上升的所以我们去左边找局部最小值
                    high = mid - 1;
                } else { //如果mid比它右边的元素大的话，说明在右边这里是一个下降的，所以去右边找局部最小值
                    low = mid + 1;
                }
            }
        }
        //当出来循环的时候，说明数组中小于等于两个元素，谁小返回谁
        return a[low] < a[high] ? low : high;
    }

    /**
     * 写个对数器
     */
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen+1);
        int[] a = new int[len];
        a[0] = (int) (Math.random() * maxValue);
        for (int i = 1; i < a.length; i++) {
            do {
                a[i] = (int) (Math.random() * maxValue);
            } while (a[i] == a[i - 1]); //如果下一个数和前一个数相等的话重新进入循环
        }
        return a;
    }

    public static boolean isLocalMin(int[] a, int minIndex) {
        if (a.length == 0) {
            return minIndex == -1;
        }
        if (a.length==1){
            return minIndex==0;
        }
        if (minIndex == 0) {
            return a[minIndex] < a[minIndex + 1];
        }
        if (minIndex == a.length - 1) {
            return a[minIndex] < a[minIndex - 1];
        }
        boolean p = a[minIndex] < a[minIndex - 1];
        boolean q = a[minIndex] < a[minIndex + 1];
        return p && q; //必须同时小于左边和右边的时候才返回ture，否则返回false
    }
    public static void print(int[] a){
        for (int i : a) {
            System.out.println(i+" ");
        }
    }

}
