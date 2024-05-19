package test;

/**
 * @description: 使用双重检验锁写一个单例模式
 * @author: lyq
 * @createDate: 5/3/2023
 * @version: 1.0
 */
public class Singleton {
    private volatile static Singleton instance;

    public Singleton() {
    }

    public static Singleton getInstance(){
       if(instance==null){
           synchronized (Singleton.class){
               if(instance==null){
                   instance=new Singleton();
               }
           }
       }
       return instance;
    }
}
