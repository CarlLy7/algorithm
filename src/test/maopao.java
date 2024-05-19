package test;

/**
 * @description: 冒泡排序
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class maopao {
    public static int[] buffSort(int[] arr){
        for (int i = 1; i < arr.length; i++) { //一共有几轮冒泡排序
            boolean flag=true;
            for (int j = 0; j < arr.length-i; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    flag=false;
                }
            }
            if(flag){
                break;
            }
        }
        return arr;
    }
    public static void swap(int[] arr,int left,int right){
        int temp=arr[left];
        arr[left]=arr[right];
        arr[right]=temp;
    }

    public static void main(String[] args) {
        int[] test=new int[]{5,3,2,10,7,9};
        test= buffSort(test);
        for (int i : test) {
            System.out.print(i+",");
        }
    }
}
