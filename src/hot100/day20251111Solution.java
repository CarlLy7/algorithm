package hot100;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.11
 * @Since: 1.0
 */

public class day20251111Solution {
    // [1145] 二叉树着色游戏
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = find(root, x);
        int leftCount = count(node.left);
        int rightCount = count(node.right);
        int other = n - 1 - leftCount - rightCount;
        return Math.max(leftCount, Math.max(rightCount, other)) > n / 2;
    }

    /**
     * 以root为根的二叉树的节点个数
     * 
     * @param root
     * @return
     */
    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }

    /**
     * 在以root为根的二叉树中找值为x的节点
     * 
     * @param root
     * @param x
     * @return
     */
    private TreeNode find(TreeNode root, int x) {
        if (root == null) {
            return null;
        }
        if (root.val == x) {
            return root;
        }
        TreeNode leftNode = find(root.left, x);
        if (leftNode != null) {
            return leftNode;
        }
        return find(root.right, x);
    }

    // [2096] 从二叉树一个节点到另一个节点每一步的方向
    StringBuilder path = new StringBuilder();
    String startPath, endPath;
    int startValue, endValue;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        this.startValue = startValue;
        this.endValue = destValue;
        traverse(root);
        // 去除startPath和endPath的公共前缀
        int p = 0, m = startPath.length(), n = endPath.length();
        while (p < m && p < n && startPath.charAt(p) == endPath.charAt(p)) {
            p++;
        }
        // 从去掉公共前缀的地方开始
        startPath = startPath.substring(p);
        endPath = endPath.substring(p);
        StringBuilder sb = new StringBuilder();
        // 需要将从根节点到startValue节点的路径变成U
        for (int i = 0; i < startPath.length(); i++) {
            sb.append("U");
        }
        startPath = sb.toString();
        return startPath + endPath;
    }

    /**
     * 从root节点出发到startValue和endValue
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val == startValue) {
            startPath = path.toString();
        }
        if (root.val == endValue) {
            endPath = path.toString();
        }
        path.append("L");
        traverse(root.left);
        path.deleteCharAt(path.length() - 1);

        path.append("R");
        traverse(root.right);
        path.deleteCharAt(path.length() - 1);
    }

    // [572] 另一棵树的子树
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return subRoot == null;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        // 判断左右子树是否有和subRoot一样的
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /**
     * 跟定两个根节点，判断两个树是否相同
     * 
     * @param root
     * @param subRoot
     * @return
     */
    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
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
