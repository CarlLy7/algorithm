package array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 反转字符串中的单词 https://leetcode.cn/problems/reverse-words-in-a-string/
 * @author: lyq
 * @createDate: 29/4/2023
 * @version: 1.0
 */
public class reverseWordsInAString {
    public String reverseWords(String s) {
        //将这个字符串的前后空格去掉
        String trim = s.trim();
        StringBuffer reverseString = new StringBuffer();
        //从后往前将每个字符加入到stringBuffer中，相当于进行了字符串的反转
        for (int i = trim.length()-1; i >=0 ; i--) {
            reverseString.append(trim.charAt(i));
        }
        //the sky is blue---->eulb si yks eht
        String s1 = reverseString.toString();
        String[] strings = s1.split(" ");//将反转后的字符串用空格分隔开
        StringBuffer reverse=new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            //因为可能有两个字符之间有多个空格的情况，所以我们不处理空格
            if(!strings[i].equals("")){
                //倒转
                for (int j = strings[i].length()-1; j >=0 ; j--) {
                    //blue is sky the
                    reverse.append(strings[i].charAt(j));
                }
                //在每一个字段后面加一个空格
                reverse.append(" ");
            }
        }
        //最后结尾多了一个空格，去掉即可
        String result = reverse.toString();
        String res = result.trim();
        return res;
    }
}
