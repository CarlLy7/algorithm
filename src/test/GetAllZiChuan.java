package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 返回字符串所有子串
 * @author: lyq
 * @createDate: 22/3/2023
 * @version: 1.0
 */
public class GetAllZiChuan {
    public static List<String> getAllSubString(String s){
        List<String> res=new ArrayList<>();
        Set<String> set=new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <s.length() ; j++) {
                if(!set.contains(s.substring(i,j))){
                    res.add(s.substring(i,j));
                    set.add(s.substring(i,j));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String test="Hello word";
        List<String> stringList = getAllSubString(test);
        for (String s : stringList) {
            System.out.println(s);
        }
    }
}
