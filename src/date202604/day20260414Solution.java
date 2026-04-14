package date202604;

import java.util.HashMap;

/**
 * @description:
 * @author: carl
 * @date: 2026.04.14
 * @Since: 1.0
 */

public class day20260414Solution {
    // [560] 和为 K 的子数组
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        int res = 0;
        // 前缀和->出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
            // preSum[i]-preSum[j]=k -> preSum[j]=preSum[i]-k
            int need = preSum[i] - k;
            if (map.containsKey(need)) {
                res += map.get(need);
            }
            map.put(preSum[i], map.getOrDefault(preSum[i], 0) + 1);
        }
        return res;
    }

    // [206] 反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // [24] 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = first.next;
        ListNode other = second.next;
        second.next = first;
        first.next = swapPairs(other);
        return second;
    }

    // [25] K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            // 如果不足k个节点了，直接返回
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    private ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null, cur = a, nxt = a;
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    private class ListNode {
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
