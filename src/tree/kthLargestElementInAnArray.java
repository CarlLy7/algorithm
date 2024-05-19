package tree;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @description: 数组中的第K个最大元素 https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * @author: lyq
 * @createDate: 22/5/2023
 * @version: 1.0
 */
public class kthLargestElementInAnArray {
    //使用快速选择排序算法实现
//    public int findKthLargest(int[] nums, int k) {
//        int n=nums.length;
//        //因为我们要找的是第k大的元素，其实是从大到小排列第k大的元素，如果是从小到大排列的话就是第n-k大的元素
//        k=n-k;
//        shuffle(nums);
//        int low=0,high=n-1;
//        while(low<=high){
//            int p=partition(nums,low,high);
//            if(p<k){
//                //去右边找
//               low=p+1;
//            }else if(p>k){
//                //去左边找
//               high=p-1;
//            }else{
//                return nums[p];
//            }
//        }
//        return -1;
//    }
//
//    private void shuffle(int[] nums) {
//        Random random = new Random();
//        int n=nums.length;
//        for (int i = 0; i < n; i++) {
//            int r=i+random.nextInt(n-i);
//            swap(nums,i,r);
//        }
//    }
//
//    private int partition(int[] nums, int low, int high) {
//        int pivot=nums[low];
//        int i=low+1,j=high;
//        while(i<=j){
//            while(i<high && nums[i]<=pivot){
//                i++;
//            }
//            while(j>low && nums[j]>pivot){
//                j--;
//            }
//            if(i>=j){
//                break;
//            }
//            swap(nums,i,j);
//        }
//        swap(nums,low,j);
//        return j;
//    }
//
//    private void swap(int[] nums, int i, int j) {
//        int temp=nums[i];
//        nums[i]=nums[j];
//        nums[j]=temp;
//    }

    //使用二叉堆算法实现
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            //如果二叉堆中的数多于k个了，那么就把最小的删除掉，保证堆中一共有k个元素，那么最后堆顶的元素就是最小的，也就是第k大的元素
            if(queue.size()>k){
                queue.poll();
            }
        }
        return queue.peek();
    }
}
