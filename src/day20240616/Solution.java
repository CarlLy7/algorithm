package day20240616;

import java.util.*;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-16 18:42
 * @version: 1.0
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        // map中存放的是是 [值：索引]
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int needVal = target - nums[i];
            if (map.containsKey(needVal)) {
                return new int[]{i, map.get(needVal)};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String encoderStr = encoder(str);
            map.putIfAbsent(encoderStr, new ArrayList<>());
            map.get(encoderStr).add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> value : map.values()) {
            res.add(value);
        }
        return res;
    }

    private String encoder(String str) {
        char[] chars = new char[26];
        for (char c : str.toCharArray()) {
            int count = c - 'a';
            chars[count]++;
        }
        return new String(chars);
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (Integer value : set) {
            if (set.contains(value - 1)) {
                continue;
            }
            int curValue = value;
            int curLen = 1;
            while (set.contains(curValue + 1)) {
                curValue += 1;
                curLen += 1;
            }
            res = Math.max(res, curLen);
        }
        return res;
    }

    //nums = [0,1,0,3,12]
    public void moveZeroes(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
            right++;
        }
        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            int curVal = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, curVal);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSum(nums, 3, 0, 0);
    }

    private List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (size < n || n < 2) {
            return res;
        }
        if (n == 2) {
            int lo = start, hi = size - 1;
            while (lo < hi) {
                int cur = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (cur < target) {
                    while (lo < hi && left == nums[lo]) lo++;
                } else if (cur > target) {
                    while (lo < hi && right == nums[hi]) hi--;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(left, right)));
                    while (lo < hi && left == nums[lo]) lo++;
                    while (lo < hi && right == nums[hi]) hi--;
                }
            }
        } else {
            for (int i = start; i < size; i++) {
                List<List<Integer>> subRes = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> subRe : subRes) {
                    subRe.add(nums[i]);
                    res.add(subRe);
                }
                while (i < size - 1 && nums[i] == nums[i + 1]) i++;
            }
        }
        return res;
    }

    public int trap(int[] height) {
        int n = height.length;
        int[] maxLeft = new int[n];
        maxLeft[0] = height[0];
        int[] maxRight = new int[n];
        maxRight[n - 1] = height[n - 1];
        int leftMax = 0, rightMax = 0;
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += (Math.min(maxLeft[i], maxRight[i]) - height[i]);
        }
        return res;
    }
}
