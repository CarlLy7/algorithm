package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.25
 * @Since: 1.0
 */

public class day20251225Solution {
    // [704] 二分查找
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    // [34] 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {left_bound(nums, target), right_bound(nums, target)};
        return res;
    }

    /**
     * 寻找右边界
     * 
     * @param nums
     * @param target
     * @return
     */
    private int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
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
    private int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 因为我要找左边界，所以相等的时候，我继续往左收敛
                right = mid - 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    // [LCR 172] 统计目标成绩的出现次数
    public int countTarget(int[] scores, int target) {
        int leftIndex = left_bound(scores, target);
        int rightIndex = right_bound(scores, target);
        if (leftIndex == -1 || rightIndex == -1) {
            return 0;
        }
        return rightIndex - leftIndex + 1;
    }
}
