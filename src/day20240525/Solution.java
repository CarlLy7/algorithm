package day20240525;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-25 15:21
 * @version: 1.0
 */
public class Solution {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int diffIndex = Math.abs(j - i);
                if (diffIndex >= indexDifference) {
                    int diff = Math.abs(nums[i] - nums[j]);
                    if (diff >= valueDifference) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] nums2NextElement = nextGreaterElement(nums2);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2NextElement.length; i++) {
            map.put(nums2[i], nums2NextElement[i]);
        }
        int n = nums1.length;
        int[] res = new int[n];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    //寻找指定元素后面第一个更大的元素
    private int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }


    HashMap<String, List<Integer>> memo = new HashMap<>();

    // 按不同优先级组合数字和运算符，计算并返回所有可能组合的结果
    public List<Integer> diffWaysToCompute(String expression) {
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }
        List<Integer> res = new ArrayList<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            // 如果是符号，就需要分
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, n));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.valueOf(expression));
        }
        memo.put(expression, res);
        return res;
    }

}
