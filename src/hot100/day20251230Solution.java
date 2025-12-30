package hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.12.30
 * @Since: 1.0
 */

public class day20251230Solution {
    // [392] 判断子序列
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i >= s.length();
    }

    // [792] 匹配子序列的单词数
    public int numMatchingSubseq(String s, String[] words) {
        // 26个英文字母,记录这个字母出现位置的索引
        List<Integer>[] charList = new ArrayList[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charList[c - 'a'] == null) {
                charList[c - 'a'] = new ArrayList<>();
            }
            charList[c - 'a'].add(i);
        }
        int res = 0;
        for (String word : words) {
            // 在word上的指针的索引
            int i = 0;
            // 在s上的指针的索引
            int j = 0;
            while (i < word.length()) {
                char c = word.charAt(i);
                // 如果s中没有这个元素则直接退出本次循环
                if (charList[c - 'a'] == null) {
                    break;
                }
                // 找到c这个字母在s中出现的大于等于j的索引位置
                int pos = left_bound(charList[c - 'a'], j);
                if (pos >= charList[c - 'a'].size()) {
                    break;
                }
                j = charList[c - 'a'].get(pos);
                // 匹配到了，两个指针都往后走
                i++;
                j++;
            }
            if (i >= word.length()) {
                res++;
            }
        }
        return res;
    }

    /**
     * 在nums中寻找目标字符target的最左边界
     * 
     * @param nums
     * @param target
     * @return
     */
    private int left_bound(List<Integer> nums, int target) {
        int left = 0, right = nums.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (right >= 0 && nums.get(right) == target) {
            return left;
        }
        return -1;
    }

    // [658] 找到 K 个最接近的元素
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int pos = left_bound(arr, x);
        // left和right都是开区间
        int left = pos - 1;
        int right = pos;
        List<Integer> res = new ArrayList<>();
        while (right - left - 1 < k) {
            // 左边到头了，往右扩
            if (left == -1) {
                right++;
            } else if (right == arr.length) {
                // 右边到头了，往左扩
                left--;
            } else if (x - arr[left] > arr[right] - x) {
                // 右扩
                right++;
            } else {
                left--;
            }
        }
        for (int i = left + 1; i < right; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    /**
     * 寻找目标的左边界
     * 
     * @param arr
     * @param target
     * @return
     */
    private int left_bound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
