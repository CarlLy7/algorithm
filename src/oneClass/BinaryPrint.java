package oneClass;

/**
 * @description:
 * @author: lyq
 * @createDate: 27/7/2022
 * @version: 1.0
 */
public class BinaryPrint {
    public static void main(String[] args) {
        int num = Integer.MIN_VALUE;
        print(num);
        print(num >> 1);// >> 表示带符号右移
        print(num >>> 1); // >>> 表示不带符号右移
    }

    public static void print(int num) {
        /**
         * 算法思路：
         *   因为整型是32位的，所以我们要想把每一位的二进制打印出来，我们想打印哪一位的时候我们就让这一位和1进行与运算，其余位都是和0进行与运算
         */
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();

    }
}
