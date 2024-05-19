package tree;

/**
 * @description: 树的子结构 https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/?show=1
 * @author: lyq
 * @createDate: 12/5/2023
 * @version: 1.0
 */
public class shuDeZiJieGouLcof {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A!=null&&B!=null)&&(traverse(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B));
    }
    //函数的定义：以node1为根节点的二叉树是否包含node2
    private boolean traverse(TreeNode node1, TreeNode node2) {
        if(node2==null){
            return true;
        }
        if(node1==null||node1.val!=node2.val){
           return false;
        }
        return traverse(node1.left,node2.left)&&traverse(node1.right,node2.right);
    }
}
