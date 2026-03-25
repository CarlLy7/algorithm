package date202603;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.25
 * @Since: 1.0
 */

public class day20260325Solution {

    // [49] 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        // 编码值->strs中对应的字符串列表
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 将原始字符串进行编码
            String encode = encode(str);
            map.putIfAbsent(encode, new ArrayList<>());
            map.get(encode).add(str);
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> value : map.values()) {
            res.add(value);
        }
        return res;
    }

    /**
     * 就一个字符串根据每个字母出现的次数进行编码
     * 
     * @param str
     * @return
     */
    private String encode(String str) {
        // 记录每个字母出现的次数
        char[] chars = new char[26];
        for (char c : str.toCharArray()) {
            chars[c - 'a']++;
        }
        return new String(chars);
    }

    // [724] 寻找数组的中心下标
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        // 前缀和数组
        // pre[i]:[0,i-1]位置的元素之和
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int left = preSum[i - 1] - preSum[0];
            int right = preSum[n] - preSum[i];
            if (left == right) {
                return i - 1;
            }
        }
        return -1;
    }

    public int pivotIndex2(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            int left = i == 0 ? 0 : preSum[i - 1];
            int right = preSum[n - 1] - preSum[i];
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    // [238] 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        // 构造一个从左到右的前缀积
        int[] preMulti = new int[n];
        preMulti[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMulti[i] = preMulti[i - 1] * nums[i];
        }
        // 构造从右到左的后缀积
        int[] suffixMulti = new int[n];
        suffixMulti[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMulti[i] = suffixMulti[i + 1] * nums[i];
        }
        int[] res = new int[n];
        res[0] = suffixMulti[1];
        res[n - 1] = preMulti[n - 2];
        for (int i = 1; i < n - 1; i++) {
            res[i] = preMulti[i - 1] * suffixMulti[i + 1];
        }
        return res;
    }

}
