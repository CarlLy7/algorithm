package test;

import java.util.Arrays;

/**
 * @description:
 * @author: lyq
 * @createDate: 7/3/2023
 * @version: 1.0
 */
public class LongestCommonPrefix {
    public static String getStr(String[] strs){
        if(strs.length<=1||strs==null){
            return "";
        }
        int len=strs.length;
        for (int i = 0; i < len; i++) {
            if(strs[i].length()<=0){
                return "";
            }
        }
        Arrays.sort(strs);
        int firstLen=strs[0].length();
        int lastLen=strs[len-1].length();
        int size=Math.min(firstLen,lastLen);

        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < size; i++) {
            if(strs[0].charAt(i)==strs[strs.length-1].charAt(i)){
                builder.append(strs[0].charAt(i));
            }else{
                break;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] test=new String[]{"flower","flow","flowe"};
        System.out.println(getStr(test));
    }
}
