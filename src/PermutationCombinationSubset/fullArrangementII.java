package PermutationCombinationSubset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 全排列II https://leetcode.cn/problems/permutations-ii/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class fullArrangementII {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        visited=new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if(track.size()==nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(i>0 && nums[i]==nums[i-1]&& !visited[i-1]){
                continue;
            }
            if(visited[i]==true){
                continue;
            }
            track.addLast(nums[i]);
            visited[i]=true;
            backTrack(nums);
            track.removeLast();
            visited[i]=false;
        }
    }
}
