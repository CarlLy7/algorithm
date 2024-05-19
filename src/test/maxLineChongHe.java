package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: 最大线段重合问题：给定很多线段，每个线段都有两个数【start，end】，表示线段的开始位置和结束位置，左右都是闭区间
 *               规定： 1.线段的开始和结束位置一定都是整数值
 *                     2、线段重合区域的长度必须>=1
 *                     返回线段最多重合区域中，包含了几条线段
 *             思路： 可以使用堆来解决，使用小顶堆
 *                   按照开始位置从小到大排序，然后将每一个线段的结尾放在小根堆中，在当进入之前进行判断，如果有比自己结尾小或者等于的节点，直接弹出，
 *                   因为比自己小或者等于自己的结尾说明我们没有重合，然后将自己的结尾放进去，此时这个堆中的节点的个数就是对应的这个线段的重合线段数，
 *                   因为此时堆里面还有的节点说明线段开始在自己之前，而且结尾在自己之后，所以说明了自己是他们的重合部分
 *
 * @author: lyq
 * @createDate: 10/3/2023
 * @version: 1.0
 */
public class maxLineChongHe {
    static class Line{
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class MyComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start-o2.start;
        }
    }
    public static int getMaxLine(int[][] arr){
        Line[] lines=new Line[arr.length];
        for (int i = 0; i < arr.length ; i++) {
            lines[i]=new Line(arr[i][0],arr[i][1]);
        }
        Arrays.sort(lines,new MyComparator()); //根据自己写的比较器，将开始位置进行从小到大排序
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        int max=0;
        for (int i = 0; i < arr.length; i++) {
            while(!queue.isEmpty() && queue.peek()<=lines[i].end){
                queue.poll();
            }
            queue.add(lines[i].end);
            max=Math.max(max,queue.size());
        }
        return max;
    }
}
