package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 重建二叉树 https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
 * @author: lyq
 * @createDate: 13/5/2023
 * @version: 1.0
 */
public class zhongJianErChaShuLcof {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    Map<Integer,Integer> indexMap=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i],i);
        }
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inorderStart, int inorderEnd) {
        if(preStart>preEnd){
            return null;
        }
        int rootVal=preorder[preStart];
        TreeNode root=new TreeNode(rootVal);
        Integer index = indexMap.get(rootVal);
        int leftSize=index-inorderStart;
        root.left=build(preorder,preStart+1,preStart+leftSize,inorder,inorderStart,index-1);
        root.right=build(preorder,preStart+leftSize+1,preEnd,inorder,index+1,inorderEnd);
        return root;
    }
}
