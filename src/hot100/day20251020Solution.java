package hot100;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.20
 * @Since: 1.0
 */

public class day20251020Solution {
    // [855] 考场就座
    class ExamRoom {
        private HashMap<Integer, int[]> startMap;
        private HashMap<Integer, int[]> endMap;
        private TreeSet<int[]> pq;
        private Integer N;

        public ExamRoom(int n) {
            startMap = new HashMap<>();
            endMap = new HashMap<>();
            N = n;
            pq = new TreeSet<>((a, b) -> {
                int distanceA = distance(a);
                int distanceB = distance(b);
                if (distanceA == distanceB) {
                    return b[0] - a[0];
                }
                return distanceA - distanceB;
            });
            addInteral(new int[] {-1, N});
        }

        /**
         * 添加线段
         * 
         * @param ints
         */
        private void addInteral(int[] ints) {
            startMap.put(ints[0], ints);
            endMap.put(ints[1], ints);
            pq.add(ints);
        }

        /**
         * 删除线段
         * 
         * @param ints
         */
        private void removeInteral(int[] ints) {
            startMap.remove(ints[0]);
            endMap.remove(ints[1]);
            pq.remove(ints);
        }

        public int seat() {
            // 最长的线段
            int[] maxInteral = pq.last();
            int x = maxInteral[0];
            int y = maxInteral[1];
            int seat;
            if (x == -1) {
                seat = 0;
            } else if (y == N) {
                seat = N-1;
            } else {
                seat = (y - x) / 2 + x;
            }
            int[] left = new int[] {x, seat};
            int[] right = new int[] {seat, y};
            removeInteral(maxInteral);
            addInteral(left);
            addInteral(right);
            return seat;
        }

        public void leave(int p) {
            int[] right = startMap.get(p);
            int[] left = endMap.get(p);
            int[] merge = new int[] {left[0], right[1]};
            removeInteral(left);
            removeInteral(right);
            addInteral(merge);
        }

        /**
         * 返回线段的长度
         * 
         * @param a
         * @return
         */
        private int distance(int[] a) {
            int x = a[0];
            int y = a[1];
            if (x == -1) {
                return y;
            } else if (y == N) {
                return N - 1 - x;
            } else {
                // 返回中点到端点的距离
                return (y - x) / 2;
            }
        }
    }
}
