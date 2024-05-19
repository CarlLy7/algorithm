package test;

/**
 * @description: 选择排序
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class xuanze {
    public static int[] chooseSort(int[] arr){
        int flag=0;
        for (int i = 0; i < arr.length-1; i++) { //进行几轮
            int min=arr[i];
            flag=i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<min){
                    min=arr[j];
                    flag=j;
                }
            }
            swap(arr,i,flag);
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
        test= chooseSort(test);
        for (int i : test) {
            System.out.print(i+",");
        }
    }
}
