package testDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @author: lyq
 * @createDate: 11/3/2023
 * @version: 1.0
 */
public class testDemo7 {
    static int[] redCount;
    static int[] buleCount;
    static int res;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        char[] cols = color.toCharArray();
        String parents = scanner.nextLine();
        char[] chars = parents.toCharArray();
        List<List<Integer>> tree=new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 1; i <n ; i++) {
            tree.get(Integer.parseInt(String.valueOf(chars[i]))).add(i+1);
        }
        redCount=new int[n+1];
        buleCount=new int[n+1];
        for (int i = 1; i <=n ; i++) {
            if(cols[i]=='R'){
                redCount[i]++;
            }else{
                buleCount[i]++;
            }
        }
        res=0;
        dfs(1,0,cols,tree);
        System.out.println(res);
    }

    private static void dfs(int node, int parent, char[] cols, List<List<Integer>> tree) {
        for (Integer integer : tree.get(node)) {
            if(integer!=parent){
                dfs(integer,node,cols,tree);
                redCount[node]+=redCount[integer];
                buleCount[node]+=buleCount[integer];
            }
        }
        if(redCount[node]==buleCount[node] && node!=1){
            res++;
        }
        if(cols[node]=='R'){
            redCount[node]++;
        }else{
            buleCount[node]++;
        }
    }
}
