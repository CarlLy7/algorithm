package day20240808;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-08 13:13
 * @version: 1.0
 */
public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        for (int i = 0; i < n; i++) {
//            minStep(scanner.nextLine());
//        }
//    }
//
//
//    private static void minStep(String str) {
//        int res = 0;
//        int empty = 0;
//        char[] chars = str.toCharArray();
//        int[] nums = new int[26];
//        for (int i = 0; i < chars.length; i++) {
//            nums[chars[i] - 'a']++;
//        }
//        for (int i = 0; i < 26; i++) {
//            if (nums[i] == 0) {
//                empty++;
//            }
//            if (nums[i] == 2) {
//                res++;
//            }
//            if (nums[i] > 2) {
//                while (nums[i] > 2) {
//                    nums[i] -= 2;
//                    empty--;
//                    res++;
//                }
//                if (nums[i] == 2) {
//                    res++;
//                }
//            }
//        }
//        System.out.println(empty > 0 ? res : res - empty);
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        for (int i = 0; i < n; i++) {
//            String line = scanner.nextLine();
//            String subStr = line.substring(1, line.length() - 1);
//            String[] split = subStr.split(",");
//            int[] nums = new int[split.length];
//            for (int j = 0; j < split.length; j++) {
//                nums[j] = Integer.parseInt(split[j]);
//            }
//            method(nums);
//        }
//    }
//
//    private static void method(int[] nums) {
//        int n = nums.length;
//        //dp[i]: 以nums[i]结尾的最长递增子序列的长度
//        int[] dp = new int[n];
//        for (int i = 0; i < dp.length; i++) {
//            dp[i] = 1;
//        }
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//        int ans = 0;
//        for (int i = 0; i < dp.length; i++) {
//            ans = Math.max(ans, dp[i]);
//        }
//        System.out.println(ans);
//    }

    // pre=ABDFGHIEC
    // mid=FDHGIBEAC

    static class TreeNode {
        private char val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }

        public TreeNode(char val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            String preStr = scanner.nextLine();
            String midStr = scanner.nextLine();
            char[] preStrCharArray = preStr.toCharArray();
            char[] midStrCharArray = midStr.toCharArray();
            TreeNode root = build(preStrCharArray, midStrCharArray);
            System.out.println(getHigh(root));
        }
        scanner.close();
    }

    //获取树的高度
    private static int getHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHigh(root.left), getHigh(root.right));
    }

    static HashMap<Character, Integer> valToIndex = new HashMap<>();

    private static TreeNode build(char[] preChars, char[] midChars) {
        for (int i = 0; i < midChars.length; i++) {
            valToIndex.put(midChars[i], i);
        }
        return buildTree(preChars, 0, preChars.length - 1, midChars, 0, midChars.length - 1);
    }

    private static TreeNode buildTree(char[] preChars, int preStart, int preEnd, char[] midChars, int midStart, int midEnd) {
        if (preStart > preEnd) {
            return null;
        }
        char rootVal = preChars[preStart];
        int rootIndex = valToIndex.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        int leftSize = rootIndex - midStart;
        root.left = buildTree(preChars, preStart + 1, preStart + leftSize, midChars, midStart, rootIndex - 1);
        root.right = buildTree(preChars, preStart + 1 + leftSize, preEnd, midChars, rootIndex + 1, midEnd);
        return root;
    }

}
