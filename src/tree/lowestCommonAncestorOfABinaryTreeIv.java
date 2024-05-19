package tree;

import java.util.HashSet;

/**
 * @description: 二叉树的最近公共祖先IV
 * @author: lyq
 * @createDate: 24/5/2023
 * @version: 1.0
 */
public class lowestCommonAncestorOfABinaryTreeIv {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        //使用HashSet来存储nodes列表中的所有节点的值
        HashSet<Integer> set=new HashSet<>(nodes.length);
        for (TreeNode node : nodes) {
            set.add(node.val);
        }
        return find(root,set);
    }

    private TreeNode find(TreeNode root, HashSet<Integer> set) {
        if(root==null){
            return null;
        }
        if(set.contains(root.val)){
            //如果root的值等于set中的值，就直接返回这个节点
            return root;
        }
        //递归处理根节点的左右子树
        TreeNode leftNode = find(root.left, set);
        TreeNode rightNode = find(root.right, set);
        if(leftNode!=null && rightNode!=null){
            //如果左右子树都找到了值，那么root就是最近公共祖先
            return root;
        }
        return leftNode!=null?leftNode:rightNode;
    }
}
