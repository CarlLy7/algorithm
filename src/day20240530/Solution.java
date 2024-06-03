package day20240530;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-29 16:41
 * @version: 1.0
 */
public class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        // 每个字母连续出现的次数
        List<Integer>[] chs = new List[26];
        for (int i = 0; i < chs.length; i++) {
            chs[i] = new ArrayList<>();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (i + 1 == n || s.charAt(i) != s.charAt(i + 1)) {
                int index=s.charAt(i)-'a';
                chs[index].add(count);
                count = 0;
                // 将单词连续出现的次数进行递减排序
                for (int j = chs[index].size() - 1; j > 0; j--) {
                    if (chs[index].get(j) > chs[index].get(j - 1)) {
                        Collections.swap(chs[index], j, j - 1);
                    }
                }
            }
        }
        int res=-1;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i].size()>0 && chs[i].get(0)>2){
                res=Math.max(res,chs[i].get(0)-2);
            }
            if (chs[i].size()>1 && chs[i].get(0)>1){
                res=Math.max(res,Math.min(chs[i].get(0)-1,chs[i].get(1)));
            }
            if (chs[i].size()>2){
                res=Math.max(res,chs[i].get(2));
            }
        }
        return res;
    }
}
