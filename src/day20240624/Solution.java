package day20240624;

import java.util.*;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-24 11:08
 * @version: 1.0
 */
public class Solution {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        for (Node p = head; p != null; p = p.next) {
            map.put(p, new Node(p.val));
        }
        for (Node p = head; p != null; p = p.next) {
            if (p.next != null) {
                map.get(p).next = map.get(p.next);
            }
            if (p.random != null) {
                map.get(p).random = map.get(p.random);
            }
        }
        return map.get(head);
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode p = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        while (p != null) {
            queue.offer(p);
            p = p.next;
        }
        ListNode p1 = dummy;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            p1.next = cur;
            p1 = p1.next;
        }
        p1.next = null;
        return dummy.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            p.next = cur;
            p = p.next;
            if (cur.next != null) {
                queue.offer(cur.next);
            }
        }
        p.next = null;
        return dummy.next;
    }

    class LRUCache {
        private int capacity;
        // 只要使用过就放到哈希链表的末尾
        private LinkedHashMap<Integer, Integer> queue;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.queue = new LinkedHashMap<>();
        }

        public int get(int key) {
            if (!queue.containsKey(key)) {
                return -1;
            }
            makeRecently(key);
            return queue.get(key);
        }

        private void makeRecently(int key) {
            int value = queue.get(key);
            queue.remove(key);
            queue.put(key, value);
        }

        public void put(int key, int value) {
            if (queue.containsKey(key)) {
                queue.put(key, value);
                makeRecently(key);
                return;
            }
            if (queue.size() >= capacity) {
                int oldKey = queue.keySet().iterator().next();
                queue.remove(oldKey);
            }
            queue.put(key, value);
        }
    }

    boolean[][] used;

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (used[i][j]) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    makeZero(matrix, i, j);
                }
            }
        }
    }

    private void makeZero(int[][] matrix, int i, int j) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int col = 0; col < n; col++) {
            if (matrix[i][col] == 0) {
                continue;
            }
            matrix[i][col] = 0;
            used[i][col] = true;
        }
        for (int row = 0; row < m; row++) {
            if (matrix[row][j] == 0) {
                continue;
            }
            matrix[row][j] = 0;
            used[row][j] = true;
        }
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }
}
