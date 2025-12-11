package hot100;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.11
 * @Since: 1.0
 */

public class day20251211Solution {
    // [378] 有序矩阵中第 K 小的元素
    public int kthSmallest(int[][] matrix, int k) {
        // 优先级队列中存放int[]:[0]:值 [1]:i [2]:j
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        for (int i = 0; i < matrix.length; i++) {
            queue.offer(new int[] {matrix[i][0], i, 0});
        }
        int res = -1;
        while (!queue.isEmpty() && k > 0) {
            int[] cur = queue.poll();
            res = cur[0];
            k--;
            int i = cur[1];
            int j = cur[2];
            if (j + 1 < matrix[0].length) {
                queue.offer(new int[] {matrix[i][j + 1], i, j + 1});
            }
        }
        return res;
    }

    // [373] 查找和最小的 K 对数字
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // int[0]:nums1[i] int[1]:nums2[i] int[2]:nums2中的索引
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return (a[0] + a[1]) - (b[0] + b[1]);
        });
        for (int i = 0; i < nums1.length; i++) {
            queue.offer(new int[] {nums1[i], nums2[0], 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty() && k > 0) {
            int[] cur = queue.poll();
            k--;
            int nextIndex = cur[2] + 1;
            if (nextIndex < nums2.length) {
                queue.offer(new int[] {cur[0], nums2[nextIndex], nextIndex});
            }
            List<Integer> temp = new ArrayList<>();
            temp.add(cur[0]);
            temp.add(cur[1]);
            res.add(temp);
        }
        return res;
    }

    // [2] 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int pre = 0;
        while (p1 != null || p2 != null || pre > 0) {
            int val = pre;
            if (p1 != null) {
                val += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                val += p2.val;
                p2 = p2.next;
            }
            pre = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
        }
        return dummy.next;
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
