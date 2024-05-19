package array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: 优势洗牌 https://leetcode.cn/problems/advantage-shuffle/
 * @author: lyq
 * @createDate: 6/5/2023
 * @version: 1.0
 */
public class advantageShuffle {
    //算法的思路其实很简单：如果我们两个最好的比较nums1可以干过nums2，那就让nums1最好的上，否则让nums1中最差的去送人头
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n=nums1.length;
        //对nums1进行一个排序，升序排列
        Arrays.sort(nums1);
        //因为nums2是不能改变顺序的，我们是和nums2进行对弈的，所以使用一个辅助数据结构来存储排序的结构
        //优先级队列中自定义了比较规则从大到小排列了，每个节点存储的结构是[索引，值]
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((int[] pair1, int[] pair2) -> {
            return pair2[1] - pair1[1];
        });
        for (int i = 0; i < nums2.length; i++) {
            priorityQueue.offer(new int[]{i,nums2[i]});
        }

        //两个人开始对弈
        int left=0,right=n-1;
        int[] res=new int[n];
        while(!priorityQueue.isEmpty()){
            int[] poll = priorityQueue.poll();
            if(nums1[right]>poll[1]){
                //nums1中最好的可以打过nums2中最好的，直接干
                //结果的位置要根据nums2的索引顺序来确定，因为是和nums2进行对弈的
                res[poll[0]]=nums1[right];
                right--;
            }else{
                //干不过，让最差的去送
                res[poll[0]]=nums1[left];
                left++;
            }
        }
        return res;
    }
}
