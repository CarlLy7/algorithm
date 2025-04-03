import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.03
 * @Since: 1.0
 */

public class day20250403Solution {
    // 31 TODO 下一个排列
    public void nextPermutation(int[] nums) {

    }

    // 32 最长有效括号
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // dp[i]表示： 以s[i-1]结尾的子串中最长有效括号的长度
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                // 左括号不可能作为合法的括号
                dp[i + 1] = 0;
            } else {
                if (!stack.isEmpty()) {
                    int leftIndex = stack.pop();
                    // 当前结尾的合法括号的长度为 i-leftIndex 要加上以s[leftIndex-1]结尾的有效括号的长度
                    int len = 1 + i - leftIndex + dp[leftIndex];
                    dp[i + 1] = len;
                } else {
                    dp[i + 1] = 0;
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 33 搜索旋转排序数组
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
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
        return -1;
    }

    // 34 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        return new int[] {leftBound(nums, target), rightBound(nums, target)};
    }

    /**
     * 在nums中寻找值等于target的右边界索引下标
     * 
     * @param nums
     * @param target
     * @return
     */
    private int rightBound(int[] nums, int target) {
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
     * 在nums中寻找值等于target的左边界索引下标
     * 
     * @param nums
     * @param target
     * @return
     */
    private int leftBound(int[] nums, int target) {
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
}
