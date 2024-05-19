package test;

import sun.plugin.javascript.navig.Array;

import java.util.Arrays;

/**
 * @description: 验证一个字符串是不是回文串
 * @author: lyq
 * @createDate: 7/3/2023
 * @version: 1.0
 */
public class isPalindromes {
    public static boolean isPalind(String s){
        if(s==null){
            return false;
        }
        int l=0;
        int r=s.length()-1;
        while(l<r){
            if(!Character.isLetterOrDigit(s.charAt(l))){ //如果左边这个不是数字或者字母，跳一下个
                l++;
            }else if(!Character.isLetterOrDigit(s.charAt(r))){//如果右边这个不是数字或者字母，跳前一个
                r--;
            }else{
                if(s.toLowerCase().charAt(l)!=s.toLowerCase().charAt(r)){
                    return false;
                }else{
                    l++;
                    r--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test="race a car";

        System.out.println(isPalind(test));
    }
}
