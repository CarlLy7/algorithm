import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.30
 * @Since: 1.0
 */

public class day20250630Solution {
    // [1609] 奇偶树
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean even = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = even ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 如果是偶数层
                if (even) {
                    if (pre >= cur.val || cur.val % 2 == 0) {
                        return false;
                    }
                } else {
                    if (pre <= cur.val || cur.val % 2 != 0) {
                        return false;
                    }
                }
                pre = cur.val;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            even = !even;
        }
        return true;
    }

    // [872] 叶子相似的树
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        LeafIterator leafIterator1 = new LeafIterator(root1);
        LeafIterator leafIterator2 = new LeafIterator(root2);
        while (leafIterator1.hasNext() && leafIterator2.hasNext()) {
            if (leafIterator1.next().val != leafIterator2.next().val) {
                return false;
            }
        }

        return !leafIterator1.hasNext() && !leafIterator2.hasNext();
    }

    // 返回叶子节点的迭代器类
    private class LeafIterator {
        private Stack<TreeNode> stack = new Stack<>();

        public LeafIterator(TreeNode root) {
            stack.push(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public TreeNode next() {
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                if (cur.left == null && cur.right == null) {
                    return cur;
                }
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
            return null;
        }
    }

    // [671] 二叉树中第二小的节点

    // 根节点一定是最小的值
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return -1;
        }
        int left = root.left.val;
        int right = root.right.val;
        // 因为根节点的值是最小的值，只需要去左右子树中，找第二小的值就可以了，因为对于左右子树来说，根节点是最小的值
        if (root.val == root.left.val) {
            left = findSecondMinimumValue(root.left);
        }
        // 根节点和右子树的根节点值一样，去右子树中找第二小的值
        if (root.val == root.right.val) {
            right = findSecondMinimumValue(root.right);
        }
        if (left == -1) {
            return right;
        }
        if (right == -1) {
            return left;
        }
        // 如果根节点等于左右子树的值，并且左右子树第二小的值都不为空，那么取更小的值
        return Math.min(left, right);
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
