package day20240801;

import java.util.*;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-01 10:50
 * @version: 1.0
 */
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int minDepth(TreeNode root) {
        int res = 1;
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left == null && curNode.right == null) {
                    return res;
                }
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }
            res++;
        }
        return res;
    }

    public int openLock(String[] deadends, String target) {
        int step = 0;
        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        Set<String> vivisted = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        vivisted.add("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (dead.contains(cur)) {
                    continue;
                }
                if (target.equals(cur)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String plusStr = plusOne(cur, j);
                    if (!vivisted.contains(plusStr)) {
                        queue.offer(plusStr);
                        vivisted.add(plusStr);
                    }
                    String downStr = downOne(cur, j);
                    if (!vivisted.contains(downStr)) {
                        queue.offer(downStr);
                        vivisted.add(downStr);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String downOne(String cur, int j) {
        char[] array = cur.toCharArray();
        if (array[j] == '0') {
            array[j] = '9';
        } else {
            array[j] -= 1;
        }
        return new String(array);
    }

    private String plusOne(String cur, int j) {
        char[] array = cur.toCharArray();
        if (array[j] == '9') {
            array[j] = '0';
        } else {
            array[j] += 1;
        }
        return new String(array);
    }

    List<String> res = new ArrayList<>();
    StringBuilder track = new StringBuilder();

    public List<String> removeInvalidParentheses(String s) {
        backTrack(s, 0);
        int maxLen = 0;
        for (String str : res) {
            maxLen = Math.max(maxLen, str.length());
        }
        HashSet<String> set = new HashSet<>();
        for (String str : res) {
            if (str.length() == maxLen) {
                set.add(str);
            }
        }
        return new ArrayList<>(set);
    }

    private void backTrack(String s, int i) {
        if (i == s.length()) {
            if (isValid(track.toString())) {
                res.add(track.toString());
            }
            return;
        }
        char c = s.charAt(i);
        if (c != '(' && c != ')') {
            track.append(c);
            backTrack(s, i + 1);
            track.deleteCharAt(track.length() - 1);
        } else {
            //保留
            track.append(c);
            backTrack(s, i + 1);
            track.deleteCharAt(track.length() - 1);
            //删除
            backTrack(s, i + 1);
        }
    }

    private boolean isValid(String str) {
        int left = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                left++;
            } else if (str.charAt(i) == ')') {
                left--;
                if (left < 0) {
                    return false;
                }
            }
        }
        return left == 0;
    }

    //[1,2,8,9] cnt=3
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int n = cards.length;
        int sum = 0;
        for (int i = n - cnt; i < n; i++) {
            sum += cards[i];
        }
        if ((sum & 1) == 0) {
            return sum;
        }
        //最大的cnt个数中，最小的奇数或者最小的偶数
        int minTemp = cards[n - cnt];
        //从前面的数中找到和minTemp奇偶性不同的最大的数
        int oddOrEven = minTemp % 2;
        int tempSum1 = 0;
        int tempSum2 = 0;
        if (oddOrEven == 0) {
            //偶数，我们需要找最大的奇数
            for (int i = n - cnt - 1; i >= 0; i--) {
                if (cards[i] % 2 != 0) {
                    swap(cards, i, n - cnt);
                    break;
                }
            }

            for (int i = n - cnt; i < n; i++) {
                tempSum1 += cards[i];
            }
        } else {
            //奇数，需要找最大的偶数
            for (int i = n - cnt - 1; i >= 0; i--) {
                if (cards[i] % 2 == 0) {
                    swap(cards, i, n - cnt);
                    break;
                }
            }

            for (int i = n - cnt; i < n; i++) {
                tempSum2 += cards[i];
            }
        }
        int ans = Math.max(tempSum1, tempSum2);
        return ans % 2 == 0 ? ans : 0;
    }

    private void swap(int[] cards, int i, int j) {
        int temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }
}
