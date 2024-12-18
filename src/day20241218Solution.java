import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2024/12/18
 */

public class day20241218Solution {

    //784
    List<String> res = new ArrayList<>();
    StringBuilder track = new StringBuilder();

    public List<String> letterCasePermutation(String s) {
        backTrack(s, 0);
        return res;
    }

    private void backTrack(String s, int index) {
        // base case
        if (index == s.length()) {
            res.add(track.toString());
            return;
        }
        if ('0' <= s.charAt(index) && s.charAt(index) <= '9') {
            track.append(s.charAt(index));
            backTrack(s, index + 1);
            track.deleteCharAt(track.length() - 1);
        } else {
            track.append(Character.toLowerCase(s.charAt(index)));
            backTrack(s, index + 1);
            track.deleteCharAt(track.length() - 1);

            track.append(Character.toUpperCase(s.charAt(index)));
            backTrack(s, index + 1);
            track.deleteCharAt(track.length() - 1);
        }
    }

    //996
//    int res = 0;
//    boolean[] used;
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public int numSquarefulPerms(int[] nums) {
//        Arrays.sort(nums);
//        used = new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        if (track.size() == nums.length) {
//            res++;
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
//                continue;
//            }
//            if (!track.isEmpty() && !isValid(track.getLast(), nums[i])) {
//                continue;
//            }
//            used[i] = true;
//            track.addLast(nums[i]);
//            backTrack(nums);
//            used[i] = false;
//            track.removeLast();
//        }
//    }
//
//    private boolean isValid(int num1, int num2) {
//        int sum = num1 + num2;
//        int c = (int) Math.sqrt(sum);
//        return c * c == sum;
//    }

    //1079
//    int res=0;
//    boolean[] used;
//    public int numTilePossibilities(String s){
//        char[] charArray = s.toCharArray();
//        Arrays.sort(charArray);
//        used=new boolean[charArray.length];
//        backTrack(charArray);
//        return res-1;
//    }
//
//    private void backTrack(char[] charArray) {
//        res++;
//        for (int i = 0; i < charArray.length; i++) {
//            if (used[i]){
//                continue;
//            }
//            if (i>0 && charArray[i]==charArray[i-1] && !used[i-1]){
//                continue;
//            }
//            used[i]=true;
//            backTrack(charArray);
//            used[i]=false;
//        }
//    }
}
