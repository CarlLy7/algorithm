package test;

/**
 * @description:
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class shellSort {
    public static int[] shell(int[] arr){
        int len= arr.length;
        int size=len/2;//初始步长
        while(size>0){
            for (int i = size; i < len ; i++) {
                int current=arr[i];
                int pre=i-size;
                while(pre>=0 && current<arr[pre]){
                    arr[pre+size]=arr[pre];
                    pre-=size;
                }
                arr[pre+size]=current;
            }
            size/=2;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] test = new int[]{3, 5, 2, 10, 7, 9};
        test = shell(test);
        for (int i : test) {
            System.out.print(i + ",");
        }
    }
}
