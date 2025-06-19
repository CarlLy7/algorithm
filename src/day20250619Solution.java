import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.19
 * @Since: 1.0
 */

public class day20250619Solution {
    // [429] N 叉树的层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> track = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                track.addLast(curNode.val);
                if (curNode.children.size() > 0) {
                    for (int j = 0; j < curNode.children.size(); j++) {
                        queue.offer(curNode.children.get(j));;
                    }
                }
            }
            res.add(track);
        }
        return res;
    }

    // [814] 二叉树剪枝
    public TreeNode pruneTree(TreeNode root) {
        // 分解问题+后序位置
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    // [842] 将数组拆分成斐波那契序列
    public List<Integer> splitIntoFibonacci(String num) {
        int n = num.length();
        for (int i = 1; i <= n; i++) {
            String first = num.substring(0, i);
            for (int j = i + 1; j <= n; j++) {
                String second = num.substring(i, j);
                LinkedList<Integer> res = isValid(num, first, second);
                if (res != null) {
                    return res;
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * 在num中，从first和second开始累加，判断是否满足累加和
     * 
     * @param num
     * @param first
     * @param second
     * @return
     */
    private LinkedList<Integer> isValid(String num, String first, String second) {
        if (first.startsWith("0") && first.length() > 1 || second.startsWith("0") && second.length() > 1) {
            return null;
        }
        // 判断是否溢出
        if (first.length() > 10 || Long.parseLong(first) > Integer.MAX_VALUE || second.length() > 10
            || Long.parseLong(second) > Integer.MAX_VALUE) {
            return null;
        }
        String sub = strAdd(first, second);
        // 找第三部分
        String next = num.substring(first.length() + second.length());
        // 如果第三个值的开头不是前两个数累加而来的，说明肯定不是
        if (!next.startsWith(sub)) {
            return null;
        }
        if (sub.length() > 10 || Long.parseLong(sub) > Integer.MAX_VALUE) {
            return null;
        }
        // 如果第三部分刚好等于前两部分之和，说明整个字符串找完了
        if (next.equals(sub)) {
            LinkedList<Integer> res = new LinkedList<>();
            res.add(Integer.parseInt(first));
            res.add(Integer.parseInt(second));
            res.add(Integer.parseInt(next));
            return res;
        }
        // 如果第三部分不完全等于前两部分之和，则继续往后分解子问题处理
        LinkedList<Integer> subRes = isValid(num.substring(first.length()), second, sub);
        if (subRes == null) {
            return null;
        }
        subRes.addFirst(Integer.parseInt(first));
        return subRes;
    }

    /**
     * 累加
     * 
     * @param first
     * @param second
     * @return
     */
    private String strAdd(String first, String second) {
        int add = 0;
        int i = first.length() - 1;
        int j = second.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || add > 0) {
            int x = i >= 0 ? first.charAt(i) - '0' : 0;
            int y = j >= 0 ? second.charAt(j) - '0' : 0;
            int res = x + y + add;
            add = res / 10;
            sb.append(res % 10);
            i--;
            j--;
        }
        return sb.reverse().toString();
    }

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
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
