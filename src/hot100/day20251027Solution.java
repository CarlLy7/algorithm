package hot100;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.27
 * @Since: 1.0
 */

public class day20251027Solution {
    // [889] 根据前序和后序遍历构造二叉树
    Map<Integer, Integer> valToIndex = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            valToIndex.put(postorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        int rootVal = preorder[preStart];
        Integer rootIndex = valToIndex.get(preorder[preStart + 1]);
        int leftSize = rootIndex - postStart + 1;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize, postorder, postStart, rootIndex);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, postorder, rootIndex + 1, postEnd - 1);
        return root;
    }

    // [652] 寻找重复的子树
    Map<String, Integer> treeToFre = new HashMap<>();
    List<TreeNode> res = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String leftSub = traverse(root.left);
        String rightSub = traverse(root.right);
        // 使用后序遍历的方式进行序列化
        String cur = leftSub + "," + rightSub + "," + root.val;
        Integer freq = treeToFre.getOrDefault(cur, 0);
        // 出现重复就添加，但是只添加一次
        if (freq == 1) {
            res.add(root);
        }
        treeToFre.put(cur, freq + 1);
        return cur;
    }

    // [297] 二叉树的序列化与反序列化
    class Codec {

        // Encodes a tree to a single string.
        String SEP = ",";
        String NULL = "#";

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        private void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(root.val).append(SEP);
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            LinkedList<String> nodes = new LinkedList<>();
            String[] split = data.split(SEP);
            for (String s : split) {
                nodes.add(s);
            }
            return deserialize(nodes);
        }

        private TreeNode deserialize(LinkedList<String> nodes) {
            String rootVal = nodes.removeFirst();
            if (rootVal.equals(NULL)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(rootVal));
            root.left = deserialize(nodes);
            root.right = deserialize(nodes);
            return root;
        }

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }
    }

    private class TreeNode {
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
}
