package tree;

/**
 * @description: 区间和的个数 https://leetcode.cn/problems/count-of-range-sum/
 * @author: lyq
 * @createDate: 15/5/2023
 * @version: 1.0
 */
public class countOfRangeSum {
    int res = 0;
    long[] temp;
    private int lower;
    private int upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        temp = new long[preSum.length];
        sort(preSum,0,preSum.length-1);
        return res;
    }

    private void sort(long[] preSum, int low, int high) {
        if(low==high){
            return;
        }
        int mid=low+(high-low)/2;
        sort(preSum,low,mid);
        sort(preSum,mid+1,high);
        merger(preSum,low,mid,high);
    }

    private void merger(long[] preSum, int low, int mid, int high) {
        if(low==high){
            return;
        }
        for (int i = low; i <=high ; i++) {
            temp[i]=preSum[i];
        }
        int start=mid+1,end=mid+1;
        for (int i = low; i <=mid ; i++) {
            while(start<=high && preSum[start]-preSum[i]<lower){
                //前缀和数组值相减就是这个区间内的元素和
                //区间和太小了，把start变大
                start++;
            }
            while(end<=high && preSum[end]-preSum[i]<=upper){
                end++;
            }
            res+=end-start;
        }
        int i=low,j=mid+1;
        for (int k = low; k <=high ; k++) {
            if (i == mid + 1) {
                preSum[k]=temp[j++];
            } else if (j==high+1) {
                preSum[k]=temp[i++];
            } else if (temp[i]>temp[j]) {
                preSum[k]=temp[j++];
            }else {
                preSum[k]=temp[i++];
            }
        }
    }
}
