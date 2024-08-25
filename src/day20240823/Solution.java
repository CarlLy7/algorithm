package day20240823;

import java.util.*;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-23 10:04
 * @version: 1.0
 */
public class Solution {
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

    List<TreeNode> res = new LinkedList<>();
    HashSet<Integer> set = new HashSet<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) {
            return res;
        }
        for (int i : to_delete) {
            set.add(i);
        }
        del(root, false);
        return res;
    }

//    static class Node {
//        public int val;
//        public List<Node> children;
//
//        public Node(int val) {
//            this.val = val;
//        }
//
//        public Node(int val, List<Node> children) {
//            this.val = val;
//            this.children = children;
//        }
//    }

    // 删除指定节点，返回删除后的根节点
    private TreeNode del(TreeNode root, boolean hasParent) {
        if (root == null) {
            return null;
        }
        boolean contains = set.contains(root.val);
        if (!contains && !hasParent) {
            res.add(root);
        }
        root.left = del(root.left, !contains);
        root.right = del(root.right, !contains);
        return contains ? null : root;
    }

//    public Node cloneTree(Node root) {
//        if (root == null) {
//            return null;
//        }
//        Node newRoot = new Node(root.val);
//        newRoot.children = new ArrayList<>();
//        for (Node child : root.children) {
//            newRoot.children.add(cloneTree(child));
//        }
//        return newRoot;
//    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    HashMap<Node, Node> copyMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (copyMap.containsKey(node)) {
            return copyMap.get(node);
        }
        Node newNode = new Node(node.val);
        copyMap.put(node, newNode);
        newNode.neighbors = new ArrayList<>();
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return newNode;
    }

    // 找到无效节点，并把该节点为根的树删除
    HashSet<Integer> visited = new HashSet<>();

    public TreeNode correctBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.right != null && visited.contains(root.right.val)) {
            return null;
        }
        root.right = correctBinaryTree(root.right);
        root.left = correctBinaryTree(root.left);
        return root;
    }
}
