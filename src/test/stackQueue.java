package test;

import java.util.Stack;

/**
 * @description: 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class stackQueue {
    static Stack<Integer> stack1=new Stack<>();
    static Stack<Integer> stack2=new Stack<>();
    public static void pushQueue(int val){
        stack1.push(val);
    }
    public static int popQueue(){
      if(stack2.isEmpty() && stack1.isEmpty()){
          throw new RuntimeException("queue is empty!");
      }
      if(stack2.isEmpty()){
          while(!stack1.isEmpty()){
              stack2.push(stack1.pop());
          }
          return stack2.pop();
      }
      //如果stack2不为空的话直接将Stack2中的数据pop出来就可以了
      return stack2.pop();
    }

}
