package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 二叉树的层平均值  https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 * @author: lyq
 * @createDate: 11/5/2023
 * @version: 1.0
 */
public class averageOfLevelsInBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res=new LinkedList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root==null){
            return res;
        }
        queue.offer(root);
        while(!queue.isEmpty()){
            //当前层的个数
            int size=queue.size();
            double sum=0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum+=node.val;
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            Double curDouble = (double) (sum / size);
            res.add(curDouble);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left=node1;
        root.right=node2;
        node1.left=null;
        node1.right=null;
        node2.left=node3;
        node2.right=node4;
        node3.left=null;
        node3.right=null;
        node4.left=null;
        node4.right=null;
        List<Double> list = averageOfLevels(root);
        System.out.println(list);
    }
}
