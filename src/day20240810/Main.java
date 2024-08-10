package day20240810;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-10 17:03
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("\\s+");
            int n = Integer.parseInt(split[0]);
            if (n == 0) {
                System.out.println("list is empty");
                continue;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(split[i + 1]);
            }
            for (int i = 0; i < n; i++) {
                if (i < n - 1) {
                    System.out.print(nums[i] + " ");
                }
                if (i == n - 1) {
                    System.out.println(nums[i]);
                }
            }
            int[] reverse = method(nums);
            for (int i = 0; i < reverse.length; i++) {
                if (i < reverse.length - 1) {
                    System.out.print(reverse[i] + " ");
                }
                if (i == reverse.length - 1) {
                    System.out.println(reverse[i]);
                }
            }
        }
        scanner.close();
    }

    private static int[] method(int[] nums) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (queue.isEmpty() || queue.getLast() != nums[i]) {
                queue.addLast(nums[i]);
            }
        }
        int[] ans = new int[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            ans[i] = queue.get(i);
        }
        return ans;
    }

//    private static int[] method(int[] nums) {
//        int n = nums.length;
//        int left = 0, right = n - 1;
//        while (left <= right) {
//            swap(nums, left, right);
//            left++;
//            right--;
//        }
//        return nums;
//    }

//    private static void swap(int[] nums, int left, int right) {
//        int temp = nums[left];
//        nums[left] = nums[right];
//        nums[right] = temp;
//    }
}
