import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @description:
 * @author: lyq
 * @createDate: 13/4/2023
 * @version: 1.0
 */
public class startimes {
//    public static int[] getInorder(int[] preorder) {
//        int n = preorder.length;
//        int[] inorder = new int[n];
//        getInorderHelper(preorder, 0, n - 1, inorder, 0);
//        return inorder;
//    }
//
//    public static void getInorderHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart) {
//        if (preStart > preEnd) {
//            return;
//        }
//
//        int root = preorder[preStart];
//        int index = -1;
//
//        // 找到根节点在中序遍历中的位置
//        for (int i = inStart; i < inStart + preEnd - preStart + 1; i++) {
//            if (inorder[i] == root) {
//                index = i;
//                break;
//            }
//        }
//
//        // 左子树的前序遍历和中序遍历
//        getInorderHelper(preorder, preStart + 1, preStart + index - inStart, inorder, inStart);
//        inorder[index] = root;
//        getInorderHelper(preorder, preStart + index - inStart + 1, preEnd, inorder, index + 1);
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String[] tmp = br.readLine().split(",");
//        int[] preorder = new int[tmp.length];
//        for (int i = 0; i < tmp.length; i++) {
//            preorder[i] = Integer.parseInt(tmp[i]);
//        }
//        int[] inorder = getInorder(preorder);
//
//        System.out.println(Arrays.toString(inorder));
//
//    }
public static void main(String[] args) throws IOException {
    Scanner scanner=new Scanner(System.in);
    String preOrder = scanner.nextLine();
    String inOrder = getInOrder(preOrder);
    System.out.println(inOrder); // 输出 4,2,5,1,6,3,7
}
    private static String getInOrder(String preOrder) {
        String[] nodes = preOrder.split(",");
        List<Integer> preOrderList = new ArrayList<>();
        for (String node : nodes) {
            preOrderList.add(Integer.parseInt(node));
        }

        List<Integer> inOrderList = buildTree(preOrderList);
        StringBuilder inOrder = new StringBuilder();
        for (int i = 0; i < inOrderList.size(); i++) {
            inOrder.append(inOrderList.get(i));
            if (i != inOrderList.size() - 1) {
                inOrder.append(",");
            }
        }
        return inOrder.toString();
    }

    private static List<Integer> buildTree(List<Integer> preOrderList) {
        if (preOrderList.isEmpty()) {
            return Collections.emptyList();
        }

        int rootVal = preOrderList.get(0);
        int index = 1;
        while (index < preOrderList.size() && preOrderList.get(index) < rootVal) {
            index++;
        }

        List<Integer> leftSubtree = buildTree(preOrderList.subList(1, index));
        List<Integer> rightSubtree = buildTree(preOrderList.subList(index, preOrderList.size()));

        List<Integer> inOrderList = new ArrayList<>();
        inOrderList.addAll(leftSubtree);
        inOrderList.add(rootVal);
        inOrderList.addAll(rightSubtree);
        return inOrderList;
    }
}
