package hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2026.01.26
 * @Since: 1.0
 */

public class day20250126Solution {
    // [76] 最小覆盖子串
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<Character, Integer>();
        Map<Character, Integer> windows = new HashMap<Character, Integer>();
        // 满足条件的字符个数
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                // 满足条件了
                if (windows.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 收缩窗口
            while (left < right && valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (windows.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    windows.put(d, windows.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
