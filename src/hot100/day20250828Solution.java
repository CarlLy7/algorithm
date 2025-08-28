package hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.28
 * @Since: 1.0
 */

public class day20250828Solution {
    // [226] 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        traverse(root.left);
        traverse(root.right);
    }

    // [230] 二叉搜索树中第 K 小的元素
    int count = 0;
    int res;

    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历去
        traverse(root, k);
        return res;
    }

    /**
     * 中序遍历二叉搜索树
     * 
     * @param root
     */
    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return;
        }
        traverse(root.right, k);

    }

    // [234] 回文链表
    // public boolean isPalindrome(ListNode head) {
    // List<Integer> list = new ArrayList<>();
    // ListNode p = head;
    // while (p != null) {
    // list.add(p.val);
    // p = p.next;
    // }
    // int[] res = new int[list.size()];
    // for (int i = 0; i < list.size(); i++) {
    // res[i] = list.get(i);
    // }
    // int left = 0, right = res.length - 1;
    // while (left < right) {
    // if (res[left] != res[right]) {
    // return false;
    // }
    // left++;
    // right--;
    // }
    // return true;
    // }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        ListNode right = reverse(slow);
        ListNode left = head;
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    /**
     * 反转链表
     * 
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = head;
        while (nxt != null) {
            nxt = nxt.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

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
