package testDemo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @description:
 * @author: lyq
 * @createDate: 11/3/2023
 * @version: 1.0
 */
public class testDemo5 {
    public static void main(String[] args) {
        //3 2 1 5  6 3 7
        //   [1,3] [2,6] [5,5]     5 6
        //1 2 3 4 5 6 7
        //1 2 3 4 5 6 7   [2,3] [5]
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        String[] start=new String[n];
        String[] end=new String[n];
        String s1 = scanner.nextLine();
        start=s1.split(" ");
        int[] intStart=new int[start.length];
        int[] intEnd=new int[start.length];
        for (int i = 0; i < start.length; i++) {
            intStart[i]=Integer.parseInt(start[i]);
        }
        String s2 = scanner.nextLine();
        end=s2.split(" ");
        for (int i = 0; i < end.length; i++) {
            intEnd[i]=Integer.parseInt(end[i]);
        }
        Line[] lines=new Line[n];
        for (int i = 0; i < n; i++) {
            lines[i]=new Line(intStart[i],intEnd[i]);
        }
        Arrays.sort(lines,new MyComparator());
        PriorityQueue<Integer> heap=new PriorityQueue<>();
        int locations=0;
        int max=0;
        for (int i = 0; i < n; i++) {
            while(!heap.isEmpty() && heap.peek()<=lines[i].start){
                heap.poll();
            }
            heap.add(lines[i].end);
            if(heap.size()>1){
                if (lines[i].end>=heap.peek()){
                    locations+=lines[i-1].end- lines[i].start;
                }else{
                    locations+=lines[i].end- lines[i].start+1;
                }
            }
            max=Math.max(max,heap.size());
        }
        System.out.print(max+" "+locations+1);
    }
    static class Line{
        private int start;
        private int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class MyComparator implements Comparator<Line>{
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start-o2.start;
        }
    }
}
