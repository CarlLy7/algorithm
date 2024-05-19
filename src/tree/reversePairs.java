package tree;

/**
 * @description: 翻转对 https://leetcode.cn/problems/reverse-pairs/
 * @author: lyq
 * @createDate: 15/5/2023
 * @version: 1.0
 */
public class reversePairs {
    int[] temp;
    int res=0;
    public int reversePairs(int[] nums) {
        int n= nums.length;
        temp=new int[n];
        sort(nums,0,n-1);
        return res;
    }

    private void sort(int[] nums, int low, int high) {
        if(low==high){
            return;
        }
        int mid=low+(high-low)/2;
        sort(nums,low,mid);
        sort(nums,mid+1,high);
        merger(nums,low,mid,high);
    }

    private void merger(int[] nums, int low, int mid, int high) {
        if(low==high){
            return;
        }
        for (int i = low; i <=high ; i++) {
            temp[i]=nums[i];
        }
        int start=mid+1;
        for (int i = low; i <=mid ; i++) {
            while(start<=high && (long)nums[i]>(long)nums[start]*2){
                start++;
            }
            res+=start-mid-1;
        }
        int i=low,j=mid+1;
        for (int k = low; k <=high ; k++) {
            if(i==mid+1){
                nums[k]=temp[j++];
            } else if (j==high+1) {
                nums[k]=temp[i++];
            } else if (temp[i]>temp[j]) {
                nums[k]=temp[j++];
            }else{
                nums[k]=temp[i++];
            }
        }
    }
}
