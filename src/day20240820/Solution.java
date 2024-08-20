package day20240820;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-20 09:54
 * @version: 1.0
 */
public class Solution {
    // 奇数放在前半部分，偶数放在后半部分
    public int[] method(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 != 0) {
                left++;
            } else {
                swap(nums, left, right);
                right--;
            }
        }
        return nums;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
