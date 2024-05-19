package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 从前序与中序遍历序列构造二叉树 https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @author: lyq
 * @createDate: 13/5/2023
 * @version: 1.0
 */
public class constructBinaryTreeFromPreorderAndInorderTraversal {
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

    Map<Integer,Integer> indexMap=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //首先将中序遍历中的所有的元素以及对应的索引下标放到一个hashMap中去
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i],i);
        }
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        //处理base case
        if(preStart>preEnd){
            return null;
        }
        int rootVal=preorder[preStart];
        TreeNode root=new TreeNode(rootVal);
        //找到这个根节点再中序遍历数组中的索引下标
        Integer inorderIndex = indexMap.get(rootVal);
        int leftSize=inorderIndex-inStart;
        //递归构造左子树
        root.left=build(preorder,preStart+1,preStart+leftSize,inorder,inStart,inorderIndex-1);
        //递归构造右子树
        root.right=build(preorder,preStart+leftSize+1,preEnd,inorder,inorderIndex+1,inEnd);
        return root;
    }
}
