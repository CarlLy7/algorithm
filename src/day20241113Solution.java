import java.util.*;

/**
 * @author: carl
 * @date: 2024/11/13
 */

public class day20241113Solution {
    public class ListNode {
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

    //21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1, p2 = l2;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }

    //86
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        ListNode p1 = dummy1;
        ListNode p2 = dummy2;
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            ListNode temp = p.next;
            p.next = null;
            p = temp;
        }
        p1.next = dummy2.next;
        return dummy1.next;
    }

    //23
    public ListNode mergeKList(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            queue.offer(node);
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return dummy.next;
    }

    //19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode node = findLastNNode(dummy, n + 1);
        node.next = node.next.next;
        return dummy.next;
    }

    /**
     * 寻找倒数第N个节点
     *
     * @param head
     * @param n
     * @return
     */
    private ListNode findLastNNode(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //876
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //142
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 只要有环直接退出循环
            if (slow == fast) {
                break;
            }
        }
        // 无环
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            if (a == null) {
                a = headB;
            } else {
                a = a.next;
            }
            if (b == null) {
                b = headA;
            } else {
                b = b.next;
            }
        }
        return a;
    }

    //82
    //迭代方法
//    public ListNode deleteDuplicates(ListNode head) {
//        ListNode dummy = new ListNode(-1);
//        ListNode p = dummy, q = head;
//        while (q != null) {
//            if (q.next != null && q.val == q.next.val) {
//                while (q.next != null && q.val == q.next.val) {
//                    q = q.next;
//                }
//                q = q.next;
//                if (q == null) {
//                    p.next = null;
//                }
//            } else {
//                p.next = q;
//                p = p.next;
//                q = q.next;
//            }
//        }
//        return dummy.next;
//    }

    // 递归方法
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return deleteDuplicates(head.next);
    }

    //1836
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy;
        HashMap<Integer, Integer> map = new HashMap<>();
        ListNode q = head;
        while (q != null) {
            map.put(q.val, map.getOrDefault(q.val, 0) + 1);
            q = q.next;
        }
        while (p != null) {
            ListNode node = p.next;
            while (node != null && map.get(node.val) > 1) {
                node = node.next;
            }
            p.next = node;
            p = p.next;
        }
        return dummy.next;
    }

    //264
    public int nthUglyNumber(int n) {
        // 三个链表上的指针
        int p2 = 1, p3 = 1, p5 = 1;
        // 三个链表
        int product2 = 1, product3 = 1, product5 = 1;
        // 最终的链表
        int[] ugly = new int[n + 1];
        // 最终链表上的指针
        int p = 1;
        while (p <= n) {
            int min = Math.min(Math.min(product2, product3), product5);
            ugly[p] = min;
            p++;
            if (min == product2) {
                product2 = ugly[p2] * 2;
                p2++;
            }
            if (min == product3) {
                product3 = 3 * ugly[p3];
                p3++;
            }
            if (min == product5) {
                product5 = 5 * ugly[p5];
                p5++;
            }
        }
        return ugly[n];
    }

    //378
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        for (int i = 0; i < matrix.length; i++) {
            queue.offer(new int[]{matrix[i][0], i, 0});
        }
        int res = -1;
        while (!queue.isEmpty() && k > 0) {
            int[] node = queue.poll();
            res = node[0];
            int i = node[1];
            int j = node[2];
            k--;
            if (j + 1 < matrix[i].length) {
                queue.offer(new int[]{matrix[i][j + 1], i, j + 1});
            }
        }
        return res;
    }

    //373
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return (a[0] + a[1]) - (b[0] + b[1]);
        });
        for (int i = 0; i < nums1.length; i++) {
            queue.offer(new int[]{nums1[i], nums2[0], 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty() && k > 0) {
            int[] cur = queue.poll();
            k--;
            int nextIndex = cur[2] + 1;
            List<Integer> pair = new ArrayList<>();
            pair.add(cur[0]);
            pair.add(cur[1]);
            res.add(pair);
            if (nextIndex < nums2.length) {
                queue.offer(new int[]{cur[0], nums2[nextIndex], nextIndex});
            }
        }
        return res;
    }

    //2
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode p1 = l1, p2 = l2;
//        int carry = 0;
//        ListNode dummy = new ListNode(-1);
//        ListNode p = dummy;
//        while (p1 != null || p2 != null || carry > 0) {
//            int val = carry;
//            if (p1 != null) {
//                val += p1.val;
//                p1 = p1.next;
//            }
//            if (p2 != null) {
//                val += p2.val;
//                p2 = p2.next;
//            }
//            carry = val / 10;
//            val = val % 10;
//            p.next = new ListNode(val);
//            p = p.next;
//        }
//        return dummy.next;
//    }

    //445
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode(-1);
        Stack<Integer> stack1 = new Stack<>();
        while (p1 != null) {
            stack1.push(p1.val);
            p1 = p1.next;
        }
        Stack<Integer> stack2 = new Stack<>();
        while (p2 != null) {
            stack2.push(p2.val);
            p2 = p2.next;
        }
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int val = carry;
            if (!stack1.isEmpty()) {
                val += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                val += stack2.pop();
            }
            carry = carry / 10;
            val = val % 10;
            ListNode newNode = new ListNode(val);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        return dummy.next;
    }
}
