import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.16
 * @Since: 1.0
 */

public class day20250616Solution {
    // [306] 累加数
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n; i++) {
            String first = num.substring(0, i);
            for (int j = i + 1; j <= n; j++) {
                String second = num.substring(i, j);
                if (isValid(num, first, second)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(String num, String first, String second) {
        if (first.startsWith("0") && first.length() > 1 || second.startsWith("0") && second.length() > 1) {
            return false;
        }
        // 计算累加之后的结果
        String sub = strAdd(first, second);
        String next = num.substring(first.length() + second.length());
        if (!next.startsWith(sub)) {
            return false;
        }
        if (next.equals(sub)) {
            return true;
        }
        return isValid(num.substring(first.length()), second, sub);
    }

    private String strAdd(String first, String second) {
        StringBuilder sb = new StringBuilder();
        int i = first.length() - 1;
        int j = second.length() - 1;
        int add = 0;
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? first.charAt(i) - '0' : 0;
            int y = j >= 0 ? second.charAt(j) - '0' : 0;
            int res = x + y + add;
            sb.append(res % 10);
            add = res / 10;
            i--;
            j--;
        }
        return sb.reverse().toString();
    }

    // [417] 太平洋大西洋水流问题
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        // 可以流入太平洋的坐标
        Queue<int[]> queueP = new LinkedList<>();
        boolean[][] visitedP = new boolean[m][n];
        // 第一列肯定可以流入太平洋
        for (int i = 0; i < m; i++) {
            queueP.offer(new int[] {i, 0});
            visitedP[i][0] = true;
        }
        // 第一行肯定可以流入太平洋
        for (int j = 0; j < n; j++) {
            queueP.offer(new int[] {0, j});
            visitedP[0][j] = true;
        }
        bfs(heights, queueP, visitedP);

        // 可以流入大西洋的坐标
        Queue<int[]> queueA = new LinkedList<>();
        boolean[][] visitedA = new boolean[m][n];
        // 最右列肯定可以流入大西洋
        for (int i = 0; i < m; i++) {
            queueA.offer(new int[] {i, n - 1});
            visitedA[i][n - 1] = true;
        }
        // 第下行肯定可以流入大西洋
        for (int j = 0; j < n; j++) {
            queueA.offer(new int[] {m - 1, j});
            visitedA[m - 1][j] = true;
        }
        bfs(heights, queueA, visitedA);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedP[i][j] && visitedA[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    /**
     * BFS将附近高的坐标加进来
     * 
     * @param heights
     * @param queue
     * @param visited
     */
    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] ints : dir) {
                int x = cur[0] + ints[0];
                int y = cur[1] + ints[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || heights[x][y] < heights[cur[0]][cur[1]]) {
                    continue;
                }
                queue.offer(new int[] {x, y});
                visited[x][y] = true;
            }
        }
    }

    // [433] 最小基因变化
    char[] AGCT = new char[] {'A', 'G', 'C', 'T'};

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(endGene)) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(startGene);
        visited.add(startGene);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(endGene)) {
                    return step;
                }
                for (String next : getAll(cur)) {
                    if (!visited.contains(next) && set.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * 获取所有AGCT变化的字符串
     * 
     * @param cur
     * @return
     */
    private List<String> getAll(String cur) {
        char[] charArray = cur.toCharArray();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            char old = charArray[i];
            for (char c : AGCT) {
                charArray[i] = c;
                res.add(new String(charArray));
            }
            // 还原
            charArray[i] = old;
        }
        return res;
    }

}
