import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.03.31
 * @Since: 1.0
 */

public class day20250331Solution {
    // 11 盛最多水的容器
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = Integer.MIN_VALUE;
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

    // 15 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return nSum(nums, 3, 0, 0);
    }

    private List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (n < 2 || len < n) {
            return ans;
        }
        // 两数之和
        if (n == 2) {
            int left = start, right = len - 1;
            while (left < right) {
                int leftVal = nums[left];
                int rightVal = nums[right];
                int sum = nums[left] + nums[right];
                if (sum < target) {
                    // 为了去除重复结果
                    while (left < right && nums[left] == leftVal)
                        left++;
                } else if (sum > target) {
                    // 为了去除重复结果
                    while (left < right && nums[right] == rightVal)
                        right--;
                } else {
                    ans.add(new ArrayList<>(Arrays.asList(leftVal, rightVal)));
                    // 为了去除重复结果
                    while (left < right && nums[left] == leftVal)
                        left++;
                    while (left < right && nums[right] == rightVal)
                        right--;
                }
            }
        } else {
            for (int i = start; i < len; i++) {
                List<List<Integer>> subAns = nSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> sub : subAns) {
                    sub.add(nums[i]);
                    ans.add(sub);
                }
                // 为了去除重复结果
                while (i < len - 1 && nums[i] == nums[i + 1])
                    i++;
            }
        }
        return ans;
    }

    // 17 电话号码的字母组合
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    String[] mapping = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return ans;
        }
        backTrack(digits, 0);
        return ans;
    }

    /**
     * 回溯算法模板
     * 
     * @param digits
     * @param start 起始索引
     */
    private void backTrack(String digits, int start) {
        // base case
        if (sb.length() == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        int index = digits.charAt(start) - '0';
        for (char c : mapping[index].toCharArray()) {
            sb.append(c);
            backTrack(digits, start + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
