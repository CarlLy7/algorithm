package day20240529;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-29 10:27
 * @version: 1.0
 */
public class Solution {
    public int maximumLength(String s) {
        int res = -1;
        int n = s.length();
        List<Integer>[] chs = new List[26];
        for (int i = 0; i < 26; i++) {
            chs[i] = new ArrayList<>();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (i + 1 == n || s.charAt(i) != s.charAt(i + 1)) {
                int index = s.charAt(i) - 'a';
                chs[index].add(count);
                count = 0;
                for (int j = chs[index].size() - 1; j > 0; j--) {
                    if (chs[index].get(j) > chs[index].get(j - 1)) {
                        Collections.swap(chs[index], j, j - 1);
                    }
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (chs[i].size() > 0 && chs[i].get(0) > 2) {
                res = Math.max(res, chs[i].get(0) - 2);
            }
            if (chs[i].size() > 1 && chs[i].get(0) > 1) {
                res = Math.max(res, Math.min(chs[i].get(0) - 1, chs[i].get(1)));
            }
            if (chs[i].size() > 2) {
                res = Math.max(res, chs[i].get(2));
            }
        }
        return res;
    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] visited;

//    public List<List<Integer>> permute(int[] nums) {
//        visited = new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }

//    private void backTrack(int[] nums) {
//        //结束条件
//        if (track.size() >= nums.length) {
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (visited[i]) {
//                continue;
//            }
//            track.addLast(nums[i]);
//            visited[i] = true;
//            backTrack(nums);
//            track.removeLast();
//            visited[i] = false;
//        }
//    }

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            board.add(sb.toString());
        }
        backTrack(board, 0);
        return res;
    }

    private void backTrack(List<String> board, int start) {
        // base case :如果到了最后一行，就直接结束了
        int n = board.size();
        if (start == n) {
            res.add(new ArrayList<>(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!valid(board, start, col)) {
                continue;
            }
            StringBuilder sb = new StringBuilder(board.get(start));
            //做选择
            sb.setCharAt(col, 'Q');
            board.set(start, sb.toString());
            backTrack(board, start + 1);
            //撤销选择
            sb.setCharAt(col, '.');
            board.set(start, sb.toString());
        }
    }

    private boolean valid(List<String> board, int row, int col) {
        int n = board.size();
        // 列
        for (int i = row - 1; i >= 0; i--) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        //左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        //右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
