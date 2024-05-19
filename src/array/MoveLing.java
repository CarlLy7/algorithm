package array;

/**
 * @description: 移动零  https://leetcode.cn/problems/move-zeroes/
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class MoveLing {
    public void moveZeroes(int[] nums) {
        if(nums.length<=0){
            return;
        }
        int slow=0,fast=0;
        while(fast<nums.length){
            if(nums[fast]!=0){
                nums[slow]=nums[fast];
                slow++;
            }
            fast++;
        }
        while(slow< nums.length){
            nums[slow]=0;
            slow++;
        }
    }
}
