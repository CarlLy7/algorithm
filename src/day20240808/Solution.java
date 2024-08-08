package day20240808;

import java.util.HashSet;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-08 11:17
 * @version: 1.0
 */
public class Solution {
    /**
     * 先筛选出买大礼包比单买便宜的大礼包
     * 优先选择大礼包，当无法凑出target的时候，再单买
     */
//    List<List<Integer>> special;
//    List<Integer> price;
//    int trackCount = 0;
//    int res = Integer.MAX_VALUE;
//
//    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
//        this.price = price;
//        List<List<Integer>> newSpecial = new ArrayList<>();
//        for (int i = 0; i < special.size(); i++) {
//            List<Integer> cur = special.get(i);
//            int count = 0;
//            for (int j = 0; j < cur.size() - 1; j++) {
//                count += cur.get(j) * price.get(j);
//            }
//            if (count > cur.get(cur.size() - 1)) {
//                newSpecial.add(cur);
//            }
//        }
//        this.special = newSpecial;
//        backTrack(needs, 0);
//        return res;
//    }
//
//    private void backTrack(List<Integer> needs, int start) {
//        // 枝剪
//        if (trackCount >= res) {
//            return;
//        }
//        boolean hasUsedSpecial = false;
//        for (int i = start; i < special.size(); i++) {
//            List<Integer> curSpecial = special.get(i);
//            //判断是否能买大礼包
//            if (!canUsedSpecial(curSpecial, needs)) {
//                continue;
//            }
//            //使用大礼包
//            hasUsedSpecial = true;
//            for (int j = 0; j < needs.size(); j++) {
//                needs.set(j, needs.get(j) - curSpecial.get(j));
//            }
//            trackCount += curSpecial.get(curSpecial.size() - 1);
//            //回溯
//            backTrack(needs, i);
//            //撤销选择
//            for (int j = 0; j < needs.size(); j++) {
//                needs.set(j, needs.get(j) + curSpecial.get(j));
//            }
//            trackCount -= curSpecial.get(curSpecial.size() - 1);
//        }
//        // 无法使用大礼包
//        if (!hasUsedSpecial) {
//            int sum = 0;
//            for (int j = 0; j < needs.size(); j++) {
//                sum += needs.get(j) * price.get(j);
//            }
//            res = Math.min(res, sum + trackCount);
//        }
//    }
//
//    // 判断是否能用大礼包
//    private boolean canUsedSpecial(List<Integer> curSpecial, List<Integer> needs) {
//        for (int i = 0; i < needs.size(); i++) {
//            if (curSpecial.get(i) > needs.get(i)) {
//                return false;
//            }
//        }
//        return true;
//    }
//    int res = Integer.MAX_VALUE;
//
//    public int minimumTimeRequired(int[] jobs, int k) {
//        int[] workers = new int[k];
//        backTrack(jobs, 0, workers);
//        return res;
//    }
//
//    private void backTrack(int[] jobs, int jobIndex, int[] workers) {
//        if (jobIndex == jobs.length) {
//            int max = 0;
//            for (int cur : workers) {
//                max = Math.max(max, cur);
//            }
//            res = Math.min(res, max);
//            return;
//        }
//        HashSet<Integer> chosen = new HashSet<>();
//        for (int workerIndex = 0; workerIndex < workers.length; workerIndex++) {
//            if (workers[workerIndex] + jobs[jobIndex] > res) {
//                continue;
//            }
//            if (chosen.contains(workers[workerIndex])) {
//                continue;
//            }
//            chosen.add(workers[workerIndex]);
//            workers[workerIndex] += jobs[jobIndex];
//            backTrack(jobs, jobIndex + 1, workers);
//            //撤销选择
//            chosen.remove(workers[workerIndex]);
//            workers[workerIndex] -= jobs[jobIndex];
//        }
//    }
    int res = Integer.MAX_VALUE;

    public int distributeCookies(int[] cookies, int k) {
        int[] children = new int[k];
        backTrack(cookies, 0, children);
        return res;
    }


    private void backTrack(int[] cookies, int cookieIndex, int[] children) {
        if (cookieIndex == cookies.length) {
            int max = 0;
            for (int cur : children) {
                max = Math.max(max, cur);
            }
            res = Math.min(res, max);
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int childrenIndex = 0; childrenIndex < children.length; childrenIndex++) {
            if (children[childrenIndex] + cookies[cookieIndex] > res) {
                continue;
            }
            if (set.contains(children[childrenIndex])) {
                continue;
            }
            set.add(children[childrenIndex]);
            children[childrenIndex] += cookies[cookieIndex];
            backTrack(cookies, cookieIndex + 1, children);
            set.remove(children[childrenIndex]);
            children[childrenIndex] -= cookies[cookieIndex];
        }
    }
}
