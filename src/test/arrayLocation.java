package test;

import java.util.Arrays;

/**
 * @description:输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分， 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class arrayLocation {
    public static void swap(int[] array) {
        Arrays.sort(array);
        int len = 0;
        for (int i = 0; i < array.length; i++) {  //记录一共有多少个奇数
            if (array[i] % 2 != 0) {
                len++;
            }
        }
        int[] temp = new int[array.length];
        int p1 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {//奇数
                temp[p1++] = array[i];
            } else { //偶数的话
                temp[len++] = array[i];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            array[i]=temp[i];
        }
    }

    public static void main(String[] args) {
        int[] test={1,2,3,4,5,6};
        swap(test);
        for (int i : test) {
            System.out.print(i+",");
        }
    }
}
