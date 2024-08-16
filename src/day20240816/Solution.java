package day20240816;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-16 12:17
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

//    HashMap<Long, Integer> map = new HashMap<>();
//    int res = 0;
//    long targetSum = 0;
//    long pathSum = 0;
//
//    public int pathSum(TreeNode root, int targetSum) {
//        this.targetSum = targetSum;
//        map.put(0L, 1);
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        pathSum += root.val;
//        res += map.getOrDefault(pathSum - targetSum, 0);
//        map.put(pathSum, map.getOrDefault(pathSum, 0) + 1);
//        traverse(root.left);
//        traverse(root.right);
//        map.put(pathSum, map.get(pathSum) - 1);
//        pathSum -= root.val;
//    }

//    int res = 0;
//    int maxDepth = 0;
//    int depth = 0;
//
//    public int findBottomLeftValue(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        depth++;
//        if (depth > maxDepth) {
//            maxDepth = depth;
//            res = root.val;
//        }
//        traverse(root.left);
//        traverse(root.right);
//        depth--;
//    }

//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> path = new LinkedList<>();
//
//
//    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
//        traverse(root, targetSum);
//        return res;
//    }
//
//    private void traverse(TreeNode root, int targetSum) {
//        if (root == null) {
//            return;
//        }
//        int remain = targetSum - root.val;
//        //前序位置
//        if (root.left == null && root.right == null) {
//            if (remain == 0) {
//                path.addLast(root.val);
//                res.add(new ArrayList<>(path));
//                path.removeLast();
//            }
//            return;
//        }
//        path.addLast(root.val);
//        traverse(root.left, remain);
//        path.removeLast();
//
//        path.addLast(root.val);
//        traverse(root.right, remain);
//        path.removeLast();
//    }

//    class FindElements {
//
//        HashSet<Integer> values = new HashSet<>();
//
//        public FindElements(TreeNode root) {
//            traverse(root, 0);
//        }
//
//        private void traverse(TreeNode root, int val) {
//            if (root == null) {
//                return;
//            }
//            root.val = val;
//            values.add(val);
//            traverse(root.left, 2 * val + 1);
//            traverse(root.right, 2 * val + 2);
//        }
//
//        public boolean find(int target) {
//            return values.contains(target);
//        }
//    }


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return subRoot == null;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        //此时以root为根的树是不满足了，所以需要分别判断root.left为根和root.right为根
        return isSubtree(root.left, subRoot) | isSubtree(root.right, subRoot);
    }

    // 判断root 和 subRoot为根得树是否一样
    private boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return isSameTree(root.left, subRoot.left) & isSameTree(root.right, subRoot.right);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        // base case
        if (head == null) return true;
        if (root == null) return false;
        // 当前值相等了，递归往下判断
        if (root.val == head.val) {
            if (check(head, root)) {
                return true;
            }
        }
        // 当前值不相等，去判断左右子树
        return isSubPath(head, root.left) | isSubPath(head, root.right);
    }

    private boolean check(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (head.val != root.val) {
            return false;
        }
        return check(head.next, root.left) | check(head.next, root.right);
    }
}
