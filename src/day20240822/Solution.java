package day20240822;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-22 10:16
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

    //    public class Codec {
//        String NULL = "#";
//        String SEP = ",";
//        StringBuilder sb = new StringBuilder();
//
//        // Encodes a tree to a single string.
//        public String serialize(TreeNode root) {
//            _serialize(root, sb);
//            return sb.toString();
//        }
//
//        private void _serialize(TreeNode root, StringBuilder sb) {
//            if (root == null) {
//                sb.append(NULL).append(SEP);
//                return;
//            }
//            sb.append(root.val).append(SEP);
//            _serialize(root.left, sb);
//            _serialize(root.right, sb);
//        }
//
//        // Decodes your encoded data to tree.
//        public TreeNode deserialize(String data) {
//            LinkedList<String> nodes = new LinkedList<>();
//            for (String line : data.split(",")) {
//                nodes.add(line);
//            }
//            return _deserialize(nodes);
//        }
//
//        private TreeNode _deserialize(LinkedList<String> nodes) {
//            if (nodes.isEmpty()) {
//                return null;
//            }
//            String first = nodes.getFirst();
//            nodes.removeFirst();
//            if (first.equals(NULL)) return null;
//            TreeNode root = new TreeNode(Integer.parseInt(first));
//            root.left = _deserialize(nodes);
//            root.right = _deserialize(nodes);
//            return root;
//        }
//    }
    public class Codec {
        String NULL = "#";
        String SEP = ",";
        StringBuilder sb = new StringBuilder();

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            _serialize(root);
            return sb.toString();
        }

        private void _serialize(TreeNode root) {
            if (root == null) {
                return;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode curNode = queue.poll();
                    if (curNode == null) {
                        sb.append(NULL).append(SEP);
                        continue;
                    }
                    sb.append(curNode.val).append(SEP);
                    queue.offer(curNode.left);
                    queue.offer(curNode.right);
                }
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] nodes = data.split(",");
            int index = 1;
            if (NULL.equals(nodes[0])) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
            // 存放根节点的队列
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                for (int i = 0; i < queue.size(); i++) {
                    TreeNode curRoot = queue.poll();
                    String leftVal = nodes[index++];
                    if (!NULL.equals(leftVal)) {
                        curRoot.left = new TreeNode(Integer.parseInt(leftVal));
                        queue.offer(curRoot.left);
                    }
                    String rightVal = nodes[index++];
                    if (!NULL.equals(rightVal)) {
                        curRoot.right = new TreeNode(Integer.parseInt(rightVal));
                        queue.offer(curRoot.right);
                    }
                }
            }
            return root;
        }
    }
}
