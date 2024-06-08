package day20240607;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-07 10:14
 * @version: 1.0
 */
public class Solution {
    public int maxOperations(int[] nums) {
        int res = 1;
        if (nums.length < 2) {
            return 0;
        }
        int cur = nums[0] + nums[1];
        for (int i = 2; i < nums.length - 1; i += 2) {
            int temp = nums[i] + nums[i + 1];
            if (temp == cur) {
                res++;
            } else {
                break;
            }
        }
        return res;
    }
}
