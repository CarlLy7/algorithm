import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.09
 * @Since: 1.0
 */

public class day20250609Solution {
    // [938] 二叉搜索树的范围和
    int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        traverse(root, low, high);
        return sum;
    }

    /**
     * 使用中序遍历，遍历一遍二叉搜索树，然后在中序位置更新一下
     * 
     * @param root
     * @param low
     * @param high
     */
    private void traverse(TreeNode root, int low, int high) {
        if (root == null) {
            return;
        }
        if (root.val < low) {
            traverse(root.right, low, high);
        } else if (root.val > high) {
            traverse(root.left, low, high);
        } else {
            sum += root.val;
            traverse(root.left, low, high);
            traverse(root.right, low, high);
        }
    }

    // [1008] 前序遍历构造二叉搜索树
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, 0, preorder.length - 1);
    }

    /**
     * 从【start,end】这个范围内构造BST
     * 
     * @param preorder
     * @param start
     * @param end
     * @return
     */
    private TreeNode build(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int p = start + 1;
        while (p <= end && preorder[p] < root.val) {
            p++;
        }
        root.left = build(preorder, start + 1, p - 1);
        root.right = build(preorder, p, end);
        return root;
    }

    // [1382] 将二叉搜索树变平衡
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> nums = traverse(root);
        return builds(nums, 0, nums.size() - 1);
    }

    private TreeNode builds(List<Integer> nums, int low, int hi) {
        if (low > hi) {
            return null;
        }
        int mid = low + (hi - low) / 2;
        // 构建根节点
        TreeNode root = new TreeNode(nums.get(mid));
        // [分解子问题]构建左子树
        root.left = builds(nums, low, mid - 1);
        // [分解子问题] 构建右子树
        root.right = builds(nums, mid + 1, hi);
        return root;
    }

    /**
     * 中序遍历BST生成一个有序数组
     * 
     * @param root
     * @return
     */
    private List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(traverse(root.left));
        res.add(root.val);
        res.addAll(traverse(root.right));
        return res;
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
