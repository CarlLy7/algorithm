package test;

/**
 * @description:
 * @author: lyq
 * @createDate: 27/3/2023
 * @version: 1.0
 */
public class minimumNumberOfRotatedArraysII {
    public int minArray(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if(numbers[mid]<numbers[high]){
                high=mid;
            }else if(numbers[mid]>numbers[high]){
                low=mid+1;
            }else{
                high-=1;
            }
        }
        return numbers[low];
    }
}
