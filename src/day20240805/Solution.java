package day20240805;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-05 11:03
 * @version: 1.0
 */
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        k = n - k;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int point = partation(nums, lo, hi);
            if (point > k) {
                hi = point - 1;
            } else if (point < k) {
                lo = point + 1;
            } else {
                return nums[point];
            }
        }
        return -1;
    }

    private int partation(int[] nums, int lo, int hi) {
        int point = nums[lo];
        int i = lo + 1, j = hi;
        while (i <= j) {
            while (i < hi && nums[i] <= point) {
                i++;
            }
            while (j > lo && nums[j] > point) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

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

    //    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        //base case
//        if (root == null) {
//            return null;
//        }
//        if (root.val == p.val || root.val == q.val) {
//            return root;
//        }
//        // 左孩子为根的树中是否存在p,q
//        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
//        // 右孩子为根的树中是否存在p,q
//        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
//        if (leftNode != null && rightNode != null) {
//            return root;
//        }
//        return leftNode != null ? leftNode : rightNode;
//    }
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        int val1 = Math.min(p.val, q.val);
//        int val2 = Math.max(p.val, q.val);
//        return find(root, val1, val2);
//    }

    private TreeNode find(TreeNode root, int val1, int val2) {
        // base case
        if (root == null) {
            return null;
        }
        //如果root比val1还小，只需要找右子树
        if (root.val < val1) {
            return find(root.right, val1, val2);
        }
        // 比val2大的话，只需要找左子树
        if (root.val > val2) {
            return find(root.left, val1, val2);
        }
        // 此时 val1<=root.val<=val2，所以root是公共祖先节点
        return root;
    }

    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    }

    ;

    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            if (a == null) {
                a = q;
            } else {
                a = a.parent;
            }
            if (b == null) {
                b = p;
            } else {
                b = b.parent;
            }
        }
        return a;
    }

    boolean foundP = false, foundQ = false;

    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = find(root, p, q);
        if (!foundP || !foundQ) {
            return null;
        }
        return res;
    }

    private TreeNode find(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = find(root.left, p, q);
        TreeNode right = find(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (root.val == p.val || root.val == q.val) {
            if (root.val == p.val) {
                foundP = true;
            }
            if (root.val == q.val) {
                foundQ = true;
            }
            return root;
        }
        return left != null ? left : right;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int hl = 0, rh = 0;
        TreeNode l = root, r = root;
        while (l != null) {
            l = l.left;
            hl++;
        }
        while (r != null) {
            r = r.right;
            rh++;
        }
        // 满二叉树
        if (hl == rh) {
            return (int) (Math.pow(2, hl) - 1);
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        return build(nums, 0, n - 1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        if (start > end) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = build(nums, start, maxIndex - 1);
        root.right = build(nums, maxIndex + 1, end);
        return root;
    }

//    HashMap<Integer, Integer> valToIndex = new HashMap<>();
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        for (int i = 0; i < inorder.length; i++) {
//            valToIndex.put(inorder[i], i);
//        }
//        int n1 = preorder.length;
//        int n2 = inorder.length;
//        return build(preorder, 0, n1 - 1, inorder, 0, n2 - 1);
//    }
//
//    private TreeNode build(int[] preorder, int start1, int end1, int[] inorder, int start2, int end2) {
//        if (start1 > end1) {
//            return null;
//        }
//        int rootVal = preorder[start1];
//        TreeNode root = new TreeNode(rootVal);
//        // 根节点在中序遍历中的索引
//        int rootIndex = valToIndex.get(rootVal);
//        //1 2 3 4 5 8
//        int size = rootIndex - start2;
//        root.left = build(preorder, start1 + 1, start1 + size, inorder, start2, rootIndex - 1);
//        root.right = build(preorder, start1 + 1 + size, end1, inorder, rootIndex + 1, end2);
//        return root;
//    }

    //    HashMap<Integer, Integer> map = new HashMap<>();
//
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        for (int i = 0; i < inorder.length; i++) {
//            map.put(inorder[i], i);
//        }
//        int n1 = inorder.length;
//        int n2 = postorder.length;
//        return build(inorder, 0, n1 - 1, postorder, 0, n2 - 1);
//    }
//
//    private TreeNode build(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postStart, int postEnd) {
//        if (inorderStart > inorderEnd) {
//            return null;
//        }
//        int rootVal = postorder[postEnd];
//        int rootIndex = map.get(rootVal);
//        TreeNode root = new TreeNode(rootVal);
//        int leftSize = rootIndex - inorderStart;
//        int rightSize = inorderEnd - rootIndex;
//        root.left = build(inorder, inorderStart, rootIndex - 1, postorder, postStart, postStart + leftSize - 1);
//        root.right = build(inorder, rootIndex + 1, inorderEnd, postorder, postStart + leftSize, postEnd - 1);
//        return root;
//    }
    // 根 左 右    左 右 根
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }
        int n1 = preorder.length;
        int n2 = postorder.length;
        return build(preorder, 0, n1 - 1, postorder, 0, n2 - 1);
    }

    //preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
    private TreeNode build(int[] preorder, int preSatrt, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preSatrt > preEnd) {
            return null;
        }
        if (preSatrt == preEnd) {
            return new TreeNode(preorder[preSatrt]);
        }
        int rootVal = preorder[preSatrt];
        TreeNode root = new TreeNode(rootVal);
        //2
        int leftRoot = preorder[preSatrt + 1];
        //2
        int leftRootIndex = map.get(leftRoot);
        // 2-0+1=3
        int leftSize = leftRootIndex - postStart + 1;
        root.left = build(preorder, preSatrt + 1, preSatrt + leftSize, postorder, postStart, leftRootIndex);
        root.right = build(preorder, preSatrt + leftSize + 1, preEnd, postorder, leftRootIndex + 1, postEnd - 1);
        return root;
    }

//    List<Integer> res = new ArrayList<>();
//    //位数
//    int digist = 0;
//    //路径
//    int track = 0;
//
//    public int[] numsSameConsecDiff(int n, int k) {
//        backTrack(n, k);
//        int[] ans = new int[res.size()];
//        for (int i = 0; i < res.size(); i++) {
//            ans[i] = res.get(i);
//        }
//        return ans;
//    }
//
//    private void backTrack(int n, int k) {
//        //base case
//        if (digist == n) {
//            res.add(track);
//            return;
//        }
//        //每位的选择列表是 0-9的每一个数字
//        for (int i = 0; i <= 9; i++) {
//            if (digist == 0 && i == 0) {
//                continue;
//            }
//            if (digist > 0 && Math.abs(i - track % 10) != k) {
//                continue;
//            }
//            digist++;
//            track = track * 10 + i;
//            backTrack(n, k);
//            digist--;
//            track = track / 10;
//        }
//    }

//    int res = 0;
//    int totalCount = 0;
//    boolean[][] visited;
//    int visitedCount = 0;
//
//    public int uniquePathsIII(int[][] grid) {
//        int m = grid.length;
//        int n = grid[0].length;
//        visited = new boolean[m][n];
//        int startI = -1, startJ = -1;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1 || grid[i][j] == 0) {
//                    totalCount++;
//                }
//                if (grid[i][j] == 1) {
//                    startI = i;
//                    startJ = j;
//                }
//            }
//        }
//        dfs(grid, startI, startJ);
//        return res;
//    }
//
//    private void dfs(int[][] grid, int i, int j) {
//        int m = grid.length;
//        int n = grid[0].length;
//        if (i < 0 || i >= m || j < 0 || j >= n) {
//            return;
//        }
//        if (visited[i][j] || grid[i][j] == -1) {
//            return;
//        }
//        if (grid[i][j] == 2 && totalCount == visitedCount) {
//            res++;
//            return;
//        }
//        visited[i][j] = true;
//        visitedCount++;
//        dfs(grid, i - 1, j);
//        dfs(grid, i + 1, j);
//        dfs(grid, i, j - 1);
//        dfs(grid, i, j + 1);
//        visited[i][j] = false;
//        visitedCount--;
//    }

    int res = 0;
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public int countArrangement(int n) {
        used = new boolean[n + 1];
        backTrack(n, 1);
        return res;
    }

    private void backTrack(int n, int index) {
        // base case
        if (index >= n) {
            res++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if ((index % i != 0) && (i % index != 0)) {
                continue;
            }
            used[i] = true;
            track.addLast(i);
            backTrack(n, index + 1);
            used[i] = false;
            track.removeLast();
        }
    }
}
