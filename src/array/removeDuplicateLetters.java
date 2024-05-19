package array;

import java.util.Stack;

/**
 * @description: 去除重复字母 https://leetcode.cn/problems/remove-duplicate-letters/
 * @author: lyq
 * @createDate: 7/5/2023
 * @version: 1.0
 */
public class removeDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        //为了满足不包含重复的字母，所以使用一个boolean[]来标志是不是存在了
        //ASCII码不超过256，所以用256大小够了
        boolean[] inStack=new boolean[256];
        Stack<Character> stack=new Stack<>();
        //记录每个字符出现的次数，大小256够用了
        int[] count=new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        for (char c : s.toCharArray()) {
            //每遍历一个字符就要将计数器减一
            count[c]--;
            if(inStack[c]){
                //如果已经在栈中了，直接跳过
                continue;
            }
            //为了保证字典序，所以比较当前的字符和栈顶的字符的字典序大小
            while(!stack.empty() && stack.peek()>c){
                //因为访问这个字符的实现先count--了，所以如果count为0说明只出现了一次
                if(count[stack.peek()]==0){
                    //虽然栈顶的元素比当前元素的字典序大但是栈顶元素就出现了一次，我们不能删除了，删除了就找不回来了
                    break;
                }
                inStack[stack.peek()]=false;
                stack.pop();
            }
            stack.push(c);
            inStack[c]=true;
        }
        StringBuilder builder=new StringBuilder();
        for (Character character : stack) {
            builder.append(character);
        }
        String res = builder.toString();
        return res;
    }
}
