import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.07
 * @Since: 1.0
 */

public class day20250707Solution {
    // [2850] 将石头分散到网格图的最少移动次数

    // 最后的结果
    // int res = Integer.MAX_VALUE;
    // // 回溯算法移动的次数
    // int moved = 0;
    // // 石子数等于0的位置个数
    // int emptyCount = 0;
    //
    // public int minimumMoves(int[][] grid) {
    // // 石子数大于1的坐标
    // List<int[]> surplus = new LinkedList<>();
    // // 石子数等于0的坐标
    // List<int[]> empty = new LinkedList<>();
    // for (int i = 0; i < grid.length; i++) {
    // for (int j = 0; j < grid[0].length; j++) {
    // if (grid[i][j] > 1) {
    // surplus.add(new int[] {i, j});
    // } else if (grid[i][j] == 0) {
    // empty.add(new int[] {i, j});
    // emptyCount++;
    // }
    // }
    // }
    // // 回溯算法
    // backTrack(grid, surplus, empty);
    // return res;
    // }
    //
    // private void backTrack(int[][] grid, List<int[]> surplus, List<int[]> empty) {
    // if (emptyCount == 0) {
    // res = Math.min(res, moved);
    // return;
    // }
    // // 以石子数大于1的坐标为起点，回溯将石子移动到所有的石子数为0的坐标
    // for (int[] ints : surplus) {
    // int srcX = ints[0];
    // int srcY = ints[1];
    // if (grid[srcX][srcY] == 1) {
    // continue;
    // }
    // for (int[] dest : empty) {
    // int destX = dest[0];
    // int destY = dest[1];
    // if (grid[destX][destY] != 0) {
    // continue;
    // }
    // // 移动
    // int step = Math.abs(srcX - destX) + Math.abs(srcY - destY);
    // emptyCount--;
    // moved += step;
    // grid[srcX][srcY]--;
    // grid[destX][destY]=1;
    // backTrack(grid, surplus, empty);
    // // 回溯
    // emptyCount++;
    // moved -= step;
    // grid[srcX][srcY]++;
    // grid[destX][destY]=0;
    // }
    // }
    // }

    // [LCR 082] 组合总和 II
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    int trackSum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backcTrack(candidates, 0, target);
        return res;
    }

    private void backcTrack(int[] candidates, int start, int target) {
        if (trackSum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            track.addLast(candidates[i]);
            trackSum += candidates[i];
            backcTrack(candidates, i + 1, target);
            // 撤销
            track.removeLast();
            trackSum -= candidates[i];
        }
    }

    // [LCR 084] 全排列 II
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    // boolean[] used;
    //
    // public List<List<Integer>> permuteUnique(int[] nums) {
    // Arrays.sort(nums);
    // used = new boolean[nums.length];
    // backTrack(nums);
    // return res;
    // }
    //
    // private void backTrack(int[] nums) {
    // if (track.size() == nums.length) {
    // res.add(new ArrayList<>(track));
    // return;
    // }
    // for (int i = 0; i < nums.length; i++) {
    // if (used[i]) {
    // continue;
    // }
    // if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
    // continue;
    // }
    //
    // track.addLast(nums[i]);
    // used[i] = true;
    // backTrack(nums);
    // track.removeLast();
    // used[i] = false;
    // }
    // }
}
