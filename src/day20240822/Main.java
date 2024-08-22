package day20240822;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-22 14:19
 * @version: 1.0
 */
//public class Main {
//    static long mod = 1000000007L;
//
//    static long[] memo;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        memo = new long[n + 1];
//        Arrays.fill(memo, -1L);
//        System.out.println((dfs(n) % mod));
//    }
//
//    private static long dfs(int n) {
//        if (n == 1) {
//            return 1L;
//        }
//        if (memo[n] != -1) {
//            return memo[n];
//        }
//        n--;
//        long res = 0L;
//        for (int i = 1; i <= n; i += 2) {
//            long left = dfs(i);
//            long right = dfs(n - i);
//            res = (res % mod + (left * right) % mod) % mod;
//        }
//        memo[n + 1] = res;
//        return res;
//    }
//}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] split = line.split(",");
        int[][] tasks = new int[split.length][2];
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(":");
            tasks[i][0] = Integer.parseInt(split1[0]);
            tasks[i][1] = Integer.parseInt(split1[1]);
        }
        Arrays.sort(tasks, (a, b) -> {
            return (a[1] - a[0]) - (b[1] - b[0]);
        });
        int res = 0;
        for (int[] task : tasks) {
            res = Math.max(res + task[0], task[1]);
        }
        System.out.println(res <= 4800 ? res : -1);
    }
}