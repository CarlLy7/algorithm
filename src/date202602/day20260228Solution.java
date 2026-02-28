package date202602;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2026.02.28
 * @Since: 1.0
 */

public class day20260228Solution {
    // [841] 钥匙和房间
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            Integer curRoom = queue.poll();
            for (Integer nextRoom : rooms.get(curRoom)) {
                if (!visited[nextRoom]) {
                    queue.offer(nextRoom);
                    visited[nextRoom] = true;
                }
            }
        }
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }

    // [994] 腐烂的橘子
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        // 存的是坐标
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 使用队列记录所有的腐烂橘子
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curIndex = queue.poll();
                int x = curIndex[0];
                int y = curIndex[1];
                for (int[] dir : dirs) {
                    int nextX = dir[0] + x;
                    int nextY = dir[1] + y;
                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && grid[nextX][nextY] == 1) {
                        queue.offer(new int[] {nextX, nextY});
                        grid[nextX][nextY] = 2;
                    }
                }
            }
            res++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return res == 0 ? 0 : res - 1;
    }

    // [433] 最小基因变化
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> valid = new HashSet<>();
        for (String b : bank) {
            valid.add(b);
        }
        if (!valid.contains(endGene)) {
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
                String curGene = queue.poll();
                if (curGene.equals(endGene)) {
                    return step;
                }
                for (String nextGene : getMutationGene(curGene)) {
                    if (!visited.contains(nextGene) && valid.contains(nextGene)) {
                        queue.offer(nextGene);
                        visited.add(nextGene);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 获取所有变异基因
    private List<String> getMutationGene(String curGene) {
        List<String> res = new ArrayList<>();
        char[] geneArray = curGene.toCharArray();
        char[] genes = new char[] {'A', 'G', 'C', 'T'};
        for (int i = 0; i < geneArray.length; i++) {
            char old = geneArray[i];
            for (char newGene : genes) {
                geneArray[i] = newGene;
                res.add(new String(geneArray));
            }
            // 在往后进行操作的时候，我们必须把前面的位置复原
            geneArray[i] = old;
        }
        return res;
    }
}
