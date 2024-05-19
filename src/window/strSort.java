package window;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 字符串的排列  https://leetcode.cn/problems/permutation-in-string/
 * @author: lyq
 * @createDate: 22/3/2023
 * @version: 1.0
 */
public class strSort {
    //    public boolean checkInclusion(String s1, String s2) {
//        if(s1.length()>s2.length()|| s1.length()==0){
//            return false;
//        }
//        Map<Character,Integer> need=new HashMap<>();
//        Map<Character,Integer> window=new HashMap<>();
//        for (int i = 0; i < s1.length(); i++) {
//            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
//        }
//        int l=0,r=0;
//        int valid=0;
//        while(r<s2.length()){
//            char c = s2.charAt(r);
//            r++;
//            if(need.containsKey(c)){
//                window.put(c,window.getOrDefault(c,0)+1);
//                if(window.get(c).equals(need.get(c))){
//                    valid++;
//                }
//            }
//            //要进行收缩的时机是：因为我们要找子串的排列所以窗口的大小是固定的就是子串的长度
//            while(r-l>=s1.length()){
//               if(valid==need.size()){
//                   return true;
//               }
//                char d = s2.charAt(l);
//                l++;
//                if(need.containsKey(d)){
//                    if(window.get(d).equals(need.get(d))){
//                        valid--;
//                    }
//                    window.put(d,window.get(d)-1);
//                }
//            }
//        }
//        return false;
//    }
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> window=new HashMap<>();
        Map<Character, Integer> need=new HashMap<>();
        int valid=0;
        int left=0,right=0;
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
        }
        while(right<s2.length()){
            char c = s2.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            //收缩
            while(right-left>=s1.length()){
                if(valid==need.size()){
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d,window.get(d)-1);
                }
            }
        }
        return false;
    }
}
