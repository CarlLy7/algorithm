import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: lyq
 * @createDate: 4/4/2023
 * @version: 1.0
 */
public class testdemo {
//    //不包含重复元素，并且元素只能用一次
//    List<List<Integer>> res=new LinkedList<>();
//    LinkedList<Integer> track=new LinkedList<>();
//    boolean[] visited;
//    public List<List<Integer>> permute(int[] nums) {
//        visited=new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        if(track.size()==nums.length){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            if(visited[i]){
//                continue;
//            }
//            track.addLast(nums[i]);
//            visited[i]=true;
//            backTrack(nums);
//            track.removeLast();
//            visited[i]=false;
//        }
//    }

    //===============================================================================
    //N皇后
//    List<List<String>> res = new LinkedList<>();
//
//    public List<List<String>> solveNQueens(int n) {
//        char[][] boards = new char[n][n];
//        for (char[] board : boards) {
//            Arrays.fill(board, '.');
//        }
//        backTrack(boards, 0);
//        return res;
//    }
//
//    private void backTrack(char[][] boards, int row) {
//        if (row == boards.length) {
//            List<String> list = Arrays.stream(boards).map(item -> {
//                return String.valueOf(item);
//            }).collect(Collectors.toList());
//            res.add(list);
//            return;
//        }
//        for (int col = 0; col < boards.length; col++) {
//            if (!isValid(boards, row, col)) {
//                continue;
//            }
//            boards[row][col] = 'Q';
//            backTrack(boards, row + 1);
//            boards[row][col] = '.';
//        }
//    }
//
//    private boolean isValid(char[][] boards, int row, int col) {
//        //同一列
//        for (int i = 0; i <= row; i++) {
//            if (boards[i][col] == 'Q') {
//                return false;
//            }
//        }
//        //右上方
//        for (int i = row - 1, j = col + 1; i >= 0 && j < boards.length; i--, j++) {
//            if (boards[i][j] == 'Q') {
//                return false;
//            }
//        }
//        //左上方
//        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
//            if (boards[i][j] == 'Q') {
//                return false;
//            }
//        }
//        return true;
//    }

    //=======================================================
//      public class TreeNode {
//      int val;
//      TreeNode left;
//      TreeNode right;
//      TreeNode() {}
//      TreeNode(int val) { this.val = val; }
//      TreeNode(int val, TreeNode left, TreeNode right) {
//          this.val = val;
//          this.left = left;
//          this.right = right;
//      }
//  }
//    HashSet<Integer> set=new HashSet<>();
//    int sum=0;
//    public boolean hasPathSum(TreeNode root, int targetSum) {
//        dfs(root);
//        return set.contains(targetSum);
//    }
//
//    private void dfs(TreeNode root) {
//        if(root==null){
//            return;
//        }
//        //前序位置
//        sum+=root.val;
//        if(root.left==null&& root.right==null){
//            //到达右子节点了。将sum放到set中
//            set.add(sum);
//        }
//        //遍历左孩子
//        dfs(root.left);
//        //遍历右孩子
//        dfs(root.right);
//        //后序位置
//        sum-=root.val;
//    }

    //=======================================
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        //前序位置，每个节点的值都是一个子集
//        res.add(new LinkedList<>(track));
//        for (int i = start; i < nums.length; i++) {
//            track.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            track.removeLast();
//        }
//    }

    //=================================================
//    List<List<Integer>> res=new LinkedList<>();
//    LinkedList<Integer> track=new LinkedList<>();
//    public List<List<Integer>> combine(int n, int k) {
//        backTrack(n,k,1);
//        return res;
//    }
//
//    private void backTrack(int n,int k, int start) {
//        if(track.size()==k){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = start; i <=n ; i++) {
//            track.addLast(i);
//            backTrack(n,k,i+1);
//            track.removeLast();
//        }
//    }

    //===================================
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    int sum = 0;
//
//    public List<List<Integer>> combinationSum3(int k, int n) {
//        backTrack(k, n, 1);
//        return res;
//    }
//
//    private void backTrack(int k, int n, int start) {
//        if (sum == n && track.size() == k) {
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = start; i <= 9; i++) {
//            track.addLast(i);
//            sum+=i;
//            backTrack(k,n,i+1);
//            track.removeLast();
//            sum-=i;
//        }
//
//    }

    //===============================================
//    List<List<Integer>> res=new LinkedList<>();
//    LinkedList<Integer> track=new LinkedList<>();
//    boolean[] visited;
//    public List<List<Integer>> permute(int[] nums) {
//        visited=new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        if(track.size()==nums.length){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if(visited[i]){
//                continue;
//            }
//            track.addLast(nums[i]);
//            visited[i]=true;
//            backTrack(nums);
//            track.removeLast();
//            visited[i]=false;
//        }
//    }

    //=========================================================
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        //先排序，将相邻的两个相等的树枝剪掉后面的，只访问一个
//        Arrays.sort(nums);
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        res.add(new LinkedList<>(track));
//        for (int i = start; i < nums.length; i++) {
//            if (i > start && nums[i] == nums[i - 1]) {
//                continue;
//            }
//            track.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            track.removeLast();
//        }
//    }

    //==========================================
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    int sum=0;
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        backTrack(candidates,0,target);
//        return res;
//    }
//
//    private void backTrack(int[] candidates, int start,int target) {
//        if(sum==target){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        //超过目标和的话，后面的就不需要再进行一个遍历了，直接退出，否则的话超出时间限制
//        if(sum>target){
//            return;
//        }
//        for (int i = start; i <candidates.length ; i++) {
//            if(i>start && candidates[i]==candidates[i-1]){
//                continue;
//            }
//            track.addLast(candidates[i]);
//            sum+=candidates[i];
//            backTrack(candidates,i+1,target);
//            track.removeLast();
//            sum-=candidates[i];
//        }
//    }
    //================================================
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] visited;
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        Arrays.sort(nums);
//        visited=new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        if(track.size()==nums.length){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if(visited[i]){
//                return;
//            }
//            //如果前面的那个树枝还没有被访问的话，这次不能访问，要剪掉
//            if(i>0 && nums[i]==nums[i-1] && !visited[i-1]){
//                continue;
//            }
//            track.addLast(nums[i]);
//            visited[i]=true;
//            backTrack(nums);
//            track.removeLast();
//            visited[i]=false;
//        }
//    }

    //======================================================
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    int sum=0;
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        backTrack(candidates,0,target);
//        return res;
//    }
//
//    private void backTrack(int[] candidates, int start, int target) {
//        if(sum==target){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        if(sum>target){
//            return;
//        }
//        for (int i = start; i < candidates.length ; i++) {
//            track.addLast(candidates[i]);
//            sum+=candidates[i];
//            backTrack(candidates,i,target);
//            track.removeLast();
//            sum-=candidates[i];
//        }
//    }
    //===============================================
//    List<List<String>> res = new LinkedList<>();
//    LinkedList<String> track = new LinkedList<>();
//    public List<List<String>> partition(String s) {
//        backTrack(s,0);
//        return res;
//    }
//
//    private void backTrack(String s, int start) {
//        if(start==s.length()){
//            res.add(new LinkedList<>(track));
//            return;
//        }
//        for (int i = start; i <s.length() ; i++) {
//            if(!isHuiWen(s,start,i)){
//                //如果不是回文串我们不能进行分隔，就跳过
//                continue;
//            }
//            //是回文串就进行分隔
//            track.addLast(s.substring(start,i+1));
//            backTrack(s,i+1);
//            track.removeLast();
//        }
//    }
//
//    private boolean isHuiWen(String s, int start, int end) {
//        while(start<end){
//            if(s.charAt(start)!=s.charAt(end)){
//                return false;
//            }
//            end--;
//            start++;
//        }
//        return true;
//    }
    //===============================================
//    List<String> res = new LinkedList<>();
//    //定义数字和映射的关系
//    String[] mapping=new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
//    public List<String> letterCombinations(String digits) {
//        if(digits.isEmpty()){
//            return res;
//        }
//        backTrack(digits,0,new StringBuilder());
//        return res;
//    }
//    //start是digits中的起始位置
//    private void backTrack(String digits, int start, StringBuilder stringBuilder) {
//        if(stringBuilder.length()==digits.length()){
//            res.add(stringBuilder.toString());
//            return;
//        }
//        for (int i = start; i <digits.length() ; i++) {
//            int location = digits.charAt(start) - '0';
//            for (char c : mapping[location].toCharArray()) {
//                stringBuilder.append(c);
//                backTrack(digits,i+1,stringBuilder);
//                stringBuilder.deleteCharAt(stringBuilder.length()-1);
//            }
//        }
//    }

    //===============================================
    //这个问题就是有重复元素，但是不可以重复选择的排列问题
//    List<String> res=new LinkedList<>();
//    StringBuilder track=new StringBuilder();
//    boolean[] visited;
//    public String[] permutation(String s) {
//       init(s.toCharArray());
//       String[] result=new String[res.size()];
//        for (int i = 0; i < res.size(); i++) {
//            result[i]=res.get(i);
//        }
//        return result;
//    }
//
//    private void init(char[] chars) {
//        visited=new boolean[chars.length];
//        Arrays.sort(chars);
//        backTrack(chars);
//
//    }
//
//    private void backTrack(char[] chars) {
//        if(track.length()==chars.length){
//            res.add(track.toString());
//            return;
//        }
//        for (int i = 0; i < chars.length; i++) {
//            if(visited[i]){
//                continue;
//            }
//            if(i>0 && chars[i]==chars[i-1] && !visited[i-1]){
//                continue;
//            }
//            track.append(chars[i]);
//            visited[i]=true;
//            backTrack(chars);
//            track.deleteCharAt(track.length()-1);
//            visited[i]=false;
//        }
//    }

    //=======================================================================
//    int[][] memo;
//    public int minDistance(String word1, String word2) {
//        int len1=word1.length();
//        int len2=word2.length();
//        memo=new int[len1][len2];
//        for (int[] ints : memo) {
//            Arrays.fill(ints,-1);
//        }
//        return dp(word1,len1-1,word2,len2-1);
//    }
//
//    private int dp(String word1, int len1, String word2, int len2) {
//        //base case
//        if(len1<0){
//            return len2+1;
//        }
//        if(len2<0){
//            return len1+1;
//        }
//        if(memo[len1][len2]!=-1){
//            return memo[len1][len2];
//        }
//        if(word1.charAt(len1)==word2.charAt(len2)){
//            memo[len1][len2]= dp(word1,len1-1,word2,len2-1);
//        }else{
//            memo[len1][len2]= min(dp(word1,len1-1,word2,len2)+1,
//                    dp(word1,len1-1,word2,len2-1)+1,
//                    dp(word1,len1,word2,len2-1)+1);
//        }
//        return memo[len1][len2];
//    }
//
//    private int min(int dp, int dp1, int dp2) {
//        return Math.min(dp,Math.min(dp1,dp2));
//    }

    //=======================================================================
//    public int minDistance(String word1, String word2) {
//        int len1=word1.length();
//        int len2=word2.length();
//        //因为数组不和我们之前的指针一样，指针可以移动到-1，但是数组最小到0，所以我们用第一行和第一列来存储base case
//        int[][] dp=new int[len1+1][len2+1];
//        for (int col = 1; col <= len2; col++) {
//            dp[0][col]=col;
//        }
//        for (int row = 1; row <= len1; row++) {
//            dp[row][0]=row;
//        }
//        for (int i = 1; i <= len1; i++) {
//            for (int j = 1; j <= len2; j++) {
//                if(word1.charAt(i-1)==word2.charAt(j-1)){
//                    dp[i][j]=dp[i-1][j-1];
//                }else{
//                    dp[i][j]=min(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+1);
//                }
//            }
//        }
//        return dp[len1][len2];
//
//    }
//
//    private int min(int i, int i1, int i2) {
//        return Math.min(i,Math.min(i1,i2));
//    }

    //=======================================================================
    //-1是没有计算，0是FALSE，1是true
//    int[][] memo;
//
//    public boolean isInterleave(String s1, String s2, String s3) {
//        int len1 = s1.length();
//        int len2 = s2.length();
//        if (len1 + len2 != s3.length()) {
//            return false;
//        }
//        memo = new int[len1 + 1][len2 + 1];
//        for (int[] ints : memo) {
//            Arrays.fill(ints, -1);
//        }
//        return dp(s1, 0, s2, 0, s3);
//    }
//
//    private boolean dp(String s1, int i, String s2, int j, String s3) {
//        int k = i + j;
//        if (k == s3.length()) {
//            return true;
//        }
//        if (memo[i][j] != -1) {
//            return memo[i][j] == 0 ? false : true;
//        }
//        boolean res = false;
//        if(i<s1.length() && j<s2.length() && s1.charAt(i)==s3.charAt(k) && s2.charAt(j)==s3.charAt(k)){
//            //或运算，只要有一个为true，结果就是true
//            res=(dp(s1,i+1,s2,j,s3) || dp(s1,i,s2,j+1,s3));
//        }else if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
//            res = dp(s1, i + 1, s2, j, s3);
//        }else if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
//            res = dp(s1, i, s2, j + 1, s3);
//        }
//        memo[i][j] = res == false ? 0 : 1;
//        return res;
//    }


    //===============================================
    //算法的思路是找到当前这个数的前面比它小的数的最大递增序列然后加一就可以了
//    public int lengthOfLIS(int[] nums) {
//        int[] dp=new int[nums.length];
//        for (int i = 0; i < dp.length; i++) {
//            //初始化，每个数自己就是一个递增序列，所以初始化都是1
//            dp[i]=1;
//        }
//        int res=0;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if(nums[i]>nums[j]){
//                  dp[i]=Math.max(dp[i],dp[j]+1);
//                }
//            }
//        }
//        for (int i = 0; i < dp.length; i++) {
//            res=Math.max(res,dp[i]);
//        }
//        return res;
//    }

    //=====================================
//    public int minCost(int[][] costs) {
////        int n=costs.length;
////        //dp数组的定义就是 dp[i][j]表示第i个房子粉刷成j对应的颜色的时候花费的最小金额
////        int[][] dp=new int[n][3];
////        //base case 也就是粉刷第一个房子的时候，价格其实都可以确定的
////        for (int j = 0; j < 3; j++) {
////            dp[0][j]=costs[0][j];
////        }
////        for (int i = 1; i <n ; i++) {
////            //相邻的房子因为不能粉刷成相同的颜色，所以前一个房子不能和当前房子的颜色相同
////            //计算出当前这个房子刷三种不同颜色对应的各自的最少的价格
////            dp[i][0]=Math.min(dp[i-1][1],dp[i-1][2])+costs[i][0];
////            dp[i][1]=Math.min(dp[i-1][0],dp[i-1][2])+costs[i][1];
////            dp[i][2]=Math.min(dp[i-1][0],dp[i-1][1])+costs[i][2];
////        }
////        //因为要计算的是最终的最少价格，所以从最后一个房子的三个颜色的花费中选择最小的那个
////        int res=Integer.MAX_VALUE;
////        for (int j = 0; j < 3; j++) {
////            res=Math.min(res,dp[n-1][j]);
////        }
////        return res;
////    }


    //======================================
//    public List<Integer> largestDivisibleSubset(int[] nums) {
//        int n = nums.length;
//        List<Integer>[] dp = new List[n];
//        Arrays.sort(nums);
//        for (int i = 0; i < dp.length; i++) {
//            dp[i] = new ArrayList<>();
//        }
//        //base case就是第一个元素肯定在里面
//        dp[0].add(nums[0]);
//
//        for (int i = 1; i < n; i++) {
//           int maxLen=0,index=-1;
//            for (int j = 0; j < i; j++) {
//                if(nums[i]%nums[j]==0 && dp[j].size()>maxLen){
//                    maxLen=dp[j].size();
//                    index=j;
//                }
//            }
//            if(index!=-1){
//                dp[i].addAll(dp[index]);
//            }
//            dp[i].add(nums[i]);
//        }
//        List<Integer> res=dp[0];
//        for (int i = 1; i <dp.length ; i++) {
//            if(res.size()<dp[i].size()){
//                res=dp[i];
//            }
//        }
//        return res;
//    }

    //===============================
    public static  int constrainedSubsetSum(int[] nums, int k) {
        int[] dp=new int[nums.length];
        //base case
        dp[0]=nums[0];
        for (int i = 1; i < k ; i++) {
            dp[i]=Max(dp[i-1],dp[i-1]+nums[i],nums[i]);
        }
       if(k!=1){
           for (int i = k; i < nums.length ; i++) {
               for (int j = 1; j < k; j++) {
                   dp[i]=Max(dp[i-j],dp[i-j]+nums[i],nums[i]);
               }
           }
       }else{
           for (int i = k; i < nums.length ; i++) {
               dp[i]=Max(dp[i-1],dp[i-1]+nums[i],nums[i]);
           }
       }
        return dp[nums.length-1];
    }

    private static int Max(int i, int i1, int num) {
        return Math.max(i,Math.max(i1,num));
    }

    public static void main(String[] args) {
        int[] nums=new int[]{10,2,-10,5,20};
        int k=2;
        int sum = constrainedSubsetSum(nums, k);
        System.out.println(sum);
    }

}