package window;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-02-27 21:40
 * @version: 1.0
 */
public class Solution {
    public int longestSubstring(String s, int k) {
        int res = 0;
        for (int i = 1; i <= 26; i++) {
            res = Math.max(res, longestKSubstring(s, k, i));
        }
        return res;
    }

    /**
     * @param s
     * @param k
     * @param count
     * @return s中出现次数不小于k次，且包含count种不同的字符的最大子数组
     */
    private int longestKSubstring(String s, int k, int count) {
        int left = 0, right = 0;
        int res = 0;
        int[] window = new int[26];
        // 窗口中不同字符串的数量
        int windowUniqueCount = 0;
        // 出现次数不小于k次的不同的字符数量
        int windowValidCount = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (window[c - 'a'] == 0) {
                windowUniqueCount++;
            }
            window[c - 'a']++;
            if (window[c - 'a'] == k) {
                windowValidCount++;
            }
            right++;
            //收缩窗口：当窗口中不同字符的数量大于count时
            while (windowUniqueCount > count) {
                char d = s.charAt(left);
                if (window[d - 'a'] == k) {
                    windowValidCount--;
                }
                window[d - 'a']--;
                if (window[d - 'a'] == 0) {
                    windowUniqueCount--;
                }
                left++;
            }
            if (windowValidCount == count) {
                res = Math.max(res, right - left);
            }
        }
        return res;
    }
}
