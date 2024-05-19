package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: lyq
 * @createDate: 3/3/2023
 * @version: 1.0
 */
public class fanshe{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list=new ArrayList<>();
        Class<? extends List> listClass = list.getClass();
        Method add = listClass.getMethod("add", Object.class);
        add.invoke(list,"hello world");
        System.out.println(list.get(0));
    }
}
