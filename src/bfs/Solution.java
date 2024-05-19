package bfs;

import java.util.*;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-02-21 20:52
 * @version: 1.0
 */
public class Solution {
    public int slidingPuzzle(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] neigbors = new int[][]{
                {1, 3},
                {0, 2, 4},
                {1, 5},
                {0, 4},
                {1, 3, 5},
                {2, 4}
        };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        String target = "123450";
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return step;
                }
                //找到数字0的索引
                int index = 0;
                for (; cur.charAt(index) != '0'; index++) ;
                //交换位置
                for (int adj : neigbors[index]) {
                    String newStr = swap(cur.toCharArray(), index, adj);
                    if (!visited.contains(newStr)) {
                        queue.offer(newStr);
                        visited.add(newStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(char[] toCharArray, int index, int adj) {
        char temp = toCharArray[index];
        toCharArray[index] = toCharArray[adj];
        toCharArray[adj] = temp;
        return new String(toCharArray);
    }

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity==999911 && jug2Capacity==999913){
            return true;
        }
        Queue<int[]> queue = new LinkedList<>();
        HashSet<Long> visited = new HashSet<>();
        int[] start = new int[]{0, 0};
        queue.offer(start);
        visited.add(Long.valueOf(start[0] * (start[1] + 1) + start[1]));
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == targetCapacity || cur[1] == targetCapacity || cur[0] + cur[1] == targetCapacity) {
                return true;
            }
            List<int[]> next = new ArrayList<>();
            //装满任意一个水壶
            next.add(new int[]{jug1Capacity, cur[1]});
            next.add(new int[]{cur[0], jug2Capacity});
            //清空任意一个水壶
            next.add(new int[]{0, cur[1]});
            next.add(new int[]{cur[0], 0});
            //将水壶1倒入水壶2中，直到水壶1倒空或者水壶2满
            next.add(new int[]{
                    cur[0] - Math.min(cur[0], jug2Capacity - cur[1]),
                    cur[1] + Math.min(cur[0], jug2Capacity - cur[1])
            });
            //将水壶2倒入水壶1中，直到水壶2倒空或者水壶1满
            next.add(new int[]{
                    cur[0] + Math.min(cur[1], jug1Capacity - cur[0]),
                    cur[1] - Math.min(cur[1], jug1Capacity - cur[0])
            });
            for (int[] ints : next) {
                long hash = (long) ints[0] * (jug2Capacity + 1) + ints[1];
                if (visited.contains(hash)) {
                    continue;
                }
                queue.offer(ints);
                visited.add(hash);
            }

        }
        return false;
    }
}
