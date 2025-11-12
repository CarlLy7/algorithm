package hot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.12
 * @Since: 1.0
 */

public class day20251112Solution {
    // [1367] 二叉树中的链表
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        if (head.val == root.val) {
            if (check(head, root)) {
                return true;
            }
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean check(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        if (head.val != root.val) {
            return false;
        }
        return check(head.next, root.left) || check(head.next, root.right);
    }

    // [1110] 删点成林
    HashSet<Integer> set = new HashSet<>();
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null)
            return new ArrayList<>();
        for (int del : to_delete) {
            set.add(del);
        }
        doDel(root, false);
        return res;
    }

    /**
     * 删除指定节点并且返回根节点
     * 
     * @param root
     * @param hasParent
     * @return
     */
    private TreeNode doDel(TreeNode root, boolean hasParent) {
        if (root == null)
            return null;
        boolean delete = set.contains(root.val);
        // 如果不需要被删除，并且没有父节点，那么就直接加入
        if (!delete && !hasParent) {
            res.add(root);
        }
        root.left = doDel(root.left, !delete);
        root.right = doDel(root.right, !delete);
        return delete ? null : root;
    }

    // [100] 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // ****************************
    private class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
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
