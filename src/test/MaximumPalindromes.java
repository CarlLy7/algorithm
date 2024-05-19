package test;

import java.util.HashSet;

/**
 * @description: 求一个字符串可以构成的最大回文数
 * @author: lyq  出现双数的可以构成回文，如果最后还剩下一个出现一次的可以让长度加1
 * @createDate: 7/3/2023
 * @version: 1.0
 */
public class MaximumPalindromes {
    public static int getMax(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        char[] chars = str.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!set.contains(chars[i])) {
                set.add(chars[i]);
            } else {
                ans++;
                set.remove(chars[i]);
            }
        }

        return set.size() > 0 ? ans * 2 + 1 : ans * 2;
    }

    public static void main(String[] args) {
        String test = "abccccdd";
        System.out.println(getMax(test));
    }
}
