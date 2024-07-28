package day20240728;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-28 17:03
 * @version: 1.0
 */
public class Solution {
    public String minWindow(String s, String t) {
        int valid = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            //收缩
            while (left < right && valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }

            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        int valid = 0;
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            while (left < right && valid == need.size()) {
                if (right - left == s1.length()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        int valid = 0;
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        StringBuilder track = new StringBuilder();
        if (n == 0) {
            return res;
        }
        backTrack(n, n, track);
        return res;
    }

    /**
     * @param leftNum:  左括号的剩余可用数量
     * @param rightNum: 右括号的剩余可用数量
     * @param track:    路径
     */
    private void backTrack(int leftNum, int rightNum, StringBuilder track) {
        if (leftNum < 0 || rightNum < 0) {
            return;
        }
        if (leftNum > rightNum) {
            return;
        }
        if (leftNum == 0 && rightNum == 0) {
            res.add(track.toString());
            return;
        }
        track.append("(");
        leftNum--;
        backTrack(leftNum, rightNum, track);
        track.deleteCharAt(track.length() - 1);
        leftNum++;

        track.append(")");
        rightNum--;
        backTrack(leftNum, rightNum, track);
        track.deleteCharAt(track.length() - 1);
        rightNum++;
    }

    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        int left = 0, right = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            //重复出现了，就收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            len = Math.max(len, right - left);
        }
        return len;
    }

    public int findLength(int[] nums1, int[] nums2) {
        return nums1.length <= nums2.length ? findMax(nums1, nums2) : findMax(nums2, nums1);
    }

    private int findMax(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int max = 0;
        //公共长度递增
        for (int len = 1; len <= m; len++) {
            max = Math.max(max, maxLen(nums1, 0, nums2, n - len, len));
        }
        //公共长度不变
        for (int i = n - m - 1; i >= 0; i--) {
            max = Math.max(max, maxLen(nums1, 0, nums2, i, m));
        }
        //公共长度递减
        for (int i = 1; i < m; i++) {
            max = Math.max(max, maxLen(nums1, i, nums2, 0, m - i));
        }
        return max;
    }

    /**
     * nums1[i...]和nums2[j...]中长度为len的范围最长的公共子数组的长度
     *
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @param len
     * @return
     */
    private int maxLen(int[] nums1, int i, int[] nums2, int j, int len) {
        int count = 0, res = 0;
        for (int k = 0; k < len; k++) {
            if (nums1[i + k] == nums2[j + k]) {
                count++;
            } else {
                res = Math.max(count, res);
                count = 0;
            }
        }
        return count > 0 ? Math.max(res, count) : res;
    }
}
