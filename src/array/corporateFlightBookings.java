package array;

/**
 * @description: 航班预订统计 https://leetcode.cn/problems/corporate-flight-bookings/
 * @author: lyq
 * @createDate: 28/4/2023
 * @version: 1.0
 */
public class corporateFlightBookings {
    //定义的一个差分数组类，这个类里面主要包括两个主要的方法，一个是更新数组中一个范围的数，另一个方法是返回更新后的数组的结果
    class Difference {
        //差分数组
        private int[] diff;

        public Difference(int[] nums) {
            int n=nums.length;
            if(n<=0){
                return;
            }
            //构造差分数组
            diff=new int[n];
            diff[0]=nums[0];
            for (int i = 1; i <n ; i++) {
                diff[i]=nums[i]-nums[i-1];
            }
        }

        //在i-j这个范围内，对数据进行val更新操作，val可以是负值
        public void incr(int i,int j,int val){
            diff[i]+=val;
            if(j+1<diff.length){
                diff[j+1]-=val;
            }
        }

        //返回更新后的数组
        public int[] result(){
            int[] res=new int[diff.length];
            res[0]=diff[0];
            for (int i = 1; i < diff.length ; i++) {
                res[i]=res[i-1]+diff[i];
            }
            return res;
        }
    }
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums=new int[n];
        Difference difference=new Difference(nums);
        for (int[] booking : bookings) {
            //题目中描述下标从1开始，因为数组下标从0开始，所以我们要减1
            int i=booking[0]-1;
            int j=booking[1]-1;
            int val=booking[2];
            difference.incr(i,j,val);
        }
        return difference.result();
    }
}
