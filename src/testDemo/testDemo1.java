package testDemo;

/**
 * @description:
 * @author: lyq
 * @createDate: 6/3/2023
 * @version: 1.0
 */
public class testDemo1 {
    public static void quickSort(int[] array,int low,int high){
        if(array.length<2){
            return;
        }
        if(low<high){
            int partition = partition(array, low, high);
            quickSort(array,low,partition-1);
            quickSort(array,partition+1,high);
        }
    }

    public static int partition(int[] array,int low,int high){
        int flag=array[low];
        while(low<high){
            while(low<high && array[high]>=flag) high--;
            array[low]=array[high];
            while(low<high && array[low]<=flag) low++;
            array[high]=array[low];
        }
        array[low]=flag;
        return low;
    }

    public static void main(String[] args) {
        int[] test=new int[]{5,2,3,10,7,8};
        quickSort(test,0, test.length-1);
        for (int i : test) {
            System.out.print(i+" ");
        }
    }
}
