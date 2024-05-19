import java.util.*;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-02-14 15:17
 * @version: 1.0
 */
public class nSum {
    // 1. 两数之和
//    public int[] twoSum(int[] nums, int target) {
//        HashMap<Integer, Integer> map=new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            int key=target-nums[i];
//            if (map.containsKey(key)){
//                return new int[]{i,map.get(key)};
//            }
//            map.put(nums[i],i);
//        }
//        return new int[]{};
//    }

    // 167. 两数之和 II - 输入有序数组
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum < target) {
                low++;
            } else if (sum > target) {
                high--;
            } else {
                return new int[]{low + 1, high + 1};
            }
        }
        return new int[]{};
    }

    // nSum的通用算法
    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        List<List<Integer>> res = new ArrayList<>();
        int size = nums.length;
        if (n < 2 || size < n) {
            return res;
        }
        // base case twoSum
        if (n == 2) {
            int low = start, high = size - 1;
            while (low < high) {
                int sum = nums[low] + nums[high];
                int left = nums[low];
                int right = nums[high];
                if (sum < target) {
                    while (low < high && left == nums[low]) low++;
                } else if (sum > target){
                    while(low<high && right== nums[high]) high--;
                }else{
                    List<Integer> arr=new ArrayList<>();
                    arr.add(nums[low]);
                    arr.add(nums[high]);
                    res.add(arr);
                    while(low<high && left==nums[low]) low++;
                    while(low<high && right==nums[high]) high--;
                }
            }
        }else{
            for (int i = start; i <size ; i++) {
                List<List<Integer>> arrList = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> item : arrList) {
                    item.add(nums[i]);
                    res.add(item);
                }
                while(i<size-1 && nums[i]==nums[i+1]) i++;
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = nSumTarget(nums, 3, 0, 0);
        return res;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = nSumTarget(nums, 4, 0, target);
        return res;
    }
}
