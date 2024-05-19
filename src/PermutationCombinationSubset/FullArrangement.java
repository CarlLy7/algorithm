package PermutationCombinationSubset;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 全排列 https://leetcode.cn/problems/permutations/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class FullArrangement {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited=new boolean[nums.length];
        backTrack(nums,visited);
        return res;
    }

    private void backTrack(int[] nums, boolean[] visited) {
        if(track.size()== nums.length){
            res.add(new LinkedList<>(track));
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i]){
                continue;
            }
            track.addLast(nums[i]);
            visited[i]=true;
            backTrack(nums,visited);
            track.removeLast();
            visited[i]=false;
        }
    }
}
