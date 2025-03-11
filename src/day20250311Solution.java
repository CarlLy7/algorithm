import java.util.*;

/**
 * @author: carl
 * @date: 2025.03.11
 */

public class day20250311Solution {
    // 417
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        // 可以流入太平洋的
        Queue<int[]> queueP = new LinkedList<>();
        boolean[][] visitedP = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            queueP.offer(new int[] {i, 0});
            visitedP[i][0] = true;
        }
        for (int j = 0; j < n; j++) {
            queueP.offer(new int[] {0, j});
            visitedP[0][j] = true;
        }
        bfs(heights, queueP, visitedP);
        // 可以流入大西洋的
        Queue<int[]> queueA = new LinkedList<>();
        boolean[][] visitedA = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            queueP.offer(new int[] {i, n - 1});
            visitedP[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            queueP.offer(new int[] {m - 1, j});
            visitedP[m - 1][j] = true;
        }
        bfs(heights, queueA, visitedA);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedP[i][j] == visitedA[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || heights[x][y] < heights[cur[0]][cur[1]]) {
                    continue;
                }
                queue.offer(new int[] {x, y});
                visited[x][y] = true;
            }
        }
    }

    // 365
    public boolean canMeasureWater(int x, int y, int target) {
        Queue<int[]> queue = new LinkedList<>();
        HashSet<Long> visited = new HashSet<>();
        queue.offer(new int[] {0, 0});
        visited.add((long)0 * (0 + 1) + 0);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == target || cur[1] == target || cur[0] + cur[1] == target) {
                return true;
            }
            // 穷举所有的可能结果
            List<int[]> nextStates = new LinkedList<>();
            // 把其中一桶灌满
            nextStates.add(new int[] {x, cur[1]});
            nextStates.add(new int[] {cur[0], y});
            // 将其中一桶清空
            nextStates.add(new int[] {0, cur[1]});
            nextStates.add(new int[] {cur[0], 0});
            // 将其中一桶倒入另一桶，要么全倒进去，要么满了倒不进去了
            nextStates.add(new int[] {cur[0] - Math.min(y - cur[1], cur[0]), cur[0] + Math.min(y - cur[1], cur[0])});
            nextStates.add(new int[] {cur[0] + Math.min(x - cur[0], cur[1]), cur[1] - Math.min(x - cur[0], cur[1])});

            for (int[] nextState : nextStates) {
                long hash = (long)nextState[0] * (y + 1) + nextState[1];
                if (visited.contains(hash)) {
                    continue;
                }
                queue.offer(nextState);
                visited.add(hash);
            }
        }
        return false;
    }

    // 721
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // key:email value: 账户索引列表
        HashMap<String, List<Integer>> emailToAccountIndexMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                List<Integer> accountIndex = emailToAccountIndexMap.getOrDefault(email, new ArrayList<>());
                accountIndex.add(i);
                emailToAccountIndexMap.put(email, accountIndex);
            }
        }
        List<List<String>> res = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        for (String email : emailToAccountIndexMap.keySet()) {
            if (visited.contains(email)) {
                continue;
            }
            LinkedList<String> mergeEmail = new LinkedList<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(email);
            visited.add(email);
            while (!queue.isEmpty()) {
                String curEmail = queue.poll();
                mergeEmail.add(curEmail);
                List<Integer> indexs = emailToAccountIndexMap.get(curEmail);
                for (int index : indexs) {
                    List<String> account = accounts.get(index);
                    for (int i = 1; i < account.size(); i++) {
                        String nextEmail = account.get(i);
                        if (visited.contains(nextEmail)) {
                            continue;
                        }
                        queue.offer(nextEmail);
                        visited.add(nextEmail);
                    }
                }
            }
            String userName = accounts.get(emailToAccountIndexMap.get(email).get(0)).get(0);
            Collections.sort(mergeEmail);
            mergeEmail.addFirst(userName);
            res.add(mergeEmail);
        }
        return res;
    }

    // 127
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet validSet = new HashSet(wordList);
        if (!validSet.contains(endWord)) {
            return 0;
        }
        int step = 1;
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curStr = queue.poll();
                char[] chars = curStr.toCharArray();
                for (int j = 0; j < curStr.length(); j++) {
                    char orignChar = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (chars[j] == c) {
                            continue;
                        }
                        chars[j] = c;
                        String newStr = new String(chars);
                        if (validSet.contains(newStr) && !visited.contains(newStr)) {
                            if (newStr == endWord) {
                                return step + 1;
                            }
                            queue.offer(newStr);
                            visited.add(newStr);
                        }
                    }
                    chars[j] = orignChar;
                }
            }
            step++;
        }
        return 0;
    }

}
