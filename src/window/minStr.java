package window;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 最小覆盖子串     https://leetcode.cn/problems/minimum-window-substring/
 * @author: lyq
 * @createDate: 22/3/2023
 * @version: 1.0
 */
public class minStr {
    //    public static String minWindow(String s, String t) {
//        Map<Character,Integer> window=new HashMap<>();
//        Map<Character,Integer> need=new HashMap<>();
//        int start=0,len=Integer.MAX_VALUE;
//        if(t.length()>s.length()){
//            return "";
//        }
//        int valid=0;//记录我们需要找的子串中有多少个进入窗口中了
//        for (int i = 0; i < t.length(); i++) {
//            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);
//        }
//        int l=0,r=0;
//        while(r<s.length()){
//            Character c=s.charAt(r);
//            r++;
//            if(need.containsKey(c)){
//                window.put(c,window.getOrDefault(c,0)+1);
//                if(window.get(c).equals(need.get(c))){
//                    valid++;
//                }
//            }
//            //需要缩小窗口的情况
//            while(valid==need.size()){
//                if(r-l<len){
//                    start=l;
//                    len=r-l;
//                }
//                char d = s.charAt(l);
//                l++;
//                //更新
//                if(need.containsKey(d)){
//                    if (window.get(d).equals(need.get(d))){
//                        valid--;
//                    }
//                    window.put(d,window.get(d)-1);
//                }
//            }
//        }
//        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
//    }
//
//    public static void main(String[] args) {
//        String s="ADOBECODEBANC",t = "ABC";
//        String s1 = minWindow(s, t);
//        System.out.println(s1);
//    }
    public String minWindow(String s, String t) {
        Map<Character, Integer> window=new HashMap<>();
        Map<Character, Integer> need=new HashMap<>();
        //因为最后要返回最短的子串，所以我们使用一个起始索引以及长度，但时候直接截取就可以了
        int start=0,len=Integer.MAX_VALUE;
        int left=0,right=0;
        int valid=0;//need中的字符出现的个数
        if(s.length()<t.length()){
            return "";
        }
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);
        }
        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            //如果是我们需要的字符的话，加入窗口中
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(window.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            //什么时候需要移除呢，那就是当我们need中需要的字符都已经进入到窗口内了
            while(valid==need.size()){
                if(right-left<len){
                    start=left;
                    len=right-left;
                }
                char d = s.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if(window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d,window.get(d)-1);
                }
            }
        }
        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
    }
}
