import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author: carl
 * @date: 2024/12/17
 */

public class day20241217Solution {

    //1593
    HashSet<String> set = new HashSet<>();
    int res;

    public int maxUniqueSplit(String s) {
        backTrack(s, 0);
        return res;
    }

    private void backTrack(String s, int index) {
        if (index == s.length()) {
            res = Math.max(res, set.size());
            return;
        }
        //当前索引不切割
        backTrack(s, index + 1);
        //当前索引切割
        String substring = s.substring(0, index + 1);
        if (!set.contains(substring)) {
            set.add(substring);
            backTrack(s.substring(index + 1), 0);
            set.remove(substring);
        }
    }

    //1849
//    boolean res = false;
//    LinkedList<String> track = new LinkedList<>();
//
//    public boolean splitString(String s) {
//        backTrack(s, 0);
//        return res;
//    }
//
//    private void backTrack(String s, int start) {
//        if (res) {
//            return;
//        }
//        if (track.size() >= 2 && String.join("", track).equals(s)) {
//            res = true;
//            return;
//        }
//        for (int i = start; i < s.length(); i++) {
//            String substring = s.substring(start, i + 1);
//            int zeroCount = 0;
//            for (int j = 0; j < substring.length(); j++) {
//                if (substring.charAt(j) == '0') {
//                    zeroCount++;
//                } else {
//                    break;
//                }
//            }
//            if (substring.length() - zeroCount > (s.length() + 1) / 2) {
//                return;
//            }
//            Long curNum = Long.parseLong(substring);
//            if (track.isEmpty() || Long.parseLong(track.getLast()) - curNum == 1) {
//                track.addLast(substring);
//                backTrack(s, i + 1);
//                track.removeLast();
//            }
//        }
//    }

    //1219
//    int res = 0;
//    boolean[][] visited;
//    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//    int[][] grid;
//
//    public int getMaximumGold(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        visited = new boolean[m][n];
//        this.grid=grid;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                dfs(i, j, grid, 0);
//            }
//        }
//        return res;
//    }
//
//    private void dfs(int i, int j, int[][] grid, int pathSum) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || j < 0 || i >= m || j >= n) {
//            return;
//        }
//        if (grid[i][j] == 0) {
//            return;
//        }
//        if (visited[i][j]) {
//            return;
//        }
//        visited[i][j] = true;
//        pathSum += grid[i][j];
//        res = Math.max(pathSum, res);
//        //上下左右
//        for (int[] dir : dirs) {
//            dfs(i + dir[0], j + dir[1], grid, pathSum);
//        }
//        visited[i][j] = false;
//        pathSum -= grid[i][j];
//    }
}
