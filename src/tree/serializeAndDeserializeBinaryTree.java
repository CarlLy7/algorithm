package tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 二叉树的序列化与反序列化 https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
 * @author: lyq
 * @createDate: 13/5/2023
 * @version: 1.0
 */
public class serializeAndDeserializeBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //    String NULL = "#";
//    String SEP = ","
    //
//     Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        serialize(root, sb);
//        return sb.toString();
//    }
//
//    private void serialize(TreeNode root, StringBuilder sb) {
//        //按照前序遍历生成一个字符串
//        if (root == null) {
//            sb.append(NULL).append(SEP);
//            return;
//        }
//        sb.append(root.val).append(SEP);
//        serialize(root.left,sb);
//        serialize(root.right,sb);
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        //根据前序遍历的规则反序列化成一棵二叉树
//        String[] nodes = data.split(SEP);
//        LinkedList<String> linkedList=new LinkedList<>();
//        for (String node : nodes) {
//            linkedList.addLast(node);
//        }
//        return deserialize(linkedList);
//    }
//
//    private TreeNode deserialize(LinkedList<String> linkedList) {
//        if(linkedList.isEmpty()){
//            return null;
//        }
//        String rootVal = linkedList.removeFirst();
//        if(rootVal.equals(NULL)){
//            return null;
//        }
//        TreeNode root=new TreeNode(Integer.parseInt(rootVal));
//        root.left=deserialize(linkedList);
//        root.right=deserialize(linkedList);
//        return root;
//    }
    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        StringBuilder sb=new StringBuilder();
//        serialize(root,sb);
//        return sb.toString();
//    }
//
//    private void serialize(TreeNode root, StringBuilder sb) {
//        if(root==null){
//            sb.append(NULL).append(SEP);
//            return;
//        }
//        serialize(root.left,sb);
//        serialize(root.right,sb);
//        sb.append(root.val).append(SEP);
//    }
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        LinkedList<String> linkedList=new LinkedList<>();
//        for (String s : data.split(SEP)) {
//            linkedList.addLast(s);
//        }
//        return deserialize(linkedList);
//    }
//
//    private TreeNode deserialize(LinkedList<String> linkedList) {
//        if(linkedList.isEmpty()){
//            return null;
//        }
//        String rootVal = linkedList.removeLast();
//        if(rootVal.equals(NULL)){
//            return null;
//        }
//        TreeNode root=new TreeNode(Integer.parseInt(rootVal));
//        root.right=deserialize(linkedList);
//        root.left=deserialize(linkedList);
//        return root;
//    }
    String NULL = "#";
    String SEP = ",";
    //层序遍历的解法
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(cur.val).append(SEP);
            queue.offer(cur.left);
            queue.offer(cur.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] nodes = data.split(SEP);
        String rootVal = nodes[0];
        if (rootVal.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        /**
         * 下面的这个for循环类比
         * while(!queue.isEmpty()){
         *  TreeNode cur=queue.poll();
         *  if(cur.left!=null){
         *      queue.offer(cur.left);
         *  }
         *  if(cur.right!=null){
         *      queue.offer(cur.right);
         *  }
         * }
         */
        for (int i = 1; i <nodes.length ; ) {
            TreeNode parent = queue.poll();
            String leftVal = nodes[i++];
            if(!leftVal.equals(NULL)){
                parent.left=new TreeNode(Integer.parseInt(leftVal));
                queue.offer(parent.left);
            }else{
                parent.left=null;
            }
            String rightVal=nodes[i++];
            if(!rightVal.equals(NULL)){
                parent.right=new TreeNode(Integer.parseInt(rightVal));
                queue.offer(parent.right);
            }else{
                parent.right=null;
            }
        }
        return root;
    }

}
