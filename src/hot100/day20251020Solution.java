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
                //因为题目说当距离相等的时候，选择索引小的，但是我们下面是通过last()来取得最大值的，所以这里要进行倒序排列，就会得到索引小的
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
         * 返回可以选择的最大位置
         * 
         * @param a
         * @return
         */
        private int distance(int[] a) {
            int x = a[0];
            int y = a[1];
            // 如果当前的开始位置是-1，那么最远肯定是结尾
            if (x == -1) {
                return y;
            } else if (y == N) {
                //如果当前位置的结尾是最后，那么可以选择的最远位置就是开头
                return N - 1 - x;
            } else {
                // 返回中点到端点的距离
                return (y - x) / 2;
            }
        }
    }
}
