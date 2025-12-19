package hot100;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.19
 * @Since: 1.0
 */

public class day20251219Solution {
    // [1] 两数之和
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[] {map.get(need), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    // [125] 验证回文串
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String str = sb.toString();
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // [75] 颜色分类
    public void sortColors(int[] nums) {
        // [0,p0)是数字0的区间，（p2,nums.length-1]是数字2的区间，[p1,p2]是数字1的区间
        int p0 = 0, p2 = nums.length - 1;
        int p = 0;
        // 因为p2是开区间，所以要等于
        while (p <= p2) {
            if (nums[p] == 0) {
                swap(nums, p, p0);
                p0++;
            } else if (nums[p] == 2) {
                swap(nums, p, p2);
                p2--;
            } else {
                p++;
            }
            if (p < p0) {
                p = p0;
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
