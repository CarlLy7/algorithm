package tree;

import java.util.Random;

/**
 * @description: 排序数组  https://leetcode.cn/problems/sort-an-array/
 * @author: lyq
 * @createDate: 15/5/2023
 * @version: 1.0
 */
public class sortAnArray {
    //    //用来进行合并有序数组的辅助数组
//    int[] temp;
//    //使用归并排序算法来进行排序
//    public int[] sortArray(int[] nums) {
//        temp=new int[nums.length];
//        sort(nums,0,nums.length-1);
//        return nums;
//    }
//
//    private void sort(int[] nums, int low, int high) {
//        if(low>=high){
//            return;
//        }
//        int mid=low+(high-low)/2;
//        //排左边
//        sort(nums,low,mid);
//        //排右边
//        sort(nums,mid+1,high);
//        merge(nums,low,mid,high);
//    }
//
//    private void merge(int[] nums, int low,int mid, int high) {
//        for (int i = low; i <=high ; i++) {
//            temp[i]=nums[i];
//        }
//        if(low>=high){
//            return;
//        }
//        //两个指针，分别指向左右区间的起点
//        int i=low,j=mid+1;
//        for (int p = low; p <=high ; p++) {
//           if(i==mid+1){
//               //左边排序完毕
//               nums[p]=temp[j++];
//           }else if(j>high){
//               //右边排序完毕
//               nums[p]=temp[i++];
//           }else if(temp[i]>temp[j]){
//               nums[p]=temp[j++];
//           }else{
//               nums[p]=temp[i++];
//           }
//        }
//    }
    //使用快速排序方法来对数组进行排序
    public int[] sortArray(int[] nums) {
        //先进行洗牌算法来将nums中的数进行随机打乱
        shuffle(nums);
        sort(nums,0,nums.length-1);
        return nums;
    }

    private void sort(int[] nums, int low, int high) {
        if(low>=high){
            return;
        }
        //对一个元素进行排序，确定这个元素最后应该在哪个位置
        int patation=partition(nums,low,high);
        //对左右子数组进行递归排序
        sort(nums,low,patation-1);
        sort(nums,patation+1,high);
    }

    private int partition(int[] nums, int low, int high) {
        int pivot=nums[low];
        //定义区间[low,i)<=pivot,  (j,high]>pivot
        int i=low+1,j=high;
        while(i<=j){
            while(i<high && nums[i]<=pivot){
                i++;
            }
            while(j>low && nums[j]>pivot){
                j--;
            }
            if(i>=j){
                break;
            }
            swap(nums,i,j);
        }
        swap(nums,low,j);
        return j;
    }

    private void shuffle(int[] nums) {
        int n=nums.length;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int a=i+random.nextInt(n-i);
            swap(nums,i,a);
        }
    }

    private void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
