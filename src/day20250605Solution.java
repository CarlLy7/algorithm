import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.05
 * @Since: 1.0
 */

public class day20250605Solution {
    // [449] 序列化和反序列化二叉搜索树
    public class Codec {

        // Encodes a tree to a single string.
        private String SEP = ",";

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            return serialize(root, sb);
        }

        /**
         * 前序遍历进行序列化
         * 
         * @param root
         * @param sb
         * @return
         */
        private String serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return "";
            }
            sb.append(root.val).append(SEP);
            serialize(root.left, sb);
            serialize(root.right, sb);
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            // 存放前序遍历的结果
            LinkedList<Integer> inorder = new LinkedList<>();
            for (String s : data.split(SEP)) {
                inorder.offer(Integer.parseInt(s));
            }
            return deserialize(inorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        /**
         * 在min-max这个范围内，对inorder中的节点进行反序列化
         * 
         * @param inorder
         * @param minValue
         * @param maxValue
         * @return
         */
        private TreeNode deserialize(LinkedList<Integer> inorder, int minValue, int maxValue) {
            if (inorder.isEmpty()) {
                return null;
            }
            // 前序遍历的第一个节点肯定是根节点
            Integer rootVal = inorder.getFirst();
            // 违法范围
            if (rootVal < minValue || rootVal > maxValue) {
                return null;
            }
            TreeNode root = new TreeNode(rootVal);
            inorder.removeFirst();
            // 构造左子树
            TreeNode left = deserialize(inorder, minValue, rootVal);
            // 构造右子树
            TreeNode right = deserialize(inorder, rootVal, maxValue);
            root.left = left;
            root.right = right;
            return root;
        }
    }

    // [501] 二叉搜索树中的众数
    // 存放众数
    List<Integer> mode = new ArrayList<>();
    // 前置节点
    TreeNode pre = null;
    // 当前值出现的次数
    int curCount;
    // 全局众数出现的次数
    int maxCount;

    public int[] findMode(TreeNode root) {
        traverse(root);
        int[] res = new int[mode.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = mode.get(i);
        }
        return res;
    }

    /**
     * 中序遍历，因为BST中序遍历是有顺序的
     * 
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        if (pre == null) {
            curCount = 1;
            maxCount = 1;
            mode.add(root.val);
        } else {
            if (pre.val == root.val) {
                curCount++;
                if (curCount == maxCount) {
                    mode.add(root.val);
                } else if (curCount > maxCount) {
                    // 这是一个新的众数，所以先将原来的众数都清空
                    mode.clear();
                    maxCount = curCount;
                    mode.add(root.val);
                }
            }
            if (pre.val != root.val) {
                curCount = 1;
                if (curCount == maxCount) {
                    mode.add(root.val);
                }
            }
        }
        pre = root;
        traverse(root.right);
    }

    // [530] 二叉搜索树的最小绝对差
    int res = Integer.MAX_VALUE;
    TreeNode preVl = null;

    public int getMinimumDifference(TreeNode root) {
        traverseTwo(root);
        return res;
    }

    private void traverseTwo(TreeNode root) {
        if (root == null) {
            return;
        }
        traverseTwo(root.left);
        if (preVl != null) {
            res = Math.min(res, root.val - preVl.val);
        }
        preVl = root;
        traverseTwo(root.right);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
