package array;

import java.util.Random;

/**
 * @description: 按权重随机选择 https://leetcode.cn/problems/random-pick-with-weight/
 * @author: lyq
 * @createDate: 4/5/2023
 * @version: 1.0
 */
public class randomPickWithWeight {
    private int[] presum;
    private Random random=new Random();
    //构造函数主要是来构造出前缀和数组
    public randomPickWithWeight(int[] w) {
        int n=w.length;
        //前缀和数组与原始数组相比有一位的偏移
        presum=new int[n+1];
        //前缀和的第一位其实就是一个标志，没有什么意义的
        presum[0]=0;
        for (int i = 1; i <=n ; i++) {
            //w数组种i-1是因为有一位的偏移
            presum[i]=presum[i-1]+w[i-1];
        }
    }

    public int pickIndex() {
        int n=presum.length;
        //target的区间是【1，presum[n-1]】
        int target=random.nextInt(presum[n-1])+1;
        //为什么最后要减一呢？还是因为前缀和和原始数组相比有一位的偏移
        return left(presum,target)-1;
    }

    //left函数是在前缀和数组中寻找大于等于target的最小的索引
    private int left(int[] presum, int target) {
        int left=0,right= presum.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(presum[mid]==target){
                right=mid-1;
            }else if(presum[mid]<target){
                left=mid+1;
            }else if(presum[mid]>target){
                right=mid-1;
            }
        }
        return left;
    }
}
