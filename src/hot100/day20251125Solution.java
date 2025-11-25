package hot100;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.11.25
 * @Since: 1.0
 */

public class day20251125Solution {
    // [606] 根据二叉树创建字符串
    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return root.val + "";
        }
        String leftSub = tree2str(root.left);
        String rightSub = tree2str(root.right);
        // 只有左子树，没有右子树
        if (root.left != null && root.right == null) {
            return root.val + "(" + leftSub + ")";
        }
        // 左子树为空，右子树不为空
        if (root.left == null && root.right != null) {
            return root.val + "()" + "(" + rightSub + ")";
        }
        // 左右子树都不为空
        return root.val + "(" + leftSub + ")" + "(" + rightSub + ")";
    }

    // [1443] 收集树上所有苹果的最少时间
    HashMap<Integer, List<Integer>> graph = new LinkedHashMap<>();
    List<Boolean> hasApple;
    HashSet<Integer> visited = new HashSet<>();

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        this.hasApple = hasApple;
        for (int i = 0; i < n; i++) {
            graph.put(i, new LinkedList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int res = collect(0);
        return res == -1 ? 0 : res;
    }

    /**
     * 遍历以 root 为根的这棵多叉树，返回收集其中的所有苹果所需的最少步数（时间） 如果返回的是 -1，说明以 root 为根的这棵多叉树中没有苹果
     * 
     * @param root
     * @return
     */
    private int collect(int root) {
        if (visited.contains(root)) {
            return -1;
        }
        visited.add(root);
        int sum = 0;
        for (Integer child : graph.get(root)) {
            int childSum = collect(child);
            if (childSum != -1) {
                sum += childSum + 2;
            }
        }
        if (sum > 0) {
            return sum;
        }
        // 如果子树没有苹果但是自己是苹果
        if (sum == 0 && hasApple.get(root)) {
            return 0;
        }
        return -1;
    }

    // [968] 监控二叉树
    int res = 0;

    public int minCameraCover(TreeNode root) {
        setCamera(root, false);
        return res;
    }

    /**
     * 给你一个二叉树的根节点，以及当前这个节点是否有父节点， 采用最优策略来设置摄像头让摄像头数量最少 如果返回-1说明值为空，0：没有被监控 1：被监控 2：自己放置摄像头
     * 
     * @param root
     * @param hasParent
     */
    private int setCamera(TreeNode root, boolean hasParent) {
        if (root == null) {
            return -1;
        }
        int leftSub = setCamera(root.left, true);
        int rightSub = setCamera(root.right, true);
        // 当前节点是叶子节点
        if (leftSub == -1 && rightSub == -1) {
            // 如果有父节点，可以让父节点来监控自己
            if (hasParent) {
                return 0;
            }
            // 如果没有父节点，需要自己放置一个摄像头
            res++;
            return 2;
        }
        // 如果左右子树有一个没有被监控到，那么当前这个节点就需要放摄像头
        if (leftSub == 0 || rightSub == 0) {
            res+=1;
            return 2;
        }

        // 如果有一个子节点放置了摄像头，当前这个节点就可以被监控到
        if (leftSub == 2 || rightSub == 2) {
            return 1;
        }

        // 如果左右子树都被监控了，那么这个节点放不放需要根据父节点判断
        if (hasParent) {
            return 0;
        } else {
            res++;
            return 2;
        }
    }

    // **************
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
