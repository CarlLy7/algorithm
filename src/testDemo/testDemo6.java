package testDemo;

import java.util.Scanner;

/**
 * @description:
 * @author: lyq
 * @createDate: 11/3/2023
 * @version: 1.0
 */
public class testDemo6 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt();//行
        int m= scanner.nextInt();//列
        int[][] graph=new int[n][m];
        graph[0][0]=0;//起始没有金币
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();//颜色
            char[] chars = str.toCharArray();
            for (int j = 0; j < m; j++) {
                int coins=scanner.nextInt();
                graph[i][j]=coins;
                if(chars[j]=='R' && i>0){
                    graph[i][j]+=graph[i-1][j];
                }else if(chars[j]=='B' && j>0){
                    graph[i][j]+=graph[i][j-1];
                }
            }
        }
        System.out.println(graph[n-1][m-1]+4);
    }
}
