/**
 * @author: luanyingqi
 * @date: 2024/11/22
 */

public class day20241122Solution {
    //LCR173
    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 左边缺失
            if (nums[mid] > mid) {
                right = mid;
            } else {
                //右边缺失
                left = mid + 1;
            }
        }
        return left;
    }

    //33
//    public int search(int[] nums, int target) {
//        int left = 0, right = nums.length - 1;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] == target) {
//                return mid;
//            } else if (nums[mid] >= nums[left]) {
//                if (target >= nums[left] && target < nums[mid]) {
//                    right = mid - 1;
//                } else {
//                    left = mid + 1;
//                }
//            } else {
//                if (nums[mid] < target && target <= nums[right]) {
//                    left = mid + 1;
//                } else {
//                    right = mid - 1;
//                }
//            }
//        }
//        return -1;
//    }

    //81
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            while (left < right && nums[left] == nums[left + 1]) {
                left++;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
            }
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target < nums[mid]) {
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
        return false;
    }
}
