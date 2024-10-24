/**
 * @description:
 * @author: Carl
 * @createDate: 2024-10-24 23:03
 * @version: 1.0
 */
public class day20241024Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftIndex = findLeftBound(nums, target);
        int rightIndex = findRightBound(nums, target);
        return new int[]{leftIndex, rightIndex};
    }

    public int findLeftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                right = mid - 1;
            }
        }
        if (left < 0 || left >= nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public int findRightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }
        if (left - 1 < 0 || left - 1 >= nums.length) {
            return -1;
        }
        return nums[left - 1] == target ? (left - 1) : -1;
    }
}
