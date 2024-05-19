package test;

import java.util.HashSet;

/**
 * @description: 找出回文子串来
 * @author: lyq
 * @createDate: 8/3/2023
 * @version: 1.0
 */
public class Palindromes {
    public static String getStr(String str){
        if(str.isEmpty()){
            return "";
        }
        HashSet<Character> set=new HashSet<>();
        int len=str.length();
        char[] chars = str.toCharArray();
        StringBuffer buffer1=new StringBuffer();
//        StringBuffer buffer2=new StringBuffer();
        int ans=0;
        for (int i = 0; i < chars.length; i++) {
            if(!set.contains(chars[i])){
                set.add(chars[i]);
            }else{
                set.remove(chars[i]);
                buffer1.append(chars[i]);
//                buffer2.append(chars[i]);
            }
        }
        if(set.size()>0){
            Character next = set.iterator().next();
            buffer1.append(next);
        }
//        String s = buffer2.reverse().toString();
//        buffer1.append(s);
        String res = buffer1.toString();
        return res;
    }

    public static void main(String[] args) {
        String test="babad";
        System.out.println(getStr(test));
    }
}
