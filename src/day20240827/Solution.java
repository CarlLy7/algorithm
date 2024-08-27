package day20240827;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-27 09:54
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

    // 利用这个map来将一个树变成图，存放所有节点和它所有的父节点
//    HashMap<Integer, TreeNode> parents = new HashMap<>();
//    List<Integer> res = new ArrayList<>();
//
//    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
//        traverse(root, null);
//        Queue<TreeNode> queue = new LinkedList<>();
//        HashSet<Integer> visited = new HashSet<>();
//        queue.offer(target);
//        visited.add(target.val);
//        int step = 0;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode curNode = queue.poll();
//                if (step == k) {
//                    res.add(curNode.val);
//                }
//                TreeNode parent = parents.get(curNode.val);
//                if (parent != null && !visited.contains(parent.val)) {
//                    queue.offer(parent);
//                    visited.add(parent.val);
//                }
//                if (curNode.left != null && !visited.contains(curNode.left.val)) {
//                    queue.offer(curNode.left);
//                    visited.add(curNode.left.val);
//                }
//                if (curNode.right != null && !visited.contains(curNode.right.val)) {
//                    queue.offer(curNode.right);
//                    visited.add(curNode.right.val);
//                }
//            }
//            step++;
//        }
//        return res;
//    }
//
//    private void traverse(TreeNode root, TreeNode parent) {
//        if (root == null) {
//            return;
//        }
//        parents.put(root.val, parent);
//        traverse(root.left, root);
//        traverse(root.right, root);
//    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //构造无向图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        //存放叶子节点的集合
        List<Integer> leafs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leafs.add(i);
            }
        }
        //删除所有的叶子节点，并且将新的叶子节点放到叶子节点列表中
        int remain = n;
        while (remain > 2) {
            remain -= leafs.size();
            List<Integer> newLeafs = new ArrayList<>();
            for (int leaf : leafs) {
                int node = graph.get(leaf).get(0);
                graph.get(node).remove(leaf);
                if (graph.get(node).size() == 1) {
                    newLeafs.add(node);
                }
            }
            leafs = newLeafs;
        }
        return leafs;
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, 0, visited);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(List<List<Integer>> rooms, int start, boolean[] visited) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        List<Integer> neighbors = rooms.get(start);
        for (int neighbor : neighbors) {
            dfs(rooms, neighbor, visited);
        }
    }
}
