import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: carl
 * @date: 2025.03.04
 */

public class day20250304Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 919
    public class CBTInserter {

        private TreeNode root;
        private Queue<TreeNode> q = new LinkedList<>();

        public CBTInserter(TreeNode root) {
            this.root = root;
            Queue<TreeNode> temp = new LinkedList<>();
            temp.offer(root);
            while (!temp.isEmpty()) {
                TreeNode cur = temp.poll();
                if (cur.left != null) {
                    temp.offer(cur.left);
                }
                if (cur.right != null) {
                    temp.offer(cur.right);
                }
                if (cur.left == null || cur.right == null) {
                    q.offer(cur);
                }
            }
        }

        public int insert(int val) {
            TreeNode newNode = new TreeNode(val);
            TreeNode cur = q.peek();
            if (cur.left == null) {
                cur.left = newNode;
            } else if (cur.right == null) {
                cur.right = newNode;
                // 右孩子都放进节点的话，说明这个节点已经满了，不能再放了，所以就需要从队列中出去
                q.poll();
            }
            q.offer(newNode);
            return cur.val;
        }

        public TreeNode get_root() {
            return this.root;
        }
    }

    // 117

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }

    // 662

    class Pair {
        private TreeNode node;
        private int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 1));
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int start = 0;
            int end = 0;
            for (int i = 0; i < size; i++) {
                Pair cur = q.poll();
                TreeNode curNode = cur.node;
                int curIndex = cur.index;
                if (i == 0) {
                    start = curIndex;
                }
                if (i == size - 1) {
                    end = curIndex;
                }
                if (curNode.left != null) {
                    q.offer(new Pair(curNode.left, curIndex * 2));
                }
                if (curNode.right != null) {
                    q.offer(new Pair(curNode.right, curIndex * 2 + 1));
                }
            }
            maxWidth = Math.max(maxWidth, end - start + 1);
        }
        return maxWidth;
    }


}
