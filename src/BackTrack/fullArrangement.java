package BackTrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 全排列 https://leetcode.cn/problems/permutations/
 * @author: lyq
 * @createDate: 27/3/2023
 * @version: 1.0
 */
public class fullArrangement {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    boolean[] visited;
    public List<List<Integer>> permute(int[] nums) {
        visited=new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if(track.size()== nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i]){
                continue;
            }
            track.addLast(nums[i]);
            visited[i]=true;
            backtrack(nums);
            track.removeLast();
            visited[i]=false;
        }
    }
}
