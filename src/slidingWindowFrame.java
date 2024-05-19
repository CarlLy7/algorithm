import java.util.HashMap;
import java.util.Map;

/**
 * @description: 滑动窗口框架
 * @author: lyq
 * @createDate: 17/5/2023
 * @version: 1.0
 */
public class slidingWindowFrame {
    // 最小覆盖子串
//    Map<Character,Integer> need=new HashMap<>();
//    Map<Character,Integer> window=new HashMap<>();
//    int valid=0;
//    public String minWindow(String s, String t) {
//        if(s.length()<t.length()){
//            return "";
//        }
//        for (int i = 0; i < t.length(); i++) {
//            need.put(t.charAt(i),need.getOrDefault(t.charAt(i),0)+1);
//        }
//        int left=0,right=0;
//        int start=0,len=Integer.MAX_VALUE;
//        while(right<s.length()){
//            char c = s.charAt(right);
//            right++;
//            if(need.containsKey(c)){
//                window.put(c,window.getOrDefault(c,0)+1);
//                if(window.get(c).equals(need.get(c))){
//                    valid++;
//                }
//            }
//            while(valid==need.size()){
//                if(right-left<len){
//                    start=left;
//                    len=right-left;
//                }
//                char d = s.charAt(left);
//                left++;
//                if(need.containsKey(d)){
//                    if(need.get(d).equals(window.get(d))){
//                        valid--;
//                    }
//                    window.put(d,window.get(d)-1);
//                }
//            }
//        }
//        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
//    }

    // 字符串的排列
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();
    int valid=0;
    //s2是否包含s1的排列
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(need.get(c).equals(window.get(c))){
                    valid++;
                }
            }
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
