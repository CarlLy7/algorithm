package day20240512;

import java.util.*;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-12 13:55
 * @version: 1.0
 */
public class day20250512 {
    class Checkout {
        // 单调队列
        Deque<Integer> d;
        // 普通队列
        Queue<Integer> q;

        public Checkout() {
            d = new LinkedList<>();
            q = new LinkedList<>();
        }

        public int get_max() {
            if (d.isEmpty()) {
                return -1;
            }
            return d.peekFirst();
        }

        public void add(int value) {
            while (!d.isEmpty() && d.peekLast() < value) {
                d.pollLast();
            }
            d.offerLast(value);
            q.offer(value);
        }

        public int remove() {
            if (q.isEmpty()) {
                return -1;
            }
            int ans = q.poll();
            if (ans == d.peekFirst()) {
                d.pollFirst();
            }
            return ans;
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String s = encode(str);
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(str);
        }
        for (List<String> value : map.values()) {
            res.add(value);
        }
        return res;
    }

    // 根据每个字母出现的次数进行编码
    private String encode(String str) {
        char[] en = new char[26];
        for (char c : str.toCharArray()) {
            int curNum = c - 'a';
            en[curNum]++;
        }
        return new String(en);
    }

    // 单调队列
    class MonotonicQueue {
        LinkedList<Integer> maxQueue;
        LinkedList<Integer> minQueue;

        public MonotonicQueue() {
            maxQueue = new LinkedList<>();
            minQueue = new LinkedList<>();
        }

        public void push(int value) {
            while (!maxQueue.isEmpty() && maxQueue.getLast() < value) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(value);
            while (!minQueue.isEmpty() && minQueue.peekLast() > value) {
                minQueue.pollLast();
            }
            minQueue.addLast(value);
        }

        public void pop(int value) {
            if (!maxQueue.isEmpty() && value == maxQueue.getFirst()) {
                maxQueue.pollFirst();
            }
            if (!minQueue.isEmpty() && value == minQueue.getFirst()) {
                minQueue.pollFirst();
            }
        }

        public int getMax() {
            if (maxQueue.isEmpty()) {
                return -1;
            }
            return maxQueue.getFirst();
        }

        public int getMin() {
            if (minQueue.isEmpty()) {
                return -1;
            }
            return minQueue.getFirst();
        }
    }

    public int longestSubarray(int[] nums, int limit) {
        MonotonicQueue window = new MonotonicQueue();
        int res = 0;
        int left = 0, right = 0;
        int windowSize = 0;
        while (right < nums.length) {
            window.push(nums[right]);
            right++;
            windowSize++;
            while (window.getMax() - window.getMin() > limit) {
                window.pop(nums[left]);
                left++;
                windowSize--;
            }
            res = Math.max(res, windowSize);
        }
        return res;
    }

    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        int res = 0;
        HashMap<Integer, Integer> valIndexMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (!valIndexMap.containsKey(preSum[i])) {
                valIndexMap.put(preSum[i], i);
            }
            // nums[0..i-1]前缀和大于0，说明满足要求
            if (preSum[i] > 0) {
                res = Math.max(res, i);
            } else {
                //nums[0...i-1]不满足要求，需要寻找子区间了preSum[i]-preSum[j]=1,在这里我们希望j尽可能小，所以在上面valIndexMap.containsKey(preSum[i])的时候我们不进行更新
                if (valIndexMap.containsKey(preSum[i] - 1)) {
                    int j = valIndexMap.get(preSum[i] - 1);
                    res = Math.max(res, i - j);
                }
            }
        }
        return res;
    }
}
