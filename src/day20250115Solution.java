/**
 * @author: carl
 * @date: 2025/1/15
 */

public class day20250115Solution {

    // 5
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    // public boolean isPalindrome(String s) {
    // int left = 0, right = s.length() - 1;
    // while (left < right) {
    // if (s.charAt(left) != s.charAt(right)) {
    // return false;
    // }
    // left++;
    // right--;
    // }
    // return true;
    // }

    // 344
    // public void reverseString(char[] s) {
    // int n = s.length;
    // int left = 0, right = n - 1;
    // while (left < right) {
    // char temp = s[left];
    // s[left] = s[right];
    // s[right] = temp;
    // left++;
    // right--;
    // }
    // }

    // 167
    // public int[] twoSum(int[] numbers, int target) {
    // int left = 0, right = numbers.length - 1;
    // while (left < right) {
    // int sum = numbers[left] + numbers[right];
    // if (sum == target) {
    // return new int[]{left + 1, right + 1};
    // } else if (sum < target) {
    // left++;
    // } else if (sum > target) {
    // right--;
    // }
    // }
    // return new int[]{-1, -1};
    // }
}
