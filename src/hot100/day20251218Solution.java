package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.18
 * @Since: 1.0
 */

public class day20251218Solution {
    // [54] 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int upper = 0, down = m - 1;
        int left = 0, right = n - 1;
        while (res.size() < m * n) {
            if (upper <= down) {
                for (int j = left; j <= right; j++) {
                    res.add(matrix[upper][j]);
                }
                upper++;
            }
            if (left <= right) {
                for (int i = upper; i <= down; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            }
            if (upper <= down) {
                for (int j = right; j >= left; j--) {
                    res.add(matrix[down][j]);
                }
                down--;
            }
            if (left <= right) {
                for (int i = down; i >= upper; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }

    // [59] 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int[][] martix = new int[n][n];
        int cur = 1;
        int up = 0, down = n - 1, left = 0, right = n - 1;
        while (cur <= n * n) {
            if (up <= down) {
                for (int j = left; j <= right; j++) {
                    martix[up][j] = cur++;
                }
                up++;
            }
            if (left <= right) {
                for (int i = up; i <= down; i++) {
                    martix[i][right] = cur++;
                }
                right--;
            }
            if (up <= down) {
                for (int j = right; j >= left; j--) {
                    martix[down][j] = cur++;
                }
                down--;
            }
            if (left <= right) {
                for (int i = down; i >= up; i--) {
                    martix[i][left] = cur++;
                }
                left++;
            }
        }
        return martix;
    }

    // [151] 反转字符串中的单词
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                sb.append(c);
            } else {
                // 空格加入
                if (sb.length() != 0 && sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }
            }
        }
        if (sb.length() == 0) {
            return "";
        }
        char[] chars = sb.toString().toCharArray();
        int n = chars.length;
        reverse(chars, 0, n - 1);
        for (int i = 0; i < n;) {
            for (int j = i; j < n; j++) {
                // 当前[i,j]就是一个单词
                if (j + 1 == n || chars[j + 1] == ' ') {
                    reverse(chars, i, j);
                    i = j + 2;
                    break;
                }
            }
        }
        return new String(chars);
    }

    /**
     * 在chars的[l,r]范围内进行翻转
     * 
     * @param chars
     * @param l
     * @param r
     */
    private void reverse(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }
}
