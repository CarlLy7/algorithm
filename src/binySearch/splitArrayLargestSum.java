package binySearch;

/**
 * @description: 分割数组的最大值 https://leetcode.cn/problems/split-array-largest-sum/
 * @author: lyq
 * @createDate: 6/5/2023
 * @version: 1.0
 */
public class splitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        //最小值就是运载weights中的最大值，每次要能装走一个，如果设置为1的话可能一个都装不走
        int left = 0;
        //最大装载数量就是所有元素之和，直接一次拉走,right是开区间，所以额外加一
        int right=1;
        for (int weight : nums) {
            left=Math.max(left,weight);
            right+=weight;
        }
        //因为求的是最小的x所以用左侧二分搜索算法
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(nums, mid) <= k) {
                right = mid;
            } else{
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
