import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.14
 * @Since: 1.0
 */

public class day20250414Solution {

    // 74 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (get(matrix, mid) == target) {
                return true;
            } else if (get(matrix, mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    private int get(int[][] matrix, int mid) {
        int n = matrix[0].length;
        int x = mid / n;
        int y = mid % n;
        return matrix[x][y];
    }

    // 75 颜色分类
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;
        int p = 0;
        while (p <= p2) {
            if (nums[p] == 0) {
                swap(nums, p0, p);
                p0++;
            } else if (nums[p] == 2) {
                swap(nums, p2, p);
                p2--;
            } else {
                p++;
            }
            if (p < p0) {
                p = p0;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    // 76 最小覆盖子串
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int start = 0, len = Integer.MAX_VALUE;
        Map<Character, Integer> window = new HashMap<>();
        int valid = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            // 先加
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            // 收缩
            while (left < right && valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    // 因为right是先加的，所以不需要再+1了
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
