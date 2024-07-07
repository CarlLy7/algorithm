package day20240707;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-07-07 19:35
 * @version: 1.0
 */
public class Solution {
    //    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode() {
//        }
//
//        TreeNode(int val) {
//            this.val = val;
//        }
//
//        TreeNode(int val, TreeNode left, TreeNode right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//    }
//
//    int res = Integer.MIN_VALUE;
//
//    public int maxPathSum(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        oneSize(root);
//        return res;
//    }
//
//    private int oneSize(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        int leftMax = Math.max(0, oneSize(root.left));
//        int rightMax = Math.max(0, oneSize(root.right));
//        int pathMax = leftMax + rightMax + root.val;
//        res = Math.max(res, pathMax);
//        return Math.max(leftMax, rightMax) + root.val;
//    }
//
//    List<List<Integer>> ans = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> permute(int[] nums) {
//        used = new boolean[nums.length];
//        backTrack(nums);
//        return ans;
//    }
//
//    private void backTrack(int[] nums) {
//        // base case
//        if (track.size() == nums.length) {
//            ans.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            used[i] = true;
//            track.add(nums[i]);
//            backTrack(nums);
//            used[i] = false;
//            track.removeLast();
//        }
//    }
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        res.add(new ArrayList<>(track));
//        for (int i = start; i < nums.length; i++) {
//            track.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            track.removeLast();
//        }
//    }
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return res;
        }
        String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTrack(map, digits, 0);
        return res;
    }

    private void backTrack(String[] map, String digits, int start) {
        if (start == digits.length()) {
            res.add(sb.toString());
            return;
        }
        int index = digits.charAt(start) - '0';
        for (char c : map[index].toCharArray()) {
            sb.append(c);
            backTrack(map, digits, start + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
