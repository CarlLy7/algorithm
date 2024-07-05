package day20240705;

import java.util.*;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-07-05 16:40
 * @version: 1.0
 */
public class Solution {
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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode lastNode = queue.peek();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
            res.add(lastNode.val);
        }
        return res;
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


    HashMap<Integer, Integer> valueToIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            valueToIndexMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inorderStart, int inorderEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = valueToIndexMap.get(root.val);
        int leftSize = rootIndex - inorderStart;
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inorderStart, rootIndex - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inorderEnd);
        return root;
    }

    int res = 0;
    // 当前路径和
    long pathSum;
    // 目标路径和
    int targetSum;
    HashMap<Long, Integer> pathSumMap = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        this.pathSum = 0;
        this.targetSum = targetSum;
        pathSumMap.put(0L, 1);
        traverse(root);
        return res;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        int curVal = root.val;
        //前序位置的值
        pathSum += curVal;
        res += pathSumMap.getOrDefault(pathSum - targetSum, 0);
        pathSumMap.put(pathSum, pathSumMap.getOrDefault(pathSum, 0) + 1);
        traverse(root.left);
        traverse(root.right);
        //后序位置
        pathSumMap.put(pathSum, pathSumMap.get(pathSum) - 1);
        pathSum -= curVal;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left == null && right == null) {
            return null;
        } else {
            return left == null ? right : left;
        }
    }
}
