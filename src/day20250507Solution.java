import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.07
 * @Since: 1.0
 */

public class day20250507Solution {
    // 239 滑动窗口最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonousQueue window = new MonotonousQueue();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                // 窗口满了
                window.push(nums[i]);
                ans.add(window.max());
                // 删除最左边元素
                window.pop(nums[i - k + 1]);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    class MonotonousQueue {
        LinkedList<Integer> queue = new LinkedList<>();

        public void push(int val) {
            while (!queue.isEmpty() && queue.getLast() < val) {
                queue.pollLast();
            }
            queue.offerLast(val);
        }

        public int max() {
            return queue.getFirst();
        }

        /**
         * 只有当前元素是单调队列中的最大值的时候，会把最大值给删除了
         * 
         * @param val
         */
        public void pop(int val) {
            if (val == queue.getFirst()) {
                queue.pollFirst();
            }
        }
    }

    // 240 搜索二维矩阵 II
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 从右上角开始，变大就往下移动，变小就往左移动
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    // 279 完全平方数
    public int numSquares(int n) {
        // dp[i]:和为i的完全平方数的最小数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // base case
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            // n-1*1,n-2*2,n-3*3这种子问题
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
