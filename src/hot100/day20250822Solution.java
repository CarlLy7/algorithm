package hot100;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.22
 * @Since: 1.0
 */

public class day20250822Solution {
    // [142] 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    // [152] 乘积最大子数组
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        // minDp[i]已nums[i]结尾的数组乘积最小值
        int[] minDp = new int[n];
        // maxDp[i]已nums[i]结尾的数组乘积最大值
        int[] maxDp = new int[n];
        minDp[0] = nums[0];
        maxDp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            minDp[i] = Math.min(nums[i], Math.min(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i]));
            maxDp[i] = Math.max(nums[i], Math.max(minDp[i - 1] * nums[i], maxDp[i - 1] * nums[i]));
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < maxDp.length; i++) {
            res = Math.max(res, maxDp[i]);
        }
        return res;
    }

    // [146] LRU 缓存
    class LRUCache {
        private Integer capacity;
        private LinkedHashMap<Integer, Integer> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new LinkedHashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 将这个使用过的key移到最后
            makeRecently(key);
            return map.get(key);
        }

        /**
         * 变成最近使用的
         * 
         * @param key
         */
        private void makeRecently(int key) {
            Integer value = map.get(key);
            map.remove(key);
            map.put(key, value);
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.put(key, value);
                makeRecently(key);
                return;
            }
            if (map.size() >= this.capacity) {
                // 删除最久未使用的，链表头部就是最老的
                Integer oldKey = map.keySet().iterator().next();
                map.remove(oldKey);
            }
            map.put(key, value);
        }

    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
