package GreedyAlgorithm;

import java.util.Arrays;

/**
 * @description: 视频拼接 https://leetcode.cn/problems/video-stitching/
 * @author: lyq
 * @createDate: 24/4/2023
 * @version: 1.0
 */
public class videoStitching {
    public int videoStitching(int[][] clips, int time) {
        //区间调度问题再加最值问题，所以使用贪心算法，排序规则是按照开始时间进行一个升序排列，如果开始时间相同再按照结束时间降序排列，
        //因为我们开始时间相同的话，我们肯定优先选择长的那个片段，这样需要的数量就会少点
        if(time==0){
            return 0;
        }
        Arrays.sort(clips,((o1, o2) -> {
            if(o1[0]==o2[0]){
                //开始时间相同的话，降序排列
                return o2[1]-o1[1];
            }else{
                //如果开始时间不同，按照开始时间进行升序排列
                return o1[0]-o2[0];
            }
        }));
        //记录最后需要几个视频片段
        int res=0;
        //当前的结束时间
        int curEndTime=0;
        //下一次的结束时间
        int nextEndTime=0;
        int n=clips.length;
        int i=0;
        //其实我们的循环条件是从排序后的第一个片段开始遍历，一直找到视频片段开始时间大于当前结束时间的片段就结束
        while (i<n && clips[i][0]<=curEndTime){
            while (i<n && clips[i][0]<=curEndTime){
                //下一次的结束时间就是开始时间小于等于当前结束时间的片段中，结束时间最晚的那个
                nextEndTime=Math.max(nextEndTime,clips[i][1]);
                i++;
            }
            //只要while循环能进来就选中了一个片段，所以res就要加1
            res++;
            curEndTime=nextEndTime;
            if (curEndTime>=time){
                return res;
            }
        }
        //如果while循环没进去，说明没有片段开始时间是0的，所以返回-1
        return -1;
    }
}
