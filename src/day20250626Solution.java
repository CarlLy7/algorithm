import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.26
 * @Since: 1.0
 */

public class day20250626Solution {
    // [559] N 叉树的最大深度
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int subMax = 0;
        for (Node child : root.children) {
            int depthI = maxDepth(child);
            subMax = Math.max(depthI, subMax);
        }
        return subMax + 1;
    }

    // [617] 合并二叉树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    // [1379] 找出克隆二叉树中的相同节点
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || cloned == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode leftTargetCopy = getTargetCopy(original.left, cloned.left, target);
        if (leftTargetCopy != null) {
            return leftTargetCopy;
        }
        TreeNode rightTargetCopy = getTargetCopy(original.right, cloned.right, target);
        if (rightTargetCopy != null) {
            return rightTargetCopy;
        }
        return null;
    }

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

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
