package hot100;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.08.07
 * @Since: 1.0
 */

public class day20250807Solution {
    // [46] 全排列
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return res;
        }
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        backTrack(nums, track, visited);
        return res;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, boolean[] visited) {
        // base case
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            track.addLast(nums[i]);
            visited[i] = true;
            backTrack(nums, track, visited);
            track.removeLast();
            visited[i] = false;
        }
    }

    // [48] 旋转图像
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int[] row : matrix) {
            reverseRow(row);
        }
    }

    /**
     * 翻转一维数组
     * 
     * @param row
     */
    private void reverseRow(int[] row) {
        int left = 0, right = row.length - 1;
        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }

    // [49] 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) {
            return res;
        }
        // key:异位词编码后的字符串 value:对应strs中哪些字符串
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String encode = encode(str);
            map.putIfAbsent(encode, new ArrayList<>());
            map.get(encode).add(str);
        }
        for (List<String> value : map.values()) {
            res.add(value);
        }
        return res;
    }

    /**
     * 利用单词出现的次数进行编码
     * 
     * @param s
     * @return
     */
    private String encode(String s) {
        char[] c = new char[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            c[index]++;
        }
        return new String(c);
    }
}
