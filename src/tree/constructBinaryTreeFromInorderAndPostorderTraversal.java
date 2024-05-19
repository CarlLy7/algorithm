package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 从中序与后序遍历序列构造二叉树 https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * @author: lyq
 * @createDate: 13/5/2023
 * @version: 1.0
 */
public class constructBinaryTreeFromInorderAndPostorderTraversal {
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

    Map<Integer,Integer> map=new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    private TreeNode build(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postStart, int postEnd) {
        if(inorderStart>inorderEnd){
            return null;
        }
        int rootVal=postorder[postEnd];
        TreeNode root=new TreeNode(rootVal);
        Integer index = map.get(rootVal);
        int leftSize=index-inorderStart;
        root.left=build(inorder,inorderStart,index-1,postorder,postStart,postStart+leftSize-1);
        root.right=build(inorder,index+1,inorderEnd,postorder,postStart+leftSize,postEnd-1);
        return root;
    }
}
