package hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2026.01.27
 * @Since: 1.0
 */

public class day20260127Solution {
    // [567] 字符串的排列
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> windows = new HashMap<Character, Integer>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int valid = 0;
        int left = 0, right = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 收缩条件
            while (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (windows.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }
        }
        return false;
    }

    // [438] 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int start = -1;
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 收缩条件:满足条件了，然后找最优解
            while (left < right && right - left >= p.length()) {
                if (valid == need.size()) {
                    start = left;
                    ans.add(start);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(windows.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }
        }
        return ans;
    }

    // [3] 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            map.put(c, map.getOrDefault(c, 0) + 1);
            // 收缩条件
            while (left < right && map.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                map.put(d, map.get(d) - 1);
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
