package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.17
 * @Since: 1.0
 */

public class day20251217Solution {
    // [283] 移动零
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // [344] 反转字符串
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    // [48] 旋转图像
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 1.以对角线为轴进行翻转
        for (int i = 0; i < m; i++) {
            //注意！！！以对角线翻转的时候，遍历一半就可以了
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 每行进行翻转
        for (int[] arr : matrix) {
            reverse(arr);
        }
    }

    /**
     * 翻转数组
     * 
     * @param arr
     */
    private void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
