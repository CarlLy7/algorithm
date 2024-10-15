import java.util.*;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-10-15 21:27
 * @version: 1.0
 */
public class day20241015Solution {
    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, track, used);
        return ans;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.addLast(nums[i]);
            backTrack(nums, track, used);
            used[i] = false;
            track.removeLast();
        }
    }

    LinkedList<List<String>> res = new LinkedList<>();

    List<List<String>> solveNQueens(int n) {
        String[][] board = new String[n][n];
        for (String[] strings : board) {
            Arrays.fill(strings, ".");
        }
        backTrack(board, 0);
        return res;
    }

    private void backTrack(String[][] board, int row) {
        if (row == board.length) {
            List<String> ans = new ArrayList<>();
            for (String[] b : board) {
                ans.add(String.join("", b));
            }
            res.add(ans);
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            board[row][col] = "Q";
            backTrack(board, row + 1);
            board[row][col] = ".";
        }
    }

    private boolean isValid(String[][] board, int row, int col) {
        int n = board[row].length;
        for (int i = 0; i <= row; i++) {
            if ("Q".equals(board[i][col])) {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if ("Q".equals(board[i][j])) {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if ("Q".equals(board[i][j])) {
                return false;
            }
        }
        return true;
    }
}
