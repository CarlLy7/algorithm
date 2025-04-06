import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-04-06 13:36
 * @Since: 1.0
 */
public class day20250406Solution {
    // 35 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 39 组合总和
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates, 0, target, 0);
        return ans;
    }

    private void backTrack(int[] candidates, int start, int target, int sum) {
        if (sum == target) {
            ans.add(new ArrayList<>(track));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            //做选择
            sum += candidates[i];
            track.addLast(candidates[i]);
            //回溯
            backTrack(candidates, i, target, sum);
            //撤销选择
            sum -= candidates[i];
            track.removeLast();
        }
    }

    /**
     * 41 缺失的第一个正数
     *
     * @param nums
     * @return 如果正确的话，nums中的数应该是从1到nums.length的大小，比如nums的大小为3的话，里面的数应该是【1，2，3】<br>
     * 如果不是的话，就需要进行位置置换
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //置换元素位置
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
