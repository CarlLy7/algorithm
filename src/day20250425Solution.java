import java.util.LinkedHashMap;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.25
 * @Since: 1.0
 */

public class day20250425Solution {
    // LRU 缓存
    private class LRUCache {
        LinkedHashMap<Integer, Integer> cache;
        int capacity;

        public LRUCache(int capacity) {
            cache = new LinkedHashMap<>(capacity);
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            makeRecently(key);
            return cache.get(key);
        }

        private void makeRecently(int key) {
            Integer value = cache.get(key);
            cache.remove(key);
            cache.put(key, value);
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                cache.put(key, value);
                makeRecently(key);
                return;
            }
            if (cache.size() >= capacity) {
                Integer removeKey = cache.keySet().iterator().next();
                cache.remove(removeKey);
            }
            cache.put(key, value);
        }
    }

    // 148 排序链表
    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        ListNode p = head;
        while (p != null) {
            queue.offer(p);
            p = p.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode q = dummy;
        while (!queue.isEmpty()) {
            q.next = new ListNode(queue.poll().val);
            q = q.next;
        }
        return dummy.next;
    }

    // 152 乘积最大子数组
    // 因为数可能有负数，那么乘积和加法又不太一样了，乘积可能会出现负负得正的问题，所以乘积不是单调的，加法则是单调的
    public int maxProduct(int[] nums) {
        // minDp[i]：以nums[i]结尾的最小乘积
        int[] minDp = new int[nums.length];
        // maxDp[i]：以nums[i]结尾的最大乘积
        int[] maxDp = new int[nums.length];
        // base case
        minDp[0] = nums[0];
        maxDp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minDp[i] = min(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i], nums[i]);
            maxDp[i] = max(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < maxDp.length; i++) {
            res = Math.max(res, maxDp[i]);
        }
        return res;
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
