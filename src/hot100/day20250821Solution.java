package hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.21
 * @Since: 1.0
 */

public class day20250821Solution {
    // [139] 单词拆分
    int[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        memo = new int[n];
        Arrays.fill(memo, -1);
        // s[0...]能否被wordDict拼出
        return dp(s, 0, wordDict);
    }

    /**
     * s[start...]能否被wordDict拼出
     * 
     * @param s
     * @param start
     * @param wordDict
     * @return
     */
    private boolean dp(String s, int start, List<String> wordDict) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != -1) {
            return memo[start] == 0 ? false : true;
        }
        for (String word : wordDict) {
            int len = word.length();
            if (start + len > s.length()) {
                continue;
            }
            String preSub = s.substring(start, start + len);
            // 如果当前字典单词无法匹配前缀
            if (!preSub.equals(word)) {
                continue;
            }
            // 当前字典单词可以匹配前缀
            if (dp(s, start + len, wordDict)) {
                memo[start] = 1;
                return true;
            }
        }
        memo[start] = 0;
        return false;
    }

    // [141] 环形链表
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // [138] 随机链表的复制
    HashMap<Node, Node> orignToClone = new HashMap<>();
    HashSet<Node> visited = new HashSet<>();

    public Node copyRandomList(Node head) {
        // 方法一:for迭代遍历
        // HashMap<Node, Node> orignToClone = new HashMap<>();
        // for (Node p = head; p != null; p = p.next) {
        // if (!orignToClone.containsKey(p)) {
        // orignToClone.put(p, new Node(p.val));
        // }
        // }
        // for (Node p = head; p != null; p = p.next) {
        // if (p.next != null) {
        // orignToClone.get(p).next = orignToClone.get(p.next);
        // }
        // if (p.random != null) {
        // orignToClone.get(p).random = orignToClone.get(p.random);
        // }
        // }
        // return orignToClone.get(head);
        // 方法二 traverse()递归遍历
        traverse(head);
        return orignToClone.get(head);
    }

    /**
     * 递归复制
     * 
     * @param head
     */
    private void traverse(Node head) {
        if (head == null) {
            return;
        }
        if (visited.contains(head)) {
            return;
        }
        if (!orignToClone.containsKey(head)) {
            orignToClone.put(head, new Node(head.val));
        }
        visited.add(head);
        // 遍历所有邻居
        traverse(head.next);
        orignToClone.get(head).next = orignToClone.get(head.next);
        traverse(head.random);
        orignToClone.get(head).random = orignToClone.get(head.random);
    }

    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
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
