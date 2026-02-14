package date202602;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.14
 * @Since: 1.0
 */

public class day20260214Solution {
    // [491] 非递减子序列
    // List<List<Integer>> res = new ArrayList<>();
    // LinkedList<Integer> track = new LinkedList<>();
    //
    // public List<List<Integer>> findSubsequences(int[] nums) {
    // backTrack(nums, 0);
    // return res;
    // }
    //
    // private void backTrack(int[] nums, int start) {
    // // base case
    // if (track.size() >= 2) {
    // res.add(new ArrayList<>(track));
    // }
    // HashSet<Integer> memo = new HashSet<>();
    // for (int i = start; i < nums.length; i++) {
    // if (!track.isEmpty() && track.getLast() > nums[i]) {
    // continue;
    // }
    // // 如果已经用过这个数了，则不可以重复使用
    // if (memo.contains(nums[i])) {
    // continue;
    // }
    // track.addLast(nums[i]);
    // memo.add(nums[i]);
    // backTrack(nums, i + 1);
    // track.removeLast();
    // }
    // }

    // [93] 复原 IP 地址
    List<String> res = new ArrayList<>();
    LinkedList<String> track = new LinkedList();

    public List<String> restoreIpAddresses(String s) {
        backTrack(s, 0);
        return res;
    }

    private void backTrack(String s, int start) {
        if (start == s.length() && track.size() == 4) {
            res.add(String.join(".", track));
        }
        for (int i = start; i < s.length(); i++) {
            if (!valid(s, start, i)) {
                continue;
            }
            if (track.size() > 4) {
                return;
            }
            // i这个位置我们是要的
            track.addLast(s.substring(start, i + 1));
            backTrack(s, i + 1);
            track.removeLast();
        }

    }

    private boolean valid(String s, int start, int end) {
        int len = end - start + 1;
        if (len > 3) {
            return false;
        }
        // 因为题目限制说只由数字组成，这里不用判断非数字了
        if (len == 1) {
            return true;
        }
        if (s.charAt(start) == '0') {
            return false;
        }
        if (Integer.parseInt(s.substring(start, end + 1)) > 255) {
            return false;
        }
        return true;
    }

    // [494] 目标和
    HashMap<String, Integer> memo = new HashMap<>();

    public int findTargetSumWays(int[] nums, int target) {
        return dp(nums, 0, target);
    }

    // dp(nums,start,target):nums[start...]目标和为remain的表达式数目
    private int dp(int[] nums, int start, int remain) {
        // 退出条件
        if (start == nums.length) {
            if (remain == 0) {
                return 1;
            }
            return 0;
        }
        String key = start + "," + remain;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // dp(nums,start+1,remain-nums[start])选择添加正号
        // dp(nums,start+1,remain+nums[start])选择添加负号
        int res = dp(nums, start + 1, remain - nums[start]) + dp(nums, start + 1, remain + nums[start]);
        memo.put(key, res);
        return res;
    }
}
