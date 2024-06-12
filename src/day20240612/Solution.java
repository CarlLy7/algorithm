package day20240612;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-12 18:02
 * @version: 1.0
 */
public class Solution {
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        // 四舍五入的一个方法
        return 100-(purchaseAmount+5)/10*10;
    }
}
