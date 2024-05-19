package test;

/**
 * @description: 两个线程交替打印 1-100
 * @author: lyq
 * @createDate: 22/3/2023
 * @version: 1.0
 */
public class Print {
       private int number=1;
       private int flag=1;
       private final Object lock=new Object();
       public void printOdd(){
           synchronized (lock){
               while(number<=100){
                   if(flag==1){
                       System.out.println(Thread.currentThread().getName()+"打印"+number);
                       number++;
                       flag=2;
                       lock.notifyAll();
                   }else{
                       try {
                           lock.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
       }

       public void printJi(){
           synchronized (lock){
               while(number<=100){
                   if(flag==2){
                       System.out.println(Thread.currentThread().getName()+"打印"+number);
                       number++;
                       flag=1;
                       lock.notifyAll();
                   }else{
                       try {
                           lock.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
       }
    public static void main(String[] args) {
           Print print=new Print();
        Thread thread1=new Thread(()->print.printOdd(),"偶线程");
        Thread thread2=new Thread(()->print.printJi(),"奇线程");
        thread1.start();
        thread2.start();
    }
}
