import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: lyq
 * @createDate: 28/3/2023
 * @version: 1.0
 */
public class test01 {
    //最小覆盖子串
//    public String minWindow(String s, String t) {
//        int left = 0, right = 0;//左右指针
//        //返回最后结果时用来进行字符截取的开始索引以及截取的长度
//        int start = 0, len = Integer.MAX_VALUE;
//        //我们最终返回的结果中的字符，以及字符出现的次数
//        HashMap<Character, Integer> need = new HashMap<>();
//        //滑动窗口
//        HashMap<Character, Integer> window = new HashMap<>();
//        //计数器，当valid的大小等于need中的字符数的时候就结束了
//        int valid = 0;
//        if(s.length()<=0){
//            return "";
//        }
//        //初始化need
//        for (int i = 0; i < t.length(); i++) {
//            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
//        }
//        while (right < s.length()) {
//            char c = s.charAt(right);
//            right++;
//            //更新滑动窗口，只要是我们最后结果中的字符就放到的窗口中
//            if (need.containsKey(c)) {
//                window.put(c, window.getOrDefault(c, 0) + 1);
//                //下面的这个操作，可能有人会有疑惑，不理解，下面我好好讲解一下
//                //意思是如果我们此时窗口中的这个字符的出现次数和我们最后结果中的这个字符的出现次数相等的时候，说明这个字符匹配结束了，所以让计数器加1
//                if (window.get(c).equals(need.get(c))) {
//                    valid++;
//                }
//            }
//            //缩小窗口的条件，当计数器的数量等于最后结果中的字符数量的时候说明我们已经把需要匹配的所有的字符都加到窗口中了
//            // 下一步就是要进行一个优化，进行缩小窗口，直到窗口中的字符不满足最后结果的字符为止
//            while (valid == need.size()) {
//                //更新最后的截取起始索引以及长度
//                if (right - left < len) {
//                    start = left;
//                    len = right - left;
//                }
//                char d = s.charAt(left);
//                left++;
//                if (need.containsKey(d)) {
//                    //如果此时我们need中的某个字符的出现次数和窗口中的对应的字符的出现出现次数一样的话，缩小窗口以后就不对应了，所以计数器减一
//                    if(window.get(d).equals(need.get(d))){
//                        valid--;
//                    }
//                    window.put(d,window.get(d)-1);
//                }
//            }
//        }
//        return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
//    }


    //---------------------------------------
//    public boolean checkInclusion(String s1, String s2) {
//        int left = 0, right = 0;//左右指针
//        //我们最终返回的结果中的字符，以及字符出现的次数
//        HashMap<Character, Integer> need = new HashMap<>();
//        //滑动窗口
//        HashMap<Character, Integer> window = new HashMap<>();
//        //计数器，当valid的大小等于need中的字符数的时候就结束了
//        int valid = 0;
//        if(s2.length()<=0){
//            return false;
//        }
//        //初始化need
//        for (int i = 0; i < s1.length(); i++) {
//            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
//        }
//        while (right < s2.length()) {
//            char c = s2.charAt(right);
//            right++;
//            //更新滑动窗口，只要是我们最后结果中的字符就放到的窗口中
//            if (need.containsKey(c)) {
//                window.put(c, window.getOrDefault(c, 0) + 1);
//                //下面的这个操作，可能有人会有疑惑，不理解，下面我好好讲解一下
//                //意思是如果我们此时窗口中的这个字符的出现次数和我们最后结果中的这个字符的出现次数相等的时候，说明这个字符匹配结束了，所以让计数器加1
//                if (window.get(c).equals(need.get(c))) {
//                    valid++;
//                }
//            }
//            //缩小窗口的条件，当窗口的大小大于等于最终结果的长度的时候
//            while (right-left>=s1.length()) {
//                if(valid==need.size()){
//                    return true;
//                }
//                char d = s2.charAt(left);
//                left++;
//                if (need.containsKey(d)) {
//                    //如果此时我们need中的某个字符的出现次数和窗口中的对应的字符的出现出现次数一样的话，缩小窗口以后就不对应了，所以计数器减一
//                    if(window.get(d).equals(need.get(d))){
//                        valid--;
//                    }
//                    window.put(d,window.get(d)-1);
//                }
//            }
//        }
//        return false;
//    }
    //-----------------------------------------

    //找出所有字母移位词
//    public List<Integer> findAnagrams(String s, String p) {
//        int left=0,right=0;
//        HashMap<Character,Integer> need=new HashMap<>();
//        HashMap<Character,Integer> window=new HashMap<>();
//        int valid=0;
//        for (int i = 0; i < p.length(); i++) {
//            need.put(p.charAt(i),need.getOrDefault(p.charAt(i),0)+1);
//        }
//        List<Integer> res=new LinkedList<>();
//        while(right<s.length()){
//            char c = s.charAt(right);
//            right++;
//            if(need.containsKey(c)){
//                window.put(c,window.getOrDefault(c,0)+1);
//                if(need.get(c).equals(window.get(c))){
//                    valid++;
//                }
//            }
//            while(right-left>=p.length()){
//                if(valid==need.size()){
//                    res.add(left);
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
//        return res;
//    }
    //----------------------------------------------
    //无重复字符的最长子串
//    public int lengthOfLongestSubstring(String s) {
//        int left=0,right=0;
//        HashMap<Character,Integer> windows=new HashMap<>();
//        int res=0;
//        while(right<s.length()){
//            char c = s.charAt(right);
//            right++;
//            windows.put(c,windows.getOrDefault(c,0)+1);
//            while(windows.get(c)>1){
//                char d = s.charAt(left);
//                left++;
//                windows.put(d,windows.get(d)-1);
//            }
//            res=Math.max(res,right-left);
//        }
//        return res;
//    }
    //---------------------------
    //字符串中的变位词
//    public boolean checkInclusion(String s1, String s2) {
//        int left=0,right=0;
//        HashMap<Character,Integer> need=new HashMap<>();
//        HashMap<Character,Integer> window=new HashMap<>();
//        if(s2.length()<=0){
//            return false;
//        }
//        int valid=0;
//        for (int i = 0; i < s1.length(); i++) {
//            need.put(s1.charAt(i),need.getOrDefault(s1.charAt(i),0)+1);
//        }
//        while(right<s2.length()){
//            char c = s2.charAt(right);
//            right++;
//            if(need.containsKey(c)){
//                window.put(c,window.getOrDefault(c,0)+1);
//                if(need.get(c).equals(window.get(c))){
//                    valid++;
//                }
//            }
//            while (right-left>=s1.length()){
//                if(valid==need.size()){
//                    return true;
//                }
//                char d = s2.charAt(left);
//                left++;
//                if(need.containsKey(d)){
//                    if(need.get(d).equals(window.get(d))){
//                        valid--;
//                    }
//                    window.put(d,window.get(d)-1);
//                }
//            }
//        }
//        return false;
//    }
    //--------------------
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c)>1){
                char d = s.charAt(left);
                left++;
                window.put(d,window.get(d)-1);
            }
            res=Math.max(res,right-left);
        }
        return res;
    }
}
