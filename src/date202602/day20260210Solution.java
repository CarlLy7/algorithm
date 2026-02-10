package date202602;

import java.util.LinkedList;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.10
 * @Since: 1.0
 */

public class day20260210Solution {
    // [967] 连续差相同的数字
    // List<Integer> ans = new ArrayList<>();
    // int track = 0;
    // int digest = 0;
    //
    // public int[] numsSameConsecDiff(int n, int k) {
    // backTrack(n, k);
    // int[] res = new int[ans.size()];
    // for (int i = 0; i < ans.size(); i++) {
    // res[i] = ans.get(i);
    // }
    // return res;
    // }
    //
    // private void backTrack(int n, int k) {
    // if (digest == n) {
    // ans.add(track);
    // return;
    // }
    // for (int i = 0; i <= 9; i++) {
    // // 去掉前置0
    // if (digest == 0 && i == 0) {
    // continue;
    // }
    // if (digest > 0 && Math.abs(track % 10 - i) != k) {
    // continue;
    // }
    // digest++;
    // track = track * 10 + i;
    // backTrack(n, k);
    // track = track / 10;
    // digest--;
    // }
    // }

    // [526] 优美的排列
    int res = 0;
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public int countArrangement(int n) {
        used = new boolean[n + 1];
        backTrack(n, 1);
        return res;
    }

    private void backTrack(int n, int index) {
        // 结束条件
        if (index > n) {
            res++;
            return;
        }
        for (int elem = 1; elem <= n; elem++) {
            // 因为元素不可重复选择
            if (used[elem]) {
                continue;
            }
            if (!(elem % index == 0 || index % elem == 0)) {
                continue;
            }
            track.addLast(elem);
            used[elem] = true;
            backTrack(n, index + 1);
            track.removeLast();
            used[elem] = false;
        }
    }

    // [37] 解数独
    // 是否有结果
    boolean found = false;

    public void solveSudoku(char[][] board) {
        backTrack(board, 0);
    }

    private void backTrack(char[][] board, int index) {
        if (found) {
            return;
        }
        int m = 9, n = 9;
        int i = index / n;
        int j = index % n;
        if (index >= m * n) {
            found = true;
            return;
        }
        // 如果此位置有了非空数字，则去找下一个空格
        if (board[i][j] != '.') {
            backTrack(board, index + 1);
            return;
        }
        // 选择列表
        for (char c = '1'; c <= '9'; c++) {
            if (!valid(board, i, j, c)) {
                continue;
            }
            board[i][j] = c;
            backTrack(board, index + 1);
            if (found) {
                return;
            }
            board[i][j] = '.';
        }
    }

    private boolean valid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 同一行是否有相同数字
            if (board[row][i] == c) {
                return false;
            }
            // 同一列是否有相同数字
            if (board[i][col] == c) {
                return false;
            }
            // 同一个3x3格子是否有相同数字
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == c) {
                return false;
            }
        }
        return true;
    }
}
