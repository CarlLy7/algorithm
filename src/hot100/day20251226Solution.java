package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.26
 * @Since: 1.0
 */

public class day20251226Solution {
    // [875] 爱吃香蕉的珂珂
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1000000001;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果以mid为速度吃完所有香蕉所花的时间小于等于h,收敛右边
            if (f(piles, mid) <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 以x为速度，求吃完piles所花的时间
     * 
     * @param piles
     * @param x
     * @return
     */
    private long f(int[] piles, int x) {
        long hour = 0;
        for (int i = 0; i < piles.length; i++) {
            hour += piles[i] / x;
            if (piles[i] % x != 0) {
                hour++;
            }
        }
        return hour;
    }

    // [1011] 在 D 天内送达包裹的能力
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int sum = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            sum += weight;
        }
        int right = sum + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (fx(weights, mid) <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 运载能力为x的时候，运载所有货物所需要的天数
     * 
     * @param weights
     * @param x
     * @return
     */
    private int fx(int[] weights, int x) {
        int days = 0;
        for (int i = 0; i < weights.length;) {
            int cap = x;
            while (i < weights.length) {
                if (cap < weights[i]) {
                    break;
                } else {
                    cap -= weights[i];
                    i++;
                }
            }
            days++;
        }
        return days;
    }

    // [410] 分割数组的最大值

    /**
     * 题目可以转换成：在k天运完所有的货物，运载能力最小
     * @param nums
     * @param k
     * @return
     */
    public int splitArray(int[] nums, int k) {
        int left=0,right=1;
        for (int num : nums) {
            //最小运载能力至少得能够运载一次货物，所以需要取最大值
            left=Math.max(left,num);
            right+=num;
        }
        while(left<right){
            int mid=left+(right-left)/2;
            if (fx(nums,mid)<=k){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }

}
