package dp;

import java.util.HashMap;

/**
 * @description: 打家劫舍III https://leetcode.cn/problems/house-robber-iii/
 * @author: lyq
 * @createDate: 16/4/2023
 * @version: 1.0
 */
public class HouseRobberIII {
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

//    //先看一下自顶向下的动态规划算法   状态还是当前房子的索引  选择依然是抢或者不抢
//    //定义一个备忘录来消除重叠子问题
//    HashMap<TreeNode,Integer> memo=new HashMap<>();
//    public int rob(TreeNode root) {
//        if(root==null){
//            return 0;
//        }
//        if(memo.containsKey(root)){
//            return memo.get(root);
//        }
//        //做选择
//        //抢
//        int do_it=root.val+(root.left==null?0:(rob(root.left.left)+rob(root.left.right)))+(root.right==null?0:(rob(root.right.left)+rob(root.right.right)));
//        //不抢
//        int not_do=rob(root.left)+rob(root.right);
//        int res=Math.max(do_it,not_do);
//        memo.put(root,res);
//        return res;
//    }


    //=========================================
    //下面使用自底向上的动态规划算法
    public int rob(TreeNode root) {
        int[] res=dp(root);
        return Math.max(res[0],res[1]);
    }
    //dp函数的定义：dp函数会返回一个大小为2的数组，【0】位置表示不抢的最大值，【1】位置表示抢的最大值
    private int[] dp(TreeNode root) {
        if(root==null){
            return new int[]{0,0};
        }
        //这个过程其实相当于通过递归函数把后面的确定了，抽象来看整个算法类似于dp数组，然后从后往前遍历
        int[] left = dp(root.left);
        int[] right = dp(root.right);
        //抢,如果此时这个节点抢了，那么下面的两个节点必须是不能抢的
        int do_it=root.val+left[0]+right[0];
        //不抢，如果这个节点不抢，那么下面的节点可能抢也可能不抢，所以选择最大的就好了
        int not_do=Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        int[] res=new int[]{not_do,do_it};
        return res;
    }
}
