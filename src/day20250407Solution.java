import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.04.07
 * @Since: 1.0
 */

public class day20250407Solution {
    // 42 接雨水
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] leftMaxHeight = new int[n];
        int[] rightMaxHeight = new int[n];
        leftMaxHeight[0] = height[0];
        rightMaxHeight[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            leftMaxHeight[i] = Math.max(height[i], leftMaxHeight[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMaxHeight[i] = Math.max(height[i], rightMaxHeight[i + 1]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(leftMaxHeight[i], rightMaxHeight[i]) - height[i];
        }
        return ans;
    }

    // 45 跳跃游戏 II
    public int jump(int[] nums) {
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 在可以调的范围内，可以到达的最远的地方
            farthest = Math.max(nums[i] + i, farthest);
            // 如果已经到了可以跳的边界了，那就必须跳了
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }

    // 46 全排列
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    boolean[] visited;

    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        backTrack(nums);
        return ans;
    }

    private void backTrack(int[] nums) {
        if (track.size() == nums.length) {
            ans.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            track.addLast(nums[i]);
            visited[i] = true;
            backTrack(nums);
            track.removeLast();
            visited[i] = false;
        }
    }
}
