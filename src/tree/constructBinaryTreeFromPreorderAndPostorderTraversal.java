package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 根据前序和后序遍历构造二叉树 https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
 * @author: lyq
 * @createDate: 13/5/2023
 * @version: 1.0
 */
public class constructBinaryTreeFromPreorderAndPostorderTraversal {
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

    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            indexMap.put(postorder[i], i);
        }
        return build(preorder,0,preorder.length-1,postorder,0,postorder.length-1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if(preStart>preEnd){
            return null;
        }
        if(preStart==preEnd){
            return new TreeNode(preorder[preStart]);
        }
        int rootVal=preorder[preStart];
        TreeNode root=new TreeNode(rootVal);
        int leftRootVal=preorder[preStart+1];
        int index=indexMap.get(leftRootVal);
        int leftSize=index-postStart+1;
        root.left=build(preorder,preStart+1,preStart+leftSize,postorder,postStart,index-1);
        root.right=build(preorder,preStart+leftSize+1,preEnd,postorder,index+1,postEnd-1);
        return root;
    }
}
