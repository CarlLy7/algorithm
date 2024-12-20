import java.util.HashMap;
import java.util.Map;

/**
 * @author: carl
 * @date: 2024/12/20
 */

public class day20241220Solution {

    //121
    public int solution(int[] prices){
        int n=prices.length;
        int[][] dp=new int[n][2];
        // base case
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for (int i = 1; i < n; i++) {
            //不持有股票
            dp[i][0]=Math.max(dp[i-1][1]+prices[i],dp[i-1][0]);
            dp[i][1]=Math.max(dp[i-1][1],-prices[i]);
        }
        return dp[n-1][0];
    }

    //473
    public boolean makesquare(int[] matchsticks) {
        // 是否能等分为4组
        return canPartitionKSubsets(matchsticks,4);
    }

    Map<Integer, Boolean> memo = new HashMap<>();

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
        sum = sum / k;
        int used = 0;
        return backTrack(k, 0, nums, 0, used, sum);
    }

    private boolean backTrack(int k, int bucketSum, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucketSum == target) {
            boolean res = backTrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) {
            return memo.get(used);
        }
        for (int i = start; i < nums.length; i++) {
            //判断是不是已经使用过了
            if (((used >> i) & 1) == 1) {
                continue;
            }
            if (bucketSum + nums[i] > target) {
                continue;
            }
            bucketSum += nums[i];
            used |= 1 << i;
            if (backTrack(k, bucketSum, nums, i + 1, used, target)) {
                return true;
            }
            bucketSum -= nums[i];
            used ^= 1 << i;
        }
        return false;
    }


    //332
//    Map<String, List<String>> graph = new HashMap<>();
//    List<String> res = new ArrayList<>();
//    List<List<String>> tickets;
//    Map<String, boolean[]> used = new HashMap<>();
//    LinkedList<String> track = new LinkedList<>();
//
//    public List<String> findItinerary(List<List<String>> tickets) {
//        this.tickets = tickets;
//        //构造邻接列表
//        for (List<String> ticket : tickets) {
//            String from = ticket.get(0);
//            String to = ticket.get(1);
//            if (!graph.containsKey(from)) {
//                graph.put(from, new ArrayList<>());
//            }
//            graph.get(from).add(to);
//        }
//        for (List<String> list : graph.values()) {
//            Collections.sort(list);
//        }
//        //初始化used
//        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
//            used.put(entry.getKey(), new boolean[entry.getValue().size()]);
//        }
//        track.add("JFK");
//        backTrack("JFK");
//        return res;
//    }
//
//    private void backTrack(String start) {
//        if (res.size() > 0) {
//            return;
//        }
//        if (track.size() == tickets.size() + 1) {
//            res.addAll(track);
//            return;
//        }
//        //如果没有这个机场
//        if (!graph.containsKey(start)) {
//            return;
//        }
//        //遍历这个起点可以到达的机场
//        List<String> endAirports = graph.get(start);
//        for (int i = 0; i < endAirports.size(); i++) {
//            String nextAirport = endAirports.get(i);
//            if (used.get(start)[i]) {
//                continue;
//            }
//            used.get(start)[i] = true;
//            track.addLast(nextAirport);
//            backTrack(nextAirport);
//            used.get(start)[i] = false;
//            track.removeLast();
//        }
//    }

    //2305
//    int res=Integer.MAX_VALUE;
//    public int distributeCookies(int[] jobs, int k){
//        int[] workload=new int[k];
//        backTrack(jobs,0,workload);
//        return res;
//    }
//
//    private void backTrack(int[] jobs, int index, int[] workload) {
//        HashSet<Integer> choose=new HashSet<>();
//        if (index==jobs.length){
//            int max=0;
//            for (int i = 0; i < workload.length; i++) {
//                max=Math.max(max,workload[i]);
//            }
//            res=Math.min(res,max);
//            return;
//        }
//        for (int i = 0; i < workload.length; i++) {
//            if (workload[i]+jobs[index]>=res){
//                continue;
//            }
//            if (choose.contains(workload[i])){
//                continue;
//            }
//            choose.add(workload[i]);
//            workload[i]+=jobs[index];
//            backTrack(jobs,index+1,workload);
//            workload[i]-=jobs[index];
//        }
//    }
}
