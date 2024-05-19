package test;

/**
 * @description:
 * @author: lyq
 * @createDate: 22/3/2023
 * @version: 1.0
 */
public class BinarySum {
    public String addBinary(String a, String b) {
      return Integer.toBinaryString(Integer.parseInt(a,2)+Integer.parseInt(b,2));
    }
}
