import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.22
 * @Since: 1.0
 */

public class day20250522Solution {
    // 109 有序链表转换二叉搜索树
    ListNode cur;

    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        for (ListNode p = head; p != null; p = p.next) {
            len++;
        }
        cur = head;
        return inorderBuild(0, len - 1);
    }

    /**
     * 使用中序遍历来构造树
     * 
     * @param left
     * @param right
     * @return
     */
    private TreeNode inorderBuild(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode leftNode = inorderBuild(left, mid - 1);
        TreeNode root = new TreeNode(cur.val);
        cur = cur.next;
        TreeNode rightNode = inorderBuild(mid + 1, right);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    // 110 平衡二叉树
    boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return isBalanced;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // 119 杨辉三角II
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        if (rowIndex == 0) {
            return curRow;
        }
        List<Integer> preRow = getRow(rowIndex - 1);
        for (int i = 0; i < preRow.size() - 1; i++) {
            curRow.add(preRow.get(i) + preRow.get(i + 1));
        }
        curRow.add(1);
        return curRow;
    }

    // 118 杨辉三角I
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1) {
            List<List<Integer>> res = new LinkedList<>();
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            res.add(firstRow);
            return res;
        }
        // 递归生成row-1行的杨辉三角
        List<List<Integer>> generate = generate(numRows - 1);
        // 得到row-1行杨辉三角的最后一行
        List<Integer> preRow = generate.get(generate.size() - 1);

        List<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        for (int i = 0; i < preRow.size() - 1; i++) {
            curRow.add(preRow.get(i) + preRow.get(i + 1));
        }
        curRow.add(1);
        generate.add(curRow);
        return generate;
    }

    public class ListNode {
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

    public class TreeNode {
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
