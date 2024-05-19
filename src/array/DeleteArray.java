package array;

/**
 * @description: 移除元素 https://leetcode.cn/problems/remove-element/
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class DeleteArray {
    public int removeElement(int[] nums, int val) {
        if(nums.length<=0){
            return 0;
        }
        int slow=0,fast=0;
        while(fast< nums.length){
            if(nums[fast]!=val){
                nums[slow]=nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
