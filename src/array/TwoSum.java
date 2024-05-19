package array;

/**
 * @description: 两数之和 https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers.length<=0){
            return new int[]{-1,-1};
        }
        int left=0,right= numbers.length-1;
        while(left<right){
            if(numbers[left]+numbers[right]==target){
                return new int[]{left+1,right+1};
            }else if(numbers[left]+numbers[right]<target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{-1,-1};
    }
}
