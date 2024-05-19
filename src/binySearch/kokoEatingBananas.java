package binySearch;

/**
 * @description: 爱吃香蕉的珂珂 https://leetcode.cn/problems/koko-eating-bananas/
 * @author: lyq
 * @createDate: 6/5/2023
 * @version: 1.0
 */
public class kokoEatingBananas {
    //在这个题目中，自变量就是要求的值，也就是速度x,函数就是对于速度x来说，需要f(x)小时吃完所有香蕉 f(x)是关于x的单调递减函数
    // target也就是给我们的时间限制
    //下面的这个函数是定于f(x)的
    private int f(int[] piles, int x) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x != 0) {
                hours++;
            }
        }
        return hours;
    }

    public int minEatingSpeed(int[] piles, int h) {
        //最小速度就是每小时吃一个
        int left = 1;
        //最大速度就是吃所有堆中最大的那个，也可以利用题目给定的范围，选超出范围的最小值即可
        int right = 1000000000 + 1;
        //因为求最小速度，所以我们使用求左侧二分搜索算法
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(f(piles,mid)<=h){
                right=mid;
            }else{
                //因为f(x)是单减的函数，所以往右移
                left=mid+1;
            }
        }
        return left;
    }
}
