package window;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 重复的DNA序列 https://leetcode.cn/problems/repeated-dna-sequences/
 * @author: lyq
 * @createDate: 3/5/2023
 * @version: 1.0
 */
public class repeatedDnaSequences {
    public List<String> findRepeatedDnaSequences(String s) {
        int[] nums=new int[s.length()];
        //将s中对应的字母转成数字存在整数数组中
        for (int i = 0; i < nums.length; i++) {
            switch (s.charAt(i)){
                case 'A':
                    nums[i]=0;
                    break;
                case 'C':
                    nums[i]=1;
                    break;
                case 'G':
                    nums[i]=2;
                    break;
                case 'T':
                    nums[i]=3;
                    break;
            }
        }
        int L=10;
        //4进制
        int R=4;
        HashSet<Integer> senn=new HashSet<>();
        HashSet<String> res=new HashSet<>();
        //滑动窗口框架
        int left=0,right=0;
        int windowHash=0;
        while(right< nums.length){
            windowHash=windowHash*R+nums[right];
            right++;
            if(right-left==L){
                if(senn.contains(windowHash)){
                    res.add(s.substring(left,right));
                }else{
                    senn.add(windowHash);
                }
                windowHash= (int) (windowHash-nums[left]*Math.pow(R,L-1));
                left++;
            }
        }
        return new LinkedList<>(res);
    }
}
