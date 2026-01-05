package hot100;

import java.util.Random;

/**
 * @description:
 * @author: carl
 * @date: 2026.01.05
 * @Since: 1.0
 */

public class day20260105Solution {
    // [33] 搜索旋转排序数组
    public int searchI(int[] nums, int target) {
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

    // [81] 搜索旋转排序数组 II
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // 因为我们不想要出现nums[left]=nums[mid]=nums[right]，因为这样我们不知道在左侧还是右侧。所以我们需要将这种情况排除
            while (left < right && nums[left] == nums[left + 1]) {
                left++;
            }
            while (left < right && nums[right] == nums[right - 1]) {
                right--;
            }
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // 在左侧
            if (nums[mid] >= nums[left]) {
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

    // [528] 按权重随机选择
    private class Solution {
        private int[] preSum;
        private Random random;

        public Solution(int[] w) {
            preSum = new int[w.length + 1];
            random = new Random();
            preSum[0] = 0;
            for (int i = 1; i <= w.length; i++) {
                preSum[i] = preSum[i - 1] + w[i - 1];
            }
        }

        public int pickIndex() {
            int n = preSum.length;
            // 在[0,preSum[n-1]]这个闭区间中随机选择一个数字
            int target = random.nextInt(preSum[n - 1]) + 1;
            // 注意，前缀和和真实的数组有一位的偏移
            return left_bound(preSum, target) - 1;
        }

        /**
         * 寻找大于等于target的左侧边界
         * 
         * @param nums
         * @param target
         * @return
         */
        private int left_bound(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }
}
