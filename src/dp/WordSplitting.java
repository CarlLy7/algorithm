package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @description: 单词拆分 https://leetcode.cn/problems/word-break/
 * @author: lyq
 * @createDate: 2/4/2023
 * @version: 1.0
 */
public class WordSplitting {
    HashSet<String> wordDict;
    //-1表示未计算，0表示无法凑出，1表示可以凑出
    int[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
       this.wordDict=new HashSet<>(wordDict);
       memo=new int[s.length()];
        for (int i = 0; i < memo.length; i++) {
            memo[i]=-1;
        }
        return dp(s,0);
    }

    private boolean dp(String s, int i) {
        //base case
        if(i==s.length()){
            return true;
        }
        if(memo[i]!=-1){
            return memo[i]==0?false:true;
        }
        for (int len = 1; i+len<=s.length() ; len++) {
            String s1 = s.substring(i, i + len);
            if(wordDict.contains(s1)){
                boolean res = dp(s, i + len);
                if(res){
                    memo[i]=1;
                    return true;
                }
            }
        }
        memo[i]=0;
        return false;
    }

}
