import java.util.*;

/**
 * @author: carl
 * @date: 2025/1/2
 */

public class day20250102Solution {

    //787
    int src;
    int dst;
    int[][] memo;
    HashMap<Integer, List<int[]>> map;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        this.src = src;
        this.dst = dst;
        map = new HashMap<>();
        K++;
        memo = new int[n][K + 1];
        for (int[] ints : memo) {
            Arrays.fill(ints, -888);
        }
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            map.putIfAbsent(to, new LinkedList<>());
            map.get(to).add(new int[]{from, price});
        }
        return dp(dst, K);
    }

    private int dp(int s, int k) {
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        if (memo[s][k] != -888) {
            return memo[s][k];
        }
        int res = Integer.MAX_VALUE;
        if (map.containsKey(s)) {
            for (int[] in : map.get(s)) {
                int from = in[0];
                int price = in[1];
                int subProblem = dp(from, k - 1);
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }
        memo[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[s][k];
    }

}
