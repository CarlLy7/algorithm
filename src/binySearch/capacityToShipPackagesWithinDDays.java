package binySearch;

/**
 * @description: 在 D 天内送达包裹的能力 https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/
 * @author: lyq
 * @createDate: 6/5/2023
 * @version: 1.0
 */
public class capacityToShipPackagesWithinDDays {
    //对于这个题目进行分析，首先target很明确就是days，自变量x就是船的运载能力 f(x)是关于x的单减函数 运载能力越大，需要运的天数越少
    public int shipWithinDays(int[] weights, int days) {
        //最小值就是运载weights中的最大值，每次要能装走一个，如果设置为1的话可能一个都装不走
        int left = 0;
        //最大装载数量就是所有元素之和，直接一次拉走,right是开区间，所以额外加一
        int right=1;
        for (int weight : weights) {
            left=Math.max(left,weight);
            right+=weight;
        }
        //因为求的是最小的x所以用左侧二分搜索算法
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(weights, mid) <= days) {
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }

    private int f(int[] weights, int x) {
        int days=0;
        for (int i = 0; i < weights.length; ) {
            int cap=x;
            while(i< weights.length){
                if(cap<weights[i]) break;
                else cap-=weights[i];
                i++;
            }
            days++;
        }
        return days;
    }
}
