package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 不同的二叉搜索树 II https://leetcode.cn/problems/unique-binary-search-trees-ii/
 * @author: lyq
 * @createDate: 22/5/2023
 * @version: 1.0
 */
public class uniqueBinarySearchTreesIi {
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

    public List<TreeNode> generateTrees(int n) {
        if(n==0){
            return new LinkedList<>();
        }
        //区间是闭区间[low,high]
        return count(1,n);
    }

    private List<TreeNode> count(int low, int high) {
        LinkedList<TreeNode> res=new LinkedList<>();
        if(low>high){
            res.add(null);
            return res;
        }
        //遍历每个节点作为根节点
        for (int i = low; i <=high ; i++) {
            //递归构造左子树
            List<TreeNode> leftTree = count(low, i - 1);
            List<TreeNode> rightTree = count(i + 1, high);
            //我们要将这个根节点对应的所有的左右子树组合遍历一遍放在res中
            for (TreeNode leftNode : leftTree) {
                for (TreeNode rightNode : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left=leftNode;
                    root.right=rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
