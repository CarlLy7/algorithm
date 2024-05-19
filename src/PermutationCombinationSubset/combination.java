package PermutationCombinationSubset;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 组合 https://leetcode.cn/problems/combinations/
 * @author: lyq
 * @createDate: 26/3/2023
 * @version: 1.0
 */
public class combination {
    List<List<Integer>> res=new LinkedList<>();
    LinkedList<Integer> track=new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
//        int[] nums=new int[n];
//        for (int i = 0; i < nums.length; i++) {
//            nums[i]=i+1;
//        }
        backTrack(1,n,k);
        return res;
    }

    private void backTrack(int start, int n,int k) {
        if(track.size()==k){
            res.add(new LinkedList<>(track));
        }
        for (int i = start; i <=n ; i++) {
            track.addLast(i);
            backTrack(i+1,n,k);
            track.removeLast();
        }
    }
}
