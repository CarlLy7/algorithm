package date202603;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.24
 * @Since: 1.0
 */

public class day20260324Solution {
    // [1] 两数之和
    public int[] twoSum(int[] nums, int target) {
        // 存放值->索引的映射
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 另外一个可以和自己凑出target的值
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[] {map.get(need), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    // [1314] 矩阵区域和
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        NumMatrix numMatrix = new NumMatrix(mat);
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x1 = Math.max(i - k, 0);
                int y1 = Math.max(j - k, 0);
                int x2 = Math.min(i + k, m - 1);
                int y2 = Math.min(j + k, n - 1);
                res[i][j] = numMatrix.getPreSum(x1, y1, x2, y2);
            }
        }
        return res;
    }

    // 对二维矩阵进行前缀和
    private class NumMatrix {
        // preSum[i][j]记录从[0,0]到[i-1,j-1]这个矩阵的元素和
        private int[][] preSum;

        public NumMatrix(int[][] maxtrix) {
            int m = maxtrix.length;
            int n = maxtrix[0].length;
            if (m == 0 || n == 0) {
                return;
            }
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    preSum[i][j] = preSum[i - 1][j] // 上面部分
                        + preSum[i][j - 1] // 左边部分
                        + maxtrix[i - 1][j - 1] // 当前值
                        - preSum[i - 1][j - 1]; // 减去重复的部分
                }
            }
        }

        /**
         * 计算范围[x1,y1]到[x2,y2]范围矩阵的和
         * 
         * @param x1
         * @param y1
         * @param x2
         * @param y2
         * @return
         */
        public int getPreSum(int x1, int y1, int x2, int y2) {
            return preSum[x2 + 1][y2 + 1] // 最大的矩形
                - preSum[x1][y2 + 1] // 减去上面的矩形
                - preSum[x2 + 1][y1] // 减去左边的矩形
                + preSum[x1][y1]; // 加上重复减去的部分
        }
    }
}
