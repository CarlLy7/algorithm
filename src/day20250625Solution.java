/**
 * @description:
 * @author: carl
 * @date: 2025.06.25
 * @Since: 1.0
 */

public class day20250625Solution {
    // [951] 翻转等价二叉树
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
            || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }

    // [1145] 二叉树着色游戏
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = findNode(root, x);
        int leftCount = count(node.left);
        int rightCount = count(node.right);
        int other = n - leftCount - rightCount - 1;
        // 如果你选择的节点所在的树的节点个数大于整个树节点个数的一半，那么你才会赢
        return Math.max(leftCount, Math.max(rightCount, other)) > n / 2;
    }

    /**
     * 返回节点个数
     * 
     * @param root
     * @return
     */
    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = count(root.left);
        int rightCount = count(root.right);
        return 1 + leftCount + rightCount;
    }

    /**
     * 找到值为x的节点
     * 
     * @param root
     * @param x
     * @return
     */
    private TreeNode findNode(TreeNode root, int x) {
        if (root == null) {
            return null;
        }
        if (root.val == x) {
            return root;
        }
        TreeNode leftNode = findNode(root.left, x);
        if (leftNode != null) {
            return leftNode;
        }
        TreeNode rightNode = findNode(root.right, x);
        if (rightNode != null) {
            return rightNode;
        }
        return null;
    }

    // [|2096] 从二叉树一个节点到另一个节点每一步的方向

    String startPath, destPath;
    int startValue, destValue;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        this.startValue = startValue;
        this.destValue = destValue;
        traverse(root, startValue, destValue);
        int p = 0, m = startPath.length(), n = destPath.length();
        while (p < m && p < n && startPath.charAt(p) == destPath.charAt(p)) {
            p++;
        }
        // 去除路径中的公共前缀
        startPath = startPath.substring(p);
        destPath = destPath.substring(p);

        // 将startPath中的所有值替换成U
        StringBuilder u = new StringBuilder();
        for (int i = 0; i < startPath.length(); i++) {
            u.append("U");
        }
        startPath = u.toString();
        return startPath + destPath;
    }

    /**
     * 从root开始遍历二叉树，找到到达startValue和destValue的路径
     * 
     * @param root
     * @param startValue
     * @param destValue
     *
     */
    StringBuilder path = new StringBuilder();

    private void traverse(TreeNode root, int startValue, int destValue) {
        if (root == null) {
            return;
        }
        if (root.val == startValue) {
            startPath = path.toString();
        }
        if (root.val == destValue) {
            destPath = path.toString();
        }

        // 类似回溯算法的框架

        // 找左子树
        path.append("L");
        traverse(root.left, startValue, destValue);
        path.deleteCharAt(path.length() - 1);

        // 找右子树
        path.append("R");
        traverse(root.right, startValue, destValue);
        path.deleteCharAt(path.length() - 1);
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
