import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: luanyingqi
 * @date: 2024/11/27
 */

public class day20241127Solution {
    //523
    public boolean checkSubarraySum(int[] nums, int k) {
        // (preSum[i]-preSum[j])%k==0 ---> preSum[i]%k==preSum[j]%k
        int n = nums.length;
        int[] preSum = new int[n];
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        for (int i = 0; i < preSum.length; i++) {
            int val = preSum[i] % k;
            if (!valToIndex.containsKey(val)) {
                valToIndex.put(val, i);
            }
        }
        for (int i = 0; i < preSum.length; i++) {
            int val = preSum[i] % k;
            if (valToIndex.containsKey(val)) {
                return i - valToIndex.get(val) + 1 >= 2;
            }
        }
        return false;
    }

    //560
    // (preSum[i]-preSum[j])==k
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> preSumToCount = new HashMap<>();
        preSumToCount.put(nums[0], 1);
        int preSum = nums[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            preSum = preSum + nums[i];
            int need = preSum - k;
            if (preSumToCount.containsKey(need)) {
                res += preSumToCount.get(need);
            }
            if (!preSumToCount.containsKey(preSum)) {
                preSumToCount.put(preSum, 1);
            } else {
                preSumToCount.put(preSum, preSumToCount.get(preSum) + 1);
            }
        }
        return res;
    }

    //325
    // (preSum[i]-preSum[j])==k   -> Math.max(i-j)
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        int preSum = nums[0];
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        valToIndex.put(preSum, 0);
        int res = 0;
        for (int i = 1; i < n; i++) {
            preSum = preSum + nums[i];
            if (!valToIndex.containsKey(preSum)) {
                valToIndex.put(preSum, i);
            }
            int need = preSum - k;
            if (valToIndex.containsKey(need)) {
                res = Math.max(res, i - valToIndex.get(need) + 1);
            }
        }
        return res;
    }

    //974
    // (preSum[i]-preSum[j])%k==0    ----> preSum[i]%k==preSum[j]%k
    public int subarraysDivByk(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> valToCount = new HashMap<>();
        int[] preSum = new int[n];
        preSum[0] = nums[0];
        int remainder = 0;
        int res = 0;
        remainder = preSum[0] % k;
        if (remainder < 0) {
            remainder += k;
        }
        valToCount.put(remainder, 1);
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
            remainder = preSum[i] % k;
            if (remainder < 0) {
                remainder += k;
            }
            if (valToCount.containsKey(remainder)) {
                res += valToCount.get(remainder);
                valToCount.put(remainder, valToCount.get(remainder) + 1);
            } else {
                valToCount.put(remainder, 1);
            }
        }
        return res;
    }

    //1124
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n];
        HashMap<Integer, Integer> valToIndex = new HashMap<>();
        preSum[0] = (hours[0] <= 8 ? -1 : 1);
        valToIndex.put(preSum[0], 0);
        int res = 0;
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + (hours[i] <= 8 ? -1 : 1);
            valToIndex.putIfAbsent(preSum[i], i);
            if (preSum[i] > 0) {
                res = Math.max(res, i + 1);
            } else {
                int need = preSum[i] - 1;
                if (valToIndex.containsKey(need)) {
                    res = Math.max(res, i - valToIndex.get(need) + 1);
                }
            }
        }
        return res;
    }

    //46
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        backTrack(nums, track, res, visited);
        return res;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res, boolean[] visited) {
        //base case
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //做选择
            track.addLast(nums[i]);
            visited[i] = true;
            backTrack(nums, track, res, visited);
            track.removeLast();
            visited[i] = false;
        }
    }

    //

}
