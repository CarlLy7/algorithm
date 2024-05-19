package array;

/**
 * @description: 区域和检索 - 数组不可变 https://leetcode.cn/problems/range-sum-query-immutable/
 * @author: lyq
 * @createDate: 28/4/2023
 * @version: 1.0
 */
public class rangeSumQueryImmutable {
    class NumArray {
        //前缀和数组,presum[i]中记录了nums[i]之前的所有元素的和，不包括nums[i]
        private int[] presum;
        public NumArray(int[] nums) {
            presum=new int[nums.length+1];
            presum[0]=0;
            for (int i = 1; i <= nums.length ; i++) {
                presum[i]=presum[i-1]+nums[i-1];
            }
        }

        public int sumRange(int left, int right) {
            //得到left->right这个范围内的和
            return presum[right+1]-presum[left];
        }
    }
}
