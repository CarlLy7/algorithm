/**
 * @author: carl
 * @date: 2025/1/9
 */

public class day20250109Solution {
    //42
    public int trap(int[] height) {
        int m = height.length;
        if (m == 0) {
            return 0;
        }
        int[] leftMax = new int[m];
        int[] rightMax = new int[m];
        leftMax[0] = height[0];
        rightMax[m - 1] = height[m - 1];
        for (int i = 1; i < m - 1; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        for (int i = m - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        int res = 0;
        for (int i = 1; i < m - 1; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }

    //11
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            int curArea = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, curArea);
            if (height[left] < height[right]) {
                left++;
            }
            if (height[right] < height[left]) {
                right--;
            }
        }
        return res;
    }
}
