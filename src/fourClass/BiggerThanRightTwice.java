package fourClass;

/**
 * @description:
 * @author: lyq
 * @createDate: 23/2/2023
 * @version: 1.0
 */
public class BiggerThanRightTwice {
    public static int getBiggerThanRightTwice(int[] array) {
        if (array.length < 2 || array == null) {
            return 0;
        }
        return process(array, 0, array.length - 1);
    }

    private static int process(int[] array, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(array, l, m) + process(array, m + 1, r) + merger(array, l, m, r);
    }

    private static int merger(int[] array, int l, int m, int r) {
        int ans = 0;
        int windowSize = m + 1;
        for (int i = l; i <= m; i++) {//左组不能滑出左组的边界
            while (windowSize <= r && array[i] > array[windowSize] * 2) {
                windowSize++;
            }
            ans += windowSize - m - 1;//实际满足的个数       7,9,13,45 2,3,5,6
        }
        //下面是进行排序
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            temp[i++] = array[p1] <= array[p2] ? array[p1++] : array[p2++];
        }
        while (p1 <= m) {
            temp[i++] = array[p1++];
        }
        while (p2 <= r) {
            temp[i++] = array[p2++];
        }
        for (int j = 0; j < temp.length; j++) {
            array[l + j] = temp[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test = new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        int res = getBiggerThanRightTwice(test);
        System.out.println(res);

    }
}
