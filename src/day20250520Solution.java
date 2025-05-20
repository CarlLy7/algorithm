import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.20
 * @Since: 1.0
 */

public class day20250520Solution {
    // 50 Pow(x, n)
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // 如果直接加负号，会导致超过了Int的最大值阈值
        if (n == Integer.MIN_VALUE) {
            return myPow(1 / x, -(n + 1)) / x;
        }
        if (n < 0) {
            return myPow(1 / x, -n);
        }
        // 奇数次幂
        if (n % 2 == 1) {
            return myPow(x, n - 1) * x;
        } else {
            double sub = myPow(x, n / 2);
            return sub * sub;
        }
    }

    // 67 二进制求和
    public String addBinary(String a, String b) {
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();
        int m = a.length();
        int n = b.length();
        int carry = 0;
        int i = 0;
        StringBuilder res = new StringBuilder();
        while (i < Math.max(m, n) || carry > 0) {
            int val = carry;
            val += i < m ? (a.charAt(i) - '0') : 0;
            val += i < n ? (b.charAt(i) - '0') : 0;
            res.append(val % 2);
            carry = val / 2;
            i++;
        }
        return res.reverse().toString();
    }

    // 44 通配符匹配
    int[][] memo;

    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        String pp = deleteRepeatx(p);
        int m = s.length();
        int n = pp.length();
        // 备忘录中记录状态，状态就是两个字符串中的索引位置
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(s, 0, pp, 0);
    }

    /**
     * s[i...]是否可以和p[j...]匹配
     * 
     * @param s
     * @param i
     * @param p
     * @param j
     * @return
     */
    private boolean dp(String s, int i, String p, int j) {
        if (j == p.length() && i == s.length()) {
            return true;
        }
        if (i == s.length()) {
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (j == p.length()) {
            return false;
        }
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }
        boolean res = false;
        if (s.charAt(i) == p.charAt(j)) {
            res = dp(s, i + 1, p, j + 1);
        } else if (p.charAt(j) == '*') {
            res = dp(s, i + 1, p, j) | dp(s, i, p, j + 1);
        } else if (p.charAt(j) == '?') {
            res = dp(s, i + 1, p, j + 1);
        } else {
            res = false;
        }
        memo[i][j] = res ? 1 : 0;
        return res;
    }

    /**
     * 删除字符串中 连续 的*，只保留一个*
     * 
     * @param p
     * @return
     */
    private String deleteRepeatx(String p) {
        if (p.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) == '*' && p.charAt(i - 1) == '*') {
                continue;
            }
            sb.append(p.charAt(i));
        }
        return sb.toString();
    }
}
