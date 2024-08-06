package day20240806;

import java.util.Scanner;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-06 16:30
 * @version: 1.0
 */
public class Main {
    public static int minStep(int x) {
        // 状态:当前的索引位置
        // 选择:前进一步,后退一步,到两倍距离
        //dp[i]:从x=0到x=i的最小步数
        int[] dp = new int[x + 1];
        for (int i = 0; i <= x; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= x; i++) {
            if (i % 2 == 1) {
                dp[i] = Math.min(dp[i - 1] + 1, Math.min(dp[i], dp[(i + 1) / 2] + 2));
            } else {
                dp[i] = Math.min(dp[i - 1] + 1, Math.min(dp[i], dp[i / 2] + 1));
            }
        }
        return dp[x];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int in = scanner.nextInt();
        System.out.println(minStep(Math.abs(in)));
    }
}
