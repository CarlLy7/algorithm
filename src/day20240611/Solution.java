package day20240611;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-11 19:28
 * @version: 1.0
 */
public class Solution {
    boolean[][] used;
    public int countBattleships(char[][] board) {
        int m=board.length;
        int n=board[0].length;
        used=new boolean[m][n];
        int res=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (used[i][j]){
                    continue;
                }
                if (board[i][j]=='X'){
                    used[i][j]=true;
                    res++;
                    traverse(board,i,j);
                }
            }
        }
        return res;
    }

    private void traverse(char[][] board, int i, int j) {
        // 遍历行
        for (int row = i; row <board.length ; row++) {
            if (board[row][j]=='X'){
                used[row][j]=true;
            }else{
                break;
            }
        }
        // 遍历列
        for (int col = j; col < board[0].length; col++) {
            if (board[i][col]=='X'){
                used[i][col]=true;
            }else{
                break;
            }
        }
    }
}
