/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-11-20 20:33
 * @version: 1.0
 */
public class day20241120Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1000000001;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(piles, mid) == h) {
                right = mid;
            } else if (f(piles, mid) < h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // x:速度 y:时间
    private int f(int[] piles, int v) {
        int hour = 0;
        for (int i = 0; i < piles.length; i++) {
            hour += piles[i] / v;
            if (piles[i] % v != 0) {
                hour++;
            }
        }
        return hour;
    }

    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 1;
        for (int i = 0; i < weights.length; i++) {
            left = Math.max(left, weights[i]);
            right += weights[i];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f2(weights, mid) <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // x:运载能力  y:天数
    int f2(int[] weights, int x) {
        int days = 0;
        for (int i = 0; i < weights.length; ) {
            int cap = x;
            while (i < weights.length) {
                if (cap < weights[i]) break;
                else cap -= weights[i];
                i++;
            }
            days++;
        }
        return days;
    }
}
