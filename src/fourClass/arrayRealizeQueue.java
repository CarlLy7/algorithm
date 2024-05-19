package fourClass;

/**
 * @description: 使用数组结构来实现一个队列
 * 算法思路：
 * 需要使用一个循环数组来实现，一个固定大小的数组，有两个指针，一个指向队首，一个指向队尾，size来表示队列中现在有几个元素，然后添加和删除的时候
 * 注意是一个循环数组，要取余
 *
 * @author: lyq
 * @createDate: 28/8/2022
 * @version: 1.0
 */
public class arrayRealizeQueue {
    private int size;
    private int begin;
    private int end;
    private int[] array;
    private final int limit;

    /**
     * 初始化
     *
     * @param limit
     */
    public arrayRealizeQueue(int limit) {
        this.array = new int[limit];
        this.size = limit;
        this.begin = 0;
        this.end = 0;
        this.limit = limit;
    }


    /**
     * 入队
     *
     * @param a
     */
    public void push(int a) {
        if (size >= limit) {
            throw new RuntimeException("队列已满，无法入队!");
        }
        size++;
        array[end] = a;
        end = (end + 1) % (limit + 1); //通过取余操作实现循环数组这个结构

    }

    /**
     * 出队
     *
     * @return
     */
    public int pop() {
        if (size <= 0) {
            throw new RuntimeException("队列为空，无法出队");
        }

        int ans = array[begin];
        begin = (begin + 1) % (limit + 1);
        size--;
        return ans;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        if(size<=0){
            return true;
        }else{
            return false;
        }
    }


}
