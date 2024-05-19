package day20240519;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-19 12:25
 * @version: 1.0
 */
public class Solution {
    // 任何一个大于1的自然数，都可以转成若干个质数的乘积，所以任何一个大于1的丑数也可以看作是多个丑数的乘积
    public boolean isUgly(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }

    public int nthUglyNumber(int n) {
        int p2 = 1, p3 = 1, p5 = 1;
        int product2 = 1, product3 = 1, product5 = 1;
        int[] ugly = new int[n + 1];
        int p = 1;
        while (p <= n) {
            int min = Math.min(Math.min(product2, product3), product5);
            ugly[p] = min;
            p++;
            if (min == product2) {
                product2 = ugly[p2] * 2;
                p2++;
            }
            if (min == product3) {
                product3 = ugly[p3] * 3;
                p3++;
            }
            if (min == product5) {
                product5 = ugly[p5] * 5;
                p5++;
            }
        }
        return ugly[n];
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        //int[]: 节点值、质子、指针位置
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < primes.length; i++) {
            queue.offer(new int[]{1, primes[i], 1});
        }
        int[] ugly = new int[n + 1];
        int p = 1;
        while (p <= n) {
            int[] cur = queue.poll();
            int val = cur[0];
            int prime = cur[1];
            int index = cur[2];
            if (ugly[p - 1] != val) {
                ugly[p] = val;
                p++;
            }
            queue.offer(new int[]{ugly[index] * prime, prime, index + 1});
        }
        return ugly[n];
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        int left = 1, right = 2000000000;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (f(mid, a, b, c) < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // f函数的定义：[1...num]的所有数字中，能够被a或b或c整除的数字的个数  随着num的增加，f（）单调递增
    private long f(int num, int a, int b, int c) {
        long res = 0;
        long setA = num / a;
        long setB = num / b;
        long setC = num / c;
        long setAB = num / lcm(a, b);
        long setAC = num / lcm(a, c);
        long setBC = num / lcm(b, c);
        long setABC = num / lcm(lcm(a, b), c);
        res = setA + setB + setC - setAB - setAC - setBC + setABC;
        return  res;
    }

    // 求a,b的最小公倍数
    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    // 求a,b的最大公因数
    private long gcd(long a, long b) {
        if (a < b) {
            return gcd(b, a);
        }
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
