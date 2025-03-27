import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.03.27
 * @Since: 1.0
 */

public class day20250327Solution {
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

    // 199
    List<Integer> ans = new ArrayList<>();
    int depth = 0;

    public List<Integer> rightSideView(TreeNode root) {
        traverse(root);
        return ans;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        depth++;
        if (ans.size() < depth) {
            ans.add(root.val);
        }
        traverse(root.right);
        traverse(root.left);
        depth--;
    }

    // 236
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null) {
//            return null;
//        }
//        // 如果有一个等于根节点，那么根节点肯定就是最近公共祖先
//        if (p == root || q == root) {
//            return root;
//        }
//        // 递归去左子树找最近公共祖先
//        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
//        // 递归去右子树找最近公共祖先
//        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);
//        // 如果左子树和右子树都能找到最近公共祖先，那么当前节点就是最近公共祖先
//        if (leftAncestor != null && rightAncestor != null) {
//            return root;
//        }
//        // 如果左子树和右子树都没有找到最近公共祖先，那么最近公共祖先null
//        if (leftAncestor == null && rightAncestor == null) {
//            return null;
//        }
//        // 如果左右子树只有一边找到了最近公共祖先，那么那个节点就是
//        return leftAncestor == null ? rightAncestor : leftAncestor;
//    }

    //235
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val > q.val) {
            // 保证第一个节点是小的，后面的是大的
            return lowestCommonAncestor(root, q, p);
        }
        // 如果root介于两个节点大小之间，则当前节点就是最近公共祖先
        if (root.val >= p.val && root.val <= q.val) {
            return root;
        }
        // root比两个节点都大，所以应该去root的左子树中去递归处理
        if (root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // root比最小的节点都小，则应该去root的右子树中递归处理
        if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return null;
    }
}
