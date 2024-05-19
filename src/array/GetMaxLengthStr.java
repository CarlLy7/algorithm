package array;

/**
 * @description: 最长回文子串  https://leetcode.cn/problems/longest-palindromic-substring/
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class GetMaxLengthStr {
    //利用双指针从中间向两端扩展，如果是奇数的话l和r其实是相等的，如果是偶数的话l和r挨着
    public String palindrome(String s,int l,int r){
        while(l>=0 && r<s.length() && s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        return s.substring(l+1,r);
    }
    public String longestPalindrome(String s) {
        if(s==null){
            return null;
        }
        String res="";
        for (int i = 0; i < s.length(); i++) {
            String res1 = palindrome(s, i, i);
            String res2 = palindrome(s, i, i + 1);
            res=res.length()>res1.length()?res:res1;
            res=res.length()>res2.length()?res:res2;
        }
        return res;
    }
}
