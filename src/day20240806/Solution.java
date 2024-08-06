package day20240806;

import java.util.HashMap;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-06 15:40
 * @version: 1.0
 */
public class Solution {
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> findSubsequences(int[] nums) {
//        int n = nums.length;
//        if (n == 0) {
//            return res;
//        }
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        if (track.size() >= 2) {
//            res.add(new ArrayList<>(track));
//        }
//        HashSet<Integer> used = new HashSet<>();
//        for (int i = start; i < nums.length; i++) {
//            if (!track.isEmpty() && track.getLast() > nums[i]) {
//                continue;
//            }
//            if (used.contains(nums[i])) {
//                continue;
//            }
//            used.add(nums[i]);
//            track.addLast(nums[i]);
//            backTrack(nums, i + 1);
//            track.removeLast();
//        }
//    }

//    List<String> res = new ArrayList<>();
//    LinkedList<String> track = new LinkedList();
//
//    public List<String> restoreIpAddresses(String s) {
//        backTrack(s, 0);
//        return res;
//    }
//
//    private void backTrack(String s, int start) {
//        if (start == s.length() && track.size() == 4) {
//            res.add(String.join(".", track));
//        }
//        for (int i = start; i < s.length(); i++) {
//            if (!isValid(s, start, i)) {
//                continue;
//            }
//            if (track.size() > 4) {
//                break;
//            }
//            track.addLast(s.substring(start, i + 1));
//            backTrack(s, i + 1);
//            track.removeLast();
//        }
//    }
//
//    private boolean isValid(String s, int start, int end) {
//        int len = end - start + 1;
//        if (len <= 0 || len > 3) {
//            return false;
//        }
//        if (len == 1) {
//            return true;
//        }
//        if (s.charAt(start) == '0') {
//            return false;
//        }
//        if (Integer.parseInt(s.substring(start, end + 1)) > 255) {
//            return false;
//        }
//        return true;
//    }

//    List<String> res = new ArrayList<>();
//    StringBuilder track = new StringBuilder();
//    String[] phone = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//
//    public List<String> letterCombinations(String digits) {
//        if (digits.isEmpty()) {
//            return res;
//        }
//        backTrack(digits, 0);
//        return res;
//    }
//
//    private void backTrack(String digits, int start) {
//        if (digits.length() == track.length()) {
//            res.add(track.toString());
//            return;
//        }
//        int index = digits.charAt(start) - '0';
//        for (char c : phone[index].toCharArray()) {
//            track.append(c);
//            backTrack(digits, start + 1);
//            track.deleteCharAt(track.length() - 1);
//        }
//    }


    public boolean makesquare(int[] matchsticks) {
        //其实目的就是将数组分成四个相等的子集
        return canPartitionKSubsets(matchsticks, 4);
    }

    HashMap<Integer, Boolean> memo = new HashMap<>();

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        //使用位运算来防止重复访问
        int used = 0;
        return backTrack(k, 0, nums, 0, used, target);
    }

    /**
     * @param k             :第几个桶
     * @param bucketSum     :当前桶中的总和
     * @param nums          :数组
     * @param start:数组开始的索引
     * @param used          :判断元素是否已经被用过了
     * @param target:目标值
     * @return
     */
    private boolean backTrack(int k, int bucketSum, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucketSum == target) {
            boolean subRes = backTrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, subRes);
            return subRes;
        }
        if (memo.containsKey(used)) {
            return memo.get(used);
        }
        for (int i = start; i < nums.length; i++) {
            //如果元素已经访问过了,直接跳过
            if (((used >> i) & 1) == 1) {
                continue;
            }
            if (bucketSum + nums[i] > target) {
                continue;
            }
            used |= 1 << i;
            bucketSum += nums[i];
            //递归下一个元素是否可以放在这个桶中
            if (backTrack(k, bucketSum, nums, i + 1, used, target)) {
                return true;
            }
            used ^= 1 << i;
            bucketSum -= nums[i];
        }
        return false;
    }


}
