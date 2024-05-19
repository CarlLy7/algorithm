package Scene;

/**
 * @description: 顺序打印
 * @author: lyq
 * @createDate: 28/3/2023
 * @version: 1.0
 */
public class ShunXuPrint {
    private int number=1;
    private int flag=1;
    private Object lock=new Object();
    public void printOdd(){
        synchronized (lock){
            while(number<=100){
                if(flag==1){
                    System.out.println(Thread.currentThread().getName()+"打印"+number);
                    flag=2;
                    number++;
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

    public void printOU(){
        synchronized (lock){
            while(number<=100){
                if(flag==2){
                    System.out.println(Thread.currentThread().getName()+"打印"+number);
                    flag=1;
                    number++;
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
        ShunXuPrint print = new ShunXuPrint();
        Thread thread1=new Thread(()->print.printOdd(),"奇数线程");
        Thread thread2=new Thread(()->print.printOU(),"偶数线程");
        thread1.start();
        thread2.start();
    }
}
