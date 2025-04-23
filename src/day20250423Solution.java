import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.23
 * @Since: 1.0
 */

public class day20250423Solution {
    // 139 单词拆分
    int[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dp(s, 0, wordDict);
    }

    /**
     * s[i...]能否被wordDict中的字符串拼接出来
     * 
     * @param s
     * @param i
     * @param wordDict
     * @return
     */
    private boolean dp(String s, int i, List<String> wordDict) {
        if (i == s.length()) {
            return true;
        }
        if (memo[i] != -1) {
            return memo[i] == 1 ? true : false;
        }
        for (String word : wordDict) {
            int len = word.length();
            if (i + len > s.length()) {
                continue;
            }
            String subStr = s.substring(i, i + len);
            if (!subStr.equals(word)) {
                continue;
            }
            // 如果后面的也可以被wordDict拼接出来，说明这个s是可以被拼接出来的
            if (dp(s, i + len, wordDict)) {
                memo[i] = 1;
                return true;
            }
        }
        memo[i] = 0;
        return false;
    }

    // 141 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // 142 环形链表II
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
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

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
