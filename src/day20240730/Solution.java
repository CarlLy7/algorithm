package day20240730;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-07-30 13:53
 * @version: 1.0
 */
public class Solution {
    /**
     * 1.最长不重复子串
     * 给定一个字符串（可能含有中文或其他任意字符），返回不含有重复字符的子串中最长的一个，如最长的有多个，则返回其中任意一个，模板如下：
     * <p>
     * 举例，find("abcdecfgh")返回"decfgh"，find("aaaaaa")返回"a"。
     *
     * @param str
     * @return
     */
    public static String find(String str) {
        HashMap<Character, Integer> window = new HashMap<>();
        int start = 0, len = 0;
        int left = 0, right = 0;
        while (right < str.length()) {
            char c = str.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 收缩
            while (window.get(c) > 1) {
                char d = str.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            if (right - left > len) {
                start = left;
                len = Math.max(len, right - left);
            }
        }
        return str.substring(start, start + len);
    }

    public static void main(String[] args) {
        String res = find("abcdecfgh");
        System.out.println(res);
        System.out.println(find("aaaaaa"));
        System.out.println(find("abcdecfghaaaaaa"));
    }

    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            //寻找s[i]开始的回文串
            String subRes1 = findPalindrome(s, i, i);
            //寻找s[i+1]开头的回文串
            String subRes2 = findPalindrome(s, i, i + 1);
            res = res.length() > subRes1.length() ? res : subRes1;
            res = res.length() > subRes2.length() ? res : subRes2;
        }
        return res;
    }


    private String findPalindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    public void nextPermutation(int[] nums) {
        //1.从后往前找到第一个降序的
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 2.从后往前找到第一个更大的数
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            //3. 交换i,j的元素
            swap(nums, i, j);
        }
        //4. 反转j之后的所有元素
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int l = start, r = nums.length - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            if (Pow(Pow(variables[i][0], variables[i][1], 10), variables[i][2], variables[i][3]) == target) {
                res.add(i);
            }
        }
        return res;
    }

    private int Pow(int x, int y, int mod) {
        int res = 1;
        while (y != 0) {
            //奇数，多乘一次
            if ((y & 1) != 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
            //舍弃y的一位
            y >>= 1;
        }
        return res;
    }

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, res = 0;
        while (l <= r) {
            int count = 0;
            int mid = (l + r) / 2;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                res = mid;
            }
        }
        return res;
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;
        BigInteger[] maxDp = new BigInteger[n];
        BigInteger[] minDp = new BigInteger[n];
        // base case
        maxDp[0] = BigInteger.valueOf(nums[0]);
        minDp[0] = BigInteger.valueOf(nums[0]);
        for (int i = 1; i < n; i++) {
            maxDp[i] = maxDp[i - 1].multiply(BigInteger.valueOf(nums[i])).max(minDp[i - 1].multiply(BigInteger.valueOf(nums[i])).max(BigInteger.valueOf(nums[i])));
            minDp[i] = minDp[i - 1].multiply(BigInteger.valueOf(nums[i])).min(maxDp[i - 1].multiply(BigInteger.valueOf(nums[i])).max(BigInteger.valueOf(nums[i])));
        }
        BigInteger res = maxDp[0];
        for (int i = 1; i < n; i++) {
            res = res.max(maxDp[i]);
        }
        return res.intValue();
    }
}
