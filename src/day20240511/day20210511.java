package day20240511;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-11 14:05
 * @version: 1.0
 */
public class day20210511 {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int res = 0;
        for (Integer num : set) {
            // 不是连续序列的第一个元素，则跳过
            if (set.contains(num - 1)) {
                continue;
            }
            int curNum = num;
            int curLen = 1;
            while (set.contains(curNum + 1)) {
                curNum += 1;
                curLen += 1;
            }
            res = Math.max(res, curLen);
        }
        return res;
    }

    // 单调队列类
    class MonotonicQueue {
        LinkedList<Integer> maxQueue = new LinkedList<>();

        // 往队列尾部插入元素，同时保证递减
        public void push(int n) {
            while (!maxQueue.isEmpty() && maxQueue.getLast() < n) {
                maxQueue.removeLast();
            }
            maxQueue.addLast(n);
        }

        // 返回最大元素
        public int max() {
            return maxQueue.getFirst();
        }

        // 得到队头元素
        public void pop(int n) {
            if (n == maxQueue.getFirst()) {
                maxQueue.pollFirst();
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue monotonicQueue = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                monotonicQueue.push(nums[i]);
            } else {
                monotonicQueue.push(nums[i]);
                int max = monotonicQueue.max();
                res.add(max);
                //移除第一个元素,原因是如果这个元素是最大值，那么后续可能就不会出现在滑动窗口中了，如果当前这个元素不是最大值，删不删没意义，因为我们得到的结果只是得到最大值
                monotonicQueue.pop(nums[i - k + 1]);
            }
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

}
