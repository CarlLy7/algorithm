package day20240708;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-07-08 09:26
 * @version: 1.0
 */
public class Solution {
//    boolean[] used;
//    boolean[] path;
//    boolean hasCycle;
//
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
//        used = new boolean[numCourses];
//        path = new boolean[numCourses];
//        hasCycle = false;
//        for (int i = 0; i < numCourses; i++) {
//            traverse(graph, i);
//        }
//        return !hasCycle;
//    }
//
//    private void traverse(List<Integer>[] graph, int s) {
//        if (path[s]) {
//            hasCycle = true;
//        }
//        if (used[s] || path[s]) {
//            return;
//        }
//        path[s] = true;
//        used[s] = true;
//        for (int i : graph[s]) {
//            traverse(graph, i);
//        }
//        path[s] = false;
//    }

//    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph = new List[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            graph[i] = new ArrayList<>();
//        }
//        for (int[] prerequisite : prerequisites) {
//            int from = prerequisite[1];
//            int to = prerequisite[0];
//            graph[from].add(to);
//        }
//        return graph;
//    }
//
//    boolean hasCycle;
//    boolean[] used, path;
//    List<Integer> ans = new ArrayList<>();
//
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
//        used = new boolean[numCourses];
//        path = new boolean[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            traverse(graph, i);
//        }
//        if (hasCycle) {
//            return new int[]{};
//        }
//        Collections.reverse(ans);
//        int[] res = new int[numCourses];
//        for (int i = 0; i < numCourses; i++) {
//            res[i] = ans.get(i);
//        }
//        return res;
//    }
//
//    private void traverse(List<Integer>[] graph, int s) {
//        if (path[s]) {
//            hasCycle = true;
//        }
//        if (used[s] || path[s]) {
//            return;
//        }
//        path[s] = true;
//        used[s] = true;
//        for (int i : graph[s]) {
//            traverse(graph, i);
//        }
//        // 后序位置
//        ans.add(s);
//        path[s] = false;
//    }

//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph = build(numCourses, prerequisites);
//        int[] indegree = new int[numCourses];
//        for (int[] prerequisite : prerequisites) {
//            int from = prerequisite[1];
//            int to = prerequisite[0];
//            indegree[to]++;
//        }
//        Queue<Integer> queue = new LinkedList<>();
//        for (int i = 0; i < numCourses; i++) {
//            // 如果当前节点的入度为0，说明可以作为起点
//            if (indegree[i] == 0) {
//                queue.offer(i);
//            }
//        }
//        int count = 0;
//        while (!queue.isEmpty()) {
//            int cur = queue.poll();
//            count++;
//            for (int i : graph[cur]) {
//                indegree[i]--;
//                if (indegree[i] == 0) {
//                    queue.offer(i);
//                }
//            }
//        }
//        return count == numCourses;
//    }

    private List<Integer>[] build(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            graph[from].add(to);
        }
        return graph;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = build(numCourses, prerequisites);
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            indegree[to]++;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[count] = cur;
            count++;
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (count != numCourses) {
            return new int[]{};
        }
        return res;
    }

//    List<List<String>> res = new ArrayList<>();
//
//    public List<List<String>> solveNQueens(int n) {
//        String[][] board = new String[n][n];
//        for (String[] strings : board) {
//            Arrays.fill(strings, ".");
//        }
//        backTrack(board, 0);
//        return res;
//    }
//
//    private void backTrack(String[][] board, int row) {
//        int n = board.length;
//        if (row == n) {
//            List<String> temp = new ArrayList<>();
//            for (String[] str : board) {
//                temp.add(String.join("", str));
//            }
//            res.add(temp);
//            return;
//        }
//        for (int col = 0; col < board[row].length; col++) {
//            if (!isValid(board, row, col)) {
//                continue;
//            }
//            board[row][col] = "Q";
//            backTrack(board, row + 1);
//            board[row][col] = ".";
//        }
//    }
//
//    private boolean isValid(String[][] board, int row, int col) {
//        for (int i = row; i >= 0; i--) {
//            if (board[i][col].equals("Q")) {
//                return false;
//            }
//        }
//        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
//            if (board[i][j].equals("Q")) {
//                return false;
//            }
//        }
//        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
//            if (board[i][j].equals("Q")) {
//                return false;
//            }
//        }
//        return true;
//    }

//    List<List<String>> res = new ArrayList<>();
//    LinkedList<String> track = new LinkedList<>();
//
//    public List<List<String>> partition(String s) {
//        backTrack(s, 0);
//        return res;
//    }
//
//    private void backTrack(String s, int start) {
//        if (start == s.length()) {
//            res.add(new ArrayList<>(track));
//        }
//        for (int i = start; i < s.length(); i++) {
//            if (!isValid(s, start, i)) {
//                continue;
//            }
//            track.addLast(s.substring(start, i + 1));
//            backTrack(s, i + 1);
//            track.removeLast();
//        }
//    }
//
//    private boolean isValid(String s, int start, int end) {
//        while (start < end) {
//            if (s.charAt(start) != s.charAt(end)) {
//                return false;
//            }
//            start++;
//            end--;
//        }
//        return true;
//    }

    boolean found = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, word, 0);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    private void dfs(char[][] board, int i, int j, String word, int start) {
        if (start == word.length()) {
            found = true;
            return;
        }
        if (found) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] != word.charAt(start)) {
            return;
        }
        board[i][j] = (char) -board[i][j];
        dfs(board, i - 1, j, word, start + 1);
        dfs(board, i + 1, j, word, start + 1);
        dfs(board, i, j - 1, word, start + 1);
        dfs(board, i, j + 1, word, start + 1);
        board[i][j] = (char) -board[i][j];
    }


//    List<String> res = new ArrayList<>();
//    StringBuilder sb = new StringBuilder();
//
//    public List<String> generateParenthesis(int n) {
//        if (n == 0) {
//            return res;
//        }
//        backTrack(n, n);
//        return res;
//    }
//
//    private void backTrack(int leftNum, int rightNum) {
//        if (leftNum > rightNum) {
//            return;
//        }
//        if (leftNum < 0 || rightNum < 0) {
//            return;
//        }
//        if (leftNum == 0 && rightNum == 0) {
//            res.add(sb.toString());
//            return;
//        }
//        //放左括号
//        sb.append("(");
//        backTrack(leftNum - 1, rightNum);
//        sb.deleteCharAt(sb.length() - 1);
//
//        //放右括号
//        sb.append(")");
//        backTrack(leftNum, rightNum - 1);
//        sb.deleteCharAt(sb.length() - 1);
//    }


    // 无重可复选组合问题
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates, target, 0, 0);
        return res;
    }

    private void backTrack(int[] candidates, int target, int start, int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            sum += candidates[i];
            track.addLast(candidates[i]);
            backTrack(candidates, target, i, sum);
            sum -= candidates[i];
            track.removeLast();
        }
    }

}
