package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.03
 * @Since: 1.0
 */

public class day20251103Solution {
    // [988] 从叶结点开始的最小字符串
    // String res;
    // StringBuilder path = new StringBuilder();
    //
    // public String smallestFromLeaf(TreeNode root) {
    // traverse(root);
    // return res;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // // 当前节点是叶子结点
    // if (root.left == null && root.right == null) {
    // path.append((char)('a' + root.val));
    // path.reverse();
    // if (res == null || res.compareTo(path.toString()) > 0) {
    // res = path.toString();
    // }
    // path.reverse();
    // path.deleteCharAt(path.length() - 1);
    // return;
    // }
    // path.append((char)('a' + root.val));
    // traverse(root.left);
    // traverse(root.right);
    // path.deleteCharAt(path.length() - 1);
    // }

    // [1022] 从根到叶的二进制数之和
    // int res = 0;
    // int path = 0;
    //
    // public int sumRootToLeaf(TreeNode root) {
    // traverse(root);
    // return res;
    // }
    //
    // private void traverse(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // if (root.left == null && root.right == null) {
    // res += path << 1 | root.val;
    // return;
    // }
    // path = path << 1 | root.val;
    // traverse(root.left);
    // traverse(root.right);
    // path = path >> 1;
    // }

    // [1457] 二叉树中的伪回文路径
    int[] count = new int[10];
    int res = 0;

    public int pseudoPalindromicPaths(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            count[root.val]++;
            // 记录奇数的个数
            int con = 0;
            for (int num : count) {
                if (num % 2 != 0) {
                    con++;
                }
            }
            // 判断一组数字能否组成回文串，只需要判断出现次数为奇数的数字的个数，如果小于等于1个，那么就是可以组成回文串的
            if (con <= 1) {
                res++;
            }
            count[root.val]--;
            return;
        }
        count[root.val]++;
        traverse(root.left);
        traverse(root.right);
        // 回退节点必须要操作
        count[root.val]--;
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
