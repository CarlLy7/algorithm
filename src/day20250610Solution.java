import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.10
 * @Since: 1.0
 */

public class day20250610Solution {
    // [1305] 两棵二叉搜索树中的所有元素
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        res.addAll(traverse(root1));
        res.addAll(traverse(root2));
        Collections.sort(res);
        return res;
    }

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

    // [LCR 155] 将二叉搜索树转化为排序的双向链表
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        // 【分解子问题】 将左子树转成排序的双向链表
        Node leftHead = treeToDoublyList(root.left);
        // 【分解子问题】 将右子树转成排序的双向链表
        Node rightHead = treeToDoublyList(root.right);
        Node leftTail, rightTail;
        if (leftHead != null) {
            leftTail = leftHead.left;
            root.left = leftTail;
            leftTail.right = root;
        } else {
            leftTail = leftHead = root;
        }
        if (rightHead != null) {
            rightTail = rightHead.left;
            root.right = rightHead;
            rightHead.left = root;
        } else {
            rightTail = rightHead = root;
        }
        leftHead.left = rightTail;
        rightTail.right = leftHead;
        return leftHead;
    }

    // [LCR 152] 验证二叉搜索树的后序遍历序列
    public boolean verifyTreeOrder(int[] postorder) {
        return check(postorder, 0, postorder.length - 1);
    }

    /**
     * 在[i,j]这个范围内检验是否符合BST的定义
     * 
     * @param postorder
     * @param i
     * @param j
     * @return
     */
    private boolean check(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        // 后序遍历，最后一个节点肯定是root节点
        int root = postorder[j];
        int left = i;
        // 找小于root的左子树范围
        while (left < j && postorder[left] < root) {
            left++;
        }
        int right = left;
        // 找大于root的右子树范围
        while (right < j && postorder[right] > root) {
            right++;
        }
        // 正常如果是一个合法的BST的话，right一定是可以到末尾的
        if (right != j) {
            return false;
        }
        // 检查左子树和右子树是否符合BST的规定
        return check(postorder, i, left - 1) && check(postorder, left, j - 1);
    }

    // [LCR 174] 寻找二叉搜索树中的目标节点
    int cnt;
    // 因为要返回第cnt大的元素，所以我们使用一个优先级队列，保存cnt的元素，此时堆顶最小的元素就是第cnt大的元素
    PriorityQueue<Integer> priorityQueue;

    public int findTargetNode(TreeNode root, int cnt) {
        this.cnt = cnt;
        priorityQueue = new PriorityQueue<>();
        traverseTwo(root);
        return priorityQueue.peek();
    }

    private void traverseTwo(TreeNode root) {
        if (root == null) {
            return;
        }
        priorityQueue.offer(root.val);
        while (priorityQueue.size() > cnt) {
            priorityQueue.poll();
        }
        traverseTwo(root.left);
        traverseTwo(root.right);
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

    private class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
