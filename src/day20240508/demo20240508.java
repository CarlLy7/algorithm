package day20240508;

import java.util.HashMap;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-08 21:47
 * @version: 1.0
 */
public class demo20240508 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (indexMap.containsKey(need)) {
                return new int[]{indexMap.get(need), i};
            }
            indexMap.put(nums[i], i);
        }
        return new int[]{};
    }
}
