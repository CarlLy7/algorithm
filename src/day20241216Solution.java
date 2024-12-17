import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: carl
 * @date: 2024/12/16
 */

public class day20241216Solution {

    //698
    HashMap<Integer, Boolean> memo = new HashMap<>();

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        sum = sum / k;
        int used = 0;
        return backTrack(k, 0, nums, 0, used, sum);
    }

    // 剩余k个桶，当前桶的大小为bucket,从nums[start]选择是否能凑成target
    private boolean backTrack(int k, int bucket, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            boolean res = backTrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) {
            return memo.get(used);
        }
        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) {
                continue;
            }
            if (bucket + nums[i] > target) {
                continue;
            }
            used |= 1 << i;
            bucket += nums[i];
            if (backTrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            used ^= 1 << i;
            bucket -= nums[i];
        }
        return false;
    }


    //473
    public boolean makesquare(int[] matchsticks) {
        return canPartitionKSubsets(matchsticks, 4);
    }

    //79
//    boolean res=false;
//    public boolean exist(char[][] board,String word){
//        int m=board.length;
//        int n=board[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                dfs(board,i,j,word,0);
//                if (res){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private void dfs(char[][] board, int i, int j, String word, int start) {
//        if (res){
//            return;
//        }
//        if (start==word.length()){
//            res=true;
//            return;
//        }
//        int m=board.length;
//        int n=board[0].length;
//        if (i<0 || j<0 || i>=m || j>=n){
//            return;
//        }
//        if (board[i][j]!=word.charAt(start)){
//            return;
//        }
//        // 对于已经选择的路径，添加负号，防止走回头路
//        board[i][j]= (char) -board[i][j];
//        dfs(board,i+1,j,word,start+1);
//        dfs(board,i-1,j,word,start+1);
//        dfs(board,i,j-1,word,start+1);
//        dfs(board,i,j+1,word,start+1);
//        board[i][j]= (char) -board[i][j];
//    }

    //17
    // 路径，选择列表
//    List<String> res = new ArrayList<>();
//    StringBuilder track = new StringBuilder();
//
//    public List<String> letterCombinations(String digits) {
//        if (digits.length() == 0) {
//            return res;
//        }
//        String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//        backTrack(digits, 0, mapping);
//        return res;
//    }
//
//    private void backTrack(String digits, int start, String[] mapping) {
//        // base case
//        if (track.length() == digits.length()) {
//            res.add(track.toString());
//            return;
//        }
//        int index = digits.charAt(start) - '0';
//        for (char d : mapping[index].toCharArray()) {
//            track.append(d);
//            backTrack(digits, start + 1, mapping);
//            track.deleteCharAt(track.length() - 1);
//        }
//    }


    //89
//    List<Integer> res=new ArrayList<>();
//    LinkedList<Integer> track=new LinkedList<>();
//    HashSet<Integer> used=new HashSet<>();
//    public List<Integer> grayCode(int n){
//        backTrack(0,n);
//        return res;
//    }
//
//    private void backTrack(int root, int n) {
//        if (res.size()!=0){
//            return;
//        }
//        if (track.size()==(1<<n)){
//            res.addAll(track);
//            return;
//        }
//        if (used.contains(root)){
//            return;
//        }
//        used.add(root);
//        track.addLast(root);
//        for (int i = 0; i < n; i++) {
//            int next=flipBit(root,i);
//            backTrack(next,n);
//        }
//        used.remove(root);
//        track.removeLast();
//    }
//
//    private int flipBit(int x, int i) {
//        return x ^ (1<<i);
//    }


}
