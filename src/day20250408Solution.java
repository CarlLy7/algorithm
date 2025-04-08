import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.08
 * @Since: 1.0
 */

public class day20250408Solution {
    // 48 旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    // 整行翻转
    private void reverse(int[] row) {
        int left = 0, right = row.length - 1;
        while (right > left) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }

    // 49 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String code = encode(str);
            map.putIfAbsent(code, new LinkedList<>());
            map.get(code).add(str);
        }
        List<List<String>> ans = new LinkedList<>();
        for (List<String> value : map.values()) {
            ans.add(value);
        }
        return ans;
    }

    private String encode(String str) {
        char[] chars = new char[26];
        for (char c : str.toCharArray()) {
            int d = c - 'a';
            chars[d]++;
        }
        return new String(chars);
    }

    // 51 N皇后
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(".");
            }
            board.add(row.toString());
        }
        backTrack(board, 0);
        return ans;
    }

    private void backTrack(List<String> board, int row) {
        if (row == board.size()) {
            ans.add(new ArrayList<>(board));
            return;
        }
        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) {
                continue;
            }
            char[] rowCharArray = board.get(row).toCharArray();
            rowCharArray[col] = 'Q';
            board.set(row, new String(rowCharArray));

            backTrack(board, row + 1);

            rowCharArray[col] = '.';
            board.set(row, new String(rowCharArray));
        }
    }

    private boolean isValid(List<String> board, int row, int col) {
        int n = board.size();
        // 检查列
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q') {
                return false;
            }
        }

        // 检查左上角
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        // 检查右上角
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
