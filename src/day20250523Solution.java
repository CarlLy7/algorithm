import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.23
 * @Since: 1.0
 */

public class day20250523Solution {
    // 120 三角形最小路径和
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        int res = Integer.MAX_VALUE;
        // base case
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            int size = row.size();
            for (int j = 0; j < size; j++) {
                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + row.get(j);
                } else {
                    dp[i][j] = dp[i - 1][j] + row.get(j);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp[n - 1][j]);
        }
        return res;
    }

    // 125 验证回文串
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (('a' <= s.charAt(i) && s.charAt(i) <= 'z') || ('0' <= s.charAt(i) && s.charAt(i) <= '9')) {
                sb.append(s.charAt(i));
            }
        }
        String trans = sb.toString();
        int left = 0, right = trans.length() - 1;
        while (left < right) {
            if (trans.charAt(left) != trans.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 127 单词接龙
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        set.remove(beginWord);
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 遍历每一层中所有数据
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                char[] charArray = cur.toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    char orign = charArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == orign)
                            continue;
                        charArray[j] = k;
                        String newStr = new String(charArray);
                        if (set.contains(newStr) && !visited.contains(newStr)) {
                            if (newStr.equals(endWord)) {
                                return step + 1;
                            }
                            visited.add(newStr);
                            queue.offer(newStr);
                        }
                    }
                    charArray[j] = orign;
                }
            }
            // 遍历完一层之后才需要增加深度值
            step++;
        }
        return 0;
    }
    //测试提交
}
