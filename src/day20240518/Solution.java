package day20240518;

import java.util.Arrays;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024年5月18日13:04:34
 * @version: 1.0
 */
public class Solution {
    public int countPrimes(int n) {
        // 记录是不是质数
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < n; i++) {
            // 如果一个数是质数，那么它乘任何一个大于1的数就不是质数了
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                res++;
            }
        }
        return res;
    }

    // 缺失和重复出现的数字， 映射  出现就变成负数  如果重复出现了就是正数了
    // nums = [1,2,2,4]
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        // 重复出现的数
        int dup = -1;
        for (int i = 0; i < n; i++) {
            // 数和索引一一映射
            int index = Math.abs(nums[i]) - 1;
            // 已经出现过了
            if (nums[index] < 0) {
                dup = Math.abs(nums[index]);
            } else {
                // 第一次出现
                nums[index] *= -1;
            }
        }
        int miss = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                miss = i + 1;
            }
        }
        return new int[]{dup, miss};
    }

    public int findDuplicate(int[] nums) {
        int res = -1;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                res = Math.abs(nums[index]);
                return res;
            } else {
                nums[index] *= -1;
            }
        }
        return res;
    }
}
