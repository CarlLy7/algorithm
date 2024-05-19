package test;

import java.util.Stack;

/**
 * @description: 给定一个入栈序列和一个出栈序列判断出栈序列是否合法
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class outStack {
    public static boolean isOutStack(int[] in,int[] out){
        if(in.length==0|| out.length==0){
            return false;
        }
        Stack<Integer> stack=new Stack<>();//需要借助这样一个栈
        int p1=0;//指向in的指针
        int p2=0;//指向out的指针
        while(p1<in.length){
            if(in[p1]!=out[p2]){
                stack.push(in[p1]);
                p1++;
            }else{
                p1++;
                p2++;
            }
        }
        if(stack.isEmpty()){
            return true;
        }else{
            while(p2<out.length){
                if(stack.pop()!=out[p2]){
                    return false;
                }else{
                    p2++;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] in=new int[]{1,2,3,4,5};
        int[] out=new int[]{4,3,5,1,2};
        System.out.println(isOutStack(in,out));
    }
}
