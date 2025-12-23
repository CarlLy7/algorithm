package hot100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.23
 * @Since: 1.0
 */

public class day20251223Solution {
    // [977] 有序数组的平方
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;
        int[] res = new int[n];
        int p = n - 1;
        while (i <= j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                res[p--] = nums[i] * nums[i];
                i++;
            } else {
                res[p--] = nums[j] * nums[j];
                j--;
            }
        }
        return res;
    }

    // [1329] 将矩阵按对角线排序
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        // 技巧：如果在一条对角线上，横纵坐标之差是相等的，你可以想一下，往右下方移动一个，横纵坐标都+1，所以差肯定不变
        // key:横纵坐标之差 value:这个对角线上的所有元素
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diff = i - j;
                map.putIfAbsent(diff, new ArrayList<>());
                map.get(diff).add(mat[i][j]);
            }
        }
        // 将对角线上的元素进行排序，因为我们等会填充数据的时候需要将这个list中元素删除，所以我们倒序排列，从末尾删除元素效率高
        for (List<Integer> value : map.values()) {
            Collections.sort(value, Collections.reverseOrder());
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diff = i - j;
                List<Integer> values = map.get(diff);
                mat[i][j] = values.get(values.size() - 1);
                values.remove(values.size() - 1);
            }
        }
        return mat;
    }

    // [1260] 二维网格迁移
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int mn = m * n;
        k = k % mn;
        // 先翻转后k个元素
        reverse(grid, mn - k, mn - 1);
        // 翻转前mn-k个元素
        reverse(grid, 0, mn - k - 1);
        // 翻转整个数组
        reverse(grid, 0, mn - 1);
        List<List<Integer>> res = new ArrayList<>();
        for (int[] ints : grid) {
            List<Integer> row = new ArrayList<>();
            for (int anInt : ints) {
                row.add(anInt);
            }
            res.add(row);
        }
        return res;
    }

    /**
     * 翻转数组【i,j】范围
     * 
     * @param grid
     * @param i
     * @param j
     */
    private void reverse(int[][] grid, int i, int j) {
        while (i < j) {
            // 从二维数组中获取元素
            int temp = get(grid, i);
            set(grid, i, get(grid, j));
            set(grid, j, temp);
            i++;
            j--;
        }
    }

    private void set(int[][] grid, int index, int val) {
        int n = grid[0].length;
        int i = index / n;
        int j = index % n;
        grid[i][j] = val;
    }

    private int get(int[][] grid, int index) {
        int n = grid[0].length;
        int i = index / n;
        int j = index % n;
        return grid[i][j];
    }

}
