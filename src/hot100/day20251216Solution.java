package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.16
 * @Since: 1.0
 */

public class day20251216Solution {
    // [27] 移除元素
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    // [167] 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left <= right) {
            int sum = numbers[left] + numbers[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[] {left + 1, right + 1};
            }
        }
        return new int[] {};
    }

    // [5] 最长回文子串
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以s[i]为中心的回文串，适用于奇数长度
            String res1 = palindrome(s, i, i);
            // 以s[i]、s[i+1]为中心的回文串，适用于偶数长度
            String res2 = palindrome(s, i, i + 1);
            res = res.length() > res1.length() ? res : res1;
            res = res.length() > res2.length() ? res : res2;
        }
        return res;
    }

    /**
     * 以s[l,r]为中心找回文串
     * 
     * @param s
     * @param l
     * @param r
     * @return
     */
    private String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }
}
