package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.29
 * @Since: 1.0
 */

public class day20251029Solution {
    // [701] 二叉搜索树中的插入操作
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    // [450] 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            // 下面两个if可以处理两种情况： 1.当前节点没有子节点，直接删除 2.当前节点只有一个子节点，来接替自己就可以
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            // 情况三：同时有左右子树，需要将左子树的最大值或者右子树的最小值来替换root,下面用的右子树的最小值
            // 右子树最小的节点
            TreeNode minNode = getMin(root.right);
            // 删除右子树最小节点
            root.right = deleteNode(root.right, minNode.val);
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        }
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    /**
     * BST中寻找最小节点
     * 
     * @param root
     * @return
     */
    private TreeNode getMin(TreeNode root) {
        TreeNode p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    // [700] 二叉搜索树中的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
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
