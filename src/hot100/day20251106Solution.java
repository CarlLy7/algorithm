package hot100;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.06
 * @Since: 1.0
 */

public class day20251106Solution {
    // [987] 二叉树的垂序遍历
    // private class TripNode {
    // private int row, col;
    // private TreeNode node;
    //
    // public TripNode(int row, int col, TreeNode node) {
    // this.row = row;
    // this.col = col;
    // this.node = node;
    // }
    // }
    //
    // public List<List<Integer>> verticalTraversal(TreeNode root) {
    // traverse(root, 0, 0);
    // Collections.sort(nodes, (a, b) -> {
    // if (a.col == b.col && a.row == b.row) {
    // return a.node.val - b.node.val;
    // }
    // if (a.col == b.col) {
    // return a.row - b.row;
    // }
    // return a.col - b.col;
    // });
    // LinkedList<List<Integer>> res = new LinkedList<>();
    // int preCol = Integer.MIN_VALUE;
    // for (TripNode node : nodes) {
    // // 如果当前列不相同，就需要创建一个新的列表了
    // if (node.col != preCol) {
    // res.add(new ArrayList<>());
    // preCol = node.col;
    // }
    // res.getLast().add(node.node.val);
    // }
    // return res;
    // }
    //
    // /**
    // * 遍历一遍二叉树将所有的节点存起来
    // *
    // * @param root
    // * @param row
    // * @param col
    // */
    // List<TripNode> nodes = new ArrayList<>();
    //
    // private void traverse(TreeNode root, int row, int col) {
    // if (root == null) {
    // return;
    // }
    // nodes.add(new TripNode(row, col, root));
    // traverse(root.left, row + 1, col - 1);
    // traverse(root.right, row + 1, col + 1);
    // }

    // [993] 二叉树的堂兄弟节点
    // private TreeNode parentX;
    // private TreeNode parentY;
    // private int depthX;
    // private int depthY;
    // private int x;
    // private int y;
    //
    // public boolean isCousins(TreeNode root, int x, int y) {
    // this.x = x;
    // this.y = y;
    // traverse(root, 0, null);
    // if (depthX == depthY && parentX != parentY) {
    // return true;
    // }
    // return false;
    // }
    //
    // private void traverse(TreeNode root, int depth, TreeNode parent) {
    // if (root == null) {
    // return;
    // }
    // if (root.val == x) {
    // parentX = parent;
    // depthX = depth;
    // }
    // if (root.val == y) {
    // parentY = parent;
    // depthY = depth;
    // }
    // traverse(root.left, depth + 1, root);
    // traverse(root.right, depth + 1, root);
    // }

    // [1315] 祖父节点值为偶数的节点和
    private int res = 0;

    public int sumEvenGrandparent(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 对于一个偶数节点，累加孙子节点的值
        if (root.val % 2 == 0) {
            // 累加左子树孙子节点的值
            if (root.left != null) {
                if (root.left.left != null) {
                    res += root.left.left.val;
                }
                if (root.left.right != null) {
                    res += root.left.right.val;
                }
            }
            // 累加右子树孙子节点的值
            if (root.right != null) {
                if (root.right.left != null) {
                    res += root.right.left.val;
                }
                if (root.right.right != null) {
                    res += root.right.right.val;
                }
            }
        }
        traverse(root.left);
        traverse(root.right);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
