package day20240526;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-26 16:26
 * @version: 1.0
 */
public class Solution {
    // 并查集
    class UF {
        // 连通分量
        private int count;
        // 各个节点的根节点
        private int[] parent;

        public UF(int n) {
            this.count = n;
            parent = new int[n];
            // 初始化每个节点的根节点是自己
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // 连接两个节点
        public void union(int p, int q) {
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            if (pRoot == qRoot) {
                return;
            }
            parent[pRoot] = qRoot;
            count--;
        }

        // 判断两个节点是否连通
        public boolean connection(int p, int q) {
            int pRoot = findRoot(p);
            int qRoot = findRoot(q);
            return pRoot == qRoot;
        }

        //返回图中的连通分量
        public int count() {
            return count;
        }

        // 查找x节点的根节点
        private int findRoot(int x) {
            if (parent[x] != x) {
                parent[x] = findRoot(parent[x]);
            }
            return parent[x];
        }
    }


    public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                char c = equation.charAt(0);
                char d = equation.charAt(3);
                uf.union(c - 'a', d - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                char c = equation.charAt(0);
                char d = equation.charAt(3);
                if (uf.connection(c - 'a', d - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> track = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                track.add(pre[i][j]);
            }
        }
        track.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        return track.get(k - 1);
    }


//    public int closedIsland(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        for (int i = 0; i < m; i++) {
//            dfs(grid, i, 0);
//            dfs(grid, i, n - 1);
//        }
//        for (int i = 0; i < n; i++) {
//            dfs(grid, 0, i);
//            dfs(grid, m - 1, i);
//        }
//        int res = 0;
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                if (grid[i][j] == 0) {
//                    res++;
//                    dfs(grid, i, j);
//                }
//            }
//        }
//        return res;
//    }
//
//    private void dfs(int[][] grid, int i, int j) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 1) {
//            return;
//        }
//        grid[i][j] = 1;
//        dfs(grid, i - 1, j);
//        dfs(grid, i + 1, j);
//        dfs(grid, i, j - 1);
//        dfs(grid, i, j + 1);
//    }

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

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) {
            nums.add(p.val);
        }
        int[] res = new int[nums.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.size() - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums.get(i)) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(nums.get(i));
        }
        return res;
    }

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            // 可以看到的更矮的人数
            int count = 0;
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
                count++;
            }
            res[i] = stack.isEmpty() ? count : count + 1;
            stack.push(heights[i]);
        }
        return res;
    }

    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return res;
    }

    class StockSpanner {
        Stack<int[]> stack;

        public StockSpanner() {
            stack = new Stack<int[]>();
        }

        public int next(int price) {
            int count = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                int[] pop = stack.pop();
                count += pop[1];
            }
            stack.push(new int[]{price, count});
            return count;
        }
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        //维护一个单调递增的数字
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && stack.peek() > c && k > 0) {
                stack.pop();
                k--;
            }
            // '0'开头的数字
            if (stack.isEmpty() && c == '0') {
                continue;
            }
            stack.push(c);
        }
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        if (stack.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
