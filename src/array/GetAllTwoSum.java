package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 返回所有满足两数之和为target的子数组
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class GetAllTwoSum {
    public List<List<Integer>> getAllTwoSum(int[] numbers, int target) {
        Arrays.sort(numbers);
        List<List<Integer>> res = new ArrayList<>();
        if (numbers.length <= 0) {
            return null;
        }
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int left=numbers[low];
            int right=numbers[high];
            if(numbers[low]+numbers[high]<target){
                while(low<high && numbers[low]==left) low++;
            }else if(numbers[low]+numbers[high]>target){
                while(low<high && numbers[high]==right) high--;
            }else{
                List<Integer> sum=new ArrayList<>();
                sum.add(numbers[low],numbers[high]);
                res.add(sum);
            }
        }
        return res;
    }
}
