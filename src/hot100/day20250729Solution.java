package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.29
 * @Since: 1.0
 */

public class day20250729Solution {
    // [5] 最长回文子串
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以s[i]为中心去寻找回文串
            String sub1 = palind(s, i, i);
            // 以s[i]、s[i+1]为中心去寻找回文串
            String sub2 = palind(s, i, i + 1);
            res = res.length() > sub1.length() ? res : sub1;
            res = res.length() > sub2.length() ? res : sub2;
        }
        return res;
    }

    /**
     * 以 l、r为起点去寻找回文串
     * 
     * @param s
     * @param l
     * @param r
     * @return
     */
    private String palind(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        // 因为substring()是左闭右开区间,并且l和r会超前走一步
        return s.substring(l + 1, r);
    }

    // [11] 盛最多水的容器
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    // [15] 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSum(nums, 3, 0, 0);
    }

    /**
     * 求n数之和
     * 
     * @param nums:数组列表
     * @param n:多少数之和
     * @param start:开始索引
     * @param target:目标值
     * @return
     */
    private List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || size < n) {
            return res;
        }
        // base case：2数之和
        if (n == 2) {
            int lo = start, hi = size - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo];
                int right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left)
                        lo++;
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right)
                        hi--;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (lo < hi && nums[lo] == left)
                        lo++;
                    while (lo < hi && nums[hi] == right)
                        hi--;
                }
            }
        } else {
            // 递归调用n数之和
            for (int i = start; i < size; i++) {
                List<List<Integer>> subRes = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> subRe : subRes) {
                    subRe.add(nums[i]);
                    res.add(subRe);
                }
                while (i < size - 1 && nums[i] == nums[i + 1])
                    i++;
            }
        }
        return res;
    }
}
