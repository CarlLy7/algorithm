package day20240514;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-14 21:54
 * @version: 1.0
 */
public class day20240514 {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, track, used);
        return res;
    }

    // 回溯算法
    private void backTrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        //结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        //站在一个节点上，看选择列表
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 做选择
            used[i] = true;
            track.addLast(nums[i]);
            backTrack(nums, track, used);
            //撤销选择
            used[i] = false;
            track.removeLast();
        }
    }
}
