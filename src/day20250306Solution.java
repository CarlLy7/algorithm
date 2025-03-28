//import java.util.*;
//
///**
// * @author: carl
// * @date: 2025.03.06
// */
//
//public class day20250306Solution {
//    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode(int x) {
//            val = x;
//        }
//    }
//
//    // 863
//    HashMap<Integer, TreeNode> parentNode = new HashMap<>();
//
//    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
//        traverse(root, null);
//        Queue<TreeNode> q = new LinkedList<>();
//        HashSet<Integer> visited = new HashSet<>();
//        q.offer(target);
//        visited.add(target.val);
//        int distance = 0;
//        List<Integer> res = new ArrayList<>();
//        while (!q.isEmpty()) {
//            int size = q.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode curNode = q.poll();
//                if (distance == k) {
//                    res.add(curNode.val);
//                }
//                TreeNode parent = parentNode.get(curNode.val);
//                if (parent != null && !visited.contains(parent.val)) {
//                    q.offer(parent);
//                    visited.add(parent.val);
//                }
//                if (curNode.left != null && !visited.contains(curNode.left.val)) {
//                    q.offer(curNode.left);
//                    visited.add(curNode.left.val);
//                }
//                if (curNode.right != null && !visited.contains(curNode.right.val)) {
//                    q.offer(curNode.right);
//                    visited.add(curNode.right.val);
//                }
//            }
//            distance++;
//        }
//        return res;
//    }
//
////    private void traverse(TreeNode node, TreeNode parent) {
////        if (root == null) {
////            return;
////        }
////        parentNode.put(node.val, parent);
////        traverse(node.left, node);
////        traverse(node.right, node);
////    }
//
//    // 341
//    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
//        int n = rooms.size();
//        boolean[] visited = new boolean[n];
//        Queue<Integer> q = new LinkedList<>();
//        q.offer(0);
//        visited[0] = true;
//        while (!q.isEmpty()) {
//            int cur = q.poll();
//            for (int nextRoom : rooms.get(cur)) {
//                if (!visited[nextRoom]) {
//                    visited[nextRoom] = true;
//                    q.offer(nextRoom);
//                }
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // 1306
//    public boolean canReach(int[] arr, int start) {
//        boolean[] visited = new boolean[arr.length];
//        Queue<Integer> q = new LinkedList<>();
//        q.offer(start);
//        visited[start] = true;
//        while (!q.isEmpty()) {
//            int cur = q.poll();
//            if (arr[cur] == 0) {
//                return true;
//            }
//            int left = cur - arr[cur];
//            if (left >= 0 && !visited[left]) {
//                q.offer(left);
//                visited[left] = true;
//            }
//            int right = cur + arr[cur];
//            if (right < arr.length && !visited[right]) {
//                q.offer(right);
//                visited[right] = true;
//            }
//        }
//        return false;
//    }
//
//}
