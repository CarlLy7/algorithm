package day20240627;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-27 09:45
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

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && min.val >= root.val) return false;
        if (max != null && max.val <= root.val) return false;
        return isValidBST(root.left, min, root) & isValidBST(root.right, root, max);
    }

    public int orangesRotting(int[][] grid) {
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> depth = new HashMap<>();
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    int index = i * n + j;
                    depth.put(index, 0);
                    queue.offer(index);
                }
            }
        }
        while (!queue.isEmpty()) {
            int curIndex = queue.poll();
            int curRow = curIndex / n;
            int curCol = curIndex % n;
            //上下左右
            if (curRow - 1 >= 0 && grid[curRow - 1][curCol] == 1) {
                grid[curRow - 1][curCol] = 2;
                int index = (curRow - 1) * n + curCol;
                queue.offer(index);
                depth.put(index, depth.get(curIndex) + 1);
                res = Math.max(res, depth.get(curIndex) + 1);
            }
            if (curRow + 1 < m && grid[curRow + 1][curCol] == 1) {
                grid[curRow + 1][curCol] = 2;
                int index = (curRow + 1) * n + curCol;
                queue.offer(index);
                depth.put(index, depth.get(curIndex) + 1);
                res = Math.max(res, depth.get(curIndex) + 1);
            }
            if (curCol - 1 >= 0 && grid[curRow][curCol - 1] == 1) {
                grid[curRow][curCol - 1] = 2;
                int index = curRow * n + curCol - 1;
                queue.offer(index);
                depth.put(index, depth.get(curIndex) + 1);
                res = Math.max(res, depth.get(curIndex) + 1);
            }
            if (curCol + 1 < n && grid[curRow][curCol + 1] == 1) {
                grid[curRow][curCol + 1] = 2;
                int index = curRow * n + curCol + 1;
                queue.offer(index);
                depth.put(index, depth.get(curIndex) + 1);
                res = Math.max(res, depth.get(curIndex) + 1);
            }
        }
        for (int[] row : grid) {
            for (int cur : row) {
                if (cur == 1) {
                    return -1;
                }
            }
        }
        return res;
    }

    boolean[] onPath;
    boolean[] visited;
    boolean hasCycle;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        onPath[s] = true;
        visited[s] = true;
        for (int next : graph[s]) {
            traverse(graph, next);
        }
        onPath[s] = false;
    }

    // 构造图
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            graph[from].add(to);
        }
        return graph;
    }

    int res = -1;
    int rank = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        rank++;
        if (rank == k) {
            res = root.val;
        }
        traverse(root.right, k);
    }
}
