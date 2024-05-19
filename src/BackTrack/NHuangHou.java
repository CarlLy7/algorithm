package BackTrack;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @description: N皇后 https://leetcode.cn/problems/n-queens/
 * @author: lyq
 * @createDate: 25/3/2023
 * @version: 1.0
 */
public class NHuangHou {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        //构造棋盘
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        backTrack(board, 0);
        return res;
    }

    /**
     * @param board
     * @param row   row行之前的行都放好了皇后
     */
    private void backTrack(char[][] board, int row) {

        //如果行数等于棋盘的行数的话，就说明我们之前都放好了,就可以结束了
        if (row == board.length) {
            res.add(Arrays.stream(board).map(item -> {
                return String.valueOf(item);
            }).collect(Collectors.toList()));
            return;
        }
        int length = board[row].length;
        for (int i = 0; i < length; i++) {
            if (!isValid(board, row, i)) {
                continue;
            }
            //选择路径
            board[row][i] = 'Q';
            //回溯
            backTrack(board, row + 1);
            //移除路径
            board[row][i] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        //判断列的合法性
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //判断右上的合法性
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j]=='Q'){
                return false;
            }
        }
        //判断左上的合法性
        for (int i = row - 1, j = col - 1; i >= 0 && j >=0; i--, j--) {
            if(board[i][j]=='Q'){
                return false;
            }
        }
        return true;
    }

}
