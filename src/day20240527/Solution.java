package day20240527;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-27 21:39
 * @version: 1.0
 */
public class Solution {
    class MonotonicQueue {
        private LinkedList<Integer> maxQueue = new LinkedList<>();

        public void push(int x) {
            while (!maxQueue.isEmpty() && maxQueue.getLast() < x) {
                maxQueue.removeLast();
            }
            maxQueue.addLast(x);
        }

        public void pop(int x) {
            if (x == maxQueue.getFirst()) {
                maxQueue.removeFirst();
            }
        }

        public int getMax() {
            return maxQueue.getFirst();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res.add(window.getMax());
                window.pop(nums[i - k + 1]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int miss = (n + rolls.length) * mean - Arrays.stream(rolls).sum();
        if (miss < n || miss > 6 * n) {
            return new int[0];
        }
        // q就是剩下的骰子的平均点数
        int q = miss / n;
        // 如果reminder大于0说明不能被均分，有剩余的点数，那么针对前i个骰子，每个骰子都加1就可以了
        int remainder = miss % n;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = q + (i < remainder ? 1 : 0);
        }
        return res;
    }
}
