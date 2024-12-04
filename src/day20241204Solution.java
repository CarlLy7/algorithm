import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2024/12/4
 */

public class day20241204Solution {
    /**
     * 类型一：元素无重不可以复选
     * 子集/组合问题：【1，2】和【2，1】是重复的，所以我们需要保证元素之间的相对位置，所以我们需要借助于一个index
     * 排列问题：【1，2】和【2，1】是不同的，所以不需要保证元素相对位置，但是要保证元素不能被重复选择，所以需要借助于boolean数组
     * 类型二：元素有重不可以复选
     * 子集/组合问题：要先排列将相同的元素挨在一起，既要保证元素的相对位置，又要保证相同元素的相对位置，所以需要增加一个枝减条件 nums[i]!=nums[i-1]
     * 排列问题：要先排列将相同的元素挨在一起，需要增加一个枝减的条件，如果nums[i]==nums[i-1]要增加一个!used[i-1]
     * 类型三:元素无重可以复选
     * 子集/组合问题：类型一和类型二中的子集/组合问题需要保证元素的相对位置，不能选择前面的元素，所以我们都是backTrack(nums,i+1),但是可以复选的话，其实可以选择前面的，就是backTrack(nums,i)
     * 排列问题： 排列问题简单，就是不需要使用boolean数组来标识一个元素是否被使用过了
     */

    //元素无重，可复选
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> permuteRepeat(int[] nums) {
        backTrack(nums);
        return res;
    }

    private void backTrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            track.addLast(nums[i]);
            backTrack(nums);
            track.removeLast();
        }
    }

    //39
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    int trackSum = 0;
//
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        backTrack(candidates, 0, target);
//        return res;
//    }
//
//    private void backTrack(int[] candidates, int start, int target) {
//        // base case
//        if (trackSum == target) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        if (trackSum > target) {
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            trackSum += candidates[i];
//            track.addLast(candidates[i]);
//            backTrack(candidates, i, target);
//            trackSum -= candidates[i];
//            track.removeLast();
//        }
//    }

    //47
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> premuteUnique(int[] nums) {
//        used = new boolean[nums.length];
//        Arrays.sort(nums);
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        // base case
//        if (track.size() == nums.length) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
//                continue;
//            }
//            used[i] = true;
//            track.addLast(nums[i]);
//            backTrack(nums);
//            used[i] = false;
//            track.removeLast();
//        }
//    }

    //40
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    int trackSum = 0;
//
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        backTrack(candidates, 0, target);
//        return res;
//    }
//
//    private void backTrack(int[] candidates, int start, int target) {
//        // base case
//        if (trackSum == target) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        // 超过目标了，直接中断，防止死循环
//        if (trackSum > target) {
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            if (i > start && candidates[i] == candidates[i - 1]) {
//                continue;
//            }
//            trackSum += candidates[i];
//            track.addLast(candidates[i]);
//            backTrack(candidates, i + 1, target);
//            trackSum -= candidates[i];
//            track.removeLast();
//        }
//    }


    //78
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsets(int[] nums) {
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        res.add(new ArrayList<>(track));
//        for (int i = start; i < nums.length; i++) {
//            track.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            track.removeLast();
//        }
//    }

    //77
//    List<List<Integer>> res = new LinkedList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        backTrack(n, k, 1);
//        return res;
//    }
//
//    private void backTrack(int n, int k, int start) {
//        if (track.size() == k) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = start; i <=n ; i++) {
//            track.addLast(i);
//            backTrack(n,k,i+1);
//            track.removeLast();
//        }
//    }

    //46
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] used;
//
//    public List<List<Integer>> premute(int[] nums) {
//        used = new boolean[nums.length];
//        backTrack(nums);
//        return res;
//    }
//
//    private void backTrack(int[] nums) {
//        // base case
//        if (track.size() == nums.length) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            used[i]=true;
//            track.addLast(nums[i]);
//            backTrack(nums);
//            used[i]=false;
//            track.removeLast();
//        }
//    }

    //90
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        Arrays.sort(nums);
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        res.add(new ArrayList<>(track));
//        for (int i = start; i < nums.length; i++) {
//            if (i > start && nums[i] == nums[i - 1]) {
//                continue;
//            }
//            track.addLast(nums[i]);
//            backTrack(nums,i+1);
//            track.removeLast();
//        }
//    }


}
