import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.18
 * @Since: 1.0
 */

public class day20250418Solution {

    // 108 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    /**
     * 在闭区间[left,right]范围内构建一颗二叉平衡搜索树<br>
     * 二叉搜索树的中序遍历就是递增的数组了，而且还要求是平衡树，所以只需要找到中间节点，左边就是左子树，右边是右子树
     * 
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归构造左子树
        root.left = build(nums, left, mid - 1);
        // 递归构造右子树
        root.right = build(nums, mid + 1, right);
        return root;
    }

    // 114 二叉树展开为链表
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 拉平左子树
        flatten(root.left);
        // 拉平右子树
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        // 将右子树拼接在左子树下面
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }


    // 118 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) {
            return res;
        }
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        res.add(firstRow);
        for (int i = 2; i <= numRows; i++) {
            // 上一行
            List<Integer> preRow = res.get(res.size() - 1);
            res.add(generateNextRow(preRow));
        }
        return res;
    }

    /**
     * 根据上一行的数据生成当前行
     * 
     * @param preRow 上一行数据
     * @return
     */
    private List<Integer> generateNextRow(List<Integer> preRow) {
        List<Integer> curRow = new ArrayList<>();
        curRow.add(1);
        for (int i = 0; i < preRow.size() - 1; i++) {
            curRow.add(preRow.get(i) + preRow.get(i + 1));
        }
        curRow.add(1);
        return curRow;
    }

    public class TreeNode {
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
