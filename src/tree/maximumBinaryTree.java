package tree;

/**
 * @description: 最大二叉树 https://leetcode.cn/problems/maximum-binary-tree
 * @author: lyq
 * @createDate: 13/5/2023
 * @version: 1.0
 */
public class maximumBinaryTree {
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

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int index=-1,maxVal=Integer.MIN_VALUE;
        //下面的这个循环就是根据给定的数组的索引范围找到最大值以及最大值的索引下标
        for (int i = low; i <=high ; i++) {
            if(nums[i]>maxVal){
                maxVal=nums[i];
                index=i;
            }
        }
        TreeNode root=new TreeNode(maxVal);
        //递归去左边区间构造左子树
        root.left=buildTree(nums,low,index-1);
        //递归去右边区间构造右子树
        root.right=buildTree(nums,index+1,high);
        return root;
    }
}
