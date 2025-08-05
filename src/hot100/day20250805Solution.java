package hot100;

import java.nio.file.Paths;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.05
 * @Since: 1.0
 */

public class day20250805Solution {
    // [33] 搜索旋转排序数组
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // [34] 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        return new int[] {findLeftBound(nums, target), findRightBound(nums, target)};
    }

    /**
     * 寻找右边界
     * 
     * @param nums
     * @param target
     * @return
     */
    private int findRightBound(int[] nums, int target) {
        // [left,right]闭区间
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

    /**
     * 寻找左边界
     * 
     * @param nums
     * @param target
     * @return
     */
    private int findLeftBound(int[] nums, int target) {
        // [left,right]闭区间
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    // [35] 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        // [left,right)左闭右开区间
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
