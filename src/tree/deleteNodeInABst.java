package tree;

/**
 * @description: 删除二叉搜索树中的节点 https://leetcode.cn/problems/delete-node-in-a-bst/
 * @author: lyq
 * @createDate: 20/5/2023
 * @version: 1.0
 */
public class deleteNodeInABst {
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

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            //下面这两个if语句可以解决要删除的节点只有一个分支或者是叶子节点的情况
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            //如果要删除的节点有左右子树的话，我们要让左子树的最大值或者右子树的最小值替换当前这个节点，下面选择右子树中最小值来进行替换
            if (root.left != null && root.right != null) {
                TreeNode minNode = getMinNode(root.right);
                //root的右子树连接删除最小节点的树
                root.right = deleteNode(root.right, minNode.val);
                //右子树最小节点连接当前要删除的root的左右子树
                minNode.left = root.left;
                minNode.right = root.right;
                //替换root和右子树中最小值
                //替换的时候可以将原来的root的左右指针置空
                root.left=null;
                root.right=null;
                root = minNode;
            }
            return root;
        } else if (root.val > key) {
            root.left=deleteNode(root.left, key);
        } else {
            root.right=deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
