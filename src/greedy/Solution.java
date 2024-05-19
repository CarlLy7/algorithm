package greedy;

import java.util.Arrays;

/**
 * @description: 贪心算法
 * @author: 小琦
 * @createDate: 2024-02-14 10:35
 * @version: 1.0
 */
public class Solution {
    //134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
        }
        if (sum < 0) {
            return -1;
        }
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                tank=0;
                start=i+1;
            }
        }
        return start==gas.length?0:start;
    }

    //455. 分发饼干
    public int findContentChildren(int[] g, int[] s) {
        int res=0;
        int lenG=g.length;
        int lenS=s.length;
        Arrays.sort(g);
        Arrays.sort(s);
        int index=lenS-1;
        for (int i = lenG-1; i >=0; i--) {
            if (index>=0 && s[index]>=g[i]){
                res++;
                index--;
            }
        }
        return res;
    }
}
