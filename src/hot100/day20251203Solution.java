package hot100;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.03
 * @Since: 1.0
 */

public class day20251203Solution {
    // [449] 序列化和反序列化二叉搜索树
    private class Codec {
        private final String SEP = ",";

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            // 前序遍历进行序列化
            sb.append(root.val).append(SEP);
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            // 记录前序遍历的顺序
            LinkedList<Integer> nums = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nums.offer(Integer.parseInt(s));
            }
            return deserialize(nums, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        /**
         * 在范围[minValue,maxValue]中根据前序遍历顺序构造二叉搜索树
         * 
         * @param nums
         * @param minValue
         * @param maxValue
         * @return
         */
        private TreeNode deserialize(LinkedList<Integer> nums, int minValue, int maxValue) {
            if (nums.isEmpty()) {
                return null;
            }
            int rootVal = nums.getFirst();
            // 当前这个节点不在这个范围内
            if (rootVal < minValue || rootVal > maxValue) {
                return null;
            }
            TreeNode root = new TreeNode(rootVal);
            nums.removeFirst();
            root.left = deserialize(nums, minValue, rootVal);
            root.right = deserialize(nums, rootVal, maxValue);
            return root;
        }
    }

    // [109] 有序链表转换二叉搜索树
    ListNode cur;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        cur = head;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        return inorderBuild(0, len - 1);
    }

    /**
     * 利用中序遍历进行转换
     * 
     * @param left
     * @param right
     * @return
     */
    private TreeNode inorderBuild(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        // 构造左子树
        TreeNode leftNode = inorderBuild(left, mid - 1);
        // 构造根节点
        TreeNode root = new TreeNode(cur.val);
        cur = cur.next;
        // 构造右子树
        TreeNode rightNode = inorderBuild(mid + 1, right);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    // [173] 二叉搜索树迭代器
    private class BSTIterator {
        private Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            pushLeftBranch(root);
        }

        /**
         * 给一个根节点，遍历所有左分支
         * 
         * @param root
         */
        private void pushLeftBranch(TreeNode root) {
            if (root == null) {
                return;
            }
            TreeNode p = root;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode result = stack.pop();
            pushLeftBranch(result.right);
            return result.val;
        }
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
