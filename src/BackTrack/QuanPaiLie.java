package BackTrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 全排列 https://leetcode.cn/problems/permutations/
 * @author: lyq
 * @createDate: 25/3/2023
 * @version: 1.0
 */
public class QuanPaiLie {
    List<List<Integer>> res=new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        //track中保存我们此时的路径
        LinkedList<Integer> track=new LinkedList<>();
        //用来标记这个节点是不是访问过了，防止重复访问
        boolean[] visited=new boolean[nums.length];
        backTrack(nums,track,visited);
        return res;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, boolean[] visited) {
        //如果路径的长度已经和我们的选择列表,就说明到底了我们直接加入到最后的结果中返回就可以了
        if(track.size()==nums.length){
            res.add(new LinkedList<>(track));
            return;
        }
        //遍历所有的选择列表进行暴力枚举
        for (int i = 0; i < nums.length; i++) {
            //如果这个节点已经访问过了就直接跳过
            if(visited[i]){
                continue;
            }
            //进行选择
            track.add(nums[i]);
            visited[i]=true;
            //进行回溯
            backTrack(nums,track,visited);
            //撤销选择
            track.removeLast();
            visited[i]=false;
        }
    }
}
