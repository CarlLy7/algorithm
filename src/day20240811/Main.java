package day20240811;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-11 14:37
 * @version: 1.0
 */
public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            String[] split = line.split("\\s+");
//            System.out.println(method(split[0], split[1]));
//        }
//        scanner.close();
//    }
//
//    private static int method(String x, String z) {
//        int m = x.length();
//        int n = z.length();
//        int[][] dp = new int[m + 1][n + 1];
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (x.charAt(i - 1) == z.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1] + 1;
//                } else {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//                }
//            }
//        }
//        return dp[m][n];
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String substring = line.substring(1, line.length() - 1);
        String[] split = substring.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int[] res = method(nums);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < res.length; i++) {
            if (i < res.length - 1) {
                sb.append(res[i]).append(",");
            }
            if (i == res.length - 1) {
                sb.append(res[i]).append("]");
            }
        }
        System.out.println(sb.toString());
    }

    private static int[] method(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            left[i] = queue.size();
            while (!queue.isEmpty() && nums[i] >= queue.peek()) {
                queue.pop();
            }
            queue.push(nums[i]);
        }
        queue = new ArrayDeque<>();
        for (int j = n - 1; j >= 0; j--) {
            right[j] = queue.size();
            while (!queue.isEmpty() && queue.peek() <= nums[j]) {
                queue.pop();
            }
            queue.push(nums[j]);
        }
        for (int i = 0; i < n; i++) {
            res[i] = left[i] + right[i] + 1;
        }
        return res;
    }
    /**
     * [5,3,8,3,2,5]
     *
     * left=
     * right=[2,2,5,5,5,6]
     * 0-(-1)+2-0=3   1-0+(2-1)=2
     */
}
