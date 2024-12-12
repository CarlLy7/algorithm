import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2024/12/12
 */

public class day20241212Solution {

    //93
//    List<String> res = new ArrayList<>();
//    LinkedList<String> track = new LinkedList<>();
//
//    public List<String> restoreIpAddresses(String s) {
//        backTrack(s, 0);
//        return res;
//    }
//
//    private void backTrack(String s, int start) {
//        // base case
//        if (track.size() == 4 && start == s.length()) {
//            res.add(String.join(",", track));
//            return;
//        }
//        for (int i = start; i < s.length(); i++) {
//            if (track.size()>=4){
//                break;
//            }
//            if (!isValid(s,start,i)){
//                continue;
//            }
//            track.addLast(s.substring(start,i+1));
//            backTrack(s,i+1);
//            track.removeLast();
//        }
//    }
//
//    private boolean isValid(String s, int start, int end) {
//        int len = end - start + 1;
//        if (len == 1) {
//            return true;
//        }
//        if (s.charAt(start) == '0') {
//            return false;
//        }
//        if (len <= 2) {
//            return true;
//        }
//        if (Integer.parseInt(s.substring(start, start + len)) > 255) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    //131
//    List<List<String>> res = new ArrayList<>();
//    LinkedList<String> track = new LinkedList<>();
//
//    public List<List<String>> partition(String s) {
//        backTrack(s, 0);
//        return res;
//    }
//
//    private void backTrack(String s, int start) {
//        if (start == s.length()) {
//            res.add(new ArrayList<>(track));
//            return;
//        }
//        for (int i = start; i < s.length(); i++) {
//            if (!isPartition(s, start, i)) {
//                continue;
//            }
//            track.add(s.substring(start, i + 1));
//            backTrack(s, i + 1);
//            track.removeLast();
//        }
//    }
//
//    private boolean isPartition(String s, int start, int end) {
//        while (start != end) {
//            if (s.charAt(start) == s.charAt(end)) {
//                start++;
//                end--;
//            } else {
//                return false;
//            }
//        }
//        return true;
//    }

    //491
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> track = new LinkedList<>();
//
//    public List<List<Integer>> findSubsequences(int[] nums) {
//        backTrack(nums, 0);
//        return res;
//    }
//
//    private void backTrack(int[] nums, int start) {
//        if (track.size() >= 2) {
//            res.add(new ArrayList<>(track));
//            return;
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
//            used.remove(nums[i]);
//            track.removeLast();
//        }
//    }

    //526
//    int ans = 0;
//    LinkedList<Integer> track = new LinkedList<>();
//    boolean[] used;
//
//    public int countArrangement(int n) {
//        used = new boolean[n + 1];
//        backTrack(n, 1);
//        return ans;
//    }
//
//    private void backTrack(int n, int index) {
//        if (index > n) {
//            ans += 1;
//            return;
//        }
//        for (int i = 1; i <= n; i++) {
//            if (used[index]) {
//                continue;
//            }
//            if (!(index % i == 0 || (i & index) == 0)) {
//                continue;
//            }
//            used[i] = true;
//            track.addLast(i);
//            backTrack(n, index + 1);
//            used[i] = false;
//            track.removeLast();
//        }
//    }

}
