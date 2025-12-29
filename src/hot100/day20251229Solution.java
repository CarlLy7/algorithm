package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.29
 * @Since: 1.0
 */

public class day20251229Solution {
    // [566] 重塑矩阵
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (r * c != m * n) {
            return mat;
        }
        int[][] res = new int[r][c];
        for (int i = 0; i < m * n; i++) {
            set(res, i, get(mat, i));
        }
        return res;
    }

    /**
     * 将二维数组index位置的元素改成val值
     * 
     * @param res
     * @param index
     * @param val
     */
    private void set(int[][] res, int index, Integer val) {
        int n = res[0].length;
        int row = index / n;
        int col = index % n;
        res[row][col] = val;
    }

    /**
     * 从一个二维数字中得到index对应的元素
     * 
     * @param mat
     * @param index
     * @return
     */
    private Integer get(int[][] mat, int index) {
        int m = mat.length;
        int n = mat[0].length;
        int row = index / n;
        int col = index % n;
        return mat[row][col];
    }

    // [74] 搜索二维矩阵
    public boolean searchMatrixI(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (get(matrix, mid) == target) {
                return true;
            } else if (get(matrix, mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    // [240] 搜索二维矩阵 II
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
