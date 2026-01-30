package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2026.01.30
 * @Since: 1.0
 */

public class day20260130Solution {
    // [209] 长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            right++;
            // 收缩条件
            while (left < right && windowSum >= target) {
                ans = Math.min(ans, right - left);
                windowSum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // [395] 至少有 K 个重复字符的最长子串
    public int longestSubstring(String s, int k) {
        int ans = 0;
        for (int i = 1; i <= 26; i++) {
            ans = Math.max(ans, longestSubstring(s, k, i));
        }
        return ans;
    }

    /**
     * 在字符串中，有count种字母出现至少k次的最大长度
     * 
     * @param s
     * @param k
     * @param count
     * @return
     */
    private int longestSubstring(String s, int k, int count) {
        int left = 0, right = 0;
        int ans = 0;
        // 窗口中有多少种字母
        int windowCharSize = 0;
        // 窗口中出现k次的字母有多少个
        int windowValidSize = 0;
        int[] nums = new int[26];
        while (right < s.length()) {
            char c = s.charAt(right);
            if (nums[c - 'a'] == 0) {
                windowCharSize++;
            }
            nums[c - 'a']++;
            if (nums[c - 'a'] == k) {
                windowValidSize++;
            }
            right++;
            // 收缩条件
            while (windowCharSize > count) {
                char d = s.charAt(left);
                if (nums[d - 'a'] == k) {
                    windowValidSize--;
                }
                nums[d - 'a']--;
                if (nums[d - 'a'] == 0) {
                    windowCharSize--;
                }
                left++;
            }
            if (windowValidSize == count) {
                ans = Math.max(ans, right - left);
            }
        }
        return ans;
    }

    // [28] 找出字符串中第一个匹配项的下标
    public int strStr(String haystack, String needle) {
        int left = 0, right = 0;
        while (right < haystack.length()) {
            char c = haystack.charAt(right);
            right++;
            // 收缩条件
            while (left < right && right - left > needle.length()) {
                char d = haystack.charAt(left);
                left++;
            }
            if (valid(haystack.substring(left, right), needle)) {
                return left;
            }
        }
        return -1;
    }

    private boolean valid(String haystack, String needle) {
        if (haystack.length() != needle.length()) {
            return false;
        }
        for (int i = 0; i < needle.length(); i++) {
            if (haystack.charAt(i) != needle.charAt(i)) {
                return false;
            }
        }
        return true;
    }

}
