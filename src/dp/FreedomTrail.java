package dp;

import java.util.*;

/**
 * @description: 自由之路 https://leetcode.cn/problems/freedom-trail/
 * @author: lyq
 * @createDate: 15/4/2023
 * @version: 1.0
 */
public class FreedomTrail {

    //这个散列表用来记录ring中每个字符出现的索引位置
    HashMap<Character, ArrayList<Integer>> charIndex=new HashMap<>();
    //使用备忘录来消除重叠子问题
    int[][] memo;
    public int findRotateSteps(String ring, String key) {
        int m=ring.length();
        int n=key.length();
        memo=new int[m][n];
        for (int i = 0; i < m; i++) {
            char c = ring.charAt(i);
            //如果此时散列表中有这个字符的话，将这个索引位置添加到list中
            if(charIndex.containsKey(c)){
              charIndex.get(c).add(i);
            }else{
                //如果此时的散列表中没有这个字符的话，将这个字符以及它对应的索引加入到散列表中
                ArrayList<Integer> list=new ArrayList<>();
                list.add(i);
               charIndex.put(c,list);
            }
        }
        return dp(ring,0,key,0);
    }

    //声明一下dp函数的定义：从ring中的i位置开始，输入从key的j位置到最后位置所需要转动和按下去的最少次数
    private int dp(String ring, int i, String key, int j) {
        //base case:当j为key的最后一个的时候
        if(j==key.length()){
            return 0;
        }
        if(memo[i][j]!=0){
            return memo[i][j];
        }
        int n=ring.length();
        //开始做选择
        int res=Integer.MAX_VALUE;
        //得到key中我们此时需要输入的字符在ring中的所有的索引下标
        for (Integer k : charIndex.get(key.charAt(j))) {
            int distance=Math.abs(k-i);//求出此时的下标到ring中i位置的绝对距离
            //顺时针或者逆时针选择
            int choice=Math.min(distance,n-distance); //choice其实是这次的最小转动次数
            //接着递归下面的
            //subProblem其实是下面的问题的最小的转动次数，key中的字符肯定需要后移一位，ring中字符的位置就要从上次的k中开始了
            int subProblem=dp(ring,k,key,j+1);
            res=Math.min(res,1+choice+subProblem);//加1是因为还要按下去
        }
        memo[i][j]=res;
        return res;
    }
}
