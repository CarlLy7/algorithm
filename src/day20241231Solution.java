import java.util.Arrays;

/**
 * @author: carl
 * @date: 2024/12/31
 */

public class day20241231Solution {

    //10
    int[][] memo;

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        memo = new int[m][n];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(s, 0, p, 0);
    }

    private boolean dp(String s, int i, String p, int j) {
        int m = s.length();
        int n = p.length();
        if (j == n) {
            return i == m;
        }
        if (i == m) {
            if ((n - j) % 2 == 1) {
                return false;
            }
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
        }
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }
        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                // *匹配0个或者多个
                res = dp(s, i, p, j + 2) | dp(s, i + 1, p, j);
            } else {
                res = dp(s, i + 1, p, j + 1);
            }
        } else {
            if (j < n - 1 && p.charAt(j + 1) != '*') {
                return false;
            }
            res = dp(s, i, p, j + 2);
        }
        memo[i][j] = (res ? 1 : 0);
        return res;
    }

}
