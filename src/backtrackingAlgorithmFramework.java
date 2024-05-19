import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @description: 回溯算法框架
 * @author: lyq
 * @createDate: 17/5/2023
 * @version: 1.0
 */
public class backtrackingAlgorithmFramework {
    //全排列
//    List<List<Integer>> res=new LinkedList<>();
//    LinkedList<Integer> track=new LinkedList<>();
//    boolean[] visited;
//    public List<List<Integer>> permute(int[] nums) {
//        visited=new boolean[nums.length];
//        track(nums);
//        return res;
//    }
//
//    private void track(int[] nums) {
//        if(track.size()== nums.length){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if(visited[i]){
//                continue;
//            }
//            track.addLast(nums[i]);
//            visited[i]=true;
//            track(nums);
//            track.removeLast();
//            visited[i]=false;
//        }
//    }

    //N皇后问题
    List<List<String>> res=new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] boards=new char[n][n];
        //构造棋盘
        for (char[] board : boards) {
            Arrays.fill(board,'.');
        }
        backTrack(boards,0);
        return res;
    }

    private void backTrack(char[][] boards, int row) {
        int n=boards.length;
        if(row==n){
            List<String> str = Arrays.stream(boards).map(item -> {
                return String.valueOf(item);
            }).collect(Collectors.toList());
            res.add(str);
            return;
        }
        int col=boards[row].length;
        for (int i = 0; i < col; i++) {
            if(!isValid(boards,row,i)){
                continue;
            }
            boards[row][i]='Q';
            backTrack(boards,row+1);
            boards[row][i]='.';
        }
    }

    private boolean isValid(char[][] boards, int row, int col) {
        //上
        for (int i = 0; i < row; i++) {
            if(boards[i][col]=='Q'){
                return false;
            }
        }
        //右上
        for (int i = row-1,j=col+1; i >=0 && j<boards[i].length ; i--,j++) {
            if(boards[i][j]=='Q'){
                return false;
            }
        }
        //左上
        for (int i = row-1,j=col-1; i >=0 && j>=0 ; i--,j--) {
            if(boards[i][j]=='Q'){
                return false;
            }
        }
        return true;
    }

}
