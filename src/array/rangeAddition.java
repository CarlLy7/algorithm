package array;

/**
 * @description: 范围加法 https://www.lintcode.com/problem/903/
 * @author: lyq
 * @createDate: 28/4/2023
 * @version: 1.0
 */
public class rangeAddition {
    //定义的一个差分数组类，这个类里面主要包括两个主要的方法，一个是更新数组中一个范围的数，另一个方法是返回更新后的数组的结果
    class Difference {
        //差分数组
        private int[] diff;

        public Difference(int[] nums) {
            int n=nums.length;
            if(n<=0){
                return;
            }
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
    public int[] getModifiedArray(int length, int[][] updates) {
        // 初始差分数组全部为0
        int[] nums=new int[length];
        Difference difference=new Difference(nums);
        for (int[] update : updates) {
            int i=update[0];//开始更新的索引位置
            int j=update[1];//结束更新的索引位置
            int val=update[2];//更新的值
            difference.incr(i,j,val);
        }
        return difference.result();
    }
}
