package testDemo;

import java.util.Scanner;

/**
 * @description:
 * @author: lyq
 * @createDate: 11/3/2023
 * @version: 1.0
 */
public class testDemo4 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();
        if(str.length()<2){
            System.out.println(0);
        }
        int len=str.length();
        char[] array = str.toCharArray();
        int min=0;
        for (int i = 0; i < len-1; i++) {
            // 2个的话是1个， 3个的话是1个，4个的话是2个  5个的话是2个
            // 111222333  11551111
            if(array[i]==array[i+1]){
                min++;
                i++;
            }
        }
        System.out.println(min);
    }


}
