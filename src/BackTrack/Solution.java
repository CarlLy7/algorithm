package BackTrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-02-18 22:02
 * @version: 1.0
 */
public class Solution {
    // 46. 全排列
//    List<List<Integer>> result=new ArrayList<>();
//    LinkedList<Integer> track=new LinkedList<>();
//    boolean[] used;
//    public List<List<Integer>> permute(int[] nums) {
//        used=new boolean[nums.length];
//        backTrack(nums);
//        return result;
//    }
//
//    private void backTrack(int[] nums) {
//        if (track.size()==nums.length){
//            result.add(new LinkedList<>(track));
//            return;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]==true){
//                continue;
//            }
//            used[i]=true;
//            track.addLast(nums[i]);
//            backTrack(nums);
//            used[i]=false;
//            track.removeLast();
//        }
//    }

    // 93. 复原 IP 地址
    List<String> res=new ArrayList<>();
    LinkedList<String> track=new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
        backTrack(s,0);
        return res;
    }

    private void backTrack(String s, int start) {
        if (start==s.length() && track.size()==4){
            res.add(String.join(".",track));
            return;
        }
        for (int i = start; i <s.length() ; i++) {
            if (track.size()>=4){
                break;
            }
            if (!isValid(s,start,i)){
                continue;
            }
            track.addLast(s.substring(start,i+1));
            backTrack(s,i+1);
            track.removeLast();
        }
    }

    private boolean isValid(String s, int start, int end) {
        int len=end-start+1;
        if (len<=0 || len>3){
            return false;
        }
        if (len==1){
            return true;
        }
        if (s.charAt(start)=='0'){
            return false;
        }
        if (len<=2){
            return true;
        }
        if (Integer.parseInt(s.substring(start,start+len))>255){
            return false;
        }
        return true;
    }
}
