package day20240814;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-14 09:45
 * @version: 1.0
 */
public class Solution {
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

//    LinkedList<Integer> res = new LinkedList<>();
//    // 记录voyage中的索引位置
//    int i = 0;
//    int[] voyage;
//    // 判断是否能通过翻转来实现
//    boolean canFlip = true;
//
//    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
//        this.voyage = voyage;
//        traverse(root);
//        if (canFlip) {
//            return res;
//        }
//        return Arrays.asList(-1);
//    }
//
//    private void traverse(TreeNode root) {
//        if (root == null || !canFlip) {
//            return;
//        }
//        // 翻转只能够翻转左右子树，不能改变当前节点值，所以这种情况一定是不可以翻转的
//        if (root.val != voyage[i++]) {
//            canFlip = false;
//            return;
//        }
//        if (root.left != null && root.left.val != voyage[i]) {
//            TreeNode tempLeft = root.left;
//            root.left = root.right;
//            root.right = tempLeft;
//            res.add(root.val);
//        }
//        traverse(root.left);
//        traverse(root.right);
//    }

//    public class Triple {
//        int row;
//        int col;
//        TreeNode node;
//
//        public Triple(TreeNode node, int row, int col) {
//            this.node = node;
//            this.row = row;
//            this.col = col;
//        }
//    }
//
//    public List<List<Integer>> verticalTraversal(TreeNode root) {
//        traverse(root, 0, 0);
//        Collections.sort(nodes, (Triple a, Triple b) -> {
//            if (a.row == b.row && a.col == b.col) {
//                return a.node.val - b.node.val;
//            }
//            if (a.col == b.col) {
//                return a.row - b.row;
//            }
//            return a.col - b.col;
//        });
//        LinkedList<List<Integer>> res = new LinkedList<>();
//        int preCol = Integer.MIN_VALUE;
//        for (int i = 0; i < nodes.size(); i++) {
//            if (preCol != nodes.get(i).col) {
//                preCol = nodes.get(i).col;
//                res.add(new ArrayList<>());
//            }
//            res.getLast().add(nodes.get(i).node.val);
//        }
//        return res;
//    }
//
//    List<Triple> nodes = new ArrayList<>();
//
//    private void traverse(TreeNode root, int row, int col) {
//        if (root == null) {
//            return;
//        }
//        nodes.add(new Triple(root, row, col));
//        traverse(root.left, row + 1, col - 1);
//        traverse(root.right, row + 1, col + 1);
//    }

//    TreeNode parentX;
//    TreeNode parentY;
//    int depthX;
//    int depthY;
//    int x;
//    int y;
//
//    public boolean isCousins(TreeNode root, int x, int y) {
//        this.x = x;
//        this.y = y;
//        traverse(root, 0, null);
//        if (parentX != parentY && depthX == depthY) {
//            return true;
//        }
//        return false;
//    }
//
//    private void traverse(TreeNode root, int depth, TreeNode parent) {
//        if (root == null) {
//            return;
//        }
//        if (root.val == x) {
//            parentX = parent;
//            depthX = depth;
//        }
//        if (root.val == y) {
//            parentY = parent;
//            depthY = depth;
//        }
//        traverse(root.left, depth + 1, root);
//        traverse(root.right, depth + 1, root);
//    }


//    public TreeNode replaceValueInTree(TreeNode root) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        root.val = 0;
//        queue.offer(root);
//        while (!queue.isEmpty()) {
//            // 记录每一层的和
//            int sum = 0;
//            int size = queue.size();
//            for (TreeNode node : queue) {
//                if (node.left != null) {
//                    sum += node.left.val;
//                }
//                if (node.right != null) {
//                    sum += node.right.val;
//                }
//            }
//            //记录非堂兄弟的节点的和
//            while (size-- > 0) {
//                TreeNode curNode = queue.poll();
//                int sumOfxy = (curNode.left == null ? 0 : curNode.left.val) + (curNode.right == null ? 0 : curNode.right.val);
//                if (curNode.left != null) {
//                    curNode.left.val = sum - sumOfxy;
//                    queue.offer(curNode.left);
//                }
//                if (curNode.right != null) {
//                    curNode.right.val = sum - sumOfxy;
//                    queue.offer(curNode.right);
//                }
//            }
//        }
//        return root;
//    }


    public int findJudge(int n, int[][] trust) {
        int[] inSum = new int[n + 1];
        int[] outSum = new int[n + 1];
        for (int[] ints : trust) {
            int x = ints[0];
            int y = ints[1];
            inSum[y]++;
            outSum[x]++;
        }
        for (int i = 1; i <= n; i++) {
            if (inSum[i] == n - 1 && outSum[i] == 0) {
                return i;
            }
        }
        return -1;
    }

}
