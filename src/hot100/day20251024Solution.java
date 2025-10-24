package hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.24
 * @Since: 1.0
 */

public class day20251024Solution {
    // [654] 最大二叉树
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);
        return root;
    }

    // [105] 从前序与中序遍历序列构造二叉树
    // Map<Integer, Integer> valToIndex = new HashMap<>();
    //
    // public TreeNode buildTree(int[] preorder, int[] inorder) {
    // for (int i = 0; i < inorder.length; i++) {
    // valToIndex.put(inorder[i], i);
    // }
    // return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    // }
    //
    // private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
    // if (preStart > preEnd || inStart > inEnd) {
    // return null;
    // }
    // int rootVal = preorder[preStart];
    // TreeNode root = new TreeNode(rootVal);
    // Integer inIndex = valToIndex.get(rootVal);
    // int size = inIndex - inStart;
    // TreeNode leftRoot = buildTree(preorder, preStart + 1, preStart + size, inorder, inStart, inIndex - 1);
    // TreeNode rightRoot = buildTree(preorder, preStart + size + 1, preEnd, inorder, inIndex + 1, inEnd);
    // root.left = leftRoot;
    // root.right = rightRoot;
    // return root;
    // }

    // [106] 从中序与后序遍历序列构造二叉树
    Map<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int inStrart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStrart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        Integer rootIndex = valToIndex.get(rootVal);
        int size = rootIndex - inStrart;
        TreeNode left = build(inorder, inStrart, rootIndex - 1, postorder, postStart, postStart + size - 1);
        TreeNode right = build(inorder, rootIndex + 1, inEnd, postorder, postStart + size, postEnd - 1);
        root.left = left;
        root.right = right;
        return root;
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
