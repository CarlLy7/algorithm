package fourClass;

import java.util.Stack;

/**
 * @description: 面试题： 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小值的功能
 * （1）它可以入栈、出栈、返回栈中元素的最小值，注意这三个操作时间复杂度都是O(1)
 * （2）设计的栈类型可以使用现成的栈结构
 * @author: lyq
 * 算法思路：
 * 使用两个栈，一个栈放数据，另一个栈放最小值
 * 如果此时这个数据比最小值栈中的数据小就把这个小的数入栈，如果比最小值栈中的栈顶元素大就把最小值栈顶元素再一次压入栈中
 * @createDate: 28/8/2022
 * @version: 1.0
 */
public class getMinStack {
    private Stack<Integer> numbersStack;
    private Stack<Integer> minStack;

    public getMinStack() {
        numbersStack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * 入栈 ,入栈的时候数据栈和最小值栈同高
     */
    public void push(int a) {
        numbersStack.push(a);
        if (minStack.isEmpty()) { //如果此时最小值栈中是空的，就把这个值压入栈中
            minStack.push(a);
        } else {
            if (a < minStack.peek()) { //如果此时的数小于最小值栈中的数就入栈
                minStack.push(a);
            } else {
                minStack.push(minStack.peek());//如果此时的数大于等于最小值栈中栈顶的元素就让栈顶元素重新入栈
            }
        }
    }

    /**
     * 出栈，出栈的时候也要保持数据栈和最小值栈保持同高
     */
    public int pop() {
        Integer ans = numbersStack.pop();
        minStack.pop();
        return ans;
    }

    /**
     * 得到最小指
     */
    public int getMinValue() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        getMinStack testStack = new getMinStack();
        testStack.push(3);
        testStack.push(2);
        testStack.push(7);
        testStack.push(6);
        testStack.push(5);
        testStack.pop();
        testStack.pop();
        testStack.pop();
        testStack.pop();

        System.out.println("入栈完成，测试开始");
        System.out.println(testStack.getMinValue());
        System.out.println("测试完成");
    }
}
