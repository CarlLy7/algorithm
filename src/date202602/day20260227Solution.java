package date202602;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.27
 * @Since: 1.0
 */

public class day20260227Solution {
    // [752] 打开转盘锁
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000") || dead.contains(target)) {
            return -1;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add("0000");
        queue.offer("0000");
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return count;
                }
                for (String neighbor : getNeigbor(cur)) {
                    if (!visited.contains(neighbor) && !dead.contains(neighbor)) {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    private List<String> getNeigbor(String cur) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            res.add(plus(cur, i));
            res.add(down(cur, i));
        }
        return res;
    }

    private String down(String str, int i) {
        char[] array = str.toCharArray();
        if (array[i] == '0') {
            array[i] = '9';
        } else {
            array[i]--;
        }
        return String.valueOf(array);
    }

    private String plus(String str, int i) {
        char[] array = str.toCharArray();
        if (array[i] == '9') {
            array[i] = '0';
        } else {
            array[i]++;
        }
        return new String(array);
    }

    // [662] 二叉树最大宽度
    private class Pair {
        TreeNode node;
        int curId;

        public Pair(TreeNode node, int curId) {
            this.node = node;
            this.curId = curId;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1));
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 每一层的最左侧和最右侧的序号
            int start = 0, end = 0;
            for (int i = 0; i < size; i++) {
                Pair curPair = queue.poll();
                TreeNode curNode = curPair.node;
                int curId = curPair.curId;
                if (i == 0) {
                    start = curId;
                }
                if (i == size - 1) {
                    end = curId;
                }
                if (curNode.left != null) {
                    queue.offer(new Pair(curNode.left, 2 * curId));
                }

                if (curNode.right != null) {
                    queue.offer(new Pair(curNode.right, 2 * curId + 1));
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    // [310] 最小高度树
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        // 叶子节点队列
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                queue.offer(i);
            }
        }
        // 树中剩余节点个数
        int nodeCount = n;
        while (nodeCount > 2) {
            int size = queue.size();
            nodeCount -= size;
            for (int i = 0; i < size; i++) {
                Integer curNode = queue.poll();
                // 遍历这个节点的所有相邻节点
                for (Integer neighbor : graph.get(curNode)) {
                    // 相邻节点删除当前节点，如果相邻节点变为叶子结点则加入队列
                    graph.get(neighbor).remove(curNode);
                    if (graph.get(neighbor).size() == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return queue;
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
