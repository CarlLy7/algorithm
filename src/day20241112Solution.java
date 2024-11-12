/**
 * @author: carl
 * @date: 2024/11/12
 */

public class day20241112Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    // 236
//    public TreeNode lowest(TreeNode root, TreeNode p, TreeNode q) {
//        return find(root, p, q);
//    }
//
//    private TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null) {
//            return root;
//        }
//        if (root.val == p.val || root.val == q.val) {
//            return root;
//        }
//        TreeNode left = find(root.left, p, q);
//        TreeNode right = find(root.right, p, q);
//        if (left != null && right != null) {
//            return root;
//        }
//        return left != null ? left : right;
//    }

    //1676
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
//        HashSet<Integer> nodeSet = new HashSet<>();
//        for (TreeNode node : nodes) {
//            nodeSet.add(node.val);
//        }
//        return find(root, nodeSet);
//    }
//
//    private TreeNode find(TreeNode root, HashSet<Integer> nodeSet) {
//        if (root == null) {
//            return root;
//        }
//        if (nodeSet.contains(root.val)) {
//            return root;
//        }
//        TreeNode left = find(root.left, nodeSet);
//        TreeNode right = find(root.right, nodeSet);
//        if (left != null && right != null) {
//            return root;
//        }
//        return left != null ? left : right;
//    }

    //1644
//    boolean existP = false;
//    boolean existQ = false;
//
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        TreeNode res = find(root, p, q);
//        if (!existP || !existQ) {
//            return null;
//        }
//        return res;
//    }
//
//    private TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null) {
//            return null;
//        }
//        TreeNode left = find(root.left, p, q);
//        TreeNode right = find(root.right, p, q);
//        if (left != null && right != null) {
//            return root;
//        }
//        if (root.val == p.val || root.val == q.val) {
//            if (root.val == p.val) {
//                existP = true;
//            }
//            if (root.val == q.val) {
//                existQ = true;
//            }
//            return root;
//        }
//        return left != null ? left : right;
//    }

    //235
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        int val1 = Math.min(p.val, q.val);
//        int val2 = Math.max(p.val, q.val);
//        return find(root, val1, val2);
//    }
//
//    private TreeNode find(TreeNode root, int val1, int val2) {
//        if (root == null) {
//            return null;
//        }
//        if (root.val > val2) {
//            return find(root.left, val1, val2);
//        }
//        if (root.val < val1) {
//            return find(root.right, val1, val2);
//        }
//        return root;
//    }

    //1650
    class Node {
        private int val;
        private Node left;
        private Node right;
        private Node parent;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(Node parent) {
            this.parent = parent;
        }

        public Node(int val, Node left, Node right, Node parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            if (a == null) {
                a = b;
            } else {
                a = a.parent;
            }
            if (b == null) {
                b = a;
            } else {
                b = b.parent;
            }
        }
        return a;
    }

    // 222
    // 计算一棵树的节点个数【时间复杂度为O(logN*logN)】
    public int countNodes(TreeNode root) {
        TreeNode l = root, r = root;
        int hl = 0, hr = 0;
        if (root == null) {
            return 0;
        }
        while (l != null) {
            l = l.left;
            hl++;
        }
        while (r != null) {
            r = r.right;
            hr++;
        }
        // 满二叉树
        if (hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
