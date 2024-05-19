package GreedyAlgorithm;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 会议室II https://www.lintcode.com/problem/919/
 * 给你输入若干形如 [begin, end] 的区间，代表若干会议的开始时间和结束时间，请你计算至少需要申请多少间会议室。
 * @author: lyq
 * @createDate: 24/4/2023
 * @version: 1.0
 */
public class MeetingRoomsII {
    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int minMeetingRooms(List<Interval> intervals) {
        //这是一个区间调度的问题，所以我们考虑使用贪心算法
        int n = intervals.size();
        //记录每个会议的开始时间
        int[] start = new int[n];
        //记录每个会议的结束时间
        int[] end = new int[n];
        int temp = 0;
        //通过循环将所有会议的开始时间和结束时间遍历并且赋值完
        for (Interval interval : intervals) {
            start[temp] = interval.start;
            end[temp] = interval.end;
            temp++;
        }
        //将开始时间和结束时间进行一个升序排列
        Arrays.sort(start);
        Arrays.sort(end);
        //通过扫描线去扫描所有的开始和结束时间，然后如果遇到一个开始时间就让count++,
        // 遇到一个结束时间就让count--，最后看还剩下几个count，剩下的count数量就是同时有几个会议在开，也就是需要的会议室数量
        int count = 0;
        //i和j是双指针
        int i = 0, j = 0;
        int res=0;
        while (i < n && j < n) {
            //开始的时候小于结束的时间
            if(start[i]<end[j]){
                count++;
                i++;
            }else{
                //开始的时候大于等于结束的时间
                count--;
                j++;
            }
            //记录每轮的最大值
            res=Math.max(res,count);
        }
        return res;
    }
}
