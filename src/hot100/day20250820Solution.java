package hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.20
 * @Since: 1.0
 */

public class day20250820Solution {
    // [128] 最长连续序列
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int res = 0;
        for (Integer num : set) {
            // 如果不是第一个元素，跳过
            if (set.contains(num - 1)) {
                continue;
            }
            int curNum = num;
            int curLen = 1;
            while (set.contains(curNum + 1)) {
                curNum++;
                curLen++;
            }
            res = Math.max(res, curLen);
        }
        return res;
    }

    // [131] 分割回文串
    List<List<String>> res = new ArrayList<>();
    LinkedList<String> track = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backTrack(s, 0);
        return res;
    }

    /**
     * 从start开始找回文串
     * 
     * @param s
     * @param start
     */
    private void backTrack(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isValid(s, start, i)) {
                continue;
            }
            track.addLast(s.substring(start, i + 1));
            backTrack(s, i + 1);
            track.removeLast();
        }
    }

    /**
     * 判断是不是回文串
     * 
     * @param s
     * @param start
     * @param end
     * @return
     */
    private boolean isValid(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // [136] 只出现一次的数字
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
