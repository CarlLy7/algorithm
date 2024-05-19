package test;

/**
 * @description: 旋转数组的最大数字  根据 https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/ 改编
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class maximumNumberOfRotatedArrays {
    public static int maxArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return numbers[left];
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4,5};
        int res = maxArray(test);
        System.out.println(res);
    }
}
