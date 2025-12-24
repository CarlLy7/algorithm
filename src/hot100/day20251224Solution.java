package hot100;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.24
 * @Since: 1.0
 */

public class day20251224Solution {
    // [867] 转置矩阵
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }

    // [14] 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();
        // 以第一个字符串的长度为标准
        for (int col = 0; col < n; col++) {
            for (int row = 1; row < m; row++) {
                String cur = strs[row];
                String pre = strs[row - 1];
                if (col >= cur.length() || col >= pre.length() || cur.charAt(col) != pre.charAt(col)) {
                    return cur.substring(0, col);
                }
            }
        }
        return strs[0];
    }

    // [870] 优势洗牌
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // int[0]:索引 int[1]:值
        // 存放nums2，并且倒序排序
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((int[] a, int[] b) -> {
            return b[1] - a[1];
        });
        for (int i = 0; i < nums2.length; i++) {
            maxQueue.offer(new int[] {i, nums2[i]});
        }
        // nums1升序
        Arrays.sort(nums1);
        int left = 0, right = n - 1;
        int[] res = new int[n];
        while (!maxQueue.isEmpty()) {
            int[] nums2Max = maxQueue.poll();
            int index = nums2Max[0];
            int maxVal = nums2Max[1];
            if (nums1[right] > maxVal) {
                res[index] = nums1[right];
                right--;
            } else {
                res[index] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
