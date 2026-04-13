package date202604;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.13
 * @Since: 1.0
 */

public class day20260413Solution {
    // [438] 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int valid = 0;
        // 滑动窗口，是左闭右开区间[left,right)
        int left = 0, right = 0;
        List<Integer> res = new ArrayList<>();
        while (right < s.length()) {
            // 扩大窗口
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            // 收缩窗口，因为字符的个数是固定的
            while (right - left >= p.length()) {
                // 满足条件了
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

    // [287] 寻找重复数
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int left = 1, right = n - 1;
        int res = 0;
        while (left <= right) {
            // 出现的数字
            int count = 0;
            int mid = (left + right) / 2;
            for (int i = 0; i < n; i++) {
                // 如果这个数字属于数组的左半部分
                if (nums[i] <= mid) {
                    count++;
                }
            }
            // 如果左边数字出现的少，说明重复的在右边
            if (count <= mid) {
                left = mid + 1;
            } else {
                // 右边数字少，说明重复的出现在左边
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }
}
