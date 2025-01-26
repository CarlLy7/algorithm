/**
 * @author: carl
 * @date: 2025/1/26
 */

public class day20250126Solution {
    // 55
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            res = Math.max(res, i + nums[i]);
            if (res <= i) {
                return false;
            }
        }
        return res >= n - 1;
    }

    // 45
    public int jump(int[] nums) {
        int n = nums.length;
        int farest = 0, end = 0;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            farest = Math.max(farest, i + nums[i]);
            if (end == i) {
                res++;
                end = farest;
            }
        }
        return res;
    }
}
