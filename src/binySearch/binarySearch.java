package binySearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;

/**
 * @description: 二分查找 https://leetcode.cn/problems/binary-search/
 * @author: lyq
 * @createDate: 3/5/2023
 * @version: 1.0
 */
public class binarySearch {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        int leftIndex = searchLeftRange(nums, target);
        int rightIndex = searchRightRange(nums, target);
        return new int[]{leftIndex, rightIndex};
    }

    private int searchLeftRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        //left=right+1
        if (left < 0 || left >= nums.length) return -1;
        return nums[left] == target ? left : -1;
    }

    private int searchRightRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        //left=right+1
        if (right < 0 || right >= nums.length) return -1;
        return nums[right] == target ? right : -1;
    }

    // 278. 第一个错误的版本
//    public int firstBadVersion(int n) {
//        int left = 1, right = n;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (isBadVersion(mid)) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        if (left < 1 || left > n) {
//            return -1;
//        }
//        return isBadVersion(left)?left:-1;
//    }
    // 875. 爱吃香蕉的珂珂
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1000000000;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (minSpeed(piles, mid) <= h) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private long minSpeed(int[] piles, int x) {
        long hour = 0;
        for (int i = 0; i < piles.length; i++) {
            hour += piles[i] / x;
            if (piles[i] % x > 0) {
                hour++;
            }
        }
        return hour;
    }

    // 1011. 在 D 天内送达包裹的能力
    public int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f(weights, mid) <= days) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private long f(int[] weights, int x) {
        long day = 0;
        for (int i = 0; i < weights.length; ) {
            int cap = x;
            while (i < weights.length) {
                if (cap < weights[i])
                    break;
                else
                    cap -= weights[i];
                i++;
            }
            day++;
        }
        return day;
    }

    //410. 分割数组的最大值
    public int splitArray(int[] nums, int k) {
        int left = Arrays.stream(nums).max().getAsInt();
        int right = Arrays.stream(nums).sum();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ff(nums, mid) <= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private long ff(int[] nums, int x) {
        long day = 0L;
        for (int i = 0; i < nums.length; ) {
            int cap = x;
            while (i < nums.length) {
                if (cap < nums[i]) {
                    break;
                } else {
                    cap -= nums[i];
                }
                i++;
            }
            day++;
        }
        return day;
    }

    // 74. 搜索二维矩阵
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

    private int get(int[][] matrix, int index) {
        int n = matrix[0].length;
        int i = index / n;
        int j = index % n;
        return matrix[i][j];
    }

    // 566. 重塑矩阵
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] res = new int[r][c];
        for (int i = 0; i < m * n; i++) {
            set(res, i, get(mat, i));
        }
        return res;
    }

    private void set(int[][] matrix, int index, int value) {
        int n = matrix[0].length;
        int i = index / n;
        int j = index % n;
        matrix[i][j] = value;
    }

    //240. 搜索二维矩阵 II
    public boolean searchMatrixII(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = n - 1;
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

    // 658. 找到 K 个最接近的元素
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int p = leftFind(arr, x);
        int left = p - 1, right = p;
        LinkedList<Integer> res = new LinkedList<>();
        while (right - left - 1 < k) {
            if (left == -1) {
                res.addLast(arr[right]);
                right++;
            } else if (right == arr.length) {
                res.addFirst(arr[left]);
                left--;
            } else if (x - arr[left] > arr[right] - x) {
                res.addLast(arr[right]);
                right++;
            } else {
                res.addFirst(arr[left]);
                left--;
            }
        }
        return res;
    }

    private int leftFind(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 162. 寻找峰值
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // LCR 069. 山脉数组的峰顶索引
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1, right = arr.length - 2;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // LCR 172. 统计目标成绩的出现次数
    public int countTarget(int[] scores, int target) {
        int leftIndex = leftFind2(scores, target);
        int rightIndex = rightFind(scores, target);
        return rightIndex - leftIndex + 1;
    }

    private int leftFind2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int rightFind(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    // LCR 173. 点名
    public int takeAttendance(int[] records) {
        int left = 0, right = records.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (records[mid] > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 33. 搜索旋转排序数组
//    public int search(int[] nums, int target) {
//        int left = 0, right = nums.length - 1;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid]==target){
//                return mid;
//            }
//            if (nums[mid]>=nums[left]){
//                //断崖左侧
//                if (target>=nums[left] && target<nums[mid]){
//                    right=mid-1;
//                }else{
//                    left=mid+1;
//                }
//            }else{
//                //断崖右侧
//                if (target>nums[mid] && target<=nums[right]){
//                    left=mid+1;
//                }else{
//                    right=mid-1;
//                }
//            }
//        }
//        return -1;
//    }

    // 81. 搜索旋转排序数组 II
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right && nums[left] == nums[left + 1]) left++;
        while (left < right && nums[right] == nums[right - 1]) right--;
        while (left <= right) {
            int mid=left+(right-left)/2;
            if (nums[mid]==target){
                return true;
            }
            if (nums[mid]>=nums[left]){
                //断崖左侧
                if (target>=nums[left] && target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if (target>nums[mid] && target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }
        return false;
    }

}
