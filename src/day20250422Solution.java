import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.22
 * @Since: 1.0
 */

public class day20250422Solution {
    // 131 分割回文串
    List<List<String>> res = new ArrayList<>();
    LinkedList<String> track = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backTrack(s, 0);
        return res;
    }

    // 判断s[0..i]是否是回文串，如果是的话，切割，然后再去s[i+1,...]分割下一个回文串
    private void backTrack(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) {
                continue;
            }
            track.addLast(s.substring(start, i + 1));
            backTrack(s, i + 1);
            track.removeLast();
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        int lo = start;
        int hi = end;
        while (lo <= hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    //136 只出现一次的数字
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = nums[i] ^ ans;
        }
        return ans;
    }

    // 138 随机链表的复制
//    public Node copyRandomList(Node head) {
//        Map<Node, Node> originToClone = new HashMap<>();
//        for (Node p = head; p != null; p = p.next) {
//            originToClone.put(p, new Node(p.val));
//        }
//        for (Node p = head; p != null; p = p.next) {
//            if (p.next != null) {
//                originToClone.get(p).next = originToClone.get(p.next);
//            }
//            if (p.random != null) {
//                originToClone.get(p).random = originToClone.get(p.random);
//            }
//        }
//        return originToClone.get(head);
//    }

    //traverse方式
    HashMap<Node, Node> originToClone = new HashMap<>();
    // 记录节点是否已经创建过了
    HashSet<Node> visited = new HashSet<>();

    public Node copyRandomList(Node head) {
        traverse(head);
        return originToClone.get(head);
    }

    private void traverse(Node head) {
        if (head == null) {
            return;
        }
        if (visited.contains(head)) {
            return;
        }
        visited.add(head);
        // 创建一个新的节点放到map中映射起来
        if (!originToClone.containsKey(head)) {
            originToClone.put(head, new Node(head.val));
        }
        Node cloneHead = originToClone.get(head);
        // 递归遍历当前节点的next节点，递归遍历后map中肯定已经存在了，就可以直接连接了
        traverse(head.next);
        cloneHead.next = originToClone.get(head.next);
        // 递归遍历当前节点的random节点，递归遍历后map中肯定已经存在了，就可以直接连接了
        traverse(head.random);
        cloneHead.random = originToClone.get(head.random);
    }


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
}
