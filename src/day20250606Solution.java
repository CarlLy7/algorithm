import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.06
 * @Since: 1.0
 */

public class day20250606Solution {

    // [653] 两数之和 IV - 输入二叉搜索树
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> sortList = traverse(root);
        int left = 0, right = sortList.size() - 1;
        while (left < right) {
            int sum = sortList.get(left) + sortList.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    /**
     * 中序遍历BST得到一个有序数组
     * 
     * @param root
     * @return
     */
    private List<Integer> traverse(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(traverse(root.left));
        res.add(root.val);
        res.addAll(traverse(root.right));
        return res;
    }

    // [703] 数据流中的第 K 大元素
    class KthLargest {
        int k;
        PriorityQueue<Integer> queue;

        public KthLargest(int k, int[] nums) {
            queue = new PriorityQueue<>();
            for (int num : nums) {
                queue.offer(num);
                // 只保留K个元素，这样堆顶就是倒数第K大的元素
                while (queue.size() > k) {
                    queue.poll();
                }
            }
            this.k = k;
        }

        public int add(int val) {
            queue.offer(val);
            while (queue.size() > k) {
                queue.poll();
            }
            return queue.peek();
        }
    }

    // [897] 递增顺序搜索树
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 拉平左右子树
        TreeNode leftTree = increasingBST(root.left);
        root.left = null;
        TreeNode rightTree = increasingBST(root.right);
        root.right = rightTree;
        if (leftTree == null) {
            return root;
        }
        TreeNode p = leftTree;
        while (p != null && p.right != null) {
            p = p.right;
        }
        p.right = root;
        return leftTree;
    }

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
}
