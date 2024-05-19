import java.util.LinkedList;
import java.util.Stack;

/**
 * @description:
 * @author: lyq
 * @createDate: 17/5/2023
 * @version: 1.0
 */
public class Solution {
    //    public char findNextGreaterLetter (char[] chars, char target) {
//        // write code here
//        for (char aChar : chars) {
//            if(aChar>target){
//                return aChar;
//            }
//        }
//        return chars[0];
//    }
    public static int calc(String expression) {
        // write code here
        //1*2*3+4/2  [2,3,4,2]   [*,+,/]
        char[] chars = expression.toCharArray();
        Stack<Character> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        if (chars.length % 2 != 1) {
            return -1;
        }
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                //如果是数字，压入数字栈中
                numStack.push(Integer.parseInt(String.valueOf(aChar)));
            } else {
                //如果不是数字
                if (strStack.empty()) {
                    //如果此时运算符栈是空的直接压入栈中
                    strStack.push(aChar);
                } else {
                    if (aChar == '*' && (strStack.peek().equals('*') || strStack.peek().equals('/'))) {
                        Character peek = strStack.pop();
                        if (peek.equals('*')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 * num1);
                        }
                        if (peek.equals('/')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 / num1);
                        }
                        strStack.push(aChar);
                    }
                    if (aChar == '/' && (strStack.peek() == '*' || strStack.peek() == '/')) {
                        Character peek = strStack.pop();
                        if (peek.equals('*')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 * num1);
                        }
                        if (peek.equals('/')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 / num1);
                        }
                        strStack.push(aChar);
                    }
                    if ((aChar == '*' || aChar == '/') && (strStack.peek().equals('+') || strStack.peek().equals('-'))) {
                        strStack.push(aChar);
                    }
                    if (aChar == '+' && (strStack.peek() == '+' || strStack.peek() == '-')) {
                        Character peek = strStack.pop();
                        if (peek.equals('+')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 + num1);
                        }
                        if (peek.equals('-')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 - num1);
                        }
                        strStack.push(aChar);
                    }
                    if (aChar == '-' && (strStack.peek().equals('+') || strStack.peek().equals('-'))) {
                        Character peek = strStack.pop();
                        if (peek.equals('+')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 + num1);
                        }
                        if (peek.equals('-')) {
                            Integer num1 = numStack.pop();
                            Integer num2 = numStack.pop();
                            numStack.push(num2 - num1);
                        }
                        strStack.push(aChar);
                    }
                    if ((aChar == '+' || aChar == '-') && (strStack.peek().equals('*') || strStack.peek().equals( '/'))) {
                        LinkedList<Character> list=new LinkedList<>();
                        while(strStack.peek().equals('*')||strStack.peek().equals('/')){
                           list.addLast(strStack.pop());
                           if(strStack.size()==0){
                               break;
                           }
                        }
                        strStack.push(aChar);
                        while(list.size()>0){
                            strStack.push(list.removeFirst());
                        }
                    }
                }
            }
        }
        while (strStack.size() > 0) {
            Character pop = strStack.pop();
            if (pop.equals('+')) {
                Integer num1 = numStack.pop();
                Integer num2 = numStack.pop();
                numStack.push(num2 + num1);
            }
            if (pop.equals('-')) {
                Integer num1 = numStack.pop();
                Integer num2 = numStack.pop();
                numStack.push(num2 - num1);
            }
            if (pop.equals('*')) {
                Integer num1 = numStack.pop();
                Integer num2 = numStack.pop();
                numStack.push(num2 * num1);
            }
            if (pop.equals('/')) {
                Integer num1 = numStack.pop();
                Integer num2 = numStack.pop();
                numStack.push(num2 / num1);
            }
        }
        return numStack.pop();
    }

    public static void main(String[] args) {
        int calc = calc("1*2*3+4/2");
        System.out.println(calc);
    }
}
