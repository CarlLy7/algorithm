package day20240820;

import java.util.Scanner;
import java.util.Stack;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-20 13:44
 * @version: 1.0
 */
public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            int a = scanner.nextInt();
//            if (a == 0) {
//                break;
//            }
//            scanner.nextLine();
//            String line = scanner.nextLine();
//            String[] split = line.split("\\s+");
//            int[] nums = new int[split.length];
//            for (int i = 0; i < split.length; i++) {
//                nums[i] = Integer.parseInt(split[i]);
//            }
//            System.out.println(method(a, nums) == true ? "Yes" : "No");
//        }
//        scanner.close();
//    }
//
//    private static boolean method(int n, int[] nums) {
//        boolean[] used = new boolean[n + 1];
//        Stack<Integer> stack = new Stack<>();
//        for (int i = 0; i < nums.length; i++) {
//            while (stack.isEmpty() || stack.peek() <= nums[i]) {
//                if (stack.isEmpty()) {
//                    for (int j = 1; j <= nums[i]; j++) {
//                        if (!used[j]) {
//                            stack.push(j);
//                            used[j] = true;
//                        }
//                    }
//                    stack.pop();
//                    break;
//                } else {
//                    int topVal = stack.peek();
//                    if (topVal != nums[i]) {
//                        for (int j = topVal + 1; j <= nums[i]; j++) {
//                            if (!used[j]) {
//                                stack.push(j);
//                                used[j] = true;
//                            }
//                        }
//                        stack.pop();
//                        break;
//                    } else {
//                        stack.pop();
//                        break;
//                    }
//                }
//            }
//        }
//        return stack.isEmpty();
//    }

    public static void main(String[] args) {
        
    }
}
