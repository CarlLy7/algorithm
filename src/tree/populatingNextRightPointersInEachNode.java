package tree;

/**
 * @description: 填充每个节点的下一个右侧节点指针 https://leetcode.cn/problems/populating-next-right-pointers-in-each-node
 * @author: lyq
 * @createDate: 12/5/2023
 * @version: 1.0
 */
public class populatingNextRightPointersInEachNode {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if(root==null){
            return null;
        }
        traverse(root.left,root.right);
        return root;
    }

    private void traverse(Node node1, Node node2) {
        if(node1==null||node2==null){
            return;
        }
        //将传过来的相邻的节点连起来
        node1.next=node2;
        //遍历同一个父节点的左右子节点
        traverse(node1.left,node1.right);
        traverse(node2.left,node2.right);
        //遍历不同父节点的相邻的节点
        traverse(node1.right,node2.left);
    }
}
