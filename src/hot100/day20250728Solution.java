package hot100;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.28
 * @Since: 1.0
 */

public class day20250728Solution {
    // [1] 两数之和
    public int[] twoSum(int[] nums, int target) {
        // key:值 value:索引下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[] {i, map.get(need)};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    // [2] 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        // 进位
        int digest = 0;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (p1 != null || p2 != null || digest > 0) {
            int temp = digest;
            if (p1 != null) {
                temp += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                temp += p2.val;
                p2 = p2.next;
            }
            digest = temp / 10;
            ListNode node = new ListNode(temp % 10);
            p.next = node;
            p = p.next;
        }
        return dummy.next;
    }

    // [3] 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0;
        int res = Integer.MIN_VALUE;
        // key:字母 value:字母出现的次数
        HashMap<Character, Integer> window = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 收缩窗口
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            // 更新结果，应该在收缩完窗口之后才进行更新
            // 这个滑动窗口算法模板是一个左闭右开区间
            res = Math.max(res, right - left);
        }
        return res;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
