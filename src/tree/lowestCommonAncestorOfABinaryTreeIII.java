package tree;

/**
 * @description: 二叉树的最近公共祖先 III
 * @author: lyq
 * @createDate: 24/5/2023
 * @version: 1.0
 */
public class lowestCommonAncestorOfABinaryTreeIII {
    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            if(a==null){
                a=q;
            }else{
                a=a.parent;
            }
            if(b==null){
                b=p;
            }else{
                b=b.parent;
            }
        }
        return a;
    }
}
