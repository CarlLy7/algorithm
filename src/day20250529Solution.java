import java.util.Arrays;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.29
 * @Since: 1.0
 */

public class day20250529Solution {
    // [576] 出界的路径数
    int[][][] memo;
    int m, n;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        memo = new int[m][n][maxMove + 1];
        for (int[][] ints : memo) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        return dp(maxMove, startRow, startColumn);
    }

    /**
     * 最大移动次数maxMove时，从（startRow,startColumn）出发移出边界的最大路径
     * 
     * @param maxMove
     * @param i
     * @param j
     * @return
     */
    private int dp(int maxMove, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (memo[i][j][maxMove] != -1) {
            return memo[i][j][maxMove];
        }
        if (maxMove == 0) {
            return 0;
        }
        long res = 0;
        res += dp(maxMove - 1, i - 1, j);
        res += dp(maxMove - 1, i + 1, j);
        res += dp(maxMove - 1, i, j - 1);
        res += dp(maxMove - 1, i, j + 1);
        res = res % 1000000007;
        memo[i][j][maxMove] = (int)res;
        return (int)res;
    }

    // [740] 删除并获得点数

    public int deleteAndEarn(int[] nums) {
        int m = nums.length;
        int[] points = new int[m + 1];
        for (int num : nums) {
            points[num] += num;
        }
        return rob(points);
    }

    private int rob(int[] points) {
        int n = points.length;
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, points[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

    // [877] 石子游戏
    public boolean stoneGame(int[] piles) {
        // 偶数堆，总数为奇数，先下手必赢，因为可以控制自己选择奇数堆还是偶数堆
        return true;
    }

}
