import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: luanyingqi
 * @date: 2024/11/26
 */

public class day20241126Solution {
    //303
    class NumArray {
        int[] preSum;

        public NumArray(int[] nums) {
            preSum = new int[nums.length];
            for (int i = 1; i < nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    //304
    class NumMatrix {
        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int x1, int y1, int x2, int y2) {
            return preSum[x2 + 1][y2 + 1] - preSum[x1][y2 + 1] - preSum[x2 + 1][y1] + preSum[x1][y1];
        }
    }

    //1314


    //724
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int leftSum = preSum[i - 1] - preSum[0];
            int rightSum = preSum[n] - preSum[i];
            if (leftSum == rightSum) {
                return i - 1;
            }
        }
        return -1;
    }

    //238
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i];
        }
        int[] suffix = new int[n];
        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        int[] res = new int[n];
        res[0] = suffix[1];
        res[n - 1] = pre[n - 2];
        for (int i = 1; i <= n - 2; i++) {
            res[i] = pre[i - 1] * suffix[i + 1];
        }
        return res;
    }

    //1352
    class ProductOfNumbers {
        ArrayList<Integer> preProduct = new ArrayList<>();

        public ProductOfNumbers() {
            preProduct.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                preProduct.clear();
                preProduct.add(1);
                return;
            }
            int n = preProduct.size();
            preProduct.add(preProduct.get(n - 1) * num);
        }

        public int getProduct(int k) {
            int n = preProduct.size();
            if (k > n - 1) {
                return 0;
            }
            return preProduct.get(n - 1) / preProduct.get(n - k - 1);
        }
    }

    //525
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = (nums[0] == 0 ? -1 : 1);
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        int res = 0;
        for (int i = 0; i < preSum.length; i++) {
            if (!valToIndex.containsKey(preSum[i])) {
                valToIndex.put(preSum[i], i);
            } else {
                res = Math.max(res, i - valToIndex.get(preSum[i]) + 1);
            }
        }
        return res;
    }
}
