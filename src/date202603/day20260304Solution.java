package date202603;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.04
 * @Since: 1.0
 */

public class day20260304Solution {
    // [721] 账户合并
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 邮箱和对应账号索引的映射
        HashMap<String, List<Integer>> emailIndexMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                List<Integer> emailIndexMapValue = emailIndexMap.getOrDefault(email, new ArrayList<>());
                emailIndexMapValue.add(i);
                emailIndexMap.put(email, emailIndexMapValue);
            }
        }
        List<List<String>> res = new ArrayList<>();
        HashSet<String> visitedEmail = new HashSet<>();
        for (String email : emailIndexMap.keySet()) {
            if (visitedEmail.contains(email)) {
                continue;
            }
            // 邮箱的下一个邮箱
            Queue<String> queue = new LinkedList<>();
            LinkedList<String> mergerEmail = new LinkedList<>();
            queue.offer(email);
            visitedEmail.add(email);
            while (!queue.isEmpty()) {
                String curEmail = queue.poll();
                mergerEmail.addLast(curEmail);
                // 含有该邮箱的所有账号索引
                List<Integer> indexs = emailIndexMap.get(curEmail);
                for (Integer index : indexs) {
                    List<String> emailList = accounts.get(index);
                    for (int i = 1; i < emailList.size(); i++) {
                        if (!visitedEmail.contains(emailList.get(i))) {
                            queue.offer(emailList.get(i));
                            visitedEmail.add(emailList.get(i));
                        }
                    }
                }
            }
            Integer firstUserIndex = emailIndexMap.get(email).get(0);
            String userName = accounts.get(firstUserIndex).get(0);
            Collections.sort(mergerEmail);
            mergerEmail.addFirst(userName);
            res.add(mergerEmail);
        }
        return res;
    }

    // [2850] 将石头分散到网格图的最少移动次数
    int res = Integer.MAX_VALUE;
    // 当前回溯移动的次数
    int move = 0;
    // 石子数量=0的位置个数
    int emptyCount = 0;

    public int minimumMoves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 记录石子数量大于1的位置
        List<int[]> remainder = new ArrayList<>();
        // 记录石子数量等于0的位置
        List<int[]> emptyIndex = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) {
                    remainder.add(new int[] {i, j});
                }
                if (grid[i][j] == 0) {
                    emptyIndex.add(new int[] {i, j});
                    emptyCount++;
                }
            }
        }
        backTrack(grid, remainder, emptyIndex);
        return res;
    }

    private void backTrack(int[][] grid, List<int[]> remainder, List<int[]> emptyIndex) {
        // 结束条件
        if (emptyCount == 0) {
            res = Math.min(move, res);
            return;
        }
        // 回溯算法：从石子数量大于1的位置遍历所有石子数量等于0的位置
        for (int[] remain : remainder) {
            int srcX = remain[0];
            int srcY = remain[1];
            if (grid[srcX][srcY] == 1) {
                continue;
            }
            for (int[] index : emptyIndex) {
                int destX = index[0];
                int destY = index[1];
                if (grid[destX][destY] != 0) {
                    continue;
                }
                int step = Math.abs(srcX - destX) + Math.abs(srcY - destY);
                move += step;
                grid[srcX][srcY]--;
                grid[destX][destY]++;
                emptyCount--;
                backTrack(grid, remainder, emptyIndex);
                // 撤销选择
                move -= step;
                grid[srcX][srcY]++;
                grid[destX][destY]--;
                emptyCount++;
            }
        }
    }
}
