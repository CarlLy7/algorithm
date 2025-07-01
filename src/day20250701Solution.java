import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.01
 * @Since: 1.0
 */

public class day20250701Solution {
    // [LCR 006] 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if (sum == target) {
                return new int[] {lo, hi};
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[] {};
    }

    // [LCR 179] 查找总价格为目标值的两个商品
    public int[] twoSum2(int[] price, int target) {
        int n = price.length;
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            int sum = price[lo] + price[hi];
            if (sum == target) {
                return new int[] {price[lo], price[hi]};
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[] {};
    }

    // [LCR 146] 螺旋遍历二维数组
    public static int[] spiralArray(int[][] array) {
        // 行数
        int n = array.length;
        if (n == 0) {
            return new int[] {};
        }
        // 列数
        int m = array[0].length;

        if (m == 0) {
            return new int[] {};
        }

        int rowHi = 0;
        int rowLow = n - 1;

        int colLeft = 0;
        int colRight = m - 1;

        List<Integer> ans = new ArrayList<>();
        while (ans.size() < m * n) {
            // 从左往右
            if (rowHi <= rowLow) {
                for (int j = colLeft; j <= colRight; j++) {
                    ans.add(array[rowHi][j]);
                }
                rowHi++;
            }
            // 从上往下
            if (colLeft <= colRight) {
                for (int i = rowHi; i <= rowLow; i++) {
                    ans.add(array[i][colRight]);
                }
                colRight--;
            }
            // 从右往左
            if (rowHi <= rowLow) {
                for (int j = colRight; j >= colLeft; j--) {
                    ans.add(array[rowLow][j]);
                }
                rowLow--;
            }
            // 从下往上
            if (colLeft <= colRight) {
                for (int i = rowLow; i >= rowHi; i--) {
                    ans.add(array[i][colLeft]);
                }
                colLeft++;
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        // int[][] array=new int[][]{{1,2,3,4},{12,13,14,5},{11,16,15,6},{10,9,8,7}};
        int[][] array = new int[][] {{}};
        int[] ints = spiralArray(array);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
