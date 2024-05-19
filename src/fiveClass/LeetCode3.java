package fiveClass;

import java.util.*;

/**
 * @description:
 * @author: lyq
 * @createDate: 26/2/2023
 * @version: 1.0
 */

public class LeetCode3 {
    //最笨的暴力解法
//    public static int lengthOfLongestSubstring(String s) {
//        if (s == null || s.length() < 1) {
//            return 0;
//        }
//        if (s.length() == 1) {
//            return 1;
//        }
//        char[] chars = s.toCharArray();
//        int n = chars.length;
//        int[] num = new int[chars.length];
//        List<Character> list=new ArrayList<>();
//        int ans = 1;
//        for (int i = 0; i < n; i++) {
//            list.add(chars[i]);
//            for (int j = i + 1; j < n; j++) {
//                if (!list.contains(chars[j])) {
//                        ans++;
//                        list.add(chars[j]);
//                } else {
//                    break;
//                }
//            }
//            num[i]=ans;
//            ans=1;
//            list.clear();
//        }
//        Arrays.sort(num);
//        return num[num.length - 1];
//    }


    //使用滑动窗口优化，核心就是做到不回溯
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int left = 0;//左指针
        int maxLen = 0;//最大不重复子串长度
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                //如果这个字符已经在map中有了，说明发生了重复，我们的左指针应该移动到发生重复的字符的下一个位置，这么做的目的是为了保证不发生回溯，这也是比O（N²）好的原因
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            //求最大长度
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
