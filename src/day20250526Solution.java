import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.26
 * @Since: 1.0
 */

public class day20250526Solution {

    // [145] 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> leftSub = postorderTraversal(root.left);
        List<Integer> rightSub = postorderTraversal(root.right);
        res.addAll(leftSub);
        res.addAll(rightSub);
        res.add(root.val);
        return res;
    }

    // [150] 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                Integer a = stack.pop();
                Integer b = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(b - a);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(b / a);
                        break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    // [172] 阶乘后的零
    public int trailingZeroes(int n) {
        int res = 0;
        long divisor = 5;
        while (divisor <= n) {
            res += n / divisor;
            divisor *= 5;
        }
        return res;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
