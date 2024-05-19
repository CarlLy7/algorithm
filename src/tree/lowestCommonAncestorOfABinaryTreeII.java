package tree;

/**
 * @description: 二叉树的最近公共祖先 II
 * @author: lyq
 * @createDate: 24/5/2023
 * @version: 1.0
 */
public class lowestCommonAncestorOfABinaryTreeII {
    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private boolean existA=false;
    private boolean existB=false;
    //注意，A和B可能不在二叉树中了
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        TreeNode res= find(root,A.val,B.val);
        //如果A和B中有一个不存在就返回null
        if(!existA || !existB){
            return null;
        }
        return res;
    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        if(root==null){
            return null;
        }
        //此时我们不能在前序位置判断了，因为后面的节点可能不存在二叉树中
        //递归处理左右子树
        TreeNode leftNode = find(root.left, val1, val2);
        TreeNode rightNode = find(root.right, val1, val2);
        //后序位置处理
        if(leftNode!=null && rightNode!=null){
            return root;
        }

        if(root.val==val1||root.val==val2){
            if(root.val==val1){
                existA=true;
            }
            if(root.val==val2){
                existB=true;
            }
            return root;
        }
        return leftNode!=null?leftNode:rightNode;
    }
}
