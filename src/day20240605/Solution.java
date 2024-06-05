package day20240605;

import java.util.*;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-05 11:32
 * @version: 1.0
 */
public class Solution {
    class RandomizedSet {
        List<Integer> nums;
        Map<Integer, Integer> indexMap;

        public RandomizedSet() {
            nums = new ArrayList<>();
            indexMap = new HashMap<>();
        }

        public boolean insert(int val) {
            if (indexMap.containsKey(val)) {
                return false;
            }
            indexMap.put(val, nums.size());
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!indexMap.containsKey(val)) {
                return false;
            }
            Integer index = indexMap.get(val);
            indexMap.put(nums.get(nums.size() - 1), index);
            Collections.swap(nums, index, nums.size() - 1);
            nums.remove(nums.size() - 1);
            indexMap.remove(val);
            return true;
        }

        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }

    //    private int sz;
//    private Map<Integer, Integer> indexMap;
//
//    public Solution(int n, int[] blacklist) {
//        sz = n - blacklist.length;
//        indexMap = new HashMap<>();
//        for (int b : blacklist) {
//            indexMap.put(b, 888);
//        }
//        int last = n - 1;
//        for (int b : blacklist) {
//            // 如果本身就在黑名单范围内，直接跳过
//            if (b >= sz) {
//                continue;
//            }
//            while (indexMap.containsKey(last)) {
//                //如果此时最后一个位置的元素本身就在黑名单中，往前
//                last--;
//            }
//            indexMap.put(b, last);
//            last--;
//        }
//    }
//
//    public int pick() {
//        int index = (int) (Math.random() * sz);
//        // 如果是黑名单中的元素，直接返回value值，value存放的是映射到的白名单中的索引
//        if (indexMap.containsKey(index)) {
//            return indexMap.get(index);
//        }
//        return index;
//    }
    private int m;
    private int n;
    private int len;
    private Map<Integer, Integer> deleteToExisted;
    private Random random = new Random();

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        this.len = m * n;
        deleteToExisted = new HashMap<>();
    }

    public int[] flip() {
        int index = random.nextInt(len);
        int res = index;
        if (deleteToExisted.containsKey(index)) {
            res = deleteToExisted.get(index);
        }
        int last = len - 1;
        if (deleteToExisted.containsKey(last)) {
            last = deleteToExisted.get(last);
        }
        deleteToExisted.put(index, last);
        len--;
        return new int[]{res / n, res % n};
    }

    public void reset() {
        this.len = this.m * this.n;
        this.deleteToExisted.clear();
    }
}
