import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author: luanyingqi
 * @date: 2024/11/25
 */

public class day20241125Solution {
    //1658
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int target = sum - x;
        int left = 0, right = 0;
        int window = 0;
        int res = Integer.MIN_VALUE;
        while (right < nums.length) {
            int c = nums[right];
            window += c;
            right++;
            while (window > target && left < right) {
                int d = nums[left];
                left++;
                window -= d;
            }
            if (window == target) {
                res = Math.max(res, right - left);
            }
        }
        return res == Integer.MIN_VALUE ? -1 : nums.length - res;
    }

    //713
    public int numSubarrayProductLessThank(int[] nums, int k) {
        int left = 0, right = 0;
        int window = 1;
        int n = nums.length;
        int res = 0;
        while (right < n) {
            window *= nums[right];
            right++;
            while (window >= k && left < right) {
                window /= nums[left];
                left++;
            }
            res += right - left;
        }
        return res;
    }

    //1004
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int window = 0;
        int res = Integer.MIN_VALUE;
        while (right < nums.length) {
            if (nums[right] == 1) {
                window++;
            }
            right++;
            // 需要转成1的0的个数超过了k个
            while (right - left - window > k) {
                if (nums[left] == 1) {
                    window--;
                }
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    //340
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, right = 0;
        HashSet<Character> set = new HashSet<>();
        int res = Integer.MIN_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            set.add(c);
            while (set.size() > k) {
                char d = s.charAt(left);
                set.remove(d);
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    //424
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        int[] window = new int[26];
        // 窗口中出现字符出现最多的次数
        int windowMax = Integer.MIN_VALUE;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window[c - 'A']++;
            windowMax = Math.max(windowMax, window[c - 'A']);
            while (right - left - windowMax > k) {
                char d = s.charAt(left);
                left++;
                window[d - 'A']--;
                // 不需要更新windowMax,因为只有windowMax变大的时候，才有可能小于k,收缩窗口的时候不可能变大的
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    //219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int left = 0, right = 0;
        HashSet<Integer> window = new HashSet<>();
        while (right < nums.length) {
            if (window.contains(nums[right])) {
                return true;
            }
            window.add(nums[right]);
            right++;
            while (right - left > k) {
                window.remove(nums[left]);
                left++;
            }
        }
        return false;
    }

    //220
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int left = 0, right = 0;
        TreeSet<Integer> window = new TreeSet<>();
        while (right < nums.length) {
            int c = nums[right];
            Integer ceiling = window.ceiling(c);
            if (ceiling != null && ceiling - c <= t) {
                return true;
            }
            Integer floor = window.floor(c);
            if (floor != null && c - floor <= t) {
                return true;
            }
            window.add(c);
            right++;
            while (right - left > k) {
                window.remove(nums[left]);
                left++;
            }
        }
        return false;
    }

    //209
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int len = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (sum >= target && left < right) {
                if (right - left < len) {
                    len = right - left;
                }
                sum -= nums[left];
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }

    //395
    public int longestSubstring(String s, int k) {
        int res = 0;
        for (int i = 1; i <= 26; i++) {
            res = Math.max(res, longestKLetterSubStr(s, k, i));
        }
        return res;
    }

    // s中有count种不同的字符，并且每个字符出现的次数大于等于k,最长字串的长度
    public int longestKLetterSubStr(String s, int k, int count) {
        int res = 0;
        int left = 0, right = 0;
        // 记录每种字符出现的次数
        int[] window = new int[26];
        // 窗口内不同的字符个数
        int windowCount = 0;
        //符合要求的字符的个数
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (window[c - 'a'] == 0) {
                windowCount++;
            }
            window[c - 'a']++;
            if (window[c - 'a'] == k) {
                valid++;
            }
            right++;
            // 超过种类了，收缩窗口
            while (windowCount > count) {
                char d = s.charAt(left);
                if (window[d - 'a'] == k) {
                    valid--;
                }
                window[d - 'a']--;
                if (window[d - 'a'] == 0) {
                    windowCount--;
                }
                left++;
            }
            if (valid == count) {
                res = Math.max(res, right - left);
            }
        }
        return res;
    }
}
