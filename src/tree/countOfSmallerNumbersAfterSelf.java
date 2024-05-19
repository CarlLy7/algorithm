package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 计算右侧小于当前元素的个数 https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
 * @author: lyq
 * @createDate: 15/5/2023
 * @version: 1.0
 */
public class countOfSmallerNumbersAfterSelf {
    class Pair{
        //值
        private Integer val;
        //索引
        private Integer id;

        public Pair(Integer val, Integer id) {
            this.val = val;
            this.id = id;
        }
    }
    private Pair[] temp;
    private int[] count;
    public List<Integer> countSmaller(int[] nums) {
        LinkedList<Integer> res=new LinkedList<>();
        int n=nums.length;
        //记录原始的值以及索引位置
        Pair[] arr=new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i]=new Pair(nums[i],i);
        }
        temp=new Pair[n];
        count=new int[n];
        sort(arr,0,n-1);
        for (int i : count) {
            res.add(i);
        }
        return res;
    }

    private void sort(Pair[] arr, int low, int high) {
        if(low==high){
            return;
        }
        int mid= low+(high-low)/2;
        sort(arr,low,mid);
        sort(arr,mid+1,high);
        merger(arr,low,mid,high);
    }

    private void merger(Pair[] arr, int low, int mid, int high) {
        if(low>=high){
            return;
        }
        for (int i = low; i <=high ; i++) {
            temp[i]=arr[i];
        }
        int i=low,j=mid+1;
        for (int k = low; k <=high ; k++) {
            if(i==mid+1){
                arr[k]=temp[j++];
            }else if(j==high+1){
                arr[k]=temp[i++];
                count[arr[k].id]+=j-mid-1;
            }else if(temp[i].val>temp[j].val){
                arr[k]=temp[j++];
            }else{
                arr[k]=temp[i++];
                count[arr[k].id]+=j-mid-1;
            }
        }
    }
}
