package day20240621;


/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-21 14:23
 * @version: 1.0
 */
public class Test {
    // 已知数组 A, B, 如果 A 中元素在 B 数组存在，打印出这个元素的下标，B 数组是不重复的.
// Input: [5, 3, 1, 5, 4] [5, 3]
// Output: [0, 1, 3]
//    public static void main(String[] args) {
//        int[] a = new int[]{5, 3, 1, 5, 4};
//        int[] b = new int[]{5, 3};
//        HashSet<Integer> set = new HashSet<>();
//        for (int i = 0; i < b.length; i++) {
//            set.add(b[i]);
//        }
//        List<Integer> res = new ArrayList<>();
//        for (int i = 0; i < a.length; i++) {
//            if (!set.contains(a[i])) {
//                continue;
//            }
//            res.add(i);
//        }
//        System.out.println(res);
//    }


// 现在数据库有一张表，用来存储一个多叉树，id为主键，pid 表示父节点的 id，已知 "-1" 表示根节点，现在要求打印出从根节点到每个子节点的路径（可以是无序的）。
//
// | id      | pid    |
// |---------|--------|
// | "A"     | "-1"   |
// | "A-1"   | "A"    |
// | "A-2"   | "A"    |
// | "A-3"   | "A"    |
// | "A-2-1" | "A-2"  |
// | "A-2-2" | "A-2"  |
// | "A-2-3" | "A-2"  |
//
// Input: [
//   {
//       "id": "A",
//       "pid": "-1"
//   },
//   {
//       "id": "A-1",
//       "pid": "A"
//   },
//   {
//       "id": "A-2",
//       "pid": "A"
//   },
//   {
//       "id": "A-3",
//       "pid": "A"
//   },
//   {
//       "id": "A-2-1",
//       "pid": "A-2"
//   },
//   {
//       "id": "A-2-2",
//       "pid": "A-2"
//   },
//   {
//       "id": "A-2-3",
//       "pid": "A-2"
//   }
// ]
// Output: [
//   "/A",
//   "/A/A-1",
//   "/A/A-2",
//   "/A/A-3",
//   "/A/A-2/A-2-1",
//   "/A/A-2/A-2-2",
//   "/A/A-2/A-2-3",
// ]


//    static class Node {
//        Node(String ID, String PID) {
//            this.ID = ID;
//            this.PID = PID;
//        }
//
//        public String ID; // 节点ID
//        public String PID; // 父节点ID
//    }
//
//    public static void main(String[] args) {
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(new Node("A", "-1"));
//        // nodes.add(new Node("B", "-1"));
//        nodes.add(new Node("A-1", "A"));
//        nodes.add(new Node("A-2", "A"));
//        nodes.add(new Node("A-3", "A"));
//        nodes.add(new Node("A-2-1", "A-2"));
//        nodes.add(new Node("A-2-2", "A-2"));
//        nodes.add(new Node("A-2-3", "A-2"));
//        nodes.add(new Node("A-2-4", "A-2"));
//        StringBuilder sb = new StringBuilder();
//        for (Node node : nodes) {
//            String subProblem = find(nodes, node);
//            System.out.println(subProblem);
//        }
//
//
//    }
//
//    public static String find(List<Node> nodes, Node cur) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("/" + cur.ID);
//        if (cur.PID.equals("-1")) {
//            return sb.toString();
//        }
//        for (Node node : nodes) {
//            if (node.ID.equals(cur.PID)) {
//                String sub = find(nodes, node);
//                sb.append(sub);
//            }
//        }
//        return sb.toString();
//    }


    // 从上到下找到最短路径（n个数字之和最小,n为矩阵的行数），可以从第一行中的任何元素开始，只能往下层走，同时只能走向相邻的节点，
    // 例如图中第一排 2 只能走向 第二排的 7、3；第二排的 7 可以走向第三排的 6、2、9
//
// | 5    | 8    | 1    | 2    |
// | 9    | 2    | 7    | 3    |
// | 3    | 6    | 2    | 9    |
//
// Input: [
//     [5, 8, 1, 2],
//     [4, 1, 7, 3],
//     [3, 6, 2, 9]
// ]
// Output: 4


//    public static void main(String[] args) {
//        int[][] matrix = new int[][]{
//                {5, 8, 1, 2},
//                {4, 1, 7, 3},
//                {3, 6, 2, 9}
//        };

}
