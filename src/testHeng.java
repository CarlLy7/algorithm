import java.util.Scanner;
import java.util.Stack;

/**
 * @description:
 * @author: lyq
 * @createDate: 23/3/2023
 * @version: 1.0
 */
public class testHeng {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        char[] array = input.toCharArray();
        StringBuilder res = new StringBuilder();
        StringBuilder res2 = new StringBuilder();
        int flag=0;
        int len = array.length;
        Stack<Character> stack = new Stack<Character>();
        if (stack.isEmpty()) {
            stack.push(array[0]);
        }
        for (int i = 1; i < len; i++) {
            if (stack.isEmpty()) {
                stack.push(array[i]);
                flag=1;
            }
            if (array[i] == '{' || array[i] == '[' || array[i] == '(') {
                stack.push(array[i]);
            } else {
                if (stack.isEmpty()) {
                    System.out.println("输入错误");
                    return;
                } else {
                    if(flag==0){
                        if (array[i] == '}') {
                            if (stack.peek().equals('{')) {
                                stack.pop();
                                res.append("红");
                            } else {
                                System.out.println("输入错误");
                                return;
                            }
                        } else if (array[i] == ']') {
                            if (stack.peek().equals('[')) {
                                stack.pop();
                                res.append("黄");
                            } else {
                                System.out.println("输入错误");
                                return;
                            }
                        } else {
                            if (stack.peek().equals('(')) {
                                stack.pop();
                                res.append("蓝");
                            } else {
                                System.out.println("输入错误");
                                return;
                            }
                        }
                    }else{
                        if (array[i] == '}') {
                            if (stack.peek().equals('{')) {
                                stack.pop();
                                res2.append("红");
                            } else {
                                System.out.println("输入错误");
                                return;
                            }
                        } else if (array[i] == ']') {
                            if (stack.peek().equals('[')) {
                                stack.pop();
                                res2.append("黄");
                            } else {
                                System.out.println("输入错误");
                                return;
                            }
                        } else {
                            if (stack.peek().equals('(')) {
                                stack.pop();
                                res2.append("蓝");
                            } else {
                                System.out.println("输入错误");
                                return;
                            }
                        }
                    }
                    }
                    }
            }
        StringBuilder reverse = res.reverse();
        String a = reverse.toString();
        String b = reverse.reverse().toString();
//        System.out.println(a + b);
        StringBuilder reverse2 = res2.reverse();
        String a2 = reverse2.toString();
        String b2 = reverse2.reverse().toString();
        String c2=a2 + b2;
        System.out.println(a+b+c2);


//        StringBuffer result = reverse.append(res.toString());
//
//        System.out.println(result);
    }
    // { 红 【黄 （蓝  蓝蓝 黄黄 红红 红红 黄黄
    //               红 黄 蓝 蓝 黄 红    红红黄黄
}
