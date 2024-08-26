package day20240826;

import java.util.*;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-26 10:04
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

    class CBTInserter {
        //保存所有可以插入子节点的节点，层序遍历
        private Queue<TreeNode> queue = new LinkedList<>();
        private TreeNode root;

        public CBTInserter(TreeNode root) {
            this.root = root;
            Queue<TreeNode> temp = new LinkedList<>();
            temp.offer(root);
            while (!temp.isEmpty()) {
                TreeNode curNode = temp.poll();
                if (curNode.left != null) {
                    temp.offer(curNode.left);
                }
                if (curNode.right != null) {
                    temp.offer(curNode.right);
                }
                if (curNode.left == null || curNode.right == null) {
                    queue.offer(curNode);
                }
            }
        }

        public int insert(int val) {
            TreeNode curRoot = queue.peek();
            TreeNode newNode = new TreeNode(val);
            if (curRoot.left == null) {
                curRoot.left = newNode;
            } else if (curRoot.right == null) {
                curRoot.right = newNode;
                queue.poll();
            }
            queue.offer(newNode);
            return curRoot.val;
        }

        public TreeNode get_root() {
            return this.root;
        }
    }

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

    class Pair {
        int id;
        TreeNode node;

        public Pair(int id, TreeNode node) {
            this.id = id;
            this.node = node;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair> queue = new LinkedList<>();
        int res = Integer.MIN_VALUE;
        queue.offer(new Pair(1, root));
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = 0, end = 0;
            for (int i = 0; i < size; i++) {
                Pair cur = queue.poll();
                int id = cur.id;
                TreeNode curNode = cur.node;
                if (i == 0) {
                    start = id;
                }
                if (i == size - 1) {
                    end = id;
                }
                if (curNode.left != null) {
                    queue.offer(new Pair(id * 2, curNode.left));
                }
                if (curNode.right != null) {
                    queue.offer(new Pair(2 * id + 1, curNode.right));
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> path = new ArrayList<>();
        while (label >= 1) {
            path.add(label);
            label = label / 2;
            int depth = getDepth(label);
            int[] levelLabel = getLevelLabel(depth);
            label = levelLabel[1] - (label - levelLabel[0]);
        }
        Collections.reverse(path);
        return path;
    }

    private int[] getLevelLabel(int depth) {
        int start = (int) Math.pow(2, depth);
        int end = start * 2 - 1;
        return new int[]{start, end};
    }

    private int getDepth(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }
}
