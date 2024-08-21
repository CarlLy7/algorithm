package day20240821;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-21 10:21
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

//    HashMap<Integer, Integer> map = new HashMap<>();
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        for (int i = 0; i < inorder.length; i++) {
//            map.put(inorder[i], i);
//        }
//        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
//    }
//
//    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
//        // 1.构造根节点
//        // 2.递归构造左右子树
//        if (preStart > preEnd) {
//            return null;
//        }
//        int rootVal = preorder[preStart];
//        TreeNode root = new TreeNode(rootVal);
//        int rootIndex = map.get(rootVal);
//        int leftSize = rootIndex - inStart;
//        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);
//        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inEnd);
//        return root;
//    }
//
//    public boolean isValidSerialization(String preorder) {
//        // 给根节点加一个入度
//        int edge = 1;
//        for (String node : preorder.split(",")) {
//            if ("#".equals(node)) {
//                edge -= 1;
//                if (edge < 0) {
//                    return false;
//                }
//            } else {
//                edge -= 1;
//                if (edge < 0) {
//                    return false;
//                }
//                edge += 2;
//            }
//        }
//        return edge == 0;
//    }
//
//    List<TreeNode>[] memo;
//
//    public List<TreeNode> allPossibleFBT(int n) {
//        if (n % 2 == 0) {
//            return new LinkedList<>();
//        }
//        memo = new LinkedList[n + 1];
//        return build(n);
//    }
//
//    private List<TreeNode> build(int n) {
//        List<TreeNode> res = new LinkedList<>();
//        if (n == 1) {
//            res.add(new TreeNode(0));
//            return res;
//        }
//        if (memo[n] != null) {
//            return memo[n];
//        }
//        for (int i = 1; i < n; i += 2) {
//            int j = n - i - 1;
//            List<TreeNode> left = build(i);
//            List<TreeNode> right = build(j);
//            for (TreeNode leftNode : left) {
//                for (TreeNode rightNode : right) {
//                    TreeNode root = new TreeNode(0);
//                    root.left = leftNode;
//                    root.right = rightNode;
//                    res.add(root);
//                }
//            }
//        }
//        memo[n] = res;
//        return res;
//    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            TreeNode temp = root;
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = temp;
            return newRoot;
        } else {
            root.right = insertIntoMaxTree(root.right, val);
        }
        return root;
    }
}
