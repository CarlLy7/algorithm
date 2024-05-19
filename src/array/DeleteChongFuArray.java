package array;

/**
 * @description: 删除有序数组中的重复项 https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class DeleteChongFuArray {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=0){
            return 0;
        }
        int slow=0,fast=0;
        while(fast<nums.length){
            if(nums[slow]!=nums[fast]){
                slow++;
                nums[slow]=nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
}
