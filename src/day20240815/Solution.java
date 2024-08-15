package day20240815;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-15 13:37
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


//    int sum = 0;
//
//    public int sumEvenGrandparent(TreeNode root) {
//        traverse(root);
//        return sum;
//    }
//
//    // 判断当前节点是不是偶数，如果是累加孙子节点的值
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        if (root.val % 2 == 0) {
//            if (root.left != null) {
//                if (root.left.left != null) {
//                    sum += root.left.left.val;
//                }
//                if (root.left.right != null) {
//                    sum += root.left.right.val;
//                }
//            }
//            if (root.right != null) {
//                if (root.right.left != null) {
//                    sum += root.right.left.val;
//                }
//                if (root.right.right != null) {
//                    sum += root.right.right.val;
//                }
//            }
//        }
//        traverse(root.left);
//        traverse(root.right);
//    }

//    int res = 0;
//
//
//    public int goodNodes(TreeNode root) {
//        traverse(root, root.val);
//        return res;
//    }
//
//    private void traverse(TreeNode root, int pathMax) {
//        if (root == null) {
//            return;
//        }
//        if (pathMax <= root.val) {
//            res++;
//        }
//        pathMax = Math.max(root.val, pathMax);
//        traverse(root.left, pathMax);
//        traverse(root.right, pathMax);
//    }

    TreeNode res = null;
    int targetDepth = -1;

    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        traverse(root, 0, u.val);
        return res;
    }

    private void traverse(TreeNode root, int depth, int targetVal) {
        if (root == null || res != null) {
            return;
        }
        if (root.val == targetVal) {
            targetVal = depth;
        } else if (depth == targetDepth) {
            res = root;
            return;
        }
        traverse(root.left, depth + 1, targetVal);
        traverse(root.right, depth + 1, targetVal);
    }
}
