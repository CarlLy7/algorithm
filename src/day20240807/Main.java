package day20240807;

import java.util.Scanner;

/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-07 12:01
 * @version: 1.0
 */
public class Main {
    //    public static Set<Long> method(HashMap<Long, Set<Integer>> map, Set<Integer> targetList) {
//        Set<Long> res = new HashSet<>();
//        for (Integer vlanId : targetList) {
//            for (Map.Entry<Long, Set<Integer>> entry : map.entrySet()) {
//                if (!entry.getValue().contains(vlanId)) {
//                    continue;
//                }
//                res.add(entry.getKey());
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int M = scanner.nextInt();
//        Set<Integer> VLANList = new HashSet<>();
//        for (int i = 0; i < M; i++) {
//            VLANList.add(scanner.nextInt());
//        }
//        scanner.nextLine();
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        HashMap<Long, Set<Integer>> map = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            String line = scanner.nextLine();
//            String[] split = line.split(" ");
//            map.put(Long.valueOf(split[0]), new HashSet<>());
//            for (int j = 2; j < split.length; j++) {
//                map.get(Long.valueOf(split[0])).add(Integer.parseInt(split[j]));
//            }
//        }
//        Set<Long> res = method(map, VLANList);
//        List<Long> ans = res.stream().sorted().collect(Collectors.toList());
//        System.out.println(ans.size());
//        for (int i = 0; i < ans.size(); i++) {
//            System.out.print(ans.get(i) + " ");
//        }
//    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String res = "";
        while (true) {
            String doIt = scanner.nextLine();
            if ("end".equals(doIt)) {
                break;
            }

            res = method(doIt);
        }
        System.out.println(res);
    }

    //记录游标的索引位置
    static int index = 0;
    static StringBuilder sb = new StringBuilder();

    private static String method(String doIt) {
        String[] split = doIt.split(" ");
        switch (split[0]) {
            case "insert":
                if (index < sb.length()) {
                    String str = sb.toString();
                    String strFirst = str.substring(0, index);
                    String strLast = str.substring(index);
                    sb.delete(0, sb.length());
                    sb.append(strFirst).append(split[1]).append(strLast);
                } else {
                    sb.append(split[1]);
                }
                index += split[1].length();
                break;
            case "move":
                int moveStep = Integer.parseInt(split[1]);
                if (moveStep > 0) {
                    //
                    if (sb.length() - index < moveStep) {
                        break;
                    }
                } else if (moveStep < 0) {
                    if ((-moveStep > index)) {
                        break;
                    }
                }
                index += moveStep;
                break;
            case "delete":
                int deleteStep = Integer.parseInt(split[1]);
                if (deleteStep <= 0) {
                    break;
                }
                if (deleteStep > index) {
                    break;
                }
                //test|  deleteStep=2   te|  2 4
                sb.delete(index - deleteStep, index);
                index -= deleteStep;
                break;
            case "copy":
                if (index > 0) {
                    String str = sb.toString();
                    String subStr = str.substring(0, index);
                    String subStrLast = str.substring(index);
                    sb.delete(0, sb.length());
                    sb.append(subStr + subStr + subStrLast);
                }
                break;
        }
        String str = sb.toString();
        String res = str.substring(0, index) + "|" + str.substring(index);
        return res;
    }

    //insert test  res=test index=4
    //insert hellohuawei res=testhellohuawei index=15
    //move -6 res=testhello|huawei index=15-6=9  [4,9)
    //delete 5  res=test|huawei index=4
    //insert practice  res=testpractice|huawei index=12
    //copy res=testpractice|testpracticehuawei index=12
}
