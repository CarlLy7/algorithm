import java.util.LinkedList;
import java.util.List;

/**
 * @author: luanyingqi
 * @date: 2024/11/21
 */

public class day20241121Solution {
    //566
    public int[][] matrixReshape(int[][] matrix, int r, int c) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m * n != r * c) {
            return matrix;
        }
        int[][] res = new int[r][c];
        for (int i = 0; i < m * n; i++) {
            set(res, i, get(matrix, i));
        }
        return res;
    }

    private void set(int[][] matrix, int index, int val) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = index / n;
        int j = index % n;
        matrix[i][j] = val;
    }

    private int get(int[][] matrix, int index) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = index / n;
        int j = index % n;
        return matrix[i][j];
    }

    //74
//    public boolean searchMatrix(int[][] matrix, int target) {
//        int m = matrix.length;
//        int n = matrix[0].length;
//        // [left,right]
//        int left = 0, right = m * n - 1;
//        while (left <= right) {
//            int mid=left+(right-left)/2;
//            if (get(matrix,mid)==target){
//                return true;
//            }else if (get(matrix,mid)<target){
//                left=mid+1;
//            }else{
//                right=mid-1;
//            }
//        }
//        return false;
//    }

    //240
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
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

    //392
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    //792
//    public int numMatchingSubseq(String s, String[] words) {
//        // 记录每一个字母出现的索引位置
//        ArrayList<Integer>[] charArrays = new ArrayList[256];
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (charArrays[c] == null) {
//                charArrays[c] = new ArrayList<>();
//            }
//            charArrays[c].add(i);
//        }
//        int res = 0;
//        for (String word : words) {
//            // s上的指针
//            int i = 0;
//            // word上的指针
//            int j = 0;
//            while (j < word.length()) {
//                char c = word.charAt(j);
//                if (charArrays[c].size() == 0) {
//                    break;
//                }
//                int pos = leftBound(charArrays[c], i);
//                if (pos == charArrays[c].size()) {
//                    break;
//                }
//                j = charArrays[c].get(pos);
//                i++;
//                j++;
//                if (j == word.length()) {
//                    res++;
//                }
//            }
//        }
//        return res;
//    }


//    private int leftBound(ArrayList<Integer> arrayList, int target) {
//        int left = 0, right = arrayList.size();
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//            if (arrayList.get(mid) < target) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return left;
//    }

    //658
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();
        int p = leftBound(arr, x);
        int left = p - 1, right = p;
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

    private int leftBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= arr.length || arr[left] != target) {
            return -1;
        }
        return left;
    }

    //35
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //162
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //852
    // 0 10 5 2   1
    // 0 2 1 0   1
    public int peakIndexInMountainArray(int[] nums) {
        int left = 0, right = nums.length;
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

    public int search(int[] nums, int target) {
        int leftIndex = leftBound(nums, target);
        int rightIndex = rightBound(nums, target);
        return rightIndex - leftIndex + 1;
    }

    private int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid=left+(right-left)/2;
            if (nums[mid]==target){
                left=mid+1;
            }else if (nums[mid]>target){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        if (right<0 || nums[right]!=target){
            return -1;
        }
        return right;
    }
}
