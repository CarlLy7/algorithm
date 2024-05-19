package tree;

import java.util.*;

/**
 * @description: 寻找重复的子树 https://leetcode.cn/problems/find-duplicate-subtrees/
 * @author: lyq
 * @createDate: 15/5/2023
 * @version: 1.0
 */
public class problemsFindDuplicateSubtrees {
    public class TreeNode {
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

    LinkedList<TreeNode> res = new LinkedList<TreeNode>();
    //记录每个子树以及它出现的次数
    Map<String,Integer> map=new HashMap<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        //遍历所有的节点，找到满足要求的重复的所有子树
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        if(root==null){
            return "#";
        }
        String resStr = sb.append(root.val).append(",").append(traverse(root.left)).append(traverse(root.right)).toString();
        Integer count = map.getOrDefault(resStr, 0);
        if(count==1){
            //如果已经出现一次了，就是重复的，直接加入结果，如果出现多次不处理了
            res.add(root);
        }
        map.put(resStr,count+1);
        return resStr;
    }
}
