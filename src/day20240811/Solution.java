package day20240811;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-11 14:37
 * @version: 1.0
 */
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//    int res = 0;
//
//    public int longestConsecutive(TreeNode root) {
//        traverse(root, 1, Integer.MIN_VALUE);
//        return res;
//    }
//
//    private void traverse(TreeNode root, int len, int parentVal) {
//        if (root == null) {
//            return;
//        }
//        if (parentVal + 1 == root.val) {
//            len++;
//        } else {
//            len = 1;
//        }
//        res = Math.max(res, len);
//        traverse(root.left, len, root.val);
//        traverse(root.right, len, root.val);
//    }

//    StringBuilder track = new StringBuilder();
//    String res = null;
//
//    public String smallestFromLeaf(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        // 到达叶子节点了
//        if (root.left == null && root.right == null) {
//            track.append((char) ('a' + root.val));
//            String str = track.reverse().toString();
//            if (res == null || res.compareTo(str) > 0) {
//                res = str;
//            }
//            track.reverse();
//            track.deleteCharAt(track.length() - 1);
//            return;
//        }
//        //前序位置
//        track.append((char) ('a' + root.val));
//        traverse(root.left);
//        traverse(root.right);
//        track.deleteCharAt(track.length() - 1);
//    }

//    int res = 0;
//    int track = 0;
//
//    public int sumRootToLeaf(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        if (root.left == null && root.right == null) {
//            res += track << 1 | root.val;
//            return;
//        }
//        track = track << 1 | root.val;
//        traverse(root.left);
//        traverse(root.right);
//        track = track >> 1;
//    }

//    int res = 0;
//    int[] path = new int[10];
//
//    public int pseudoPalindromicPaths(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        if (root.left == null && root.right == null) {
//            path[root.val]++;
//            if (isValid(path)) {
//                res++;
//            }
//            path[root.val]--;
//            return;
//        }
//        path[root.val]++;
//        traverse(root.left);
//        traverse(root.right);
//        path[root.val]--;
//    }
//
//    private boolean isValid(int[] nums) {
//        int danNum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] % 2 == 0) {
//                continue;
//            } else {
//                danNum++;
//                if (danNum > 1) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
