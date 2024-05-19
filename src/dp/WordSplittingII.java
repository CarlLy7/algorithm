package dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 单词拆分II https://leetcode.cn/problems/word-break-ii/
 * @author: lyq
 * @createDate: 2/4/2023
 * @version: 1.0
 */
public class WordSplittingII {
    //使用回溯算法的框架来解题的
//    List<String> res = new LinkedList<>();
//    LinkedList<String> track = new LinkedList<>();
//    HashSet<String> wordDict;
//
//    public List<String> wordBreak(String s, List<String> wordDict) {
//        this.wordDict = new HashSet<>(wordDict);
//        backTrack(s, 0);
//        return res;
//    }
//
//    private void backTrack(String s, int i) {
//        //如果已经遍历到了字符串中的最后一个位置，代表结束了，我们将track中的字符串加入res中，注意用空格分隔
//        if (i == s.length()) {
//            res.add(String.join(" ", track));
//            return;
//        }
//        for (int len = 1; i + len <= s.length(); len++) {
//            String prefix = s.substring(i, i + len);
//            //如果s中的字符串在字典中的话，回溯
//            if(wordDict.contains(prefix)){
//                //加入路径
//                track.addLast(prefix);
//                //递归处理
//                backTrack(s,i+len);
//                //从路径中移除
//                track.removeLast();
//            }
//        }
//    }


    //==========================================
    //使用动态规划来解
    HashSet<String> wordDict;
    //使用备忘录消除重叠子问题
    List<String>[] memo;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.wordDict=new HashSet<>(wordDict);
        memo=new List[s.length()];
        return dp(s,0);
    }

    private List<String> dp(String s, int i) {
        List<String> res=new LinkedList<>();
        //base case 到最后了，加入一个“”就结束了
        if(i==s.length()){
            res.add("");
            return res;
        }
        if(memo[i]!=null){
            return memo[i];
        }
        for (int len = 0; i+len <=s.length() ; len++) {
            String prefix = s.substring(i, i + len);
            if(wordDict.contains(prefix)){
                List<String> list = dp(s, i + len);
                for (String s1 : list) {
                    if(s1.isEmpty()){
                        //如果遍历的结果是空的话，直接将prefix加上就行了
                        res.add(prefix);
                    }else{
                        //如果不为空的话，我们要加空格拼接
                        res.add(prefix+" "+s1);
                    }
                }
            }
        }
        memo[i]=res;
        return res;
    }

}
