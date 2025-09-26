package hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.09.26
 * @Since: 1.0
 */

public class day20250926Solution {
    // [228] 汇总区间
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // 要不就是结束了，要不就是找到下一个区间了
            if (j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if (i != j) {
                    sb.append("->").append(nums[j]);
                }
                res.add(sb.toString());
                i = j + 1;
            }
        }
        return res;
    }

    // [57] 插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        list.add(newInterval);
        // 先按照起始进行从小到大排序，然后如果开始相同按照end从小到大排序
        list.sort((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        List<int[]> res = new ArrayList<>();
        int[] first = list.get(0);
        int start = first[0];
        int end = first[1];
        int from = start;
        for (int i = 1; i < list.size(); i++) {
            int[] cur = list.get(i);
            from = cur[0];
            int to = cur[1];
            if (end < from) {
                res.add(new int[] {start, end});
                start = from;
                end = to;
            } else {
                if (end <= to) {
                    end = to;
                }
            }
        }
        res.add(new int[] {start, end});
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = res.get(i)[0];
            ans[i][1] = res.get(i)[1];
        }
        return ans;
    }
}
