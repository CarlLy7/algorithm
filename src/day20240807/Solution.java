package day20240807;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-07 10:37
 * @version: 1.0
 */
public class Solution {
    //    boolean found = false;
//    LinkedList<String> track = new LinkedList<>();
//
//    public boolean splitString(String s) {
//        backTrack(s, 0);
//        return found;
//    }
//
//    private void backTrack(String s, int start) {
//        if (found) {
//            return;
//        }
//        if (start == s.length()) {
//            if (track.size() >= 2 && String.join("", track).equals(s)) {
//                found = true;
//                return;
//            }
//        }
//        for (int i = start; i < s.length(); i++) {
//            String subStr = s.substring(start, i + 1);
//            // 0开头的数量
//            int zeroCount = 0;
//            for (int j = 0; j < subStr.length(); j++) {
//                if (subStr.charAt(j) == '0') {
//                    zeroCount++;
//                } else {
//                    break;
//                }
//            }
//            if (subStr.length() - zeroCount > (s.length() + 1) / 2) {
//                return;
//            }
//            Long curNum = Long.parseLong(subStr);
//            if (track.isEmpty() || Long.parseLong(track.getLast()) - curNum == 1) {
//                track.addLast(subStr);
//                backTrack(s, i + 1);
//                track.removeLast();
//            }
//        }
//    }

//    HashSet<String> set = new HashSet<>();
//    int res = 0;
//
//    public int maxUniqueSplit(String s) {
//        backTrack(s, 0);
//        return res;
//    }
//
//    private void backTrack(String s, int start) {
//        if (start == s.length()) {
//            res = Math.max(res, set.size());
//            return;
//        }
//        //不切割
//        backTrack(s, start + 1);
//        //切割
//        String subStr = s.substring(0, start + 1);
//        if (!set.contains(subStr)) {
//            set.add(subStr);
//            //进行下一轮递归
//            backTrack(s.substring(start + 1), 0);
//            set.remove(subStr);
//        }
//    }

    // 元素有重不可复选的排列问题,不是全排列是普通排列
//    int res = 0;
//    boolean[] used;
//
//    public int numTilePossibilities(String tiles) {
//        char[] chars = tiles.toCharArray();
//        Arrays.sort(chars);
//        used = new boolean[chars.length];
//        backTrack(chars);
//        return res - 1;
//    }
//
//    private void backTrack(char[] chars) {
//        res++;
//        for (int i = 0; i < chars.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
//                continue;
//            }
//            used[i] = true;
//            backTrack(chars);
//            used[i] = false;
//        }
//    }

//    LinkedList<Integer> track = new LinkedList<>();
//    int res = 0;
//    boolean[] used;
//
//    public int numSquarefulPerms(int[] nums) {
//        int n = nums.length;
//        used = new boolean[n];
//        Arrays.sort(nums);
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
//            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
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
//    private boolean isValid(int a, int b) {
//        int sum = a + b;
//        int c = (int) Math.sqrt(sum);
//        return c * c == (a + b);
//    }

    List<String> res = new ArrayList<>();
    StringBuilder track = new StringBuilder();

    public List<String> letterCasePermutation(String s) {
        backTrack(s, 0);
        return res;
    }

    private void backTrack(String s, int start) {
        if (start == s.length()) {
            res.add(track.toString());
            return;
        }
        if (s.charAt(start) >= '0' && s.charAt(start) <= '9') {
            track.append(s.charAt(start));
            backTrack(s, start + 1);
            track.deleteCharAt(track.length() - 1);
        } else {
            //直接加或者转加
            track.append(Character.toLowerCase(s.charAt(start)));
            backTrack(s, start + 1);
            track.deleteCharAt(track.length() - 1);

            track.append(Character.toUpperCase(s.charAt(start)));
            backTrack(s, start + 1);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
