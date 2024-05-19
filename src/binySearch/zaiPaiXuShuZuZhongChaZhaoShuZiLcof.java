package binySearch;

/**
 * @description: 在排序数组中查找数字 I https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * @author: lyq
 * @createDate: 3/5/2023
 * @version: 1.0
 */
public class zaiPaiXuShuZuZhongChaZhaoShuZiLcof {
    //找第一个索引
    public int findFirstIndex(int[] nums,int target){
        int left=0,right= nums.length-1;
        //找第一个索引
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                //当前的数等于我们的目标数的时候，不能直接返回，要进行再去左边找
                right=mid-1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }
        }
        if(left== nums.length){
            return -1;
        }
        return nums[left]==target?left:-1;
    }

    //找最后一个索引
    public int findLastIndex(int[] nums,int target){
        int left=0,right= nums.length-1;
        //找最后一个索引
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                //当前的数等于我们的目标数的时候，不能直接返回，要进行再去右边找
                left=mid+1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }
        }
        if(right<0){
            return -1;
        }
        return nums[right]==target?right:-1;
    }
    public int search(int[] nums, int target) {
        int firstIndex = findFirstIndex(nums, target);
        int lastIndex = findLastIndex(nums, target);
        if(firstIndex==-1||lastIndex==-1) {
            return 0;
        }
        return lastIndex-firstIndex+1;
    }
}
