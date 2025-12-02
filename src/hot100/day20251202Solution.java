package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.02
 * @Since: 1.0
 */

public class day20251202Solution {
    // [1008] 前序遍历构造二叉搜索树
    // public TreeNode bstFromPreorder(int[] preorder) {
    // return build(preorder, 0, preorder.length - 1);
    // }
    //
    // private TreeNode build(int[] preorder, int start, int end) {
    // if (start > end) {
    // return null;
    // }
    // int rootVal = preorder[start];
    // TreeNode root = new TreeNode(rootVal);
    // int p = start + 1;
    // while (p <= end && preorder[p] < rootVal) {
    // p++;
    // }
    // root.left = build(preorder, start + 1, p - 1);
    // root.right = build(preorder, p, end);
    // return root;
    // }

    // [108] 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        int rootVal = nums[mid];
        TreeNode root = new TreeNode(rootVal);
        root.left = build(nums, start, mid - 1);
        root.right = build(nums, mid + 1, end);
        return root;
    }

    // [1382] 将二叉搜索树变平衡
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> arr = traverse(root);
        int[] nums = new int[arr.size()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr.get(i);
        }
        return build(nums, 0, nums.length - 1);
    }

    /**
     * 中序遍历二叉树
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
