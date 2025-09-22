package hot100;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.22
 * @Since: 1.0
 */

public class day20250922Solution {
    // [523] 连续的子数组和
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        // (preSum[i]-preSum[j])%k==0 --->preSum[i]%k==preSum[j]%k 所以将preSum[i]%k作为值，然后记录索引位置
        for (int i = 0; i < preSum.length; i++) {
            int val = preSum[i] % k;
            if (!valToIndex.containsKey(val)) {
                valToIndex.put(val, i);
            }
            // 因为要找尽可能长的，所以肯定索引越小越好
        }
        for (int i = 1; i < preSum.length; i++) {
            int need = preSum[i] % k;
            if (valToIndex.containsKey(need)) {
                if (Math.abs(valToIndex.get(need) - i) > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // [524] 通过删除字母匹配到字典里最长单词
    public String findLongestWord(String s, List<String> dictionary) {
        // 先按长度从小到大排列，然后长度相同的按照字典序从小到大排列
        dictionary.sort((a, b) -> {
            if (a.length() != b.length()) {
                return Integer.compare(b.length(), a.length());
            }
            return a.compareTo(b);
        });
        for (String dic : dictionary) {
            if (isSubsequence(dic, s)) {
                return dic;
            }
        }
        return "";
    }

    // [392] 判断子序列
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    // [525] 连续数组
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
        }
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        int res = 0;
        for (int i = 0; i < preSum.length; i++) {
            if (!valToIndex.containsKey(preSum[i])) {
                valToIndex.put(preSum[i], i);
            } else {
                res = Math.max(res, i - valToIndex.get(preSum[i]));
            }
        }
        return res;
    }
}
