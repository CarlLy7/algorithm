package test;

/**
 * @description: 旋转数组的最小数字  https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class minimumNumberOfRotatedArrays {
    public static int minArray(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int mid = low + (high - low) /2;
            if(numbers[mid]<numbers[high]){
                high=mid;
            }else if(numbers[mid]>numbers[high]){
                low=mid+1;
            }else{
                high-=1;
            }
        }
        return numbers[low];
    }
    public static void main(String[] args) {
        int[] test=new int[]{2,2,2,0,1};
        int res = minArray(test);
        System.out.println(res);
    }
}
