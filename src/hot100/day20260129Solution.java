package hot100;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @description:
 * @author: carl
 * @date: 2026.01.29
 * @Since: 1.0
 */

public class day20260129Solution {
    // [424] 替换后的最长重复字符
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        // 记录每个字母出现的次数
        int[] count = new int[26];
        // 窗口内出现次数最多的字母出现的次数
        int maxCount = 0;
        int ans = 0;
        while (right < s.length()) {
            int c = s.charAt(right) - 'A';
            right++;
            count[c]++;
            // 更新出现次数最多的字母的出现次数
            maxCount = Math.max(maxCount, count[c]);
            // 收缩条件
            while (left < right && right - left - maxCount > k) {
                int d = s.charAt(left) - 'A';
                left++;
                count[d]--;
                maxCount = Math.max(maxCount, count[d]);
            }
            // 现在肯定是满足条件的
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    // [219] 存在重复元素 II
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int left = 0, right = 0;
        HashSet<Integer> set = new HashSet<>();
        while (right < nums.length) {
            if (set.contains(nums[right])) {
                return true;
            }
            set.add(nums[right]);
            right++;
            while (right - left > k) {
                set.remove(nums[left]);
                left++;
            }
        }
        return false;
    }

    // [220] 存在重复元素 III
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        // TreeSet可以找到略大于一个值或者略小于一个值的元素
        TreeSet<Integer> set = new TreeSet<>();
        int left = 0, right = 0;
        while (right < nums.length) {
            // 找到略大于nums[right]的值
            Integer ceiling = set.ceiling(nums[right]);
            if (ceiling != null && (long)ceiling - nums[right] <= valueDiff) {
                return true;
            }
            // 找到略小于nums[right]的值
            Integer floor = set.floor(nums[right]);
            if (floor != null && (long)nums[right] - floor <= valueDiff) {
                return true;
            }
            set.add(nums[right]);
            right++;
            // 收缩条件·
            while (left < right && right - left > indexDiff) {
                set.remove(nums[left]);
                left++;
            }
        }
        return false;
    }
}
