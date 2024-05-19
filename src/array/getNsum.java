package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 返回N数之后为target的所有子数组 “模板方法”
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class getNsum {
    public static List<List<Integer>> getN(int[] nums, int n, int start, long target){
        int size= nums.length;
        List<List<Integer>> res=new ArrayList<>();
        if(n<2 || size<n){
            return res;
        }

        //如果是返回两数之和的话我们需要进行一个单独的处理
        if(n==2){
            int low=start,high=size-1;
            while(low<high){
                int l=nums[low];
                int r=nums[high];
                if(nums[low]+nums[high]<target){
                    while(low<high && nums[low]==l)
                        low++;
                }else if(nums[low]+nums[high]>target){
                    while(low<high && nums[high]==r)
                       high--;
                }else{
                    ArrayList<Integer> s = new ArrayList<>();
                    s.add(l);
                    s.add(r);
                    res.add(s);
                    while(low<high && nums[low]==l) low++;
                    while(low<high && nums[high]==r) high--;
                }
            }
        }else{ //如果不是两数之和的话我们使用递归来处理
            for (int i = start; i <size ; i++) {
                List<List<Integer>> list = getN(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> s : list) {
                    s.add(nums[i]);
                    res.add(s);
                }
                while(i<size-1&& nums[i]==nums[i+1]) i++;
            }
        }
        return res;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = getN(nums, 4, 0, target);
        return res;
    }
}
