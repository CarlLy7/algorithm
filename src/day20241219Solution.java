import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: carl
 * @date: 2024/12/19
 */

public class day20241219Solution {

    //1723
    int res = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        // 每个工人的分配情况
        int[] workLoad = new int[k];
        backTrack(jobs, 0, workLoad);
        return res;
    }

    private void backTrack(int[] jobs, int start, int[] workLoad) {
        if (start == jobs.length) {
            int max=0;
            for (int i = 0; i < workLoad.length; i++) {
                max=Math.max(max,workLoad[i]);
            }
            res=Math.min(res,max);
            return;
        }
        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i < workLoad.length; i++) {
            if (workLoad[i]+jobs[start]>=res){
                continue;
            }
            if (set.contains(workLoad[i])){
                continue;
            }
            //做选择
            set.add(workLoad[i]);
            workLoad[i]+=jobs[start];
            backTrack(jobs,start+1,workLoad);
            //撤销
            workLoad[i]-=jobs[start];
        }
    }

    //638
//    int res = Integer.MAX_VALUE;
//    // 当前大礼包的总花费
//    int trackSum = 0;
//    List<List<Integer>> specials;
//    List<Integer> price;
//
//    public int shoppingOffers(List<Integer> price, List<List<Integer>> specials, List<Integer> needs) {
//        List<List<Integer>> newSpecials = new ArrayList<>();
//        for (int i = 0; i < specials.size(); i++) {
//            List<Integer> special = specials.get(i);
//            int totalPrice = 0;
//            for (int j = 0; j < special.size() - 1; j++) {
//                totalPrice += special.get(j) * price.get(j);
//            }
//            if (totalPrice > special.get(special.size() - 1)) {
//                newSpecials.add(special);
//            }
//        }
//        this.specials = newSpecials;
//        this.price = price;
//        backTrack(needs, 0);
//        return res;
//    }
//
//    private void backTrack(List<Integer> needs, int start) {
//        // base case:当前大礼包的钱已经比res大或者相等了，直接退出
//        if (trackSum >= res) {
//            return;
//        }
//        boolean hasUsedSpecial = false;
//
//        for (int i = start; i < specials.size(); i++) {
//            List<Integer> special = specials.get(i);
//            if (!canSpecial(needs, special)) {
//                continue;
//            }
//            hasUsedSpecial = true;
//            trackSum += special.get(special.size() - 1);
//            for (int j = 0; j < special.size() - 1; j++) {
//                needs.set(j, needs.get(j) - special.get(j));
//            }
//            backTrack(needs, i);
//
//            //撤销选择
//            trackSum -= special.get(special.size() - 1);
//            for (int j = 0; j < special.size() - 1; j++) {
//                needs.set(j, needs.get(j) + special.get(j));
//            }
//        }
//        //如果遍历一遍大礼包，发现都不能用
//        if (!hasUsedSpecial) {
//            int totalPrice = 0;
//            for (int i = 0; i < needs.size(); i++) {
//                totalPrice += needs.get(i) * price.get(i);
//            }
//            res = Math.min(res, totalPrice + trackSum);
//        }
//    }
//
//    /**
//     * 判断是否能选择大礼包
//     *
//     * @param needs
//     * @param special
//     * @return
//     */
//    private boolean canSpecial(List<Integer> needs, List<Integer> special) {
//        for (int i = 0; i < special.size() - 1; i++) {
//            if (special.get(i) > needs.get(i)) {
//                return false;
//            }
//        }
//        return true;
//    }
}
