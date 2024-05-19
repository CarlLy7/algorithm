package window;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 无重复字符的最长子串 https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * @author: lyq
 * @createDate: 22/3/2023
 * @version: 1.0
 */
public class NoChongFuMaxStr {
    //    public static int lengthOfLongestSubstring(String s) {
//        if (s.length() <= 0) {
//            return 0;
//        }
//        Map<Character, Integer> window = new HashMap<>();
//        int res = 0;
//        int l = 0, r = 0;
//        while (r < s.length()) {
//            char c = s.charAt(r);
//            r++;
//            window.put(c, window.getOrDefault(c, 0) + 1);
//            while (window.get(c) > 1) {
//                char d = s.charAt(l);
//                l++;
//                window.put(d,window.get(d)-1);
//            }
//            res = Math.max(res,r-l);
//        }
//        return res;
//    }
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> window=new HashMap<>();
        int res=0;
        int left=0,right=0;
        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            window.put(c,window.getOrDefault(c,0)+1);
            while(window.get(c)>1){
                char d = s.charAt(left);
                left++;
                window.put(d,window.get(d)-1);
            }
            res=Math.max(res,right-left);
        }
        return res;
    }
}
