package day20240610;

import java.util.Arrays;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-10 10:48
 * @version: 1.0
 */
public class Solution {
    //people = [1,2,2,3], limit = 3
    //[1,3,7] limit = 7
    ////[1,2,4,5] limit = 6
    // 3,3,4,5   5
    public int numRescueBoats(int[] people, int limit) {
        if (people.length<=1){
            return 1;
        }
       Arrays.sort(people);
       int n=people.length;
       int res=0;
       int left=0,right=n-1;
       while(left<=right){
           if (people[left]+people[right]<=limit){
               res++;
               left++;
               right--;
           }else{
               res++;
               right--;
           }
       }
       return res;
    }
}
