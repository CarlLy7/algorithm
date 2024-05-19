package test;

/**
 * @description:
 * @author: lyq
 * @createDate: 4/3/2023
 * @version: 1.0
 */
public class replaceSpace {
    public static String replace(String str){
        return str.replaceAll("\\s","%20");
    }

    public static void main(String[] args) {
        System.out.println(replace("We Are Happy"));
    }
}
