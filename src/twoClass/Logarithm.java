package twoClass;

/**
 * @description: 对数器
 * 什么是对数器？ 对数器可以理解为我们自己模拟一个大的随机样本来验证我们的代码的正确性，并且可以帮我们定位到错误的例子便于我们调试
 * 可以理解为自己写了一个leetcode上的在线代码运行器一样
 * @author: lyq
 * @createDate: 19/8/2022
 * @version: 1.0
 */
public class Logarithm {
    public static void main(String[] args) {
        int maxLen=5;
        int maxValue=1000;
        for (int i = 0; i < 10000; i++) {
            int[] a = randomArray(maxLen, maxValue);
            int[] b = copyArray(a);
            choiceSort(a);
            if(!isSorted(a)){
                System.out.println("排序算法错误，错误的一个例子如下");
                for (int j = 0; j < b.length; j++) {
                    System.out.print(b[j]+" ");
                }
                break;
            }
        }
        System.out.println("排序成功");
    }

    //构建一个长度在[0,maxLen-1]之间，大小在[0,maxValue-1]之间的数组
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] array=new int[len];
        for (int i = 0; i < len; i++) {
            array[i]=(int) (Math.random() * maxValue);
        }
        return  array;
    }

    //复制数组的方法
    public static int[] copyArray(int[] array){
        int[] a=new int[array.length];
        for (int i = 0; i < array.length; i++) {
            a[i]=array[i];
        }
        return  a;
    }

    //判断这个数组是不是有序的（这里默认递增）
    public static boolean isSorted(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            if(array[i]>array[i+1])
                return false;
        }
        return true;
    }

    //选择排序算法   2 54 12 36 54 12 5
    public static void choiceSort(int[] a){
        int min=0;
        //先找到最小元素的位置，然后和第一个位置的元素进行交换
        for (int i = 0; i < a.length-1; i++) {
            for (int j = i; j < a.length; j++) {
                if(a[j]<a[min]){
                    min=j;
                }
            }
            int temp=a[i];
            a[i]=a[min];
            a[min]=a[i];
        }
    }
}
