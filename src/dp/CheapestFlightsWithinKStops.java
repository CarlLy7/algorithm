package dp;

import java.util.*;

/**
 * @description: K 站中转内最便宜的航班 https://leetcode.cn/problems/cheapest-flights-within-k-stops/
 * @author: lyq
 * @createDate: 17/4/2023
 * @version: 1.0
 */
public class CheapestFlightsWithinKStops {
    //使用动态规划来解决这个问题，首先要明确状态：目的地，可以中转的次数   选择：走哪条路
    //这个问题可以分解为目的地相邻的节点的最小路径和+到目的地的路径长度取最小值
    //求每个节点的入度
    //形式 目的地->【来源，路径长度】
    HashMap<Integer, List<int[]>> indegree = new HashMap<>();
    int src, destination;
    //使用备忘录来消除重叠子问题
    int[][] memo;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //k次中转意味着可以选择k+1条路径
        k++;
        this.src = src;
        this.destination = dst;
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[]{from, price});
        }
        memo=new int[n][k+1];
        for (int[] ints : memo) {
            Arrays.fill(ints,-888);
        }
        return dp(destination,k);
    }

    //dp函数的定义：从出发地在k步之内到目的地destination的最小路径和
    private int dp(int destination, int k) {
        //base case
        if(destination==src){
            return 0;
        }
        if(k==0){
            return -1;
        }
        if(memo[destination][k]!=-888){
            return memo[destination][k];
        }
        int res=Integer.MAX_VALUE;
        if(indegree.containsKey(destination)){
            for (int[] fromAndPrice : indegree.get(destination)) {
                //求出目的地的入度的节点的最小路径和
                int subProblem=dp(fromAndPrice[0],k-1);
                //下面的步骤就是保证取出目的地的所有入度的节点到达目的地最小的路径和
                if(subProblem!=-1){
                    res=Math.min(res,subProblem+fromAndPrice[1]);
                }
            }
        }
        memo[destination][k]=res==Integer.MAX_VALUE?-1:res;
        return memo[destination][k];
    }

}
